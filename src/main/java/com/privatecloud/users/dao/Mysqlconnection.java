package com.privatecloud.users.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mysqlconnection {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/CMPE283";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";
	
	public Connection connection = null;
	private static Mysqlconnection instance;
	
	private Mysqlconnection() {	
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			//System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static Mysqlconnection getInstance() {
		if(instance == null)
			instance = new Mysqlconnection();
		
		return instance;
	}

}

