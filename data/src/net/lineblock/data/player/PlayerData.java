package net.lineblock.data.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.craftbukkit.libs.jline.internal.InputStreamReader;

import net.lineblock.data.data.DataManager;
import net.lineblock.json.JSONObject;
import net.lineblock.json.JsonUtils;

public class PlayerData {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 5950169519311563575L;
	private static Map<String, Integer> expectedVersion = new HashMap<>();
	public static HashMap<String, PlayerData> onlineData = new HashMap<>();
	private LinePlayer player;
	public static void increaseExpectedVersion(PlayerData data) {
		String name = data.getName();
		int version = data.version + 1;
	    if (expectedVersion.containsKey(name))
	    	expectedVersion.remove(name); 
	    expectedVersion.put(name, Integer.valueOf(version));
	}
	  public static void resetExpectedVersion(PlayerData data) {
	    expectedVersion.remove(data.getName());
	  }
	  public static int getExpectedVersion(String name) {
	    if (!expectedVersion.containsKey(name))
	      return 0; 
	    return ((Integer)expectedVersion.get(name)).intValue();
	  }
	  private String uuid = "00000000000000000000000000000000";
	  private String name;
	  private boolean premium = false;
	  private String lastIP = "0.0.0.0";
	  private boolean bannedIp = false;
	  private String passHash = "/";
	  private Rank rank = Rank.PLAYER;
	  private long money = 0L;
	  private int kicks = 0;
	  private long muteUntil = System.currentTimeMillis();
	  private long banUntil = System.currentTimeMillis();
	  private int banDuration = 0;
	  private String punishmentReasonCurrent = "/";
	  private String punishmentReason = "/";
	  private int banCount = 0;
	  private int muteCount = 0;
	  private int muteDuration = 0;
	  private String muteReason = "/";
	  private String muteCurrentReason = "/";
	  private long lastUpdated = System.currentTimeMillis();
	  private long firstCreated = System.currentTimeMillis();
	  private String particle = "/";
	  private String currentParticle = ";";
	  private String emotes = ";";
	  public PlayerData instance;
	  public int version = 0;
	  private boolean banned = false;
	  //private int selectedKit = -1;
	  //private Map<LineGames, Map<Integer, Integer>> kits = new HashMap<>();
	  public PlayerData(UUID uuid) {
	    this.uuid = uuid.toString();
	    loadData();
	  }
	  public void setName(String name) {
	    this.name = name;
	  }
	  public void saveData() {
	    DataManager.getInstance().getLineBase().moderationTable().saveData(this);
	  }
	  public void setPlayer(LinePlayer player) {
	    this.player = player;
	  }
	  public LinePlayer getPlayer() {
	    return this.player;
	  }
	  public void loadData() {
	    DataManager.getInstance().getLineBase().moderationTable().fetchData(this);
	  }
	  public String getName() {
	    return this.name;
	  }
	  public long getMoney() {
	    return this.money;
	  }
	  public void setMoney(long l) {
	    this.money = l;
	  }
	  public void giveMoney(int money) {
	    this.money += money;
	  }
	  public void withdrawMoney(int money) {
	    this.money -= money;
	  }
	  public Rank getRank() {
	    return this.rank;
	  }
	  public void setRank(Rank rank) {
	    this.rank = rank;
	  }
	  public long getLastUpdated() {
	    return this.lastUpdated;
	  }
	  public void setLastUpdated(long lastUpdated) {
	    this.lastUpdated = lastUpdated;
	  }
	  public Date getLastUpdatedDate() {
	    return calendarToDate(this.lastUpdated);
	  }
	  public long getFirstCreated() {
	    return this.firstCreated;
	  }
	  public void setFirstCreated(long firstCreated) {
	    this.firstCreated = firstCreated;
	  }
	  public Date getFirstCreatedDate() {
	    return calendarToDate(this.firstCreated);
	  }
	  public void setLastIP(String lastIP) {
	    this.lastIP = lastIP;
	  }
	  public String getLastIP() {
	    return this.lastIP;
	  }
	  public void setBannedIp(boolean bool) {
		  this.bannedIp = bool;
	  }
	  public boolean isBannedIp() {
		  return bannedIp;
	  }
	  public void setPassHash(String passHash) {
	    this.passHash = passHash;
	  }
	  public String getPassHash() {
	    return this.passHash;
	  }
	  public void setKicks(int kicks) {
	    this.kicks = kicks;
	  }
	  public int getKicks() {
	    return this.kicks;
	  }
	  public void setMuteUntil(long muteUntil) {
	    this.muteUntil = muteUntil;
	  }
	  public long getMuteUntil() {
	    return this.muteUntil;
	  }
	  public Date getMuteUntilDate() {
	    return calendarToDate(this.muteUntil);
	  }
	  public void setBanUntil(long banUntil) {
	    this.banUntil = banUntil;
	  }
	  public long getBanUntil() {
	    return this.banUntil;
	  }
	  public void setBanDuration(int duration) {
		  this.banDuration = duration;
	  }
	  public int getBanDuration() {
		  return banDuration;
	  }
	  public Date getBanUntilDate() {
	    return calendarToDate(this.banUntil);
	  }
	  public long getBanUntilAsLong() {
		  return this.banUntil;
	  }
	  public void setBanCount(int count) {
		  this.banCount = count;
	  }
	  public int getBanCount() {
		  return banCount;
	  }
	  public void setMuteCount(int count) {
		  this.muteCount = count;
	  }
	  public int getMuteCount() {
		  return muteCount;
	  }
	  public void setMuteDuration(int duration) {
		  this.muteDuration = duration;
	  }
	  public int getMuteDuration() {
		  return this.muteDuration;
	  }
	  public void setMuteCurrentReason(String reason) {
		  this.muteCurrentReason = reason;
	  }
	  public String getMuteCurrentReason() {
		  return this.muteCurrentReason;
	  }
	  public void setMuteReason(String reason) {
		  this.muteReason = reason;
	  }
	  public String getMuteReason() {
		  return this.muteReason;
	  }
	  public void setpunishmentReasonCurrent(String punishmentReasonCurrent) {
	    this.punishmentReasonCurrent = punishmentReasonCurrent;
	  }
	  public String getpunishmentReasonCurrent() {
	    return this.punishmentReasonCurrent;
	  }
	  public void setpunishmentReason(String punishmentReason) {
	    this.punishmentReason = punishmentReason;
	  }
	  public String getpunishmentReason() {
	    return this.punishmentReason;
	  }
	  public String getUuid() {
	    return this.uuid;
	  }
	  public void setPremium(boolean premium) {
	    this.premium = premium;
	  }
	  public boolean isPremium() {
	    return this.premium;
	  }
	  @SuppressWarnings("resource")
	  public boolean isPremiumName() {
	    try {
	      URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + this.name);
	      BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
	      StringBuilder result = new StringBuilder();
	      String line;
	      while ((line = in.readLine()) != null)
	        result.append(line); 
	      return !result.toString().equals("");
	    } catch (IOException ex) {
	      ex.printStackTrace();
	      return false;
	    } 
	  }
	  public String getMojangUUID() {
	    try {
	      JSONObject jsonObj = JsonUtils.readJsonFromUrl("https://api.mojang.com/users/profiles/minecraft/" + this.name);
	      return jsonObj.getString("id");
	    } catch (IOException ex) {
	      ex.printStackTrace();
	      return "00000000000000000000000000000000";
	    } 
	  }
	  private Date calendarToDate(long time) {
		  Date resultdate = new Date(time);
		  return resultdate;
	  }
	  public void setParticle(String array) {
	    this.particle = array;
	  }
	  public String getParticle() {
	    return this.particle;
	  }
	  public void setCurrentCosmetique(String currentParticle) {
	    this.currentParticle = currentParticle;
	  }
	  public String getCurrentCosmetique() {
	    return this.currentParticle;
	  }
	  public void setEmotes(String array) {
	    this.emotes = array;
	  }
	  public String getEmotes() {
	    return this.emotes;
	  }
	  public void merge(PlayerData data) {
	    if (!this.name.equals(data.name))
	      throw new IllegalArgumentException("Tried to merge different player datas : " + this.name + " =/= " + data.name); 
	    this.instance = this;
	    this.premium = data.premium;
	    this.lastIP = data.lastIP;
	    this.passHash = data.passHash;
	    this.rank = data.rank;
	    this.money = data.money;
	    this.kicks = data.kicks;
	    this.muteUntil = data.muteUntil;
	    this.banUntil = data.banUntil;
	    this.punishmentReasonCurrent = data.punishmentReasonCurrent;
	    this.punishmentReason = data.punishmentReason;
	    this.lastUpdated = data.lastUpdated;
	    this.firstCreated = data.firstCreated;
	    this.version = data.version;
	  }
	public boolean isBanned() {
		return banned;
	}
	
}
