package net.lineblock.socket.exceptions;

import net.lineblock.socket.other.ServerData;

public class ServerDataAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	static {
		ExceptionsManager.register(ServerDataAlreadyExistsException.class);
	}
	
	private ServerData data;
	
	public ServerData getData() {return data;}
	
	public ServerDataAlreadyExistsException(ServerData data) {
		super(
				"ServerData already exists: "
				+ "\nServerPort: "+data.getServerPort()
				+ "\nSocketPort: "+data.getSocketPort()
				+ "\nName: "+data.getName()
				+ "\nId: "+data.getId()
				+ "\nJsonItem: "+data.getJsonItem()
		);
	}

}
