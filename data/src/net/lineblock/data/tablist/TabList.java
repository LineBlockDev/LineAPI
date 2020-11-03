package net.lineblock.data.tablist;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.lineblock.data.LineData;
import net.lineblock.data.player.LinePlayer;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R2.IChatBaseComponent;
import net.minecraft.server.v1_16_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_16_R2.PacketPlayOutPlayerListHeaderFooter;

public class TabList implements Runnable {

	public static int TICK_COUNT= 0;
	public static long[] TICKS= new long[600];
	public static long LAST_TICK= 0L;
	 
	public static double getTPS() {
		return getTPS(100);
	}
	 
	public static double getTPSPercentage() {
		double tps = getTPS();
	    double lag = Math.round((1.0D - tps / 20.0D) * 100.0D);
	    return lag;
	}
	
	public static double getTPS(int ticks) {
		if (TICK_COUNT< ticks) {
			return 20.0D;
		}
		int target = (TICK_COUNT- 1 - ticks) % TICKS.length;
		long elapsed = System.currentTimeMillis() - TICKS[target];
	 
		return ticks / (elapsed / 1000.0D);
	}
	 
	public static long getElapsed(int tickID) {
		if (TICK_COUNT- tickID >= TICKS.length) {
		}
	 
		long time = TICKS[(tickID % TICKS.length)];
		return System.currentTimeMillis() - time;
	}
	 
	public void run() {
		TICKS[(TICK_COUNT% TICKS.length)] = System.currentTimeMillis();
	 
		TICK_COUNT+= 1;
	}
	
	public static void sendPacket(Player p) {
		LinePlayer linePlayer = LineData.getOnlinePlayer(p.getUniqueId());
		String header = LineData.getInstance().getPREFIX() + "\n" + ChatColor.RED + "Rank: " + ChatColor.GOLD + linePlayer.getRank().getName() + "		" + ChatColor.RED + "Money: " + ChatColor.GOLD + linePlayer.getMoney() + "		" + ChatColor.RED + "Player count: " + ChatColor.GOLD + Bukkit.getOnlinePlayers().size();
		String footer = ChatColor.RED + "TPS: " + ChatColor.GOLD + getTPS() + "\n" + ChatColor.RED + "play.lineblock.fr";
		sendHeaderFooter(p, header, footer);
	}
	
	public static void sendHeaderFooter(Player player, String header, String footer) {
        IChatBaseComponent tabHeader = ChatSerializer.a("{\"text\": \"" + header + "\"}");
        IChatBaseComponent tabFooter = ChatSerializer.a("{\"text\": \"" + footer + "\"}");
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        try
        {
            Field headerField = packet.getClass().getDeclaredField("header");
            headerField.setAccessible(true);
            headerField.set(packet, tabHeader);
            headerField.setAccessible(false);
            Field footerField = packet.getClass().getDeclaredField("footer");
            footerField.setAccessible(true);
            footerField.set(packet, tabFooter);
            footerField.setAccessible(false);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
	
}
