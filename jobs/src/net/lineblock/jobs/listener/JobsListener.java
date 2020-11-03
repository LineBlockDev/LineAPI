package net.lineblock.jobs.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.lineblock.jobs.data.JobsPlayerDataManager;

public class JobsListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		JobsPlayerDataManager.getBoxPlayerData(e.getPlayer().getUniqueId());
	}
	
}
