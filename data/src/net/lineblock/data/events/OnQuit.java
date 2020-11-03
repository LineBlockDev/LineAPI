package net.lineblock.data.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.lineblock.data.LineData;
import net.lineblock.data.player.LinePlayer;
import net.lineblock.data.tablist.TabList;

public class OnQuit implements Listener {

	@EventHandler
	public static void onQuit(PlayerQuitEvent event) {
		LinePlayer linePlayer = LineData.getOnlinePlayer(event.getPlayer().getUniqueId());
		linePlayer.saveData();
		LineData.removeOnlinePlayer(linePlayer);
		event.setQuitMessage(null);
		for (Player p : Bukkit.getOnlinePlayers())
			if (!p.equals(event.getPlayer()))
				TabList.sendPacket(p);
	}
	
}
