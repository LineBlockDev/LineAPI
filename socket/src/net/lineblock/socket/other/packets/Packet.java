package net.lineblock.socket.other.packets;

import net.lineblock.json.JSONObject;

public class Packet {

	private JSONObject obj;
	private PacketTypes type;
	
	public Packet(PacketTypes _type, JSONObject _obj) {
		obj = _obj;
		type = _type;
	}
	
	public static Packet decode(String str) {
		String[] st = str.split("/:/");
		PacketTypes type = PacketTypes.getForName(st[0]);
		JSONObject obj = new JSONObject(st[1]);
		return new Packet(
				type,
				obj
		);
	}
	
	public JSONObject getObj() {
		return obj;
	}
	public PacketTypes getType() {
		return type;
	}
	@Override
	public String toString() {
		return type.getName()+"/:/"+obj.toString();
	}

}
