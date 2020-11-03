package net.lineblock.chat.chats;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;

import net.lineblock.data.LineData;
import net.lineblock.data.player.LinePlayer;
import net.lineblock.data.player.Rank;
import net.minecraft.server.v1_16_R2.ChatMessageType;
import net.minecraft.server.v1_16_R2.IChatBaseComponent;
import net.minecraft.server.v1_16_R2.PacketPlayOutChat;

public class Chat {

	private String name;
	private String cmd;
	private String prefix;
	private Rank minRank;
	
	public Chat(String name, String cmd, String prefix, Rank minRank) {
		this.name = name;
		this.prefix = prefix;
		this.cmd = cmd;
		this.minRank = minRank;
	}

	public void send(LinePlayer linePlayer, String message) {
		String[] m = message.split(" ");
		for (LinePlayer p : LineData.getOnlinePlayers().values()) {
			if (p.getRank().higherOrEqualTo(minRank)) {
				String messageToSend = "";
				for (String msg : m) {
					if (msg.startsWith("@")) {
						if (p.getName().equals(msg.replaceFirst("@", ""))) {
							msg = p.getCustomName() + ChatColor.RESET;
							p.getPlayer().playSound(p.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
							String s1 = ChatColor.translateAlternateColorCodes('&', "&7[&6LineChat&7] &2Vous avez été mentionné par: &4" + linePlayer.getCustomName());
				            PacketPlayOutChat bar = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + s1 + "\"}"), ChatMessageType.GAME_INFO, p.getUUID());
				            ((CraftPlayer) p.getPlayer()).getHandle().playerConnection.sendPacket(bar);
						}
						
					}
					messageToSend += msg + " ";
				}
				if (!(name == "PublicChat")) {
					if (linePlayer.getRank().higherOrEqualTo(Rank.VIP)) {
						p.sendMessage(prefix + ChatColor.GRAY + " | " + linePlayer.getCustomName() + ChatColor.GRAY + " | " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', messageToSend));
					} else {
						p.sendMessage(prefix + ChatColor.GRAY + " | " + linePlayer.getCustomName() + ChatColor.GRAY + " | " + ChatColor.RESET +  messageToSend);
					}
				} else {
					if (linePlayer.getRank().higherOrEqualTo(Rank.VIP)) {
						p.sendMessage(linePlayer.getCustomName() + ChatColor.GRAY + " | " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', messageToSend));
					} else {
						p.sendMessage(linePlayer.getCustomName() + ChatColor.GRAY + " | " + ChatColor.RESET + messageToSend);
					}
					
				}
					
			}

		}
		Bukkit.getConsoleSender().sendMessage(prefix + " | " + linePlayer.getCustomName() + " | " + message);
	}
	
	public String getName() {
		return name;
	}

	public String getCmd() {
		return cmd;
	}
	
	public String getPrefix() {
		return prefix;
	}

}