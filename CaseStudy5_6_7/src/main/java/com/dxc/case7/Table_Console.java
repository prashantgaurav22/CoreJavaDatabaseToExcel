package com.dxc.case7;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class Table_Console {

	public static void main(String[] args) throws InterruptedException {

		

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter User Id to Search");
		String userId = scanner.next();

		
			String jdbcURL = "jdbc:mysql://localhost:3306/glic10";
	        String username = "root";
	        String password = "Banty@9825";
	 
	        String excelFilePath = "Reviews-export.xlsx";
	try 
	   (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
	            String sql = "SELECT * FROM employee";
	 
	            Statement statement = connection.createStatement();
	 
	            ResultSet result = statement.executeQuery(sql);
	 
	            XSSFWorkbook workbook2 = new XSSFWorkbook();

	        
		
			List<String> list = new ArrayList<String>();
			int i = 0;
			 
			 
			while (result.next()) {

				list.add("User ID---> " + result.getString("userid") + ", Role--->" + result.getString("role"));

			}
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j).contains(userId)) {
					System.out.println(list.get(j));
				}

				
			

		
			 statement.close();
			}}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
		
	       
