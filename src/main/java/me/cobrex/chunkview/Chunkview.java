package me.cobrex.chunkview;

import me.cobrex.chunkview.command.ChunkviewCommand;
import me.cobrex.chunkview.command.ChunkviewParticleCommand;
import me.cobrex.chunkview.listener.PlayerListener;
import me.cobrex.chunkview.utilities.Log;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Chunkview extends JavaPlugin implements Listener {

	public static Chunkview instance;

	public static ArrayList<Player> viewers = new ArrayList<>();

	public static ArrayList<Location> viewerslocs = new ArrayList<>();

	@Override
	public void onEnable() {

		instance = this;
		getConfig().options().copyDefaults();
		saveDefaultConfig();

		Log.log("Enabling Chunkview by Cobrex");

		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(PlayerListener.getInstance(), this);

		getCommand("cv").setExecutor(new ChunkviewCommand());
		getCommand("cvp").setExecutor(new ChunkviewParticleCommand());
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("chunkview")) {
			if (sender instanceof Player) {
				if (!sender.hasPermission("chunkview.reload")) {
					sender.sendMessage((ChatColor.RED + "You don't have permission to use this command"));
					return true;
				}
				if (args.length == 0) {
					sender.sendMessage(ChatColor.RED + "Usage: /chunkview reload");
					return true;
				}
				if (args.length > 0) {
					if (args[0].equalsIgnoreCase("reload")) {
						sender.sendMessage((ChatColor.RED + "Configuration file reloaded"));
						this.reloadConfig();
						this.saveDefaultConfig();
					} else {
						sender.sendMessage(ChatColor.RED + "Usage: /chunkview reload");
						return true;
					}
				}
			} else {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.RED + "Usage: /chunkview reload");
					return true;
				}
				if (args.length > 0) {
					if (args[0].equalsIgnoreCase("reload")) {
						sender.sendMessage((ChatColor.RED + "Plugin reloaded"));
						this.reloadConfig();
						this.saveDefaultConfig();
					} else {
						sender.sendMessage(ChatColor.RED + "Usage: /chunkview reload");
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (command.getName().equalsIgnoreCase("chunkview")) {
			if (sender.hasPermission("chunkview.reload")) {
				return Arrays.asList("reload");
			}
		}
		return null;
	}

	public static Chunkview getInstance() {
		return instance;
	}

	@Override
	public void onDisable() {

	}
}
