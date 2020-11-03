package net.lineblock.socket.other.listeners;

import net.lineblock.socket.other.packets.Packet;

public class ReceivedPacketEvent extends PacketEvent {

	private Packet p;
	
	public ReceivedPacketEvent(Packet p) {
		this.p = p;
	}
	
	public Packet getPacket() {
		return p;
	}

}
