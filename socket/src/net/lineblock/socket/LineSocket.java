package net.lineblock.socket;

public final class LineSocket {

	public static boolean bungee = false;
	public static boolean bot = false;
	public static String PREFIX = "§r[§4LineSocket§r]";
	
	public static boolean isBungee() {
		return bungee;
	}
	public static boolean isBot() {
		return bot;
	}
	@SuppressWarnings("deprecation")
	public static void println(String msg) {
		if(isBungee())
			LineSocketBungee.getInstance().getProxy().getConsole().sendMessage(PREFIX+" "+msg);
		else if(isBot()) {
			for(String str : new String[] {"§4","§c","§6","§e","§2","§a","§b","§3","§1","§9","§d","§5","§f","§7","§8","§0","§k","§l","§m","§n","§o","§r"}) {
				msg = msg.replaceAll(str, "");
			}
			System.out.println(PREFIX+" "+msg);
		}else
			LineSocketSpigot.getInstance().getServer().getConsoleSender().sendMessage(PREFIX+" "+msg);
	}

}
