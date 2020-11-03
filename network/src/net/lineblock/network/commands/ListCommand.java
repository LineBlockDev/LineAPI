package net.lineblock.network.commands;

import net.lineblock.network.LineNetwork;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ListCommand extends Command {

	public ListCommand() {
		super("list", "line.network.list", "l", "liste");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length != 0) {
			if (sender instanceof ProxiedPlayer) {
				ProxiedPlayer player = (ProxiedPlayer) sender;
				player.sendMessage(ChatMessageType.SYSTEM, new TextComponent(LineNetwork.PREFIX + "La commande est : /list"));
			} else {
				sender.sendMessage(new TextComponent(LineNetwork.PREFIX + "Cette command ne peut qu'etre executer en tant que joueur !"));
			}
		} else {
			if (sender instanceof ProxiedPlayer) {
				ProxiedPlayer player = (ProxiedPlayer) sender;
				player.sendMessage(ChatMessageType.SYSTEM, new TextComponent(LineNetwork.PREFIX));
				player.sendMessage(ChatMessageType.SYSTEM, new TextComponent(ChatColor.DARK_RED + "  Joueur en ligne sur le proxy :"));
				player.sendMessage(ChatMessageType.SYSTEM, new TextComponent(ChatColor.RED + "    " + LineNetwork.getInstance().getProxy().getOnlineCount()));
				player.sendMessage(ChatMessageType.SYSTEM, new TextComponent(ChatColor.DARK_AQUA + "  Vous etes connecte sur le serveur :"));
				player.sendMessage(ChatMessageType.SYSTEM, new TextComponent(ChatColor.AQUA + "    " + player.getServer().getInfo().getName()));
				player.sendMessage(ChatMessageType.SYSTEM, new TextComponent(ChatColor.DARK_GREEN + "  Le MOTD du serveur est :"));
				player.sendMessage(ChatMessageType.SYSTEM, new TextComponent(ChatColor.GREEN + "    " + player.getServer().getInfo().getMotd()));
			} else {
				sender.sendMessage(new TextComponent(LineNetwork.PREFIX + "Cette command ne peut qu'etre executer en tant que joueur !"));
			}
			
		}
		
	}

}
