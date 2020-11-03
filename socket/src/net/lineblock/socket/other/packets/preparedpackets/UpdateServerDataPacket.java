package net.lineblock.socket.other.packets.preparedpackets;

import java.util.List;

import net.lineblock.json.JSONObject;
import net.lineblock.socket.other.ServerData;
import net.lineblock.socket.other.packets.Packet;
import net.lineblock.socket.other.packets.PacketTypes;

public class UpdateServerDataPacket extends Packet {

	public UpdateServerDataPacket(List<ServerData> datas) {
		super(
				PacketTypes.UPDATE_SERVER_DATA,
				new JSONObject("{\"datas\":["+build(datas)+"]}")
		);
	}
	
	public static String build(List<ServerData> datas) {
		String strServs = "";
		for (int i = 0; i < datas.size(); i++) {
			ServerData data = datas.get(i);
			strServs += data.toString();
			if(i != datas.size()-1)
				strServs += ",";
		}
		return strServs;
	}

}
