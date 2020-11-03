package net.lineblock.socket.exceptions;

import java.util.ArrayList;

public final class ExceptionsManager {

	private static final ArrayList<Class<? extends Exception>> Registered = new ArrayList<>();
	
	public static final void register(Class<? extends Exception> clss) {
		Registered.add(clss);
	}
	
	public static final Class<? extends Exception> get(String str) throws ExceptionNotFoundException {
		for(Class<? extends Exception> s : Registered)
			if(s.getCanonicalName().equals(str))
				return s;
		throw new ExceptionNotFoundException();
	}

}
