package me.cobrex.chunkview.utilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Log {

	private static final ConsoleCommandSender console = Bukkit.getConsoleSender();

	public static void log(String message) {
		console.sendMessage("[Chunkview]" + ChatColor.translateAlternateColorCodes('&', message));
	}
	
	public static void tell(Player player, String message) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
}
