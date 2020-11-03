package net.lineblock.socket.other.listeners;

public interface PacketListener {

	void onPacketSended(SendedPacketEvent p);
	void onPacketReceived(ReceivedPacketEvent p);

}
