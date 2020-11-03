package net.lineblock.socket.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.lineblock.json.JSONException;
import net.lineblock.json.JSONObject;
import net.lineblock.socket.exceptions.ServerDataAlreadyExistsException;
import net.lineblock.socket.server.SocketNetwork;

public class ServerData {
	
	public static final HashMap<Integer, ServerData> ServerPort = new HashMap<>();
	public static final HashMap<Integer, ServerData> SocketPort = new HashMap<>();
	public static final HashMap<String, ServerData> Name = new HashMap<>();
	public static final HashMap<String, ServerData> Id = new HashMap<>();
	
	public static final List<ServerData> allServers = new ArrayList<ServerData>();
	public static final List<ServerData> withoutBungee = new ArrayList<ServerData>();
	
	public static ServerData BungeeData;
	public static ServerData BotData;
	
	static {
		try {
			BungeeData = new ServerData(SocketNetwork.BUNGEE_SERVER_PORT, SocketNetwork.BUNGEE_SOCKET_PORT, "Bungee", "BungeeCord-01", "").add();
			BotData = new ServerData(SocketNetwork.BUNGEE_SERVER_PORT, 2999, "Bot", "DiscordBot-01", "").add();
			withoutBungee.remove(BungeeData);
			withoutBungee.remove(BotData);
		} catch (ServerDataAlreadyExistsException e) {
			e.printStackTrace();
		}
	}
	
	private int serverPort = 25566;
	private int socketPort = 3001;
	private String name = "Server-01";
	private String id = "Server-01-01";
	private String jsonItem = "";
	
	public ServerData(int _seP, int _soP, String _name, String _id, String _jsonItem) {
		serverPort = _seP;
		socketPort = _soP;
		name = _name;
		id = _id;
		jsonItem = _jsonItem;
	}
	
	public ServerData add() throws ServerDataAlreadyExistsException {
		try {
			if(ServerPort.containsKey(serverPort))
				throw new ServerDataAlreadyExistsException(this);
			if(SocketPort.containsKey(socketPort))
				throw new ServerDataAlreadyExistsException(this);
			if(Name.containsKey(name))
				throw new ServerDataAlreadyExistsException(this);
			if(Id.containsKey(id))
				throw new ServerDataAlreadyExistsException(this);
		}catch(ExceptionInInitializerError e) {}
		
		allServers.add(this);
		withoutBungee.add(this);
		ServerPort.put(serverPort, this);
		SocketPort.put(socketPort, this);
		Name.put(name, this);
		Id.put(id, this);
		
		return this;
	}
	
	public int getServerPort() {return serverPort;}
	public int getSocketPort() {return socketPort;}
	public String getName() {return name;}
	public String getId() {return id;}
	public String getJsonItem() {return jsonItem;}
	
	public static ServerData getByServerPort(int serverP) {return ServerPort.get(serverP);}
	public static ServerData getBySocketPort(int socketP) {return SocketPort.get(socketP);}
	public static ServerData getByName(String nam) {return Name.get(nam);}
	public static ServerData getById(String i) {return Id.get(i);}
	
	public static List<ServerData> getAllServers() {return allServers;}
	
	public static ServerData getBungeeData() {return BungeeData;}
	
	public static void removeServer(ServerData data) {
		try {ServerPort.remove(data.getServerPort());}catch(NullPointerException e) {}
		try {SocketPort.remove(data.getSocketPort());}catch(NullPointerException e) {}
		try {Name.remove(data.getName());}catch(NullPointerException e) {}
		try {Id.remove(data.getId());}catch(NullPointerException e) {}
		try {allServers.remove(data);}catch(NullPointerException e) {}
		try {withoutBungee.remove(data);}catch(NullPointerException e) {}
	}
	
	@Override
	public String toString() {
		return new JSONObject("{\"name\":\""+name+"\",\"id\":\""+id+"\",\"serverPort\":"+serverPort+",\"socketPort\":"+socketPort+",\"item\":"+(jsonItem=="" ? "\"\"" : jsonItem)+"}").toString();
	}

	@SuppressWarnings("unchecked")
	public static List<ServerData> fromArrayToList(List<Object> list) {
		List<ServerData> sds = new ArrayList<>();
		for(Object obj : list) {
			JSONObject jobj = new JSONObject((HashMap<String,Object>) obj);
			String item = "";
			try {
				item = jobj.getJSONObject("item").toString();
			}catch(JSONException e) {}
			sds.add(
					new ServerData(
							jobj.getInt("serverPort"),
							jobj.getInt("socketPort"),
							jobj.getString("name"),
							jobj.getString("id"),
							item
					)
			);
		}
		return sds;
	}

}
