package net.lineblock.network.commands;

import java.util.ArrayList;

import net.lineblock.network.LineNetwork;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class SendCommand extends Command {

	public SendCommand() {
		super("send", "line.network.admin.send", "s");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length < 2) {
			sender.sendMessage(new TextComponent(LineNetwork.PREFIX + "La command est : /send <joueur> <serveur> [raison]"));
		} else {
			ArrayList<String> serveurName = new ArrayList<>();
			for (String serveur : LineNetwork.getInstance().getProxy().getServers().keySet()) {
				serveurName.add(serveur);
			}
			if (LineNetwork.getInstance().getProxy().getPlayers().contains(LineNetwork.getInstance().getProxy().getPlayer(args[0]))) {
				ProxiedPlayer target = LineNetwork.getInstance().getProxy().getPlayer(args[0]);
				if (serveurName.contains(args[1])) {
					ServerInfo serverInfo = LineNetwork.getInstance().getProxy().getServers().get(args[1]);
					if (args.length > 2) {
						StringBuilder sb = new StringBuilder();
						for (int i = 2; i < args.length; i++)
							sb.append(args[i] + " ");
						if (serverInfo.canAccess(target)) {
							target.connect(serverInfo);
							target.sendMessage(new TextComponent(" "));
							target.sendMessage(new TextComponent(LineNetwork.PREFIX));
							target.sendMessage(new TextComponent(ChatColor.GOLD + " Recapitulatif de l'action :"));
							target.sendMessage(new TextComponent(ChatColor.BLUE + "    Vous venez d'etre envoie sur le serveur : " + serverInfo.getName()));
							target.sendMessage(new TextComponent(ChatColor.GREEN + "    La raison est : " + sb.toString()));
							target.sendMessage(new TextComponent(ChatColor.YELLOW + "    Le joueur qui vous a envoie : " + sender.getName()));
							target.sendMessage(new TextComponent(" "));
							sender.sendMessage(new TextComponent(" "));
							sender.sendMessage(new TextComponent(LineNetwork.PREFIX));
							sender.sendMessage(new TextComponent(ChatColor.GOLD + " Recapitulatif de l'action :"));
							sender.sendMessage(new TextComponent(ChatColor.BLUE + "    Vous avez envoie le joueur sur le serveur : " + serverInfo.getName()));
							sender.sendMessage(new TextComponent(ChatColor.GREEN + "    La raison est : " + sb.toString()));
							sender.sendMessage(new TextComponent(ChatColor.YELLOW + "    Vous avez envoie le joueur : " + target.getName()));
							sender.sendMessage(new TextComponent(" "));
						}
						sender.sendMessage(new TextComponent(" "));
						sender.sendMessage(new TextComponent(LineNetwork.PREFIX));
						sender.sendMessage(new TextComponent(ChatColor.GOLD + " Recapitulatif de l'action :"));
						sender.sendMessage(new TextComponent(ChatColor.BLUE + "    Vous avez essai d'envoie le joueur sur le serveur : " + serverInfo.getName()));
						sender.sendMessage(new TextComponent(ChatColor.GREEN + "    La raison etait : " + sb.toString()));
						sender.sendMessage(new TextComponent(ChatColor.YELLOW + "    Vous avez essai d'envoie le joueur : " + target.getName()));
						sender.sendMessage(new TextComponent(ChatColor.DARK_RED + "    L'action a echoue, car le serveur est offline !"));
						sender.sendMessage(new TextComponent(" "));
					} else {
						if (serverInfo.canAccess(target)) {
							target.connect(serverInfo);
							target.sendMessage(new TextComponent(" "));
							target.sendMessage(new TextComponent(LineNetwork.PREFIX));
							target.sendMessage(new TextComponent(ChatColor.GOLD + " Recapitulatif de l'action :"));
							target.sendMessage(new TextComponent(ChatColor.BLUE + "    Vous venez d'etre envoie sur le serveur : " + serverInfo.getName()));
							target.sendMessage(new TextComponent(ChatColor.YELLOW + "    Le joueur qui vous a envoie : " + sender.getName()));
							target.sendMessage(new TextComponent(" "));
							sender.sendMessage(new TextComponent(" "));
							sender.sendMessage(new TextComponent(LineNetwork.PREFIX));
							sender.sendMessage(new TextComponent(ChatColor.GOLD + " Recapitulatif de l'action :"));
							sender.sendMessage(new TextComponent(ChatColor.BLUE + "    Vous avez envoie le joueur sur le serveur : " + serverInfo.getName()));
							sender.sendMessage(new TextComponent(ChatColor.YELLOW + "    Vous avez envoie le joueur : " + target.getName()));
							sender.sendMessage(new TextComponent(" "));
						}
						sender.sendMessage(new TextComponent(" "));
						sender.sendMessage(new TextComponent(LineNetwork.PREFIX));
						sender.sendMessage(new TextComponent(ChatColor.GOLD + " Recapitulatif de l'action :"));
						sender.sendMessage(new TextComponent(ChatColor.BLUE + "    Vous avez essai d'envoie le joueur sur le serveur : " + serverInfo.getName()));
						sender.sendMessage(new TextComponent(ChatColor.YELLOW + "    Vous avez essai d'envoie le joueur : " + target.getName()));
						sender.sendMessage(new TextComponent(ChatColor.DARK_RED + "    L'action a echoue, car le serveur est offline !"));
						sender.sendMessage(new TextComponent(" "));
					}
				} else {
					sender.sendMessage(new TextComponent(LineNetwork.PREFIX + "Le serveur " + args[1] + " est introuvable !"));
					sender.sendMessage(new TextComponent(LineNetwork.PREFIX + "  La liste des serveur est :"));
					for (String serveur : serveurName) {
						sender.sendMessage(new TextComponent(LineNetwork.PREFIX + "    " + serveur));
					}
				}
			} else {
				sender.sendMessage(new TextComponent(LineNetwork.PREFIX + "Le joueur " + args[0] + " est hors-ligne !"));
			}
				
			
		}
		
	}

}
