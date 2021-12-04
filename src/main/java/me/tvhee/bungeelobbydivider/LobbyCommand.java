package me.tvhee.bungeelobbydivider;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class LobbyCommand extends Command
{
	private final BungeeLobbyDividerPlugin plugin;

	public LobbyCommand(BungeeLobbyDividerPlugin plugin)
	{
		super("lobby", "", "hub");
		this.plugin = plugin;
	}

	@Override
	public void execute(CommandSender sender, String[] args)
	{
		if(sender instanceof ProxiedPlayer)
		{
			ProxiedPlayer player = (ProxiedPlayer) sender;
			plugin.findServerAndConnect(player);
		}
	}
}
