package sait.mms.contracts;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Assignment 4 - Movie Management System
 * 
 * @author Hoa Nguyen 
 * @author Miguel Mulingbayan
 *
 */
public interface DatabaseDriver {
	
	void connect() throws SQLException;
	
	ResultSet get(String query) throws SQLException; // select
	
	int update(String query) throws SQLException; //insert, update, delete
	
	void disconnect() throws SQLException;
}
