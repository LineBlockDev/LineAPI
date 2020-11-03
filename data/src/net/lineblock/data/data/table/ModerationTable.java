package net.lineblock.data.data.table;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.lineblock.data.data.base.Base;
import net.lineblock.data.player.PlayerData;
import net.lineblock.data.player.Rank;
import net.lineblock.data.report.ReportLevel;
import net.lineblock.data.report.ReportManager;

public class ModerationTable extends Table {

	private boolean isDebug = false;
	
	public ModerationTable(Base parentBase) {
		super("users", parentBase);
	}

	public void realTimeFetchingData(PlayerData data) {
		if (isDebug)
			println(" Check if the database exists ...");
		try {
			String query = "CREATE TABLE IF NOT EXISTS `LineDB`.`users` (";
			query += "`uuid` varchar(255) NOT NULL, ";
			query += "`name` varchar(25) NOT NULL, ";
			query += "`premium` ENUM('true','false') NOT NULL, ";
			query += "`last_ip` varchar(25) NOT NULL, ";
			query += "`banned_ip` ENUM('true','false') NOT NULL, ";
			query += "`pass_hash` varchar(255) NOT NULL, ";
			query += "`rank` int(2) NOT NULL, ";
			query += "`money` float NOT NULL, ";
			query += "`kicks` int(3) NOT NULL, ";
			query += "`mute_until` float NOT NULL, ";
			query += "`ban_until` float NOT NULL, ";
			query += "`ban_duration` int(255) NOT NULL, ";
			query += "`punishment_current_reason` varchar(255) NOT NULL, ";
			query += "`punishment_reason` varchar(255) NOT NULL, ";
			query += "`ban_count` int(3) NOT NULL, ";
			query += "`mute_count` int(3) NOT NULL, ";
			query += "`mute_duration` int(255) NOT NULL, ";
			query += "`mute_current_reason` varchar(255) NOT NULL, ";
			query += "`mute_reason` varchar(255) NOT NULL, ";
			query += "`last_update` float NOT NULL, ";
			query += "`first_creation` float NOT NULL);";
			this.database.updateSQL(query);
			if (isDebug)
				println(" Checked if Database exist !");
		} catch (ClassNotFoundException | SQLException e) {
			ReportManager.register(e, ReportLevel.CRITICAL);
			e.printStackTrace();
		}
		if (isDebug)
			println(" Fetching data for user " + data.getUuid() + " ... ");
		try {
			ResultSet rs = this.database.querySQL("SELECT * FROM " + this.table + " WHERE `uuid`='" + data.getUuid() + "';");
			if (rs.first()) {
				data.setName(rs.getString("name"));
				data.setPremium(rs.getBoolean("premium"));
				data.setLastIP(rs.getString("last_ip"));
				data.setBannedIp(rs.getBoolean("banned_ip"));
				data.setPassHash(rs.getString("pass_hash"));
				data.setRank(Rank.getFromLevel(rs.getInt("rank")));
				data.setMoney(rs.getLong("money"));
				data.setKicks(rs.getInt("kicks"));
				data.setMuteUntil(rs.getLong("ban_until"));
				data.setBanDuration(rs.getInt("ban_duration"));
				data.setpunishmentReasonCurrent(rs.getString("punishment_current_reason"));
				data.setpunishmentReason(rs.getString("punishment_reason"));
				data.setBanCount(rs.getInt("ban_count"));
				data.setMuteCount(rs.getInt("mute_count"));
				data.setMuteDuration(rs.getInt("mute_duration"));
				data.setMuteCurrentReason(rs.getString("mute_current_reason"));
				data.setMuteReason(rs.getString("mute_reason"));
				data.setLastUpdated(rs.getLong("last_update"));
				data.setFirstCreated(rs.getLong("first_creation"));
			}
			rs.close();
			if (isDebug)
				println(" Data fetched for user " + data.getUuid() + " (" + data.getName() + ") !");
		} catch (ClassNotFoundException|SQLException e) {
			ReportManager.register(e, ReportLevel.CRITICAL);
			if (isDebug)
				println(" A FATAL ERROR WAS OCCURED ");
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public void realTimeSavingData(PlayerData data) {
		if (isDebug)
			println(" Sending data for user " + data.getName() + " ... ");
		try {
			ResultSet rs = this.database.querySQL("SELECT * FROM " + this.table + " WHERE `uuid`='" + data.getUuid() + "';");
			if (rs.first()) {
				if (isDebug)
					println(" " + data.getName() + " is an old player (" + data.getFirstCreatedDate().getYear() + "." + data.getFirstCreatedDate().getMonth() + "." + data.getFirstCreatedDate().getDay() + "/" + data.getFirstCreatedDate().getHours() + ":" + data.getFirstCreatedDate().getMinutes() + ":" + data.getFirstCreatedDate().getSeconds() + ")");
				String query = "UPDATE `users` SET ";
				query += "`name`='" + data.getName() + "', ";
				query += "`premium`='" + data.isPremium() + "', ";
				query += "`last_ip`='" + data.getLastIP() + "', ";
				query += "`banned_ip`='" + data.isBannedIp() + "', ";
				query += "`pass_hash`='" + data.getPassHash() + "', ";
				query += "`rank`='" + data.getRank().getLevel() + "', ";
				query += "`money`='" + data.getMoney() + "', ";
				query += "`kicks`='" + data.getKicks() + "', ";
				query += "`mute_until`='" + data.getMuteUntil() + "', ";
				query += "`ban_until`='" + data.getBanUntil() + "', ";
				query += "`ban_duration`='" + data.getBanDuration() + "', ";
				query += "`punishment_current_reason`='" + data.getpunishmentReasonCurrent() + "', ";
				query += "`punishment_reason`='" + data.getpunishmentReason() + "', ";
				query += "`ban_count`='" + data.getBanCount() + "', ";
				query += "`mute_count`='" + data.getMuteCount() + "', ";
				query += "`mute_duration`='" + data.getMuteDuration() + "', ";
				query += "`mute_current_reason`='" + data.getMuteCurrentReason() + "', ";
				query += "`mute_reason`='" + data.getMuteReason() + "', ";
				query += "`last_update`='" + System.currentTimeMillis() + "' ";
				query += "WHERE `uuid`='" + data.getUuid() + "';";
				this.database.updateSQL(query);
			} else {
				if (isDebug)
					println(" " + data.getName() + " is a new player !");
				String query = "INSERT INTO `users` VALUES(";
				query += "'" + data.getUuid() + "', ";
				query += "'" + data.getName() + "', ";
				query += "'" + data.isPremium() + "', ";
				query += "'" + data.getLastIP() + "', ";
				query += "'" + data.isBannedIp() + "', ";
				query += "'" + data.getPassHash() + "', ";
				query += "'" + data.getRank().getLevel() + "', ";
				query += "'" + data.getMoney() + "', ";
				query += "'" + data.getKicks() + "', ";
				query += "'" + System.currentTimeMillis() + "', ";
				query += "'" + System.currentTimeMillis() + "', ";
				query += "'" + data.getBanDuration() + "', ";
				query += "'" + data.getpunishmentReasonCurrent() + "', ";
				query += "'" + data.getpunishmentReason() + "', ";
				query += "'" + data.getBanCount() + "', ";
				query += "'" + data.getMuteCount() + "', ";
				query += "'" + data.getMuteDuration() + "', ";
				query += "'" + data.getMuteCurrentReason() + "', ";
				query += "'" + data.getMuteReason() + "', ";
				query += "'" + System.currentTimeMillis() + "', ";
				query += "'" + System.currentTimeMillis() + "');";
				this.database.updateSQL(query);
			}
			rs.close();
			if (isDebug)
				println(" Done !");
		} catch (ClassNotFoundException|SQLException e) {
			ReportManager.register(e, ReportLevel.CRITICAL);
			if (isDebug)
				println(" A FATAL ERROR WAS OCCURED");
		}
		
	}
	
	public Thread fetchData(PlayerData data) {
		Thread thread = new Thread(() -> realTimeFetchingData(data));
		thread.start();
		return thread;
	}
	
	public Thread saveData(PlayerData data) {
		Thread thread = new Thread(() -> realTimeSavingData(data));
		thread.start();
		return thread;
	}
	
	public ResultSet querySQL(String query) throws ClassNotFoundException, SQLException {return super.database.querySQL(query);}
	public void updateSQL(String query) throws ClassNotFoundException, SQLException {super.database.updateSQL(query);}
	
}
/*if (rs.first()) {
				this.database.updateSQL("UPDATE " + this.table + " SET ban_start=" + data.getBanUntil() + ", ban_duration=" + data.getBanDuration() + ", ban_count=" + data.getBanCount() + ", ban_current_reason="" + data.getCurrentBanReason() + "", banned_ip=" + String.valueOf(data.isBannedIp()) + ", mute_start=" + data.getMuteUntil() + ", mute_duration=" + data.getMuteDuration() + ", mute_count=" + data.getMuteCount() + ", mute_reason="" + data.getMuteReason() + "", mute_current_reason="" + data.getMuteCurrentReason() + "";");
			} else {
				this.database.updateSQL("INSERT INTO " + this.table + "(ban_start, ban_duration, ban_count, ban_reason, ban_current_reason, banned_ip, mute_start, mute_duration, mute_count, mute_reason, mute_current_reason) VALUES(" + data.getBanUntil() + ", " + data.getBanDuration() + ", "" + data.getBanReason() + "", "" + data.getCurrentBanReason() + "", " + String.valueOf(data.isBannedIp()) + ", " + data.getMuteStart() + ", " + data.getMuteDuration() + ", "" + data.getMuteReason() + "", "" + data.getMuteCurrentReason() + "");f);
			}*/
