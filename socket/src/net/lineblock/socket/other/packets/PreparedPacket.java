package net.lineblock.socket.other.packets;

public class PreparedPacket {

	private String uncrypted;
	private String crypted;
	
	public PreparedPacket(Packet packet) {
		uncrypted = packet.toString();
		crypted = uncrypted;
	}
	
	public String getCrypted() {
		return crypted;
	}
	public String getUncrypted() {
		return uncrypted;
	}

}
