package net.lineblock.data.data.table;

import net.lineblock.data.LineData;
import net.lineblock.data.data.base.Base;
import net.md_5.bungee.api.ChatColor;

public class Table {

	public final String table;
	public final Base database;
	
	public Table(String name, Base parentBase) {
		this.table = name;
		this.database = parentBase;
	}
	
	protected void println(String message) {
		LineData.getCS().sendMessage("[" + ChatColor.GOLD + "DATABASE" + ChatColor.RESET + " | " + ChatColor.GOLD + this.database.name() + ChatColor.RESET +  " | " + ChatColor.GOLD + this.table + ChatColor.RESET + "]" + ChatColor.DARK_RED + message);
	}
	
}
