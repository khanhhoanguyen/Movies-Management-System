package sait.mms.drivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sait.mms.contracts.DatabaseDriver;

/**
 * Assignment 4 - Movie Management System
 * 
 * @author Hoa Nguyen 
 * @author Miguel Mulingbayan
 *
 *	The MariaDBDriver Class implements DatabaseDriver interface
 */
public class MariaDBDriver implements DatabaseDriver {
	private static final String SERVER = "localhost";
	private static final int PORT = 3306;
	private static final String DATABASE = "cprg251";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "password";

	Connection conn = null;

	@Override
	public void connect() throws SQLException {
		String dsn = this.getDSN();
		conn = DriverManager.getConnection(dsn);

	}

	private String getDSN() {
		String dataSourceName = String.format("jdbc:mariadb://%s:%d/%s?user=%s&password=%s", SERVER, PORT, DATABASE,
				USERNAME, PASSWORD);
		return dataSourceName;
	}

	@Override
	public ResultSet get(String query) throws SQLException {
		 Statement stmt = conn.createStatement();
		 ResultSet resultSet = stmt.executeQuery(query);
		 
		 return resultSet;
	}

	@Override
	public int update(String query) throws SQLException {
		Statement stmt = conn.createStatement();
		int rows= stmt.executeUpdate(query);
		
		return rows;
		
	}

	@Override
	public void disconnect() throws SQLException {
		 if(conn!=null && !conn.isClosed())
		 {
			 conn.close();
		 }
	}

}
