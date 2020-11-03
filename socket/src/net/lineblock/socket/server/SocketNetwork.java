package net.lineblock.socket.server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import net.lineblock.socket.other.ServerData;
import net.lineblock.socket.other.packets.PreparedPacket;

public class SocketNetwork  {

	public static final int BUNGEE_SOCKET_PORT = 3000;
	public static final int BUNGEE_SERVER_PORT = 25565;
	
	private ServerSocket sk;
	
	private int port = 3001;
	private boolean BUNGEE_SIDE = false;
	
	public SocketNetwork(int port, SocketNetwork nt) throws IOException {
		this.port = port;
		if(port == 3000)
			BUNGEE_SIDE = true;
		sk = new ServerSocket(port, 100);
		nt = this;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(!sk.isClosed())
					read();
			}
		}).start();
	}
	
	public void read() {
		try {
			Socket client = sk.accept();
			new ClientProcessor(client).start();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String write(ServerData data, PreparedPacket pp) {
		try {
			Socket socket = new Socket("127.0.0.1", data.getSocketPort());
			socket.getOutputStream().write(pp.getCrypted().getBytes());
			socket.getOutputStream().flush();
			String ret = ClientProcessor.read(new BufferedInputStream(socket.getInputStream()));
			socket.close();
			return ret;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void close() throws IOException {sk.close();}
	public int getPort() {return port;}
	public boolean isBUNGEE_SIDE() {return BUNGEE_SIDE;}
	public ServerSocket getSk() {return sk;}

}
