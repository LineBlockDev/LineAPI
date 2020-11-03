package net.lineblock.socket.other.packets.preparedpackets;

import net.lineblock.json.JSONObject;
import net.lineblock.socket.other.ServerData;
import net.lineblock.socket.other.packets.Packet;
import net.lineblock.socket.other.packets.PacketTypes;

public class RegisterPacket extends Packet {

	public RegisterPacket(ServerData data) {
		super(
				PacketTypes.REGISTER,
				new JSONObject(
						"{\"name\":\""+data.getName()+"\","
						+ "\"socketPort\":"+data.getSocketPort()+","
						+ "\"serverPort\":"+data.getServerPort()+","
						+ "\"id\":\""+data.getId()+"\","
						+ "\"item\":"+data.getJsonItem()+"}"
				)
				);
	}

}
