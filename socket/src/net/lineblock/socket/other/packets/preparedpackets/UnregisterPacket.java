package net.lineblock.socket.other.packets.preparedpackets;

import net.lineblock.json.JSONObject;
import net.lineblock.socket.other.ServerData;
import net.lineblock.socket.other.packets.Packet;
import net.lineblock.socket.other.packets.PacketTypes;

public class UnregisterPacket extends Packet {

	public UnregisterPacket(ServerData data) {
		super(PacketTypes.UNREGISTER, new JSONObject("{\"id\":\""+data.getId()+"\"}"));
	}

}
