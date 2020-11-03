package net.lineblock.socket;

import java.io.IOException;

import net.lineblock.socket.cmds.bungee.BccCommand;
import net.lineblock.socket.cmds.bungee.DmCommand;
import net.lineblock.socket.cmds.bungee.RegisterServerListCommand;
import net.lineblock.socket.other.listeners.MainPacketListener;
import net.lineblock.socket.other.listeners.PacketListenerManager;
import net.lineblock.socket.server.SocketNetwork;
import net.md_5.bungee.api.plugin.Plugin;

public class LineSocketBungee extends Plugin {

	private static LineSocketBungee instance;
	
	private static SocketNetwork server;
	
	@Override
	public void onEnable() {
		instance = this;
		try {
			LineSocket.println("§6Starting bungee socket...");
			server = new SocketNetwork(SocketNetwork.BUNGEE_SOCKET_PORT, server);
			LineSocket.bungee = true;
			PacketListenerManager.register(new MainPacketListener());
			LineSocket.println("§6Bungee socket started");
		} catch (IOException e) {
			e.printStackTrace();
			LineSocket.println("§4Unable to start bungee socket");
		}
		
		getProxy().getPluginManager().registerCommand(this, new RegisterServerListCommand());
		getProxy().getPluginManager().registerCommand(this, new BccCommand());
		getProxy().getPluginManager().registerCommand(this, new DmCommand());
		
	}
	
	@Override
	public void onDisable() {
		try {
			server.getSk().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static LineSocketBungee getInstance() {return instance;}
	public static SocketNetwork getServer() {return server;}
	
}
