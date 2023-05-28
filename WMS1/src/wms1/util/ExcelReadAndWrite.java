/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wms1.util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

/**
 *
 * @author Aadhya
 */
public class ExcelReadAndWrite {
    
    public static List<List<String>> readXls(String filePath) {
        System.out.println("XLS");
        List<List<String>> data = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(new File(filePath));
            HSSFWorkbook wb = new HSSFWorkbook(fis);
            HSSFSheet sheet = wb.getSheetAt(0);
            FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
            for (Row row : sheet) {
                List<String> rl = new ArrayList<>();
                for (Cell cell : row) {
                    switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            rl.add(cell.getNumericCellValue() + "");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            rl.add(cell.getStringCellValue());
                            break;
                        default:
                            rl.add(cell.getStringCellValue());
                            break;
                    }
                }
                data.add(rl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public static List<List<String>> readXlsx(String filePath) {
        System.out.println("XLSX");
        List<List<String>> data = new ArrayList<>();
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator<Row> itr = sheet.iterator();
            while (itr.hasNext()) {
                List<String> rl = new ArrayList<>();
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            rl.add(cell.getStringCellValue());
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            rl.add(cell.getNumericCellValue() + "");
                            break;
                        default:
                            rl.add(cell.getStringCellValue());
                            break;
                    }
                }
                data.add(rl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public static String writeXlsx(List<String> headers, List<Object[]> data, String type, String fileName) {
        FileOutputStream out = null;
        String filePath = null;
        try {
            if (fileName == null) {
                fileName = type.toUpperCase() + "_" + (new SimpleDateFormat("HH_mm_ss").format(new Date()));
            }
            filePath = ReadCSV.baseFolder(type) + "/" + fileName + ".xlsx";
            FileInputStream inputStream = new FileInputStream("D:/mytemplate.xlsx");
            XSSFWorkbook wb_template = new XSSFWorkbook(inputStream);
            inputStream.close();
            SXSSFWorkbook wb = new SXSSFWorkbook(wb_template);
            wb.setCompressTempFiles(true);
            SXSSFSheet sh = (SXSSFSheet) wb.getSheetAt(0);
            sh.setRandomAccessWindowSize(100);// keep 100 rows in memory,
            XSSFColor color = new XSSFColor(new Color(43, 150, 150));
            XSSFCellStyle cellStyle = wb_template.createCellStyle();
            cellStyle.setFillForegroundColor(color);
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cellStyle.setAlignment(HorizontalAlignment.RIGHT);
            int rows = 0;
            Row row = sh.createRow(rows++);
            int cellnum = 0;
            for (String key : headers) {
                Cell cell = row.createCell(cellnum++);
                cell.setCellValue(key.toString());
                cell.setCellStyle(cellStyle);
            }
            for (Object[] key : data) {
                Row row1 = sh.createRow(rows++);
                cellnum = 0;
                for (Object obj : key) {
                    Cell cell = row1.createCell(cellnum++);
                    if (obj != null) {
                        cell.setCellValue(obj.toString());
                    } else {
                        cell.setCellValue("");
                    }
                }
            }
            out = new FileOutputStream(filePath);
            wb.write(out);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception ex) {
                    
                }
            }
        }
        return filePath;
    }
    
    public static String writeXlsx(List<String> headers, List<Object[]> data, String type, String fileName, Map<String, Object> filters) {
        FileOutputStream out = null;
        String filePath = null;
        try {
            if (fileName == null) {
                fileName = type.toUpperCase() + "_" + (new SimpleDateFormat("HH_mm_ss").format(new Date()));
            }
            filePath = ReadCSV.baseFolder(type) + "/" + fileName + ".xlsx";
            FileInputStream inputStream = new FileInputStream("D:/mytemplate.xlsx");
            XSSFWorkbook wb_template = new XSSFWorkbook(inputStream);
            inputStream.close();
            SXSSFWorkbook wb = new SXSSFWorkbook(wb_template);
            wb.setCompressTempFiles(true);
            SXSSFSheet sh = (SXSSFSheet) wb.getSheetAt(0);
            sh.setRandomAccessWindowSize(100);// keep 100 rows in memory,
            XSSFColor color = new XSSFColor(new Color(43, 150, 150));
            XSSFCellStyle cellStyle = wb_template.createCellStyle();
            cellStyle.setFillForegroundColor(color);
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            int rows = 0;
            Row row1 = sh.createRow(rows++);
            Cell cell1 = row1.createCell(0);
            cell1.setCellValue(type);
            cell1.setCellStyle(cellStyle);
            if (filters != null && !filters.isEmpty()) {
                for (String val : filters.keySet()) {
                    Row row2 = sh.createRow(rows++);
                    Cell cell2 = row2.createCell(0);
                    cell2.setCellValue(val);
                    Cell cell3 = row2.createCell(1);
                    if (filters.get(val) instanceof Date) {
                        cell3.setCellValue(new SimpleDateFormat("DD-MMM-yyyy").format((Date) filters.get(val)));
                    } else {
                        cell3.setCellValue(filters.get(val).toString());
                    }
                   }
            }
            rows++;
            Row row = sh.createRow(rows++);
            int cellnum = 0;
            for (String key : headers) {
                Cell cell = row.createCell(cellnum++);
                cell.setCellValue(key.toString());
                cell.setCellStyle(cellStyle);
            }
            for (Object[] key : data) {
                Row row10 = sh.createRow(rows++);
                cellnum = 0;
                for (Object obj : key) {
                    Cell cell = row10.createCell(cellnum++);
                    if (obj != null) {
                        cell.setCellValue(obj.toString());
                    } else {
                        cell.setCellValue("");
                    }
                }
            }
            out = new FileOutputStream(filePath);
            wb.write(out);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception ex) {
                    
                }
            }
        }
        return filePath;
    }
    
    public static double objToDouble(Object obj) {
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception e) {
            return 0;
        }
    }
    
    public static void main(String[] args) {
        List<List<String>> f = readXls("D:\\WMS_Files\\purchares_xlsx.xls");
        System.out.println(f);
        //writeXlsx(f, "D:\\stock_temp.xlsx");
    }
    
}
