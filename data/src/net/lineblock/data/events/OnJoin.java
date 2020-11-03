package net.lineblock.data.events;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import net.lineblock.data.LineData;
import net.lineblock.data.player.LinePlayer;
import net.lineblock.data.player.Rank;
import net.lineblock.data.player.RankSet;
import net.lineblock.data.report.ReportLevel;
import net.lineblock.data.report.ReportManager;
import net.lineblock.data.tablist.TabList;

public class OnJoin implements Listener {

	public static final ArrayList<LinePlayer> verify = new ArrayList<>();
	public static final ArrayList<Player> RawVerify = new ArrayList<>();
	
	public OnJoin() {
		@SuppressWarnings("unused")
		BukkitTask autoKick = new BukkitRunnable() {
			@Override
			public void run() {
				try {
					for(LinePlayer p : verify) {
						if (p.getRank().lowerThan(Rank.STAFF)) {
							p.getPlayer().kickPlayer(LineData.getInstance().getPREFIX() + "\n\nLe grade minimum requis est: " + Rank.STAFF.getBukkitColor() + Rank.STAFF.getName());
							p.saveData();
						}else {
							RankSet.setRank(p.getPlayer(), p.getRank());
							if (LineData.isDebug())
								LineData.getInstance().println("Le joueur " + p.getName() + " est desormais en ligne !");
						}
						verify.remove(p);
						RawVerify.remove(p.getPlayer());
						for (Player p1 : Bukkit.getOnlinePlayers()) {
							TabList.sendPacket(p1);
						}
					}
				}catch(ConcurrentModificationException e) {
				}catch(Exception e) {
					e.printStackTrace();
					ReportManager.register(e, ReportLevel.ALERT);
				}
			}
		}.runTaskTimer(LineData.getInstance(), 0, 10);
	}
	
	@EventHandler
	public static void onJoin(PlayerJoinEvent event) {
		RawVerify.add(event.getPlayer());
		new BukkitRunnable() {
			@Override
			public void run() {
				LinePlayer p = new LinePlayer(event.getPlayer().getUniqueId());
				LineData.addOnlinePlayer(p);
				p.getData().setName(event.getPlayer().getName());
				verify.add(p);
			}
		}.runTaskLater(LineData.getInstance(), 25);
		event.setJoinMessage(null);
		
	}
	
}
