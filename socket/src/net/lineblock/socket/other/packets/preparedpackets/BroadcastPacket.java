package net.lineblock.socket.other.packets.preparedpackets;

import net.lineblock.json.JSONObject;
import net.lineblock.socket.other.packets.Packet;
import net.lineblock.socket.other.packets.PacketTypes;

public class BroadcastPacket extends Packet {

	public BroadcastPacket(String message, net.md_5.bungee.api.CommandSender sender) {
		super(PacketTypes.BROADCAST, new JSONObject("{\"message\":\""+message+"\", \"name\":\""+sender.getName()+"\"}"));
	}
	
}
