package net.lineblock.data.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.lineblock.data.report.ReportLevel;
import net.lineblock.data.report.ReportManager;

public abstract class Database { 

	public static final String MSG_PREFIX = "DATABASE";
	protected Connection connection = null;
	public abstract Connection openConnection() throws SQLException, ClassNotFoundException;
	
	public boolean checkConnection() {      
		try {
			return (this.connection != null && !this.connection.isClosed() && this.connection.isValid(2));
		} catch (SQLException e) {
			ReportManager.register(e, ReportLevel.CRITICAL);
			return false;
		}
	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
	public boolean closeConnection() throws SQLException {
		if (this.connection == null)
			return false;
		this.connection.close();
		return true;
	}
	
	public ResultSet querySQL(String query) throws SQLException, ClassNotFoundException {
		if (!checkConnection())
			openConnection();
		Statement statement = this.connection.createStatement();
		return statement.executeQuery(query);
	}
	
	public int updateSQL(String query) throws SQLException, ClassNotFoundException {
		if (!checkConnection())
			openConnection();
		Statement statment = this.connection.createStatement();
		return statment.executeUpdate(query);
	}
	
}
