package net.lineblock.chat.listeners;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.lineblock.chat.LineChat;
import net.lineblock.chat.chats.Chat;
import net.lineblock.data.LineData;
import net.lineblock.data.player.LinePlayer;
import net.lineblock.data.player.Rank;

public class PlayerEventsListener implements Listener {

	public static List<String> joinMessages;
	public static List<String> quitMessages;
	public static List<String> deathMessages;
	
	public PlayerEventsListener() {
		joinMessages = LineChat.getInstance().getConfig().getStringList("messages.join");
		quitMessages = LineChat.getInstance().getConfig().getStringList("messages.quit");
		deathMessages = LineChat.getInstance().getConfig().getStringList("messages.death");
	}
	
	@EventHandler
	public void onJSP(AsyncPlayerChatEvent event) {
		LinePlayer linePlayer = LineData.getOnlinePlayer(event.getPlayer().getUniqueId());
		if (!linePlayer.getData().isBanned()) {
			if (event.getMessage().startsWith("$")) {
				if (linePlayer.getRank().higherOrEqualTo(Rank.ADMINISTRATEUR)) {
					if (LineChat.getChat().containsKey("AdminChat")) {
						LineChat.getChat().get("AdminChat").send(linePlayer, event.getMessage().replace("$", ""));
					} else {
						Chat adminChat = new Chat("AdminChat", "$", "§7[§4Admin§7]§r", Rank.ADMINISTRATEUR);
						LineChat.getChat().put(adminChat.getName(), adminChat);
						LineChat.getChat().get("AdminChat").send(linePlayer, event.getMessage().replace("$", ""));
					}
				} else {
					if (LineChat.getChat().containsKey("PublicChat")) {
						LineChat.getChat().get("PublicChat").send(linePlayer, event.getMessage());
					} else {
						Chat staffChat = new Chat("PublicChat", "", "§7[§4Public§7]§r", Rank.PLAYER);
						LineChat.getChat().put(staffChat.getName(), staffChat);
						LineChat.getChat().get("PublicChat").send(linePlayer, event.getMessage());
					}
				}
			} else if (event.getMessage().startsWith("%")) {
				if (linePlayer.getRank().higherOrEqualTo(Rank.STAFF)) {
					if (LineChat.getChat().containsKey("StaffChat")) {
						LineChat.getChat().get("StaffChat").send(linePlayer, event.getMessage().replace("%", ""));
					} else {
						Chat staffChat = new Chat("StaffChat", "%", "§7[§4Staff§7]§r", Rank.STAFF);
						LineChat.getChat().put(staffChat.getName(), staffChat);
						LineChat.getChat().get("StaffChat").send(linePlayer, event.getMessage().replace("%", ""));
					}
				} else {
					if (LineChat.getChat().containsKey("PublicChat")) {
						LineChat.getChat().get("PublicChat").send(linePlayer, event.getMessage());
					} else {
						Chat staffChat = new Chat("PublicChat", "", "§7[§4Public§7]§r", Rank.PLAYER);
						LineChat.getChat().put(staffChat.getName(), staffChat);
						LineChat.getChat().get("PublicChat").send(linePlayer, event.getMessage());
					}
				}
			} else {
				if (LineChat.getChat().containsKey("PublicChat")) {
					LineChat.getChat().get("PublicChat").send(linePlayer, event.getMessage());
				} else {
					Chat staffChat = new Chat("PublicChat", "", "§7[§4Public§7]§r", Rank.PLAYER);
					LineChat.getChat().put(staffChat.getName(), staffChat);
					LineChat.getChat().get("PublicChat").send(linePlayer, event.getMessage());
				}
			}
		} else {
			linePlayer.sendMessage("§4Vous n'avez pas la permission d'écrire §bOwO");
		}
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onJoin(final PlayerJoinEvent e) {
		e.setJoinMessage(null);
		try {
			new BukkitRunnable() {
				@Override
				public void run() {
					Bukkit.getServer().broadcastMessage(
							ChatColor.translateAlternateColorCodes('&', joinMessages.get(new Random().nextInt(joinMessages.size())))
							.replaceAll("%PLAYER%", LineData.getOnlinePlayer(e.getPlayer().getUniqueId()).getCustomName())
					);
				}
			}.runTaskLater(LineChat.getInstance(), 25+9);
		}catch(NullPointerException e1) {
			if(LineData.isDebug())
				LineData.getCS().sendMessage("Le joueur "+e.getPlayer().getName()+" s'est deconnecté durant l'envoi du message de bienvenue");
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		try {
			Bukkit.getServer().broadcastMessage(
					ChatColor.translateAlternateColorCodes('&', quitMessages.get(new Random().nextInt(quitMessages.size())))
					.replaceAll("%PLAYER%", LineData.getOnlinePlayer(e.getPlayer().getUniqueId()).getCustomName())
			);
		}catch(NullPointerException e1) {
			if(LineData.isDebug())
				LineData.getCS().sendMessage("Le joueur "+e.getPlayer().getName()+" s'est deconnecté durant l'envoi du message d'au-revoir");
		}
	}
	
	@EventHandler
	public void onDie(PlayerDeathEvent e) {
		e.setDeathMessage(null);
		Bukkit.getServer().broadcastMessage(
				ChatColor.translateAlternateColorCodes('&', deathMessages.get(new Random().nextInt(deathMessages.size())))
				.replaceAll("%PLAYER%", LineData.getOnlinePlayer(e.getEntity().getUniqueId()).getCustomName())
		);
	}

}
