package net.lineblock.socket.other.packets;

import java.util.HashMap;

public enum PacketTypes {

	REGISTER("REGISTER"),
	UNREGISTER("UNREGISTER"),
	BROADCAST("BROADCAST"),
	BAN("BAN"),
	MUTE("MUTE"),
	KICK("KICK"),
	SEND("SEND"),
	BANLOG("BANLOG"),
	MUTELOG("MUTELOG"),
	KICKLOG("KICKLOG"),
	UPDATE_SERVER_DATA("UPDATESERVERDATA"),
	BOT_SEND_MESSAGE("BOTSENDMESSAGE");
	
	private static HashMap<String, PacketTypes> SP;
	
	private String name;
	
	static {
		SP = new HashMap<>();
		for(PacketTypes pt : values())
			SP.put(pt.getName(), pt);
	}
	
	private PacketTypes(String _name) {
		name = _name;
	}
	
	public String getName() {return name;}
	
	public static PacketTypes getForName(String n) {return SP.get(n);}
	
}
