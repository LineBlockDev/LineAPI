package net.lineblock.socket.cmds.bungee;

import net.lineblock.socket.LineSocketSpigot;
import net.lineblock.socket.other.ServerData;
import net.lineblock.socket.other.packets.PreparedPacket;
import net.lineblock.socket.other.packets.preparedpackets.BotSendMessagePacket;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class DmCommand extends Command {

	public DmCommand() {
		super("dm", "lineblock.socket.dm", "discordmessage");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(args.length >= 2) {
			String channelId = args[0];
			StringBuilder msg = new StringBuilder();
			for (int i = 1; i < args.length; i++)
				msg.append(args[i]+" ");
			new Thread(new Runnable() {
				@Override
				public void run() {
					LineSocketSpigot.getClient().write(ServerData.BotData, new PreparedPacket(new BotSendMessagePacket(LineSocketSpigot.getData(), sender, channelId, msg.toString())));
				}
			}).start();
		}
	}

}
