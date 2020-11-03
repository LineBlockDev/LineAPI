package net.lineblock.socket.other.listeners;

import net.lineblock.socket.other.packets.Packet;

public class SendedPacketEvent extends PacketEvent {

	private Packet p;
	
	public SendedPacketEvent(Packet p) {
		this.p = p;
	}
	
	public Packet getPacket() {
		return p;
	}

}
