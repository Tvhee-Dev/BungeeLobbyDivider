package me.tvhee.bungeelobbydivider;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class BungeeLobbyDividerPlugin extends Plugin
{
	private File configFile;
	private Configuration config;

	@Override
	public void onEnable()
	{
		this.configFile = new File(getDataFolder(), "config.yml");

		saveDefaultConfig();

		getProxy().getPluginManager().registerCommand(this, new BungeeLobbyDividerCommand(this));
		getProxy().getPluginManager().registerListener(this, new ServerListener(this));

		getLogger().info("has been enabled!");
		getLogger().info("made by Tvhee for MinetraxNetwork");
	}

	@Override
	public void onDisable()
	{
		getLogger().info("has been disabled!");
		getLogger().info("made by Tvhee for MinetraxNetwork");
	}

	public void findServerAndConnect(ProxiedPlayer player)
	{
		List<String> servers = getConfig().getStringList("plugin.lobbyservers");
		List<ServerInfo> bungeeServers = new ArrayList<>();

		for(String server : servers)
		{
			ServerInfo bungeeServer = ProxyServer.getInstance().getServerInfo(server);

			if(bungeeServer != null)
				bungeeServers.add(bungeeServer);
		}

		if(bungeeServers.isEmpty())
		{
			if(player != null)
			{
				player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&6---------------------------------------------------")));
				player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&6BungeeLobbyDivider &cCould not find a lobby server!")));
				player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&6---------------------------------------------------")));
			}
			return;
		}

		ServerInfo server = bungeeServers.get(new Random().nextInt(bungeeServers.size()));

		try
		{
			player.connect(server);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return;
		}

		ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&6----------------------------------------------------")));
		ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&6BungeeLobbyDivider &e" + player.getName() + " &ahas been sent to &e" + server.getName() + "&a!")));
		ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&6----------------------------------------------------")));
	}

	public Configuration getConfig()
	{
		return config;
	}

	public void saveDefaultConfig()
	{
		if(!configFile.exists())
		{
			configFile.getParentFile().mkdirs();
			InputStream is = getResourceAsStream("config.yml");

			try
			{
				Files.copy(is, configFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void saveConfig()
	{
		try
		{
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, configFile);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void reloadConfig()
	{
		try
		{
			config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
