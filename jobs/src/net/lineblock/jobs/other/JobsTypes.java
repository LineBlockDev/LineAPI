package net.lineblock.jobs.other;

import java.util.HashMap;

public enum JobsTypes {

	MINER("Mineur"),
	FARMER("Agriculteur"),
	LUMBERJACK("Bucheron"),
	WIZARD("Magicien"),
	HUNTER("Chasseur"),
	FISHER("Pêcheur"),
	BREEDER("Eleveur"),
	LIBRARIAN("Bibliothécaire");
	
	private String name;
	
	private static HashMap<String, JobsTypes> SJ;
	static {
		SJ = new HashMap<>();
		for(JobsTypes jt : values())
			SJ.put(jt.getName().toLowerCase(), jt);
	}
	
	private JobsTypes(String _name) {
		name = _name;
	}
	
	public String getName() {return name;}
	
	public static JobsTypes getByName(String s) {return SJ.get(s.toLowerCase());}
	
}
