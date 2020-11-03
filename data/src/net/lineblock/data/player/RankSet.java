package net.lineblock.data.player;

import org.bukkit.entity.Player;

public final class RankSet {

	public static final void setRank(Player player, Rank rank) {
		for (int i = 0; i <= Rank.getRankCount(); i++) {
			if (rank.getLevel() == i) {
				player.setPlayerListName(rank.getBold().toString() + rank.getBukkitColor() + "[" + rank.getPrefix() + "] " + player.getName());
				player.setCustomName(rank.getBold().toString() + rank.getBukkitColor() + "[" + rank.getName() + "] " + player.getName());
				player.setDisplayName(rank.getBold().toString() + rank.getBukkitColor() + "[" + rank.getName() + "] " + player.getName());
			}
			
		}
		
	}
	
}
