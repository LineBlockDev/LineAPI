package net.lineblock.socket.other.listeners;

public class MainPacketListener implements PacketListener {

	@Override
	public void onPacketSended(SendedPacketEvent p) {
		
	}

	@Override
	public void onPacketReceived(ReceivedPacketEvent p) {
		/*LineSocket.println("§6"+p.getPacket().getObj().toString());*/
	}

}
