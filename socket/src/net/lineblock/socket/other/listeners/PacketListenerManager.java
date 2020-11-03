package net.lineblock.socket.other.listeners;

import java.util.ArrayList;

public final class PacketListenerManager {

	private static final ArrayList<PacketListener> pl = new ArrayList<>();
	
	public static final void register(PacketListener p) {
		pl.add(p);
	}
	
	public static ArrayList<PacketListener> getPl() {
		return pl;
	}
	
	public static final void call(PacketEvent e) {
		for(PacketListener p : pl) {
			if(e instanceof ReceivedPacketEvent) {
				p.onPacketReceived((ReceivedPacketEvent) e); 
			}else if(e instanceof SendedPacketEvent) {
				p.onPacketSended((SendedPacketEvent) e);
			}
		}
	}
	
}
