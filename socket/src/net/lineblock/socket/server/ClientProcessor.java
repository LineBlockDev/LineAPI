package net.lineblock.socket.server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ConcurrentModificationException;
import java.util.List;

import org.bukkit.Bukkit;

import net.lineblock.json.JSONException;
import net.lineblock.json.JSONObject;
import net.lineblock.socket.LineSocket;
import net.lineblock.socket.LineSocketBungee;
import net.lineblock.socket.exceptions.ServerDataAlreadyExistsException;
import net.lineblock.socket.exceptions.UnknownPacketException;
import net.lineblock.socket.other.ServerData;
import net.lineblock.socket.other.listeners.PacketListenerManager;
import net.lineblock.socket.other.listeners.ReceivedPacketEvent;
import net.lineblock.socket.other.packets.Packet;
import net.lineblock.socket.other.packets.PreparedPacket;
import net.lineblock.socket.other.packets.preparedpackets.UpdateServerDataPacket;
import net.md_5.bungee.api.chat.TextComponent;

public class ClientProcessor extends Thread {

	@SuppressWarnings("deprecation")
	public ClientProcessor(Socket client) {
		try {
			PrintWriter writer = new PrintWriter(client.getOutputStream());
			BufferedInputStream reader = new BufferedInputStream(client.getInputStream());
			
			String msg = read(reader);
			String toSend = "{}";
			
			Packet packet = Packet.decode(msg);
			JSONObject obj = packet.getObj();
			
			PacketListenerManager.call(new ReceivedPacketEvent(packet));
			
			switch (packet.getType()) {
			case BAN:
				break;
			case BANLOG:
				break;
			case KICK:
				break;
			case KICKLOG:
				break;
			case MUTE:
				break;
			case MUTELOG:
				break;
			case SEND:
				break;
			case BOT_SEND_MESSAGE:
				break;
			case UPDATE_SERVER_DATA:
				List<ServerData> list = ServerData.fromArrayToList(packet.getObj().getJSONArray("datas").toList());
				String allId = "";
				for(ServerData data : list)
					allId += data.getId()+", ";
				LineSocket.println("§6Updating servers: "+allId);
				try {
					for(ServerData data : ServerData.withoutBungee) {
						ServerData.removeServer(data);
					}
				}catch(ConcurrentModificationException e) {}
				for(ServerData data : list) {
					if(!data.getName().equals("Bungee")) {
						data.add();
					}
				}
				break;
				
			case BROADCAST:
				LineSocket.println("§6Sending server broadcast from §b"+obj.getString("name")+" §6with content: §b"+obj.getString("message").replaceAll("\n", "[/n]"));
				if(!LineSocket.isBungee()) {
					try {
						Bukkit.getServer().broadcastMessage(
								org.bukkit.ChatColor.translateAlternateColorCodes('&', "<-=====&4ANNONCE&r=====->\n"+obj.getString("message"))
						);
						toSend = "{\"message\": \"Broadcast successfully sended: "+obj.getString("message")+"\"}";
					}catch(Exception e) {
						e.printStackTrace();
						toSend = "{\"message\": \""+e.getClass().getCanonicalName()+"\"}";
					}
				}else {
					try {
						LineSocketBungee.getInstance().getProxy().broadcast(new TextComponent(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', "<-=====&4ANNONCE&r=====->\n"+obj.getString("message"))));
						toSend = "{\"message\": \"Broadcast successfully sended: "+obj.getString("message")+"\"}";
					}catch(Exception e) {
						e.printStackTrace();
						toSend = "{\"message\": \""+e.getClass().getCanonicalName()+"\"}";
					}
				}
				break;
				
			case REGISTER:
				LineSocket.println("§6Register server: "+obj.getString("id"));
				try {
					new ServerData(obj.getInt("serverPort"), obj.getInt("socketPort"), obj.getString("name"), obj.getString("id"), obj.getJSONObject("item").toString()).add();
					toSend = "{\"message\": \"Server correctly registered\"}";
				} catch (ServerDataAlreadyExistsException e) {
					e.printStackTrace();
					toSend = "{\"message\": \""+e.getClass().getCanonicalName()+"\"}";
				} catch(JSONException e) {
					e.printStackTrace();
					toSend = "{\"message\": \""+e.getClass().getCanonicalName()+"\"}";
				}
				if(LineSocket.isBungee())
					for(ServerData data : ServerData.withoutBungee)
						if(data.getSocketPort() != client.getPort() || data.getSocketPort() != client.getPort())
							LineSocketBungee.getServer().write(data, new PreparedPacket(new UpdateServerDataPacket(ServerData.getAllServers())));
				break;
				
			case UNREGISTER:
				LineSocket.println("§6Unregister server: "+obj.getString("id"));
				try {
					ServerData.removeServer(ServerData.getById(obj.getString("id")));
					toSend = "{\"message\": \"Server correctly unregistered\"}";
					if(LineSocket.isBungee())
						for(ServerData data : ServerData.withoutBungee)
							if(data.getSocketPort() != client.getPort() || data.getSocketPort() != client.getPort())
								LineSocketBungee.getServer().write(data, new PreparedPacket(new UpdateServerDataPacket(ServerData.getAllServers())));
				}catch(Exception e) {
					e.printStackTrace();
					toSend = "{\"message\": \""+e.getClass().getCanonicalName()+"\"}";
				}
				break;
				
			default:
				toSend = "{\"message\": \""+UnknownPacketException.class.getCanonicalName()+"\"}";
				break;
			}
			
			writer.write(toSend);
			writer.flush();
			
			writer.close();
			
			client.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		super.stop();
	}
	
	public static String read(BufferedInputStream reader) throws IOException {      
	      String response = "";
	      int stream;
	      byte[] b = new byte[4096];
	      stream = reader.read(b);
	      response = new String(b, 0, stream);
	      return response;
	}

}
