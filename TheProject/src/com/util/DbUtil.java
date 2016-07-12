package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

	private static Connection connection = null;

	public static Connection getConnection() {

		if (connection != null) {
			return connection;
		} else {
			try {
				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://127.0.0.1:3306/project_asset?useUnicode=true&amp;characterEncoding=utf-8";
				//String url = "jdbc:mysql://172.26.18.68:3306/project_asset?useUnicode=true&amp;characterEncoding=utf-8";
				String user = "root";
				String password = "1234";
				Class.forName(driver);
				//connection = DriverManager.getConnection(url, user, password);
				connection = DriverManager.getConnection(url, user, password);
				System.out.println("connection complete!!");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return connection;
		}

	}

}
