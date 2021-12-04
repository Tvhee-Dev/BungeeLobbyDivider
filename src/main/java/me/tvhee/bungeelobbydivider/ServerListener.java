package me.tvhee.bungeelobbydivider;

import com.jakub.premium.api.event.UserEvent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerListener implements Listener
{
	private final BungeeLobbyDividerPlugin plugin;

	public ServerListener(BungeeLobbyDividerPlugin plugin)
	{
		this.plugin = plugin;
	}

	@EventHandler
	public void onServerLogin(UserEvent.Login loginEvent)
	{
		ProxiedPlayer player = loginEvent.getUserProfile().getProxiedPlayer();
		plugin.findServerAndConnect(player);
	}
}
