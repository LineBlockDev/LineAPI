package net.lineblock.data.data.base;

import java.util.ArrayList;
import java.util.List;

import net.lineblock.data.LineData;
import net.lineblock.data.data.MySQLConnector;
import net.lineblock.data.data.table.Table;

public abstract class Base extends MySQLConnector {

	private final List<Table> tables;
	
	public Base(String database) {
		this(database, LineData.getInstance().getConfig().getString("sql.user"), LineData.getInstance().getConfig().getString("sql.password"));
	}
	
	public Base(String database, String user, String password) {
		super(database, user, password);
		this.tables = initTables(new ArrayList<>());
	}
	
	public abstract List<Table> initTables(List<Table> paramList);
	
	public List<Table> getTables() {
		return this.tables;
	}
	
}
