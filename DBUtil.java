package com.amstech.restaurant.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtil {

	private final String URL = "jdbc:mysql://localhost:3306/restaurant";
	private final String USERNAME = "root";
	private final String PASSWORD = "ayush123";
	private final String DRIVER = "com.mysql.cj.jdbc.Driver";

	public DBUtil() {
		System.out.println("Creating DBUtil Object...");
	}

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		System.out.println("Connecting restaurant..");
		Class.forName(DRIVER);
		Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		System.out.println("Connected successfully.");
		return connection;
	}

	public void getClose(Connection conn, PreparedStatement pstmt) throws SQLException {

		if (pstmt != null)
			pstmt.close();

		if (conn != null)
			conn.close();

	}

	
}
