package com.hsbc.store.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigConnection {

	private static Connection connection = null;

	static {
		try {

			String url = "jdbc:postgresql://localhost:5432/store";
			String user = "postgres";
			String pass = "11620234";

			Connection conn = DriverManager.getConnection(url, user, pass);

			connection = conn;
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}

}
