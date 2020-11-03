package net.lineblock.socket.other.packets.preparedpackets;

import net.lineblock.json.JSONObject;
import net.lineblock.socket.other.EmbedMessage;
import net.lineblock.socket.other.ServerData;
import net.lineblock.socket.other.packets.Packet;
import net.lineblock.socket.other.packets.PacketTypes;

public class BotSendMessagePacket extends Packet {

	public BotSendMessagePacket(ServerData data, net.md_5.bungee.api.CommandSender sender, String channelId, String message) {
		super(PacketTypes.BOT_SEND_MESSAGE, 
				new JSONObject(
						"\"name\":"+sender.getName()+","+
						"\"channelId\":\""+channelId+"\","+
						"\"server\":"+
							"{\"name\":\""+data.getName()+"\","+
							"\"id\":\""+data.getId()+"\"},"+
						"\"message\":\""+message+"\"}"
				)
		);
	}

	public BotSendMessagePacket(ServerData data, net.md_5.bungee.api.CommandSender sender, String channelId, EmbedMessage ebm) {
		super(PacketTypes.BOT_SEND_MESSAGE, 
				new JSONObject(
						"\"name\":"+sender.getName()+","+
						"\"channelId\":\""+channelId+"\","+
						"\"server\":"+
							"{\"name\":\""+data.getName()+"\","+
							"\"id\":\""+data.getId()+"\"},"+
						"\"embed\":\""+ebm.toString()+"\"}"
				)
		);
	}

}
