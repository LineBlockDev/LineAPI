package net.lineblock.chat;

import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

import net.lineblock.chat.chats.Chat;
import net.lineblock.chat.listeners.PlayerEventsListener;

public class LineChat extends JavaPlugin {

	private static LineChat instance;
	private static HashMap<String, Chat> chat = new HashMap<String, Chat>();
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		instance = this;
		
		getServer().getPluginManager().registerEvents(new PlayerEventsListener(), this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static LineChat getInstance() {return instance;}
	public static HashMap<String, Chat> getChat() {return chat;}
}
