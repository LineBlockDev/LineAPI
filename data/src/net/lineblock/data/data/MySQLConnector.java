package net.lineblock.data.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.lineblock.data.LineData;
import net.md_5.bungee.api.ChatColor;

public class MySQLConnector extends Database {

	private final String database;
	private final String user;
	private final String password;
	private boolean debug = false;
	
	public MySQLConnector(String database, String user, String password) {
		this.database = database;
		this.user = user;
		this.password = password;
	}
	
	public Connection openConnection() throws ClassNotFoundException, SQLException {
		if (debug)
			println("Connecting to database ... ");
		if (checkConnection())
			return this.connection;
		Class.forName("com.mysql.jdbc.Driver");
		this.connection = DriverManager.getConnection("jdbc:mysql://localhost/" + this.database + "?useSSL=false&autoReconnect=true&characterEncoding=utf8", this.user, this.password);
		if (debug)
			println("Connection to database etablished !");
		return this.connection;
	}
	
	public String name() {
		return this.database;
	}
	
	public void println(String message) {
		LineData.getCS().sendMessage("[" + ChatColor.GOLD + "DATABASE" + ChatColor.RESET + " | " + ChatColor.GOLD + this.database + ChatColor.RESET + "] " + ChatColor.DARK_RED + message);
	}

}
