/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconnection;

/**
 *
 * @author celstra31
 */
import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyTestClass {

    public int progressCount = 0;
    public String proText = "Started";

    public String generateScript(String urlNew, String newSchema, String newPassword, String urlOld, String oldSchema, String oldPassword, String fileName, Set<String> set, String prefname) {

        String result = "";
        Connection newCon = null;
        Connection oldCon = null;
        try {
            MyTestClass c = new MyTestClass();
            this.progressCount++;
            this.proText = "New Coonection";
            newCon = c.getConnection(newSchema, urlNew, newPassword);

            if (newCon == null) {
                result = "Could not establish the connection for New Schema";
            } else {
                this.progressCount++;
                this.proText = "Old Coonection";
                oldCon = c.getConnection(oldSchema, urlOld, oldPassword);

                if (oldCon == null) {
                    result = "Could not establish the connection for Old Schema";
                }
            }
            String schema = "";
            if (prefname != null && prefname.trim().length() > 0) {
                schema = prefname.trim().toUpperCase() + ".";
            }

            if (result.length() == 0) {
                SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyyHHmmss");
                java.util.Date date = new java.util.Date();

                if (!new File(fileName).isDirectory()) {
                    (new File(fileName)).mkdirs();
                }
                File file = new File(fileName + "/" + sd.format(date) + "_" + oldSchema + "_script.sql");
                // creates the file
                file.createNewFile();
                // creates a FileWriter Object
                FileWriter writer = new FileWriter(file);

                if (set.contains("Sequence")) {
                    writer.write("\n\n----SEQUENCE \n--------------------------------------\n");
                    this.progressCount++;
                    this.proText = "Old Sequence";
                    Set<String> oldSeq = c.getSeqFromSchema(oldCon, oldSchema);
                    this.progressCount++;
                    this.proText = "New Sequence";
                    Set<String> newSeq = c.getSeqFromSchema(newCon, newSchema);
                    this.progressCount++;
                    this.proText = "Sequence Scripts";
                    for (String s : newSeq) {
                        if (!oldSeq.contains(s)) {
                            String SEQUENCE = "CREATE SEQUENCE "
                                    + schema + s
                                    + "  MINVALUE 1 MAXVALUE 9999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE NOORDER  NOCYCLE ;";
                            writer.write(SEQUENCE);
                            writer.write("\n");

                        }
                    }

                }

                if (set.contains("Tables")) {
                    this.progressCount++;
                    this.proText = "New tables";
                    Map<String, List<ColumnDto>> newDb = c.getColumnMapFromSchema(newCon, newSchema);
                    this.progressCount++;
                    this.proText = "Old tables";
                    Map<String, List<ColumnDto>> oldDb = c.getColumnMapFromSchema(oldCon, oldSchema);
                    this.progressCount++;
                    this.proText = "Primery Keys";
                    Map<String, String> prim = c.getPrimaryMapFromSchema(newCon, newSchema);
                    this.progressCount++;
                    this.proText = "table Triggers";
                    Map<String, String> triggerMap = c.getTriggerMapFromSchema(newCon, newSchema);

                    Map<String, List<ColumnDto>> newTables = new HashMap<String, List<ColumnDto>>();

                    List<ColumnDto> newColumna = new ArrayList<ColumnDto>();
                    List<ColumnDto> modifyColumns = new ArrayList<ColumnDto>();
                    for (Map.Entry<String, List<ColumnDto>> e : newDb.entrySet()) {
                        if (oldDb.containsKey(e.getKey())) {
                            List<ColumnDto> newColumns = e.getValue();
                            List<ColumnDto> oldColumns = oldDb.get(e.getKey());
                            for (ColumnDto n : newColumns) {
                                int count = 0;
                                for (ColumnDto o : oldColumns) {
                                    if (n.getColumnName().equalsIgnoreCase(
                                            o.getColumnName())
                                            && n.getColumnType().equalsIgnoreCase(
                                                    o.getColumnType())
                                            && n.getColumnLength() == o
                                            .getColumnLength()) {
                                        count = 1;
                                        break;
                                    } else {
                                        if (n.getColumnName().equalsIgnoreCase(
                                                o.getColumnName())) {
                                            if (!n.getColumnType().equalsIgnoreCase(
                                                    o.getColumnType())
                                                    || n.getColumnLength() != o
                                                    .getColumnLength()) {
                                                count = 2;
                                                break;

                                            }

                                        }
                                    }

                                }

                                if (count == 0) {
                                    newColumna.add(n);
                                } else if (count == 2) {
                                    modifyColumns.add(n);
                                }

                            }

                        } else {
                            newTables.put(e.getKey(), e.getValue());
                        }
                    }

                    //   System.out.println("--New Tables :  " + newTables.size());
                    //   System.out.println("--Add Column :  " + newColumna.size());
                    //    System.out.println("--Modify Columns :  " + modifyColumns.size());
                    this.progressCount++;
                    this.proText = "Table Scripts";
                    // Writes the content to the file
                    writer.write("\n\n----New Tables\n--------------------------------------\n");

                    for (Map.Entry<String, List<ColumnDto>> e : newTables.entrySet()) {
                        String prime = "";
                        if (prim.containsKey(e.getKey())) {
                            prime = prim.get(e.getKey());
                        }

                        String table = "CREATE TABLE " + schema + e.getKey() + " (";
                        for (ColumnDto a : e.getValue()) {
                            table += " " + a.getColumnName();
                            if (a.getColumnType().equalsIgnoreCase("NVARCHAR2")) {
                                table += " VARCHAR2(" + a.getColumnLength()
                                        + " BYTE) ";
                            } else if (a.getColumnType().equalsIgnoreCase("VARCHAR2")
                                    || a.getColumnType().equalsIgnoreCase("CHAR")) {
                                table += " " + a.getColumnType() + "("
                                        + a.getColumnLength() + " BYTE)";
                            } else {
                                table += " " + a.getColumnType();
                            }

                            if (prime.equalsIgnoreCase(a.getColumnName())) {
                                table += "  NOT NULL PRIMARY KEY";
                            }
                            if (a.getDefaultValue() != null) {
                                table += " default " + a.getDefaultValue() + "";
                            }

                            table += ",";
                        }
                        if (table.trim().length() > 0) {
                            table = table.substring(0, table.length() - 1);
                        }
                        table += ");";

                        writer.write(table);
                        writer.write("\n");
                        if (prime != null && prime.trim().length() > 0
                                && triggerMap.containsKey(e.getKey())) {
                            String tri = " CREATE OR REPLACE TRIGGER " + schema + "SEQ_" + e.getKey()
                                    + " BEFORE INSERT ON   " + e.getKey()
                                    + "   FOR EACH ROW   WHEN (new." + prime
                                    + " is null) " + triggerMap.get(e.getKey())
                                    + " \n/\n";
                            tri += "ALTER TRIGGER " + schema + "SEQ_" + e.getKey() + " ENABLE;";
                            writer.write(tri);
                            writer.write("\n");
                        }

                    }
                    this.progressCount++;
                    this.proText = "New  Columns";
                    writer.write("\n\n----Add Columns\n--------------------------------------\n");
                    for (ColumnDto a : newColumna) {
                        String type = "VARCHAR2";
                        String add = "alter table " + schema + a.getTableName() + " add ("
                                + a.getColumnName();

                        if (a.getColumnType().equalsIgnoreCase("NVARCHAR2")) {
                            add += " VARCHAR2(" + a.getColumnLength()
                                    + " BYTE) ";
                            if (a.getDefaultValue() != null) {
                                add += " default " + a.getDefaultValue() + "";
                            }
                        } else if (a.getColumnType().equalsIgnoreCase("VARCHAR2")
                                || a.getColumnType().equalsIgnoreCase("CHAR")) {
                            add += " " + a.getColumnType() + "(" + a.getColumnLength()
                                    + " BYTE) ";
                            if (a.getDefaultValue() != null) {
                                add += " default " + a.getDefaultValue() + "";
                            }
                        } else if (a.getColumnType().equalsIgnoreCase("NUMBER")
                                || a.getColumnType().equalsIgnoreCase("FLOAT")) {
                            add += " " + a.getColumnType() + " ";
                            if (a.getDefaultValue() != null) {
                                add += " default " + a.getDefaultValue() + "";
                            } else {
                                add += " default 0";
                            }
                        } else {
                            add += " " + a.getColumnType();
                        }

                        add += " );";
                        writer.write(add);
                        writer.write("\n");

                    }
                    this.progressCount++;
                    this.proText = "Midify  Columns";
                    writer.write("\n\n----Midify Columns\n--------------------------------------\n");
                    for (ColumnDto m : modifyColumns) {
                        String modify = "Alter table " + schema + m.getTableName() + " MODIFY "
                                + m.getColumnName();

                        if (m.getColumnType().equalsIgnoreCase("NVARCHAR2")) {
                            modify += " VARCHAR2(" + m.getColumnLength() + " BYTE)";
                        } else if (m.getColumnType().equalsIgnoreCase("VARCHAR2")
                                || m.getColumnType().equalsIgnoreCase("CHAR")) {
                            modify += " " + m.getColumnType() + "("
                                    + m.getColumnLength() + " BYTE)";
                        } else if (m.getColumnType().equalsIgnoreCase("NUMBER")
                                || m.getColumnType().equalsIgnoreCase("FLOAT")) {
                            modify += " " + m.getColumnType() + " default 0";
                        } else {
                            modify += " " + m.getColumnType();
                        }
                        modify += ";";
                        writer.write(modify);
                        writer.write("\n");

                    }
                    this.progressCount++;
                }
                if (set.contains("Indexes")) {
                    writer.write("\n\n---Indexes\n--------------------------------------\n");
                    this.progressCount++;
                    this.proText = "Old  Index";
                    Map<String, List<String>> oldIndes = c.getIndexColumnFromSchema(oldCon, oldSchema);
                    this.progressCount++;
                    this.proText = "New  Index";
                    Map<String, List<String>> newIndes = c.getIndexColumnFromSchema(newCon, newSchema);
                    this.progressCount++;
                    this.proText = "Index Scripts";
                    for (Map.Entry<String, List<String>> n : newIndes.entrySet()) {
                        if (oldIndes.containsKey(n.getKey())) {
                            List<String> nls = n.getValue();
                            List<String> ols = oldIndes.get(n.getKey());
                            if (nls.size() != ols.size()) {
                                writer.write("drop index "
                                        + schema + n.getKey().split("@##@")[0] + ";");
                                writer.write("\n");
                                String sp[] = n.getKey().split("@##@");
                                String index = "CREATE INDEX " + schema + sp[0] + " ON " + schema + sp[1]
                                        + " (";
                                for (String s : n.getValue()) {
                                    index += s + ",";
                                }
                                if (n.getValue().size() > 0) {
                                    index = index.substring(0,
                                            index.trim().length() - 1);
                                }
                                index += ") LOGGING TABLESPACE \"USERS\" PCTFREE 10 INITRANS 2 STORAGE (   INITIAL 65536   NEXT 1048576   MINEXTENTS 1   MAXEXTENTS 2147483645   BUFFER_POOL DEFAULT );";
                                writer.write(index);
                                writer.write("\n");
                            } else {
                                boolean check = false;
                                for (String s : nls) {
                                    boolean check1 = true;
                                    for (String o : ols) {
                                        if (s.equalsIgnoreCase(o)) {
                                            check1 = false;
                                            break;
                                        }
                                    }
                                    if (check1) {
                                        check = true;
                                        break;
                                    }
                                }
                                if (check) {
                                    writer.write("drop index "
                                            + schema + n.getKey().split("@##@")[0] + ";");
                                    writer.write("\n");
                                    String sp[] = n.getKey().split("@##@");
                                    String index = "CREATE INDEX " + schema + sp[0] + " ON "
                                            + sp[1] + " (";
                                    for (String s : n.getValue()) {
                                        index += s + ",";
                                    }
                                    if (n.getValue().size() > 0) {
                                        index = index.substring(0, index.trim()
                                                .length() - 1);
                                    }
                                    index += ") LOGGING TABLESPACE \"USERS\" PCTFREE 10 INITRANS 2 STORAGE (   INITIAL 65536   NEXT 1048576   MINEXTENTS 1   MAXEXTENTS 2147483645   BUFFER_POOL DEFAULT );";
                                    writer.write(index);
                                    writer.write("\n");

                                }

                            }

                        } else {
                            String sp[] = n.getKey().split("@##@");
                            String index = "CREATE INDEX " + schema + sp[0] + " ON " + schema + sp[1]
                                    + " (";
                            for (String s : n.getValue()) {
                                index += s + ",";
                            }
                            if (n.getValue().size() > 0) {
                                index = index.substring(0, index.trim().length() - 1);
                            }
                            index += ") LOGGING TABLESPACE \"USERS\" PCTFREE 10 INITRANS 2 STORAGE (   INITIAL 65536   NEXT 1048576   MINEXTENTS 1   MAXEXTENTS 2147483645   BUFFER_POOL DEFAULT );";
                            writer.write(index);
                            writer.write("\n");
                        }
                    }

                }
                if (set.contains("ForeignKey")) {
                    writer.write("\n\n---Foreign Key\n--------------------------------------\n");
                    this.progressCount++;
                    this.proText = "Old ForeignKey";
                    Map<String, List<String>> oldForeignKey = c
                            .getForeignKeyFromSchema(oldCon, oldSchema);
                    this.progressCount++;
                    this.proText = "New ForeignKey";
                    Map<String, List<String>> newForeignKey = c
                            .getForeignKeyFromSchema(newCon, newSchema);

                    this.progressCount++;
                    this.proText = "ForeignKey Scripts";
                    for (Map.Entry<String, List<String>> n : newForeignKey.entrySet()) {
                        if (!oldForeignKey.containsKey(n.getKey())) {
                            List<String> ls = n.getValue();
                            String index = "ALTER TABLE " + schema + ls.get(0)
                                    + " ADD CONSTRAINT " + ls.get(2)
                                    + " FOREIGN KEY  (" + ls.get(1) + ") REFERENCES  "
                                    + schema + ls.get(3) + "(" + ls.get(4) + ");";
                            writer.write(index);
                            writer.write("\n");
                        }
                    }

                }

                if (set.contains("Views")) {
                    writer.write("\n\n---Views\n--------------------------------------\n");
                    this.progressCount++;
                    this.proText = "Old Views";
                    Map<String, ColumnDto> oldViews = c
                            .getViewsFromSchema(oldCon, oldSchema);
                    this.progressCount++;
                    this.proText = "New Views";
                    Map<String, ColumnDto> newViews = c
                            .getViewsFromSchema(newCon, newSchema);

                    this.progressCount++;
                    this.proText = "View Scripts";
                    for (Map.Entry<String, ColumnDto> n : newViews.entrySet()) {
                        int count = 0;
                        if (oldViews.containsKey(n.getKey())) {

                            List<ColumnDto> newCols = n.getValue().getColumns();
                            List<ColumnDto> oldCols = oldViews.get(n.getKey()).getColumns();
                            for (ColumnDto c1 : newCols) {
                                boolean check = true;
                                for (ColumnDto c2 : oldCols) {
                                    if (c1.getColumnName().equalsIgnoreCase(c2.getColumnName()) && c1.getColumnType().equalsIgnoreCase(c2.getColumnType())) {
                                        check = false;
                                        break;
                                    }

                                }
                                if (check) {
                                    count = 1;
                                    break;
                                }

                            }

                        } else {
                            count = 1;
                        }
                        if (count == 1) {
                            String index = "CREATE OR REPLACE FORCE VIEW " + schema + n.getKey() + " as " + n.getValue().getColumnName() + ";";
                            writer.write(index);
                            writer.write("\n");
                            writer.write("\n");
                        }
                    }

                }
                if (set.contains("MaterializedViews")) {
                    writer.write("\n\n---Materialized Views\n--------------------------------------\n");
                    this.progressCount++;
                    this.proText = "Old MaterializedViews";
                    Map<String, ColumnDto> oldmViews = c
                            .getMViewsFromSchema(oldCon, oldSchema);
                    this.progressCount++;
                    this.proText = "New MaterializedViews";
                    Map<String, ColumnDto> newmViews = c
                            .getMViewsFromSchema(newCon, newSchema);

                    this.progressCount++;
                    this.proText = "MaterializedViews Scripts";
                    for (Map.Entry<String, ColumnDto> n : newmViews.entrySet()) {
                        int count = 0;
                        if (oldmViews.containsKey(n.getKey())) {

                            List<ColumnDto> newCols = n.getValue().getColumns();
                            List<ColumnDto> oldCols = oldmViews.get(n.getKey()).getColumns();
                            for (ColumnDto c1 : newCols) {
                                boolean check = true;
                                for (ColumnDto c2 : oldCols) {
                                    if (c1.getColumnName().equalsIgnoreCase(c2.getColumnName()) && c1.getColumnType().equalsIgnoreCase(c2.getColumnType())) {
                                        check = false;
                                        break;
                                    }

                                }
                                if (check) {
                                    count = 2;
                                    break;
                                }

                            }

                        } else {
                            count = 1;
                        }
                        if (count == 1 || count == 2) {

                            if (count == 2) {
                                writer.write("drop MATERIALIZED VIEW " + schema + n.getKey() + "; ");
                                writer.write("\n");
                            }
                            String index = "CREATE MATERIALIZED  VIEW " + schema + n.getKey() + " as " + n.getValue().getColumnName() + ";";
                            writer.write(index);
                            writer.write("\n");
                            writer.write("\n");
                        }
                    }

                }

                if (set.contains("Procedure")) {
                    writer.write("\n\n---Procedures\n--------------------------------------\n");
                    this.progressCount++;
                    this.proText = "New  Procedure";
                    Map<String, java.util.Date> newPro = c.getProcedureInfo(newCon, newSchema);
                    this.progressCount++;
                    this.proText = "Old  Procedure";
                    Map<String, java.util.Date> oldPro = c.getProcedureInfo(oldCon, oldSchema);

                    //  System.out.println("newPro : " + newPro.size());
                    //   System.out.println("oldPro : " + oldPro.size());
                    this.progressCount++;
                    this.proText = "Procedure Scripts";
                    for (Map.Entry<String, java.util.Date> n : newPro.entrySet()) {
                        List<String> proLs = null;
                        if (oldPro.containsKey(n.getKey())) {
                            if (n.getValue().after(oldPro.get(n.getKey()))) {
                                proLs = c.getProcedureBody(newCon, newSchema, n.getKey());
                            }
                        } else {
                            proLs = c.getProcedureBody(newCon, newSchema, n.getKey());
                        }
                        if (proLs != null) {
                            writer.write("Create or replace ");
                            for (String s : proLs) {
                                s = s.replaceFirst("PROCEDURE ", "PROCEDURE " + schema);
                                writer.write(s);
                            }
                            writer.write("\n");
                            writer.write("/");
                            writer.write("\n");
                        }
                    }

                }

                if (set.contains("Function")) {
                    writer.write("\n\n---Functions\n--------------------------------------\n");
                    this.progressCount++;
                    this.proText = "new Function";
                    Map<String, java.util.Date> newPro = c.getFunctionInfo(newCon, newSchema);
                    this.progressCount++;
                    this.proText = "Old Function";
                    Map<String, java.util.Date> oldPro = c.getFunctionInfo(oldCon, oldSchema);
                    this.progressCount++;
                    this.proText = "Function Scripts";
                    for (Map.Entry<String, java.util.Date> n : newPro.entrySet()) {
                        List<String> proLs = null;
                        if (oldPro.containsKey(n.getKey())) {
                            if (n.getValue().after(oldPro.get(n.getKey()))) {
                                proLs = c.getFunctionBody(newCon, newSchema, n.getKey());
                            }
                        } else {
                            proLs = c.getFunctionBody(newCon, newSchema, n.getKey());
                        }
                        if (proLs != null) {
                            writer.write("Create or replace ");
                            for (String s : proLs) {
                                s = s.replaceFirst("FUNCTION ", "FUNCTION " + schema);
                                writer.write(s);
                            }
                            writer.write("\n");
                            writer.write("/");
                            writer.write("\n");
                        }
                    }

                }

                if (set.contains("MenuItems")) {
                    writer.write("\n\n---Menu Items\n--------------------------------------\n");
                    this.progressCount++;
                    this.proText = "new MenuItems";
                    Map<String, String> newMenu = c.getMenuItema(newCon, newSchema);
                    this.progressCount++;
                    this.proText = "Old MenuItems";
                    Map<String, String> oldMenu = c.getMenuItema(oldCon, oldSchema);

                    Map<String, String> topMenus = new Hashtable<>();
                    Map<String, String> groupMenus = new Hashtable<>();
                    Map<String, Set<String>> groupMenusLs = new Hashtable<>();
                    Map<String, Set<String>> menuMap = new HashMap<String, Set<String>>();
                    for (Map.Entry<String, String> n : newMenu.entrySet()) {
                        if (!oldMenu.containsKey(n.getKey())) {
                            String ids = c.checkMenuAvailable(oldCon, n.getKey());
                            try {
                                String[] ss = n.getKey().split("@#@");
                                String[] or = n.getValue().split("@");
                                String topMenu = "";
                                String groupMenu = "";
                                String menu = "";

                                if (ids.length() == 0) {
                                    topMenu = c.getTopMenu(ss[0], "1", or[0].split("#")[1], schema);
                                    groupMenu = c.getGroupMenu(ss[1], schema + "seq_top_menu.currval", "1", or[1].split("#")[1], schema);
                                    menu = c.getMenuItem(newCon, schema + "seq_menu_item_group.currval", or[2].split("#")[0], "1", schema);
                                } else {
                                    String[] s = ids.split("@#@");
                                    if (s.length == 1) {
                                        groupMenu = c.getGroupMenu(ss[1], s[0], "1", or[1].split("#")[1], schema);
                                        menu = c.getMenuItem(newCon, schema + "seq_menu_item_group.currval", or[2].split("#")[0], "1", schema);
                                    } else if (s.length == 2) {
                                        menu = c.getMenuItem(newCon, s[1], or[2].split("#")[0], "1", schema);
                                    }
                                }
                                if (!topMenus.containsKey(ss[0])) {
                                    topMenus.put(ss[0], topMenu);
                                }
                                if (!groupMenus.containsKey(ss[0] + "@#@" + ss[1])) {
                                    groupMenus.put(ss[0] + "@#@" + ss[1], groupMenu);
                                }

                                if (!groupMenusLs.containsKey(ss[0])) {
                                    Set<String> ls = new HashSet<String>();
                                    ls.add(ss[1]);
                                    groupMenusLs.put(ss[0], ls);
                                } else {
                                    Set<String> ls = groupMenusLs.get(ss[0]);
                                    ls.add(ss[1]);
                                    groupMenusLs.put(ss[0], ls);
                                }

                                if (!menuMap.containsKey(ss[0] + "@#@" + ss[1])) {
                                    Set<String> ls = new HashSet<String>();
                                    ls.add(menu);
                                    menuMap.put(ss[0] + "@#@" + ss[1], ls);
                                } else {
                                    Set<String> ls = menuMap.get(ss[0] + "@#@" + ss[1]);
                                    ls.add(menu);
                                    menuMap.put(ss[0] + "@#@" + ss[1], ls);
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    this.progressCount++;
                    this.proText = "Menu Items Scripts";
                    for (Map.Entry<String, String> n : topMenus.entrySet()) {
                        if (n.getValue().length() > 0) {
                            writer.write(n.getValue());
                            writer.write("\n");
                        }
                        for (String s : groupMenusLs.get(n.getKey())) {
                            if (groupMenus.get(n.getKey() + "@#@" + s).length() > 0) {
                                writer.write(groupMenus.get(n.getKey() + "@#@" + s));
                                writer.write("\n");
                            }
                            for (String ms : menuMap.get(n.getKey() + "@#@" + s)) {
                                if (ms.length() > 0) {
                                    writer.write(ms);
                                    writer.write("\n");
                                    String rm = c.getRoleMenuMap(schema + "SEQ_MENU_ITEM.currval", "1", schema);
                                    writer.write(rm);
                                    writer.write("\n");
                                }
                            }
                            writer.write("\n");

                        }
                        writer.write("\n");
                    }
                    writer.write("\n");

                }

                writer.flush();
                writer.close();
                System.out.print(" End  ");
                result = "success";
            }
        } catch (Exception e) {
            result = e.getMessage();
        } finally {
            try {
                if (newCon != null) {
                    newCon.close();
                }
                if (oldCon != null) {
                    oldCon.close();
                }
            } catch (Exception e) {
                result = e.getMessage();
            }

        }
        //     System.out.println("result : " + result);
        return result;
    }

    public Map<String, Integer> getDynamicReports(Connection con) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select a.MODULE_NAME,a.MENU_ITEMS_ID from DYNAMIC_REPORTS a");
            while (rs.next()) {
                String seq = rs.getString(1);
                int id = rs.getInt(2);
                map.put(seq, id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public Map<String, String> getMenuItema(Connection con, String schemaName) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(" select a.TOP_MENU_TITLE||'@#@'||b.MENU_ITEM_GROUP_TITLE||'@#@'||c.MENU_ITEM_TITLE||'@#@'||c.USER_TYPE_ID,a.TOP_MENU_ID||'#'||a.MENUORDER||'@'||b.MENU_ITEM_GROUP_ID||'#'||b.MENUORDER||'@'||c.MENU_ITEM_ID||'#'||c.MENUORDER from TOP_MENU a,MENU_ITEM_GROUP b,MENU_ITEM c  where a.TOP_MENU_ID=b.TOP_MENU_ID and b.MENU_ITEM_GROUP_ID=c.MENU_ITEM_GROUP_ID"
                    + " and c.MENU_ITEM_URL not in ('dynamicreports.xhtml','devicereports.xhtml','Daily Reports','Dashboard','custmore','dynamicdashboardreports.xhtml')");
            while (rs.next()) {
                String seq = rs.getString(1);
                String id = rs.getString(2);
                map.put(seq, id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public List<String> getMenuScript(Connection newCon, String menuName, String ids, String orders, String schema) {
        List<String> ls = new ArrayList<>();
        try {
            String[] ss = menuName.split("@#@");
            String[] or = orders.split("@");
            if (ids.length() == 0) {
                String topMenu = getTopMenu(ss[0], "1", or[0].split("#")[1], schema);
                ls.add(topMenu);
                String groupMenu = getGroupMenu(ss[1], schema + "seq_top_menu.currval", "1", or[1].split("#")[1], schema);
                ls.add(groupMenu);
                String menu = getMenuItem(newCon, schema + "seq_menu_item_group.currval", or[2].split("#")[0], "1", schema);
                ls.add(menu);

            } else {
                String[] s = ids.split("@#@");
                if (s.length == 1) {
                    String groupMenu = getGroupMenu(ss[1], s[0], "1", or[1].split("#")[1], schema);
                    ls.add(groupMenu);
                    String menu = getMenuItem(newCon, schema + "seq_menu_item_group.currval", or[2].split("#")[0], "1", schema);
                    ls.add(menu);
                } else if (s.length == 2) {
                    String menu = getMenuItem(newCon, s[1], or[2].split("#")[0], "1", schema);
                    ls.add(menu);
                }
            }
            if (!ls.isEmpty()) {
                String rm = getRoleMenuMap(schema + "SEQ_MENU_ITEM.currval", "1", schema);
                ls.add(rm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public String getTopMenu(String menuName, String iconId, String orderId, String schema) {
        String sql = "Insert into " + schema + "top_menu (TOP_MENU_ID,MENU_ICON_ID,TOP_MENU_TITLE,TM_TOOL_TIP,MENU_URL,MENUORDER) values"
                + "                        (" + schema + "seq_top_menu.nextval," + iconId + ",'" + menuName + "','" + menuName + "','" + menuName + "'," + orderId + ");";
        return sql;
    }

    public String getGroupMenu(String menuName, String topMenu, String iconId, String orderId, String schema) {
        String sql = "Insert into " + schema + "menu_item_group (MENU_ITEM_GROUP_ID,MENU_ICON_ID,TOP_MENU_ID,MENU_ITEM_GROUP_TITLE,MIG_TOOL_TIP,MENUORDER) values"
                + "    (" + schema + "seq_menu_item_group.nextval," + iconId + "," + topMenu + ",'" + menuName + "','" + menuName + "'," + orderId + ");";
        return sql;
    }

    public String getMenuItem(Connection newCon, String groupMenu, String menuId, String iconId, String schema) {
        String sql = "Insert into " + schema + "MENU_ITEM (MENU_ITEM_ID,MENU_ICON_ID,MENU_ITEM_GROUP_ID,USER_TYPE_ID,MENU_ITEM_TITLE,MI_TOOL_TIP,MENU_ITEM_URL,MENU_ISCRUD,MENUORDER) values (" + schema + "seq_menu_item.nextval,";
        try {
            Statement stmt = newCon.createStatement();
            ResultSet rs = stmt.executeQuery("select USER_TYPE_ID,MENU_ITEM_TITLE,MI_TOOL_TIP,MENU_ITEM_URL,MENU_ISCRUD,MENUORDER from MENU_ITEM  where MENU_ITEM_ID=" + menuId);
            while (rs.next()) {
                sql += iconId + "," + groupMenu + "," + rs.getInt(1) + ",'" + rs.getString(2) + "','" + rs.getString(3) + "','" + rs.getString(4) + "'," + rs.getString(5) + "," + rs.getString(6) + ");";
            }
        } catch (Exception e) {
        }
        return sql;
    }

    public String getRoleMenuMap(String menuid, String roleId, String schema) {
        String sql = "Insert into " + schema + "ROLE_MENU_ITEM_MAP (ROLE_MENU_ITEM_MAP_ID,ROLE_ID,MENU_ITEM_ID,ADD_OPERATION,EDIT_OPERATION,DELETE_OPERATION) values"
                + " (" + schema + "seq_ROLE_MENU_ITEM_MAP.nextval," + roleId + "," + menuid + ",1,1,1);";
        return sql;
    }

    public String checkMenuAvailable(Connection oldCon, String menuName) {
        String ids = "";
        try {
            Statement stmt = oldCon.createStatement();
            String[] s = menuName.split("@#@");
            ResultSet rs = stmt.executeQuery("select a.TOP_MENU_ID from TOP_MENU a where lower(a.TOP_MENU_TITLE)='" + s[0].toLowerCase() + "'");
            int topId = 0;
            while (rs.next()) {
                topId = rs.getInt(1);
            }
            if (topId > 0) {
                ids = topId + "";
                ResultSet rs1 = stmt.executeQuery("select a.MENU_ITEM_GROUP_ID from MENU_ITEM_GROUP a where lower(a.MENU_ITEM_GROUP_TITLE)='" + s[1].toLowerCase() + "'  and a.TOP_MENU_ID=" + topId);
                int grId = 0;
                while (rs1.next()) {
                    grId = rs1.getInt(1);
                }
                if (grId > 0) {
                    ids += "@#@" + grId;
                    ResultSet rs2 = stmt.executeQuery("select a.MENU_ITEM_ID from MENU_ITEM a where lower(a.MENU_ITEM_TITLE)='" + s[2].toLowerCase() + "' and a.MENU_ITEM_GROUP_ID=" + grId + " and a.USER_TYPE_ID=" + s[3]);
                    while (rs2.next()) {
                        int menuId = rs2.getInt(1);
                        ids += "@#@" + menuId;
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ids;
    }

    public void generateDateFromTable(Connection con, String schemaName, String tableName) {

        try {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt
                    .executeQuery("SELECT atc.COLUMN_NAME,atc.DATA_TYPE FROM  ALL_TAB_COLUMNS atc where atc.owner='"
                            + schemaName.toUpperCase()
                            + "' order by atc.TABLE_NAME");
            Map<String, String> map = new HashMap<String, String>();
            List<String> col = new ArrayList<String>();
            String printint = "Insert into " + tableName + " (";
            while (rs.next()) {
                map.put(rs.getString(1), rs.getString(2));
                col.add(rs.getString(1));
                printint += rs.getString(1) + ",";
            }
            printint = printint.substring(0, printint.length() - 1);
            printint += ") values (";

            String sql = "select * from " + tableName;
            ResultSet rs1 = stmt.executeQuery(sql);
            while (rs.next()) {
                for (String s : col) {
                    if (map.get(s).equalsIgnoreCase("NUMBER")
                            || map.get(s).equalsIgnoreCase("FLOAT")) {
                        printint += rs.getString(s) + ",";

                    } else {
                        printint += "'" + rs.getString(s) + "',";
                    }
                }
            }
            printint = printint.substring(0, printint.length() - 1);
            printint += ");";
            //  System.out.println(printint);

        } catch (Exception e) {

        }
    }

    public Map<String, List<ColumnDto>> getColumnMapFromSchema(Connection con, String schemaName) throws SQLException {
        Map<String, List<ColumnDto>> result = null;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt
                    .executeQuery("SELECT atc.TABLE_NAME,atc.COLUMN_NAME,atc.DATA_TYPE,atc.char_length,atc.DATA_DEFAULT FROM  ALL_TAB_COLUMNS atc,all_tables a where atc.owner='"
                            + schemaName.toUpperCase()
                            + "' and atc.TABLE_NAME=a.TABLE_NAME AND atc.owner    =a.owner and a.TABLE_NAME not in (select b.MVIEW_NAME from all_mviews b where b.OWNER=atc.owner) order by atc.TABLE_NAME");
            result = new HashMap<String, List<ColumnDto>>();
            while (rs.next()) {

                ColumnDto c = new ColumnDto();
                c.setTableName(rs.getString(1));
                c.setColumnName(rs.getString(2));
                c.setColumnType(rs.getString(3));
                c.setColumnLength(Integer.parseInt(rs.getString(4)));
                c.setDefaultValue(rs.getString(5));
                if (result.containsKey(rs.getString(1))) {
                    List<ColumnDto> la = result.get(rs.getString(1));
                    la.add(c);
                    result.put(rs.getString(1), la);

                } else {
                    List<ColumnDto> la = new ArrayList<ColumnDto>();
                    la.add(c);
                    result.put(rs.getString(1), la);
                }

            }

            //    System.out.println(schemaName + "  :   " + result.size());
        } catch (Exception e) {
            result = null;
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, ColumnDto> getViewsFromSchema(Connection con, String schemaName) throws SQLException {
        Map<String, ColumnDto> result = null;
        try {
            Statement stmt = con.createStatement();

            String sql = "select a.VIEW_NAME,a.TEXT,(select LISTAGG(b.COLUMN_NAME||'@@'||b.DATA_TYPE,'###') within group ( order by b.COLUMN_NAME)  "
                    + "  from ALL_TAB_COLUMNS b where b.TABLE_NAME=a.VIEW_NAME and b.OWNER=a.OWNER) from all_views a where a.OWNER='"
                    + schemaName.toUpperCase()
                    + "'";
            ResultSet rs = stmt
                    .executeQuery(sql);

            //   System.out.println("sql::::" + sql);
            result = new HashMap<String, ColumnDto>();
            while (rs.next()) {

                ColumnDto c = new ColumnDto();
                c.setTableName(rs.getString(1));
                c.setColumnName(rs.getString(2));
                //   System.out.println(rs.getString(1) + "rs.getString(1)::::" + rs.getString(3));
                if (rs.getString(3) != null) {
                    String[] cols = rs.getString(3).split("###");
                    List<ColumnDto> lsCols = new ArrayList<>();
                    for (String s : cols) {
                        ColumnDto c1 = new ColumnDto();
                        c1.setColumnName(s.split("@@")[0]);
                        c1.setColumnType(s.split("@@")[1]);
                        lsCols.add(c1);
                    }
                    c.setColumns(lsCols);
                    result.put(rs.getString(1), c);
                }
            }
            //    System.out.println(schemaName + "  :   " + result.size());
        } catch (Exception e) {
            result = null;
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, ColumnDto> getMViewsFromSchema(Connection con, String schemaName) throws SQLException {
        Map<String, ColumnDto> result = null;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt
                    .executeQuery("select a.MVIEW_NAME,a.QUERY,(select LISTAGG(b.COLUMN_NAME||'@@'||b.DATA_TYPE,'###') within group ( order by b.COLUMN_NAME)   "
                            + "  from ALL_TAB_COLUMNS b where b.TABLE_NAME=a.MVIEW_NAME and b.OWNER=a.OWNER) from all_mviews a where a.OWNER='"
                            + schemaName.toUpperCase()
                            + "'");
            result = new HashMap<String, ColumnDto>();
            while (rs.next()) {

                ColumnDto c = new ColumnDto();
                c.setTableName(rs.getString(1));
                c.setColumnName(rs.getString(2));
                String[] cols = rs.getString(3).split("###");
                List<ColumnDto> lsCols = new ArrayList<>();
                for (String s : cols) {
                    ColumnDto c1 = new ColumnDto();
                    c1.setColumnName(s.split("@@")[0]);
                    c1.setColumnType(s.split("@@")[1]);
                    lsCols.add(c1);
                }
                c.setColumns(lsCols);
                result.put(rs.getString(1), c);
            }
            //   System.out.println(schemaName + "  :   " + result.size());
        } catch (Exception e) {
            result = null;
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, List<String>> getIndexColumnFromSchema(Connection con,
            String schemaName) throws SQLException {
        Map<String, List<String>> result = new HashMap<String, List<String>>();

        try {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt
                    .executeQuery("select a.INDEX_NAME, a.TABLE_NAME, b.column_name from USER_INDEXES a ,ALL_IND_COLUMNS b where a.index_name=b.index_name and  a.uniqueness='NONUNIQUE' and lower(a.table_owner)='"
                            + schemaName.toLowerCase()
                            + "' order by a.INDEX_NAME");

            while (rs.next()) {
                String key = rs.getString(1) + "@##@" + rs.getString(2);
                if (result.containsKey(key)) {
                    List<String> ls = result.get(key);
                    if(!ls.contains(rs.getString(3))){
                          ls.add(rs.getString(3));
                    result.put(key, ls);
                    }
                } else {
                    List<String> ls = new ArrayList<String>();
                    ls.add(rs.getString(3));
                    result.put(key, ls);
                }

            }

            // System.out.println(schemaName +
            // " : getIndexColumnFromSchema :   "
            // + result.size());
        } catch (Exception e) {
            //	e.printStackTrace();
        }
        return result;
    }

    public Map<String, List<String>> getForeignKeyFromSchema(Connection con, String schemaName) throws SQLException {
        Map<String, List<String>> result = new HashMap<String, List<String>>();
        try {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt
                    .executeQuery(" SELECT a.table_name, a.column_name,a.constraint_name, uc.table_name, uc.column_name        FROM all_cons_columns a "
                            + "   JOIN all_constraints c ON a.owner = c.owner  AND a.constraint_name = c.constraint_name JOIN all_constraints c_pk ON c.r_owner = c_pk.owner "
                            + " AND c.r_constraint_name = c_pk.constraint_name   join USER_CONS_COLUMNS uc on uc.constraint_name = c.r_constraint_name    WHERE  lower(C.R_OWNER) ='"
                            + schemaName.toLowerCase()
                            + "' order by a.constraint_name");

            while (rs.next()) {
                String key = rs.getString(3);
                List<String> ls = new ArrayList<String>();
                ls.add(rs.getString(1));
                ls.add(rs.getString(2));
                ls.add(rs.getString(3));
                ls.add(rs.getString(4));
                ls.add(rs.getString(5));
                result.put(key, ls);

            }

            // System.out.println(schemaName +
            // " : getIndexColumnFromSchema :   "
            // + result.size());
        } catch (Exception e) {
            //	e.printStackTrace();
        }
        return result;
    }

    public Map<String, String> getPrimaryMapFromSchema(Connection con, String schemaName) throws SQLException {
        Map<String, String> result = new HashMap<String, String>();

        try {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt
                    .executeQuery("SELECT   c2.table_name,   c2.column_name  FROM  user_constraints c1, all_cons_columns c2 WHERE  c1.constraint_type = 'P'  AND c1.constraint_name = c2.constraint_name and c1.owner='"
                            + schemaName.toUpperCase() + "'");

            while (rs.next()) {
                if (!result.containsKey(rs.getString(1))) {
                    result.put(rs.getString(1), rs.getString(2));
                }

            }
            // System.out.println(schemaName + "  :   " + result.size());
        } catch (Exception e) {
            //	e.printStackTrace();
        }
        return result;
    }

    public Map<String, String> getTriggerMapFromSchema(Connection con, String schemaName) throws SQLException {
        Map<String, String> result = new HashMap<String, String>();

        try {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt
                    .executeQuery("SELECT atc.table_name,atc.trigger_body FROM  all_triggers atc where atc.owner='"
                            + schemaName.toUpperCase() + "'");

            while (rs.next()) {
                if (!result.containsKey(rs.getString(1))) {
                    result.put(rs.getString(1), rs.getString(2));
                }

            }
            // System.out.println(schemaName + "  :   " + result.size());
        } catch (Exception e) {
            //	e.printStackTrace();
        }
        return result;
    }

    public Set<String> getSeqFromSchema(Connection con, String schemaName)
            throws SQLException {
        Set<String> result = new HashSet<String>();
        try {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt
                    .executeQuery("SELECT atc.sequence_name FROM  ALL_SEQUENCES atc where atc.sequence_owner='"
                            + schemaName.toUpperCase()
                            + "' order by atc.sequence_name");

            while (rs.next()) {
                try {
                    String seq = rs.getString(1);
                    seq = seq.replaceAll("SEQ_", "");
                    String ss = seq.substring(0, seq.indexOf("_"));
                    int a = Integer.parseInt(ss);

                } catch (Exception e) {
                    result.add(rs.getString(1));
                }

            }

            // System.out.println(schemaName + "  :   " + result.size());
        } catch (Exception e) {
            //	e.printStackTrace();
        }
        return result;
    }

    public Connection getConnection(String schemaName, String url, String password) {
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(url, schemaName, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public Map<String, java.util.Date> getProcedureInfo(Connection con, String schemaName) {
        Map<String, java.util.Date> map = new HashMap<String, java.util.Date>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT OBJECT_NAME, TIMESTAMP FROM USER_OBJECTS WHERE OBJECT_TYPE = 'PROCEDURE'");
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");

            while (rs.next()) {
                String seq = rs.getString(1);
                java.util.Date date = sd.parse(rs.getString(2));
                map.put(seq, date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    public List<String> getProcedureBody(Connection con, String schemaName, String procedureName) {
        List<String> ls = new ArrayList<String>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select text from user_source where TYPE='PROCEDURE' and  name = '" + procedureName + "' order by line");
            while (rs.next()) {
                String seq = rs.getString(1);
                ls.add(seq);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public Map<String, java.util.Date> getFunctionInfo(Connection con, String schemaName) {
        Map<String, java.util.Date> map = new HashMap<String, java.util.Date>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT OBJECT_NAME, TIMESTAMP FROM USER_OBJECTS WHERE OBJECT_TYPE = 'FUNCTION'");
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");

            while (rs.next()) {
                String seq = rs.getString(1);
                java.util.Date date = sd.parse(rs.getString(2));
                map.put(seq, date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    public List<String> getFunctionBody(Connection con, String schemaName, String procedureName) {
        List<String> ls = new ArrayList<String>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select text from user_source where TYPE='FUNCTION' and  name = '" + procedureName + "' order by line");
            while (rs.next()) {
                String seq = rs.getString(1);
                ls.add(seq);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public int getProgressCount() {
        return progressCount;
    }

    public void setProgressCount(int progressCount) {
        this.progressCount = progressCount;
    }

    public String getProText() {
        return proText;
    }

    public void setProText(String proText) {
        this.proText = proText;
    }

}
