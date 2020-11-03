package net.lineblock.data.command;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.UUID;

import net.lineblock.json.JSONException;
import net.lineblock.json.JSONObject;
import net.lineblock.json.JsonUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.lineblock.data.LineData;
import net.lineblock.data.data.DataManager;
import net.lineblock.data.data.table.ModerationTable;
import net.lineblock.data.player.LinePlayer;
import net.lineblock.data.player.PlayerData;
import net.lineblock.data.player.Rank;
import net.lineblock.data.player.RankSet;

public class SetRankCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, final String[] args) {
		if (cmd.getName().equalsIgnoreCase("setrank")) {
			if (args.length != 2) {
				sender.sendMessage(LineData.getInstance().getPREFIX() + "La commande est /setrank <joueur> <grade>");
				sender.sendMessage(ChatColor.DARK_GREEN + "Les grade disponible sont :");
				for (int i = 0; i < Rank.getRankCount() + 1; i++) {
					sender.sendMessage("  - " + ChatColor.DARK_PURPLE + Rank.getFromLevel(i).getName() + " -> " + Rank.getFromLevel(i).getLevel());
				}
				
			} else {
				try {
					if (Integer.valueOf(args[1]) != null) {
						if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))) {
							LineData.getOnlinePlayer(Bukkit.getPlayer(args[0]).getUniqueId()).setRank(Rank.getFromLevel(Integer.valueOf(args[1])));
							RankSet.setRank(Bukkit.getPlayer(args[0]), Rank.getFromLevel(Integer.valueOf(args[1])));
							sender.sendMessage(LineData.getInstance().getPREFIX() + "Resume de votre action :");
							sender.sendMessage(ChatColor.DARK_RED + "    Vous avez mis le grade : " + Rank.getFromLevel(Integer.valueOf(args[1])).getName());
							sender.sendMessage(ChatColor.GOLD + "    Le joueur affecte est : " + Bukkit.getPlayer(args[0]).getName());
							sender.sendMessage(ChatColor.GOLD + "    Son UUID est : " + Bukkit.getPlayer(args[0]).getUniqueId());
							sender.sendMessage(ChatColor.GOLD + "    Son nom actuel est : " + Bukkit.getPlayer(args[0]).getCustomName());
							Bukkit.getPlayer(args[0]).sendMessage(LineData.getInstance().getPREFIX() + sender.getName() + " a mis change votre garde. Votre nouveau grade est : " + Rank.getFromLevel(Integer.valueOf(args[1])));
							return true;
						} else {
							try {
								final ModerationTable table = DataManager.getInstance().getLineBase().moderationTable();
								ResultSet rs = table.querySQL("SELECT `uuid` FROM `"+table.table+"` WHERE `name`='"+args[0]+"';");
								if(rs.first()) {
									final PlayerData pd = new LinePlayer(UUID.fromString(rs.getString("uuid"))).getData();
									final Rank rank = Rank.getFromLevel(Integer.valueOf(args[1]));
									new Thread(new Runnable() {
										@Override
										public void run() {
											try {
												Thread.sleep(1000);
											} catch (InterruptedException e) {}
											pd.setName(args[0]);
											pd.setRank(rank);
											table.saveData(pd);
										}
									}).start();
									
									sender.sendMessage(LineData.getInstance().getPREFIX() + "Resume de votre action :");
									sender.sendMessage(ChatColor.DARK_RED + "    Vous avez mis le grade : " + Rank.getFromLevel(Integer.valueOf(args[1])).getName());
									sender.sendMessage(ChatColor.GOLD + "    Le joueur affect� est : " + args[0]);
									sender.sendMessage(ChatColor.GOLD + "    Son UUID est : " + UUID.fromString(rs.getString("uuid")));
									sender.sendMessage(ChatColor.GOLD + "    Son nom actuel est : " + rank.getBold() + rank.getBukkitColor() + "[" + rank.getPrefix() + "] " + args[0]);
									
									return true;
								}else {
									sender.sendMessage(LineData.getInstance().getPREFIX() + "Le joueur "+args[0]+" ne s'est jamais connect� !");
									return false;
								}
							}catch(Exception e) {
								sender.sendMessage(LineData.getInstance().getPREFIX() + "Le joueur "+args[0]+" pas en ligne !");
								sender.sendMessage(LineData.getInstance().getPREFIX() + "La commande est /setrank <joueur> <grade>");
								sender.sendMessage("Les grades disponibles sont :");
								for (int i = 0; i < Rank.getRankCount() + 1; i++) {
									sender.sendMessage("  - " + Rank.getFromLevel(i).getName() + " -> " + Rank.getFromLevel(i).getLevel());
								}
								e.printStackTrace();
								return false;
							}
						}
						
					} else {
						sender.sendMessage(LineData.getInstance().getPREFIX() + "La commande est /setrank <joueur> <grade>");
						sender.sendMessage("Les grade disponible sont :");
						for (int i = 0; i < Rank.getRankCount() + 1; i++) {
							sender.sendMessage("  - " + ChatColor.DARK_PURPLE + Rank.getFromLevel(i).getName() + " -> " + Rank.getFromLevel(i).getLevel());
						}
						return false;
					}
				}catch(NumberFormatException e) {
					sender.sendMessage(LineData.getInstance().getPREFIX() + "La commande est /setrank <joueur> <grade>");
					sender.sendMessage("Les grade disponible sont :");
					for (int i = 0; i < Rank.getRankCount() + 1; i++) {
						sender.sendMessage("  - " + ChatColor.DARK_PURPLE + Rank.getFromLevel(i).getName() + " -> " + Rank.getFromLevel(i).getLevel());
					}
					return false;
				}
				
			}
			
		}
		return false;
	}
	
	@SuppressWarnings("unused")
	private String getUniqueIdForPlayer(String p) {
		try {
			JSONObject jsonObj = JsonUtils.readJsonFromUrl("https://api.mojang.com/users/profiles/minecraft/" + p);
			System.out.println(jsonObj);
			return jsonObj.getString("id");
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
