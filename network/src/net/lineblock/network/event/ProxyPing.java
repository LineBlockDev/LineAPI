package net.lineblock.network.event;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyPing implements Listener {

	public static final String MOTD_UP = "          &e&b&k|&b&k|&b&k|&r&4 Line&fBlock&6(1.8-1.16)&b&k|&b&k|&b&k|&r&4 &r &a\n&fSurvie &4&linedit &f| &9Emotes &ftout &4&lfrais &f| &5Mini-jeux &f| &d...";
	  
	@EventHandler
	public void onPing(ProxyPingEvent event) {
		ServerPing ping = event.getResponse();
	    ServerPing.Players players = ping.getPlayers();
	    ping.setDescriptionComponent((BaseComponent)new TextComponent(ChatColor.translateAlternateColorCodes('&', MOTD_UP)));
	    ServerPing.Protocol prot = new ServerPing.Protocol(ChatColor.translateAlternateColorCodes('&', "&4Acces Limite&8- [&7" + players.getOnline() + "&8/&7" + players.getMax() + "&8]"), ping.getVersion().getProtocol() - 1);
	    ping.setVersion(prot);
	    event.setResponse(ping);
	}
	
}