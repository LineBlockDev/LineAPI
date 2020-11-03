package net.lineblock.data;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import net.lineblock.data.command.SetRankCommand;
import net.lineblock.data.events.OnJoin;
import net.lineblock.data.events.OnLogin;
import net.lineblock.data.events.OnMove;
import net.lineblock.data.events.OnQuit;
import net.lineblock.data.events.OnTick;
import net.lineblock.data.player.LinePlayer;
import net.lineblock.data.tablist.TabList;
import net.md_5.bungee.api.ChatColor;

public class LineData extends JavaPlugin {

	private static LineData instance;
	private static HashMap<UUID, LinePlayer> onlinePlayer = new HashMap<>();
	private String PREFIX = "[" + ChatColor.GOLD + "LineData" + ChatColor.RESET + "] " + ChatColor.DARK_RED;
	private static HashMap<UUID, LinePlayer> freezedPlayer = new HashMap<>();
	private static ConsoleCommandSender cs;
	private static boolean debug = false;
	
	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		cs = getServer().getConsoleSender();
		debug = getConfig().getBoolean("debug");
		this.getServer().getPluginManager().registerEvents(new OnJoin(), this);
		this.getServer().getPluginManager().registerEvents(new OnLogin(), this);
		this.getServer().getPluginManager().registerEvents(new OnQuit(), this);
		this.getServer().getPluginManager().registerEvents(new OnMove(), this);
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new TabList(), 100L, 1L);
		new OnTick();
		getCommand("setrank").setExecutor(new SetRankCommand());
	}
	
	@Override
	public void onDisable() {
		
	}

	public static LineData getInstance() {return instance;}
	public static LinePlayer getOnlinePlayer(UUID uuid) {return onlinePlayer.get(uuid);}
	public static void addOnlinePlayer(LinePlayer player) {LineData.onlinePlayer.put(player.getUUID(), player);}
	public static void removeOnlinePlayer(LinePlayer player) {LineData.onlinePlayer.remove(player.getUUID());}
	public static HashMap<UUID, LinePlayer> getOnlinePlayers() {return onlinePlayer;}
	public String getPREFIX() {return PREFIX;}
	public static HashMap<UUID, LinePlayer> getFreezedPlayers() {return freezedPlayer;}
	public static void addFreezedPlayer(LinePlayer freezedPlayer) {LineData.freezedPlayer.put(freezedPlayer.getUUID(), freezedPlayer);}
	public static void removeFreezedPlayer(LinePlayer freezedPlayer ) {LineData.freezedPlayer.remove(freezedPlayer.getUUID());}
	public void println(String msg) {cs.sendMessage(LineData.getInstance().getPREFIX() + msg);}
	public static ConsoleCommandSender getCS() {return cs;}
	public static boolean isDebug() {return debug;}
	
}
