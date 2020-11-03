package net.lineblock.data.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.lineblock.data.LineData;
import net.md_5.bungee.api.ChatColor;

public class ReportManager {
	
	@SuppressWarnings("resource" )
	public static boolean register(Throwable th, ReportLevel level) {
		if (level == ReportLevel.CRITICAL) {
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			for (Player player : Bukkit.getServer().getOnlinePlayers()) {
				out.writeUTF("Connect");
				out.writeUTF("lobby");
				player.sendPluginMessage(LineData.getInstance(), "BungeeCord", out.toByteArray());
				out.writeUTF("Message");
				out.writeUTF(player.getName().toString());
				out.writeUTF(ChatColor.RED.toString() + ChatColor.BOLD + " - AN FATAL ERROR WAS OCCURED -");
				player.sendPluginMessage(LineData.getInstance(), "BungeeCord", out.toByteArray());
			}
			String timeLog = (new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS")).format(Calendar.getInstance().getTime());
			File folder = new File("api-reports/");
			if (!folder.exists())
				folder.mkdirs();
			File logFile = new File("api-reports/" + timeLog + "-" + level.toString() + ".log");
			try {
				try {
					PrintWriter writer = new PrintWriter(logFile);
					th.printStackTrace(writer);
					writer.flush();
					writer.close();
				} catch (Throwable e2) {
					throw e2;
				}
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		} else if (level == ReportLevel.ALERT) {
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			for (Player player : Bukkit.getOnlinePlayers()) {
				out.writeUTF("Message");
				out.writeUTF(player.getName().toString());
				out.writeUTF(ChatColor.RED.toString() + ChatColor.BOLD + " - AN ERROR WAS OCCURED -");
				player.sendPluginMessage(LineData.getInstance(), "BungeeCord", out.toByteArray());
			}
			
			String timeLog = (new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS")).format(Calendar.getInstance().getTime());
			File folder = new File("api-reports/");
			if (!folder.exists())
				folder.mkdirs();
			File logFile = new File("api-reports/" + timeLog + "-" + level.toString() + ".log");
			StringWriter errors = new StringWriter();
			th.printStackTrace(new PrintWriter(errors));
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true));
				try {
					writer.write(errors.toString());
				} catch (Throwable e2) {
					try {
						writer.close();
					} catch (Throwable e) {
						e.addSuppressed(e);
					}
					throw e2;
				}
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
		
	}
	
}
