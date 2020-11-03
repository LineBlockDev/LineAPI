package net.lineblock.data.player;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

import net.lineblock.data.LineData;

public enum Rank {
	ADMINISTRATEUR(0, "Administrateur", "Admin", "DARK_RED", true, "admin"),
	RESP_DEV(1, "Responsable Developpeur", "Resp. Dev.", "BLUE", false, "respdev"),
	RESP_MODO(2, "Responsable Moderation", "Resp. Modo.", "RED", false, "respmodo"),
	RESP_BUILD(3, "Responsable Builder", "Resp. Build.", "GREEN", false, "respbuild"),
	RESP_ANIMATION(4, "Responsable Animation", "Resp. Anim.", "GOLD", false, "respanim"),
	SM(4, "Super-Moderateur", "SM", "RED", false, "SM"),
	MODO(5, "Moderateur", "Modo", "RED", false, "modo"),
	DEV(6, "Developpeur", "Dev", "BLUE", false, "dev"),
	BUILDER(7, "Builder", "Build", "GREEN", false, "builder"),
	STAFF(8, "Staff", "Staff", "AQUA", false, "staff"),
	YOUTUBER(9, "Youtuber", "YT", "RED", false, "youtuber"),
	LEGEND(10, "Legende", "L", "LIGHT_PURPLE", false, "legend"),
	VIP_PLUS_PLUS(11, "VIP++", "VIP++", "DARK_AQUA", false, "vipplusplus"),
	VIP_PLUS(12, "VIP+", "VIP+", "YELLOW", false, "vipplus"),
	VIP(13, "VIP", "VIP", "DARK_PURPLE", false, "vip"),
	PLAYER(14, "Player", "P", "GRAY", false, "player");
	
	private final int level;
	private final String name;
	private final String prefix;
	private final String color;
	private final boolean bold;
	private final String permission;
	
	Rank(int level, String name, String prefix, String color, boolean bold, String permission) {
		this.level = level;
		this.name = name;
		this.prefix = prefix;
		this.color = color;
		this.bold = bold;
		this.permission = permission;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public ChatColor getBukkitColor() {
		return ChatColor.valueOf(this.color);
	}
	
	public net.md_5.bungee.api.ChatColor getBungeeColor() {
		return net.md_5.bungee.api.ChatColor.valueOf(this.color);
 	}
	
	public net.md_5.bungee.api.ChatColor getColor() {
		return getBungeeColor();
	}
	
	public boolean isBold() {
		return this.bold;
	}
	
	public ChatColor getBold() {
		if (isBold())
			return ChatColor.BOLD;
		return ChatColor.RESET;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean hasPrefix() {
		return (this.prefix != null);
	}
	
	public boolean lowerOrEqualTo(Rank otherRank) {
		return (this.level >= otherRank.level);
	}
	
	public boolean higherOrEqualTo(Rank otherRank) {
		return (this.level <= otherRank.level);
	}
	
	public boolean lowerThan(Rank otherRank) {
		return (this.level > otherRank.level);
	}
	
	public boolean higherThan(Rank otherRank) {
		return (this.level < otherRank.level);
	}
	
	public boolean equalsTo(Rank otherRank) {
		return (this.level == otherRank.level);
	}

	public static Rank getFromLevel(int level) {
		for (Rank rank : values()) {
			if (rank.getLevel() == level) 
				return rank;
		}
		return null;
	}

	public static List<LinePlayer> getPlayers(Rank lowBoundary, Rank highBoundary) {
		List<LinePlayer> output = new ArrayList<>();
		for (LinePlayer player : LineData.getOnlinePlayers().values()) {
	    	if (player.getRank().higherOrEqualTo(lowBoundary) && player.getRank().lowerOrEqualTo(highBoundary))
	    		output.add(player); 
	    }
	    return null;
	}
	public String getPrefix() {return prefix;}
	
	public String getPermission() {
		return permission;
	}
	
	public static int getRankCount() {
		return PLAYER.getLevel();
	}
	
}
