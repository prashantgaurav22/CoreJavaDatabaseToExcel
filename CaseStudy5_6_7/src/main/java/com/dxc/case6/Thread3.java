package com.dxc.case6;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Thread3 implements Runnable {

	String jdbcURL = "jdbc:mysql://localhost:3306/glic10";
	String username = "root";
	String password = "Banty@9825";
	String excelFilePath = ("C:\\Users\\pgaurav\\GLIC\\DatabaseToExcel\\case6.xls");
	int batchSize = 20;
	Connection connection = null;

	public void run() {
		try {
			DataFormatter formatter = new DataFormatter();
			connection = DriverManager.getConnection(jdbcURL, username, password);
			connection.setAutoCommit(false);
			String sql = "INSERT INTO employees (grps, division, ssn,userid,role,enrolldate) VALUES (?, ?, ?, ?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			int rowNumber = 3;

			FileInputStream inputStream = new FileInputStream(excelFilePath);
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0);

			int lastRow = firstSheet.getLastRowNum();

			while (!(rowNumber > lastRow)) {
				System.out.println("Thread 3");
				Row firstRow = firstSheet.getRow(rowNumber);

				for (Cell cell : firstRow) {
					int columnIndex = cell.getColumnIndex();
					switch (columnIndex) {
					case 0:
						int grps = (int) cell.getNumericCellValue();
						statement.setInt(1, grps);
						break;
					case 1:
						int division = (int) cell.getNumericCellValue();
						statement.setInt(2, division);
						break;
					case 2:
						int ssn = (int) cell.getNumericCellValue();
						statement.setInt(3, ssn);
						break;
					case 3:
						String userid = cell.getStringCellValue();
						statement.setString(4, userid);
						break;
					case 4:
						String role = cell.getStringCellValue();
						statement.setString(5, role);
						break;
					case 5:
						String enrolldate = formatter.formatCellValue(cell);
						statement.setString(6, enrolldate);
						break;
					}

				}

				statement.addBatch();
				statement.executeBatch();

				connection.commit();
				rowNumber = rowNumber + 3;
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (IOException ex1) {
			System.out.println("Error reading file");
			ex1.printStackTrace();
		} catch (SQLException ex2) {
			System.out.println("Database error");
			ex2.printStackTrace();
		}

	}

}