package net.lineblock.data.player;
import java.util.ArrayList;
public class PlayerList {
	
	private ArrayList<LinePlayer> playerList;
	
	public PlayerList() {playerList = new ArrayList<LinePlayer>();}
	
	public void add(LinePlayer p) {playerList.add(p);}
	
	public void remove(LinePlayer p) {playerList.remove(p);}
	
	public boolean contains(String p) {ArrayList<String> strList = new ArrayList<String>();for(LinePlayer pl : playerList) {strList.add(pl.getName());}return strList.contains(p);}
	
	public boolean contains(LinePlayer p) {return playerList.contains(p);}
	
	public int size() {return playerList.size();}
	
	public LinePlayer get(int i) {return playerList.get(i);}
	
	public LinePlayer get(String i) {LinePlayer p = null;for(LinePlayer pl : playerList) {if(pl.getName().equals(i)) {p = pl;return p;}}return p;}
	
	@Deprecated public ArrayList<LinePlayer> getRawList() {return playerList;}
	
}
