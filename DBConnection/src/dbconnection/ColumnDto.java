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
import java.util.List;

public class ColumnDto {

    private String tableName;
    private String columnName;
    private String columnType;
    private String newColumnType;
    private int columnLength;
    private int newColumnLength;
    private String primeryKey;
    private String defaultValue;
    private List<ColumnDto> columns;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getNewColumnType() {
        return newColumnType;
    }

    public void setNewColumnType(String newColumnType) {
        this.newColumnType = newColumnType;
    }

    public int getColumnLength() {
        return columnLength;
    }

    public void setColumnLength(int columnLength) {
        this.columnLength = columnLength;
    }

    public int getNewColumnLength() {
        return newColumnLength;
    }

    public void setNewColumnLength(int newColumnLength) {
        this.newColumnLength = newColumnLength;
    }

    public String getPrimeryKey() {
        return primeryKey;
    }

    public void setPrimeryKey(String primeryKey) {
        this.primeryKey = primeryKey;
    }

    public List<ColumnDto> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnDto> columns) {
        this.columns = columns;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

}
