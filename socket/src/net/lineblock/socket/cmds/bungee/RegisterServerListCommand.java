package net.lineblock.socket.cmds.bungee;

import net.lineblock.socket.other.ServerData;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class RegisterServerListCommand extends Command {

	public RegisterServerListCommand() {
		super("serverlist", "lineblock.socket.rsl", "registeredservers");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		StringBuilder sb = new StringBuilder();
		for(ServerData data : ServerData.getAllServers()) {
			sb.append(" §1- §6Nom: §9"+data.getName()+", §6Id: §9"+data.getId()+", §6Server port: §9"+data.getServerPort()+", §6Socket port: §9"+data.getSocketPort()+"\n");
		}
		sender.sendMessage(new TextComponent("<-=====&4SERVEURS&r=====->\n"+sb.toString()));
	}

}
