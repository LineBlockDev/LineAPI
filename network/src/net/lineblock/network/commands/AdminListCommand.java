package net.lineblock.network.commands;

import java.util.ArrayList;

import net.lineblock.network.LineNetwork;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class AdminListCommand extends Command {

	public AdminListCommand() {
		super("adminlist", "line.network.admin.list", "al", "adminl", "alist", "aliste", "adminliste");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length == 1) {
			ArrayList<String> serveurName = new ArrayList<String>();
			for (String serveur : LineNetwork.getInstance().getProxy().getServers().keySet())
				serveurName.add(serveur);
			if (serveurName.contains(args[0])) {
				
			} else {
				if (serveurName.contains(args[0])) {
					ServerInfo serveurInfo = LineNetwork.getInstance().getProxy().getServers().get(args[0]);
					ArrayList<ProxiedPlayer> players = (ArrayList<ProxiedPlayer>) serveurInfo.getPlayers();
					sender.sendMessage(new TextComponent(" "));
					sender.sendMessage(new TextComponent(LineNetwork.PREFIX + "Les joueurs en ligne sur le serveur " + args[0] + " : S"));
					for (int i = 0; i < players.size(); i++) {
						sender.sendMessage(new TextComponent("  " + i + " : " + players.get(i)));
					}
				} else {
					sender.sendMessage(new TextComponent(LineNetwork.PREFIX + "Le serveur est inexistant !"));
					sender.sendMessage(new TextComponent("  Les serveur disponible sont : "));
					for (int i = 0; i < serveurName.size(); i++)
						sender.sendMessage(new TextComponent("    " + serveurName.get(i)));
				}
				
			}
		} else {
			sender.sendMessage(new TextComponent(LineNetwork.PREFIX + "La command est : /adminlist <serveur>"));
		}
			
	}

}