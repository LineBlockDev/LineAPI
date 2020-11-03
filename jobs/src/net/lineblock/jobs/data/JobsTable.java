package net.lineblock.jobs.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.lineblock.data.data.base.Base;
import net.lineblock.data.data.table.Table;
import net.lineblock.data.report.ReportLevel;
import net.lineblock.data.report.ReportManager;
import net.lineblock.jobs.other.JobsTypes;

public class JobsTable extends Table {

	public JobsTable(Base parent) {
		super("hub-boxes", parent);
	}
	
	public void realTimeFetchingData(JobsPlayerData data) {
		try {
			String query = "CREATE TABLE IF NOT EXISTS `+"+table+"+` (";
				query += "`uuid` varchar(255) NOT NULL,";
				query += "`blocked` tinyint(1) NOT NULL,";
				for (int i = 0; i < JobsTypes.values().length; i++) {
					query += "`"+JobsTypes.values()[i].getName()+"` LONG";
					if(i != JobsTypes.values().length-1)
						query += ",";
					else
						query += ";";
				}
			ResultSet rs = super.database.querySQL(query);
			rs.close();
		} catch (ClassNotFoundException | SQLException e) {
			println(" A FATAL ERROR WAS OCCURED ");
			e.printStackTrace();
			ReportManager.register(e, ReportLevel.CRITICAL);
		}
		try {
			ResultSet rs = super.database.querySQL("SELECT * FROM " + this.table + " WHERE `uuid`='" + data.getUuid() + ";");
			if (rs.first()) {
				data.setBlocked(rs.getBoolean("blocked"));
				for(JobsTypes jt : JobsTypes.values()) {
					data.setJobXp(jt, rs.getLong(jt.getName()));
				}
			}else {
				data.setBlocked(false);
				for(JobsTypes jt : JobsTypes.values()) {
					data.setJobXp(jt, 0);
				}
				String query = "INSERT INTO " + this.table;
					query += "(";
					query += "`uuid`,";
					query += "`blocked`,";
					for (int i = 0; i < JobsTypes.values().length; i++) {
						query += "`"+JobsTypes.values()[i].getName()+"`";
						if(i != JobsTypes.values().length-1)
							query += ",";
						else
							query += ";";
					}
					query += ") VALUES (";
					query += "'"+data.getUuid()+"',";
					query += (data.isBlocked() ? 1 : 0)+",";
					for (int i = 0; i < JobsTypes.values().length; i++) {
						query += ""+data.getJobXp(JobsTypes.values()[i]);
						if(i != JobsTypes.values().length-1)
							query += ",";
						else
							query += ");";
					}
				super.database.querySQL(query);
			}
			rs.close();
		} catch (ClassNotFoundException|SQLException e) {
			println(" A FATAL ERROR WAS OCCURED ");
			e.printStackTrace();
			ReportManager.register(e, ReportLevel.CRITICAL);
		}
	
	}
	
	public void realTimeSavingData(JobsPlayerData data) {
		try {
			String st = "UPDATE * FROM " + this.table + " (";
					for (int i = 0; i < JobsTypes.values().length; i++) {
						st += "`"+JobsTypes.values()[i]+"`";
						if(i != JobsTypes.values().length-1)
							st += ",";
					}
					st += ") SET (";
					st += (data.isBlocked() ? 1 : 0);
					for (int i = 0; i < JobsTypes.values().length; i++) {
						st += ""+data.getJobXp(JobsTypes.values()[i])+"";
						if(i != JobsTypes.values().length-1)
							st += ",";
					}
					st += "WHERE `uuid`='" + data.getUuid() + "';";
			ResultSet rs = super.database.querySQL(st);
			rs.close();
		} catch (ClassNotFoundException|SQLException e) {
			println(" A FATAL ERROR WAS OCCURED ");
			e.printStackTrace();
			ReportManager.register(e, ReportLevel.CRITICAL);
		}
		
	}
	
	public Thread fetchData(JobsPlayerData data) {
		Thread thread = new Thread(() -> realTimeFetchingData(data));
		thread.start();
		return thread;
	}
	
	public Thread saveData(JobsPlayerData data) {
		Thread thread = new Thread(() -> realTimeSavingData(data));
		thread.start();
		return thread;
	}

}
