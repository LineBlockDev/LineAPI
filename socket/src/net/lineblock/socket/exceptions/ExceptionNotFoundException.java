package net.lineblock.socket.exceptions;

public class ExceptionNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	static {
		ExceptionsManager.register(ExceptionNotFoundException.class);
	}
	
}
