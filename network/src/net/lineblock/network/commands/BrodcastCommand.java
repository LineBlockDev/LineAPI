package net.lineblock.network.commands;

import net.lineblock.network.LineNetwork;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Command;

public class BrodcastCommand extends Command {

	public BrodcastCommand() {
		super("bc", "line.network.admin.bc", "broadcast", "broadc", "bcast");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			if (args.length >= 2) {
				switch (args[0].toLowerCase()) {
				case "global":
					sendGlobalBC(1, args);
					break;

				case "g":
					sendGlobalBC(1, args);
					break;
					
				case "serveur":
					sendServerBC(1, args, ((ProxiedPlayer) sender).getServer());
					break;
					
				case "server":
					sendServerBC(1, args, ((ProxiedPlayer) sender).getServer());
					break;
					
				case "s":
					sendServerBC(1, args, ((ProxiedPlayer) sender).getServer());
					break;
				}
			} else {
				sender.sendMessage(new TextComponent(LineNetwork.PREFIX + "La command est : /bc <gloabal:serveur> <message>"));
			}
		} else {
			if (args.length >= 1) {
				sendGlobalBC(0, args);
			} else {
				sender.sendMessage(new TextComponent(LineNetwork.PREFIX + "La command est : /bc <massage>"));
			}
			
		}
			
	}

	private void sendGlobalBC(int argsInt, String[] args) {
		StringBuilder sb = new StringBuilder();
		for (int i = argsInt; i < args.length; i++) {
			sb.append(args[i] + " ");
		}
		char[] c = "&".toCharArray();
		LineNetwork.getInstance().getProxy().broadcast(new TextComponent(" "));
		LineNetwork.getInstance().getProxy().broadcast(new TextComponent(LineNetwork.PREFIX));
		LineNetwork.getInstance().getProxy().broadcast(new TextComponent(ChatColor.DARK_RED + "  [Annonce]"));
		LineNetwork.getInstance().getProxy().broadcast(new TextComponent(ChatColor.RED + "    " + ChatColor.translateAlternateColorCodes(c[0], sb.toString())));
		LineNetwork.getInstance().getProxy().broadcast(new TextComponent(" "));
	}
	
	private void sendServerBC(int argsInt, String[] args, Server server) {
		StringBuilder sb = new StringBuilder();
		for (int i = argsInt; i < args.length; i++) {
			sb.append(args[i] + " ");
		}
		char[] c = "&".toCharArray();
		for (ProxiedPlayer p : server.getInfo().getPlayers()) {
			p.sendMessage(ChatMessageType.SYSTEM, new TextComponent(" "));
			p.sendMessage(ChatMessageType.SYSTEM, new TextComponent(LineNetwork.PREFIX));
			p.sendMessage(ChatMessageType.SYSTEM, new TextComponent(ChatColor.DARK_RED + "  [Annonce]"));
			p.sendMessage(ChatMessageType.SYSTEM, new TextComponent(ChatColor.RED + "    " + ChatColor.translateAlternateColorCodes(c[0], sb.toString())));
			p.sendMessage(ChatMessageType.SYSTEM, new TextComponent(" "));
		}
		
	}
	
}
