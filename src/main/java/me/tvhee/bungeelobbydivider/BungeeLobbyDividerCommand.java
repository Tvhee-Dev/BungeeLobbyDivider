package me.tvhee.bungeelobbydivider;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class BungeeLobbyDividerCommand extends Command
{
	private final BungeeLobbyDividerPlugin plugin;

	public BungeeLobbyDividerCommand(BungeeLobbyDividerPlugin plugin)
	{
		super("bungeelobbydividerreload", "bld.command.reload", "bldreload", "bldr");
		this.plugin = plugin;
	}

	@Override
	public void execute(CommandSender sender, String[] args)
	{
		plugin.reloadConfig();

		sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&6----------------------------------------------------")));
		sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&6BungeeLobbyDivider &ahas been reloaded successfully!")));
		sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&6----------------------------------------------------")));
	}
}
