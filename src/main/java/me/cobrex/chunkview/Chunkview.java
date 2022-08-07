package me.cobrex.chunkview;

import me.cobrex.chunkview.command.ChunkviewCommand;
import me.cobrex.chunkview.listener.PlayerListener;
import me.cobrex.chunkview.utilities.Log;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Chunkview extends JavaPlugin implements Listener {

	public static Chunkview instance;

	public static ArrayList<Player> viewers = new ArrayList<>();

	public static ArrayList<Location> viewerslocs = new ArrayList<>();

	@Override
	public void onEnable() {

		instance = this;

		Log.log("Enabling Chunkview by Cobrex");

		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(PlayerListener.getInstance(), this);

		getCommand("chunkview").setExecutor(new ChunkviewCommand());

//		getConfig().options().copyDefaults(true);
//		saveConfig();

	}

	public static Chunkview getInstance() {
		return instance;
	}

	@Override
	public void onDisable() {
		
	}
}
