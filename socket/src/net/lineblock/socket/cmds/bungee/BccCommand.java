package net.lineblock.socket.cmds.bungee;

import net.lineblock.socket.LineSocketSpigot;
import net.lineblock.socket.other.ServerData;
import net.lineblock.socket.other.packets.PreparedPacket;
import net.lineblock.socket.other.packets.preparedpackets.BroadcastPacket;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class BccCommand extends Command {

	public BccCommand() {
		super("bcc", "lineblock.socket.bcc");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(args.length >= 3) {
			try {
				ServerData data = ServerData.getByName(args[0]);
				if(data.getName().equalsIgnoreCase(args[0])) {
					StringBuilder sb = new StringBuilder();
					for (int i = 1; i < args.length; i++)
						sb.append(args[i]+" ");
					new Thread(new Runnable() {
						@Override
						public void run() {
							LineSocketSpigot.getClient().write(data, new PreparedPacket(new BroadcastPacket(sb.toString(), sender)));
						}
					}).start();
				}
			}catch(NullPointerException e) {
				sender.sendMessage(new TextComponent("§4Le serveur demandé est introuvable ! §6/serverlist §9pour plus d'aide"));
			}
		}
	}

}
