package net.lineblock.network;

import net.lineblock.network.commands.AdminListCommand;
import net.lineblock.network.commands.BrodcastCommand;
import net.lineblock.network.commands.ListCommand;
import net.lineblock.network.commands.SendCommand;
import net.lineblock.network.event.ProxyPing;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;

public class LineNetwork extends Plugin {
	
	public static final String PREFIX = "[" + ChatColor.BLUE + "Line" + ChatColor.RESET + "Block" + ChatColor.RED + "Network" + ChatColor.RESET + "] " + ChatColor.DARK_RED;
	private static LineNetwork instance;
	
	@Override
	public void onEnable() {
		instance = this;
		this.getProxy().getPluginManager().registerCommand(this, new ListCommand());
		this.getProxy().getPluginManager().registerCommand(this, new AdminListCommand());
		this.getProxy().getPluginManager().registerCommand(this, new BrodcastCommand());
		this.getProxy().getPluginManager().registerCommand(this, new SendCommand());
		
		this.getProxy().getPluginManager().registerListener(this, new ProxyPing());
	}
	
	@Override
	public void onDisable() {
		
	}

	public static LineNetwork getInstance() {
		return instance;
	}

}
