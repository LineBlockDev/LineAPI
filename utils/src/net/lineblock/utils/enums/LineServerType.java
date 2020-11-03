package net.lineblock.utils.enums;

import org.bukkit.Bukkit;

public enum LineServerType {
	HUB("Lobby-x"),
	UNKNOWN_GAME("Jeux-x"),
	SHEEPWARS("Sheepwars-x"),
	SKYWARS("SkyWars-x"),
	SKYWARSDUEL("SkyWarsDuel-x"),
	TNTRUN("Tntrun-x");
	
	private final String name;
	
	LineServerType(String name) {
		this.name = name;
	}
	
	public String getName(int i) {
		return this.name.replaceAll("x", String.valueOf(i));
	}
	
	public static LineServerType getServerTypeFromServerName() {
		for (LineServerType type : values()) {
			String currentType = type.getName(0).split("-")[0];
			if (Bukkit.getServer().getName().startsWith(currentType)) {
				return type;
			}
			
		}
		return UNKNOWN_GAME;
	}
	
}
