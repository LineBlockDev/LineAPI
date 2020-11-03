package net.lineblock.socket.exceptions;

public class UnknownPacketException extends Exception {

	private static final long serialVersionUID = 1L;
	
	static {
		ExceptionsManager.register(UnknownPacketException.class);
	}

	public UnknownPacketException() {
		
	}

}
