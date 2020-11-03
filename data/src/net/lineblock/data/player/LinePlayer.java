package net.lineblock.data.player;

import java.util.Date;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.lineblock.data.LineData;
import net.lineblock.data.data.DataManager;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.InheritanceNode;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R2.PacketPlayOutEntityEquipment;

public class LinePlayer {

	private Player player;
	private PlayerData data;
	boolean damageable;
	boolean canBreakBlocks;
	boolean canPlaceBlocks;
	private UUID uuid;
	   
	public LinePlayer(UUID uuid) {
		this.damageable = true;
		this.canBreakBlocks = true;
		this.canPlaceBlocks = true;
		this.uuid = uuid;
	    this.data = new PlayerData(uuid);
	    this.data.setPlayer(this);
	    loadData();
	}
	public Player getPlayer() {this.player = Bukkit.getPlayer(uuid); return this.player;}
	public PlayerData getData() {this.player = Bukkit.getPlayer(uuid); return this.data;}
	public UUID getUUID() {this.player = Bukkit.getPlayer(uuid); return this.uuid;}
	public void saveData() {DataManager.getInstance().getLineBase().moderationTable().saveData(this.data);}	
	public void loadData() {DataManager.getInstance().getLineBase().moderationTable().fetchData(this.data);}
	public void setMoney(long money) {this.player = Bukkit.getPlayer(uuid); this.data.setMoney(money);}
	public void giveMoney(int money) {this.player = Bukkit.getPlayer(uuid); this.data.giveMoney(money);}
	public void withdrawMoney(int money) {this.player = Bukkit.getPlayer(uuid); this.data.withdrawMoney(money);}
	public void setRank(Rank rank) {this.player = Bukkit.getPlayer(uuid); this.data.setRank(rank);}
	public long getMoney() {this.player = Bukkit.getPlayer(uuid); return this.data.getMoney();}
	public Rank getRank() {this.player = Bukkit.getPlayer(uuid); return this.data.getRank();}
	public boolean hasRank(Rank rank) {this.player = Bukkit.getPlayer(uuid); return this.data.getRank().higherOrEqualTo(rank);}
	public Date getLastConnection() {this.player = Bukkit.getPlayer(uuid); return this.data.getLastUpdatedDate();}
	public boolean hasEnabledMentions() {this.player = Bukkit.getPlayer(uuid); return true;}
	public void removeScoreboard() {this.player = Bukkit.getPlayer(uuid); this.player.setScoreboard(null);}
	  
	public void setGameMode(GameMode mode) {
		this.player = Bukkit.getPlayer(uuid);
	  this.player.setGameMode(mode);
	}
  
	public GameMode getGameMode() {
		this.player = Bukkit.getPlayer(uuid);
	  return this.player.getGameMode();
	}
  
  public PlayerInventory getInventory() {
	  this.player = Bukkit.getPlayer(uuid);
	  return this.player.getInventory();
  }
  
  public Location getLocation() {
	  this.player = Bukkit.getPlayer(uuid);
	  return this.player.getLocation();
  }
  
  public void teleport(Location location) {
	  this.player = Bukkit.getPlayer(uuid);
	  this.player.teleport(location);
  }
  
  public String getName() {
	  this.player = Bukkit.getPlayer(uuid);
	  return this.player.getName();
  }
  
  public String getCustomName() {
	  this.player = Bukkit.getPlayer(uuid);
	  return this.player.getCustomName();
  }
  public Location getEyeLocation() {
	  this.player = Bukkit.getPlayer(uuid);
	  return this.player.getEyeLocation();
  }
  public World getWorld() {
	  this.player = Bukkit.getPlayer(uuid);
	  return this.player.getWorld();
  }
  public void setItem(int slot, ItemStack item) {
	  this.player = Bukkit.getPlayer(uuid);
	  this.player.getInventory().setItem(slot, item);
  }
  public void setLevel(int level) {
	  this.player = Bukkit.getPlayer(uuid);
	  this.player.setLevel(level);
  }
  public void setExp(float exp) {
	  this.player = Bukkit.getPlayer(uuid);
	  this.player.setExp(exp);
  }
  public void sendToServer(String server) {
	  this.player = Bukkit.getPlayer(uuid);
    player.sendMessage("&eRedirection vers &6" + server.toString() + "...");
	ByteArrayDataOutput out = ByteStreams.newDataOutput();
	out.writeUTF("Connect");
	out.writeUTF(server);
	this.player.sendPluginMessage((Plugin)LineData.getInstance(), "BungeeCord", out.toByteArray());
  }
  public void freeze(boolean bool, LinePlayer p, String reason) {
	  this.player = Bukkit.getPlayer(uuid);
	  if (bool) {
		  player.sendMessage(LineData.getInstance().getPREFIX() + "Vous venez d'etre freeze pour " + ChatColor.BOLD + ChatColor.GOLD + reason);
		  LineData.addFreezedPlayer(p);
	  } else {
		  LineData.removeFreezedPlayer(p);
		  player.sendMessage(LineData.getInstance().getPREFIX() + "Votre sanction de freeze vient d'etre levé !");
	  }
	  
  }
  public void setDamageable(boolean damageable) {
	  this.player = Bukkit.getPlayer(uuid);
	  this.damageable = damageable;
  }
  public boolean isDamageable() {
	  this.player = Bukkit.getPlayer(uuid);
	  return this.damageable;
  }
  public void setCanBreakBlocks(boolean canBreakBlocks) {
	  this.player = Bukkit.getPlayer(uuid);
	  this.canBreakBlocks = canBreakBlocks;
  }
  public boolean canBreakBlocks() {
	  this.player = Bukkit.getPlayer(uuid);
	  return this.canBreakBlocks;
  }
  public void setCanPlaceBlocks(boolean canPlaceBlocks) {
	  this.player = Bukkit.getPlayer(uuid);
	  this.canPlaceBlocks = canPlaceBlocks;
  }
  public boolean canPlaceBlocks() {
	  this.player = Bukkit.getPlayer(uuid);
	  return this.canPlaceBlocks;
  }
  public void setListName(String prefix) {this.player = Bukkit.getPlayer(uuid);player.setPlayerListName(prefix);}
  public void setDisplayName(String display) {this.player = Bukkit.getPlayer(uuid);player.setDisplayName(display);}
  public void setCustomName(String custom) {this.player = Bukkit.getPlayer(uuid);player.setCustomName(custom);}
  
  public void sendMessage(String m) {
	  this.player = Bukkit.getPlayer(uuid);
	  getPlayer().sendMessage(m);
  }
  
  public void setGroup(Rank rank) {
	  this.player = Bukkit.getPlayer(uuid);
	  LuckPerms luckPerms = Bukkit.getServicesManager().getRegistration(LuckPerms.class).getProvider();
	  User user = luckPerms.getUserManager().getUser(player.getUniqueId());
	  InheritanceNode r = InheritanceNode.builder("group." + rank.getPermission()).build();
	  user.data().add(r);
	  luckPerms.getUserManager().saveUser(user);
  }
	  
}
