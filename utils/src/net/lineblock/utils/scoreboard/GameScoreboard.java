package net.lineblock.utils.scoreboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import net.lineblock.data.player.LinePlayer;
import net.lineblock.data.report.ReportLevel;
import net.lineblock.data.report.ReportManager;

public class GameScoreboard {
  private static int ID = 0;
  
  private static final List<ChatColor> colors = Arrays.asList(ChatColor.values());
  
  private final Scoreboard scoreboard;
  
  private final Objective objective;
  
  private final List<BoardLine> boardLines = new ArrayList<>();
  
  public GameScoreboard(String title) {
    this(title, "b" + ID);
  }
  
  public GameScoreboard(String title, String objName) {
    int idSize = String.valueOf(ID).length();
    if (objName.length() > 16 - idSize)
      objName = objName.substring(0, 16 - idSize); 
    objName = objName + ID;
    this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    if (this.scoreboard.getObjective(objName) == null) {
      this.objective = this.scoreboard.registerNewObjective(objName, "dummy");
    } else {
      this.objective = this.scoreboard.getObjective(objName);
    } 
    try {
      this.objective.setDisplayName(title);
    } catch (IllegalArgumentException ex) {
      ReportManager.register(ex, ReportLevel.INFO);
    } 
    this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    for (int i = 0; i < colors.size(); i++) {
      ChatColor color = colors.get(i);
      Team team = this.scoreboard.registerNewTeam("b" + ID + "l" + i);
      team.addEntry(color.toString());
      this.boardLines.add(new BoardLine(color, i, team));
    } 
    ID++;
  }
  
  public void showScoreboard(Player player) {
    player.setScoreboard(this.getScoreboard());
  }
  
  public void hideScoreboard(LinePlayer player) {
    player.removeScoreboard();
  }
  
  public void setTitle(String title) {
    this.objective.setDisplayName(title);
  }
  
  public void setLine(int line, String value) {
    setLine(line, value, false);
  }
  
  public void setLine(int line, String value, boolean trim) {
    if (!validate(line, value))
      return; 
    value = (trim == true) ? value.trim() : value;
    BoardLine boardLine = getBoardLine(line);
    Validate.notNull(boardLine, "Unable to find scoreboard line with index of " + line + ".");
    this.objective.getScore(boardLine.getColor().toString()).setScore(line);
    int mid = halfSplit(value);
    String prefix = value.substring(0, mid);
    String suffix = ChatColor.getLastColors(prefix) + value.substring(mid);
    boardLine.getTeam().setPrefix(prefix);
    boardLine.getTeam().setSuffix(suffix);
  }
  
  public void removeLine(int line) {
    if (!validate(line, ""))
      return; 
    BoardLine boardLine = getBoardLine(line);
    Validate.notNull(boardLine, "Unable to find scoreboard line with index of " + line + ".");
    this.scoreboard.resetScores(boardLine.getColor().toString());
  }
  
  public Scoreboard getScoreboard() {
    return this.scoreboard;
  }
  
  private BoardLine getBoardLine(int line) {
    return this.boardLines.stream().filter(boardLine -> (boardLine.getLine() == line)).findFirst().orElse(null);
  }
  
  private boolean validate(int line, String value) {
    if (Math.abs(line) > 15) {
      ReportManager.register(new IndexOutOfBoundsException("The received line number is higher than maximum allowed (" + line + " > 15)"), ReportLevel.INFO);
      return false;
    } 
    if (value.length() > 32) {
      ReportManager.register(new IndexOutOfBoundsException("The received string length is longer than maximum allowed (" + value.length() + " > 32)"), ReportLevel.INFO);
      return false;
    } 
    return true;
  }
  
  public class BoardLine {
    private final ChatColor color;
    
    private final int line;
    
    private final Team team;
    
    public BoardLine(ChatColor color, int line, Team team) {
      this.color = color;
      this.line = line;
      this.team = team;
    }
    
    public ChatColor getColor() {
      return this.color;
    }
    
    public int getLine() {
      return this.line;
    }
    
    public Team getTeam() {
      return this.team;
    }
  }
  
  private static int halfSplit(String value) {
    int mid = value.length() / 2 + 1;
    String prefix = value.substring(0, mid);
    if (prefix.split("")[prefix.length() - 1].equals("à¸ȼà¸‡"))
      return mid + 1; 
    return mid;
  }
}

