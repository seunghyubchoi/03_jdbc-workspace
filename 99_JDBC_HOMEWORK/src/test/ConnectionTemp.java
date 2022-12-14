package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTemp {

	public ConnectionTemp() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	/*
	 * public Connection getConnection() {
	 * 
	 * Connection conn = null; try { conn = DriverManager.getConnection(jdbcUrl,
	 * user, password); } catch (SQLException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); }
	 * 
	 * return conn;
	 */
}
