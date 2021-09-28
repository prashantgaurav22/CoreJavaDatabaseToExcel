package com.dxc.case5;

import java.io.*;
import java.sql.*;
 
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
 
/**
 * A simple Java program that exports data from database to Excel file.
 * @author Nam Ha Minh
 * (C) Copyright codejava.net
 */
public class DatabaseToExcel {
 
    public static void main(String[] args) {
        new DatabaseToExcel().export();
    }
     
    public void export() {
        String jdbcURL = "jdbc:mysql://localhost:3306/angular20";
        String username = "root";
        String password = "Banty@9825";
 
        String excelFilePath = "Reviews-export.xlsx";
 
        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            String sql = "SELECT * FROM employee";
 
            Statement statement = connection.createStatement();
 
            ResultSet result = statement.executeQuery(sql);
 
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Reviews");
 
            writeHeaderLine(sheet);
 
            writeDataLines(result, workbook, sheet);
 
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
 
            statement.close();
 
        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
    }
 
    private void writeHeaderLine(XSSFSheet sheet) {
 
        Row headerRow = sheet.createRow(0);
 
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("EmployeeId");
 
        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("First Name");
 
        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Last Name");
 
        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Email");
 
       
    }
 
    private void writeDataLines(ResultSet result, XSSFWorkbook workbook,
            XSSFSheet sheet) throws SQLException {
        int rowCount = 1;
 
        while (result.next()) {
            int employeeId = result.getInt("employee_id");
            String firstName = result.getString("first_name");
            String lastName = result.getString("last_name");
            String email = result.getString("email");
           
 
            Row row = sheet.createRow(rowCount++);
 
            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(employeeId);
 
            cell = row.createCell(columnCount++);
            cell.setCellValue(firstName);
 
            /*
            cell = row.createCell(columnCount++);
 
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper creationHelper = workbook.getCreationHelper();
           
            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
            cell.setCellStyle(cellStyle);
 
            cell.setCellValue(timestamp);
 */
            cell = row.createCell(columnCount++);
 
            cell.setCellValue(lastName);
 
            cell = row.createCell(columnCount);
            cell.setCellValue(email);
  
     
        }
        System.out.println("Thanks Your ExcelSheet is Created!");
    }
 
}