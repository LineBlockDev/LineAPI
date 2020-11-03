package net.lineblock.data.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import net.lineblock.data.LineData;

public class OnMove implements Listener {

	@EventHandler
	public static void onMove(PlayerMoveEvent event) {
		if (LineData.getFreezedPlayers().containsKey(event.getPlayer().getUniqueId()))
			event.setCancelled(true);
		
		if(OnJoin.RawVerify.contains(event.getPlayer()))
			event.setCancelled(true);
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) 
			if(OnJoin.RawVerify.contains(e.getEntity()))
				e.setCancelled(true);
	}
	
}
