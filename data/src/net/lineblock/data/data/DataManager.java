package net.lineblock.data.data;

import java.sql.SQLException;

import net.lineblock.data.LineData;
import net.lineblock.data.data.base.Base;
import net.lineblock.data.data.base.LineBase;
import net.lineblock.data.report.ReportLevel;
import net.lineblock.data.report.ReportManager;

public class DataManager extends Thread {

	public static final String HOST = LineData.getInstance().getConfig().getString("sql.host");
	public static final String USER = LineData.getInstance().getConfig().getString("sql.user");
	public static final String PASSWORD = LineData.getInstance().getConfig().getString("sql.password");
	private static DataManager instance;
	private LineBase linebase;
	
	public DataManager() {
		instance = this;
		this.linebase = new LineBase();
	}
	
	private boolean connect(Base base) {
		Long start = Long.valueOf(System.currentTimeMillis());
		try {
			base.openConnection();
			Double stop = Double.valueOf((System.currentTimeMillis() - start.longValue()) / 1000.0D);
			base.println("Connected to database (" + stop + "s)");
		} catch (ClassNotFoundException | SQLException e) {
			ReportManager.register(e, ReportLevel.INFO);
			Double stop = Double.valueOf((System.currentTimeMillis() - start.floatValue()) / 1000.0D);
			base.println("Database unreachable (" + stop + "s)");
			return false;
		}
		return true;
	}
	
	public LineBase getLineBase() {
		while (!this.linebase.checkConnection())
			connect((Base)this.linebase);
		return this.linebase;
	}
	
	public static DataManager getInstance() {
		if (instance == null)
			new Thread(new DataManager()).start();;
		return instance;
	}
	
}
