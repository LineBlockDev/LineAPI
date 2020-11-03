package net.lineblock.data.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.lineblock.data.LineData;
import net.lineblock.data.tablist.TabList;

public class OnTick extends BukkitRunnable {

	public OnTick() {
		runTaskTimer(LineData.getInstance(), 0, 20);
	}
	
	@Override
	public void run() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (LineData.getOnlinePlayers().containsValue(p)) {
				TabList.sendPacket(p);
			}
			
		}
		
	}

}
