package net.lineblock.socket;

import java.io.IOException;
import java.net.ServerSocket;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import net.lineblock.json.JSONObject;
import net.lineblock.socket.exceptions.ServerDataAlreadyExistsException;
import net.lineblock.socket.other.ItemBuilder;
import net.lineblock.socket.other.ServerData;
import net.lineblock.socket.other.packets.PreparedPacket;
import net.lineblock.socket.other.packets.preparedpackets.RegisterPacket;
import net.lineblock.socket.other.packets.preparedpackets.UnregisterPacket;
import net.lineblock.socket.server.SocketNetwork;

public class LineSocketSpigot extends JavaPlugin {

	private static LineSocketSpigot instance;
	private static ServerData data;
	
	private static SocketNetwork client;
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		instance = this;
		
		initAndRegister();
	}
	
	@Override
	public void onDisable() {
		try {
			new Thread(new Runnable() {
				@Override
				public void run() {
					client.write(ServerData.getBungeeData(), new PreparedPacket(new UnregisterPacket(data)));
				}
			}).start();
			client.getSk().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static LineSocketSpigot getInstance() {return instance;}
	
	private void initAndRegister() {
		int nextP = getConfig().getInt("socket.port");
		getConfig().set("socket.item", new ItemBuilder(Material.ACACIA_DOOR_ITEM).build());
		try {
			LineSocket.println("§6Creating data...");
			data = new ServerData(
					getServer().getPort(),
					nextP,
					getConfig().getString("socket.name"),
					getConfig().getString("socket.id"),
					new ItemBuilder(getConfig().getItemStack("socket.item")).toJson()
			).add();
			LineSocket.println("§6Data created");
		} catch (ServerDataAlreadyExistsException e1) {
			e1.printStackTrace();
		}
		try {
			LineSocket.println("§6Create socketNetwork...");
			client = new SocketNetwork(nextP, client);
			LineSocket.println("§6SocketNetwork created");
		} catch (IOException e) {
			e.printStackTrace();
		}
		LineSocket.println("§6Sending register packet...");
		new Thread(new Runnable() {
			@Override
			public void run() {
				String msg = client.write(
						ServerData.getBungeeData(),
						new PreparedPacket(new RegisterPacket(data))
				);
				LineSocket.println("§6Register packet sended: §b"+new JSONObject(msg).getString("message"));
			}
		}).start();
	}
	
	@SuppressWarnings("unused")
	private int getNextPort() {
		int nextPort = 3001;
		boolean b = true;
		while(b) {
			ServerSocket ss = null;
			try {
				ss = new ServerSocket(nextPort);
			}catch(Exception e) {
				nextPort++;
			}
			try {ss.close();} catch (Exception e) {}
			if(nextPort == 3050)
				b = false;
		}
		return nextPort;
	}
	
	public static SocketNetwork getClient() {return client;}
	public static ServerData getData() {return data;}

}
