package me.cobrex.chunkview.command;

import me.cobrex.chunkview.Chunkview;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class ChunkviewCommand implements CommandExecutor, TabCompleter {

	private Plugin instance;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player to send this command!");

			return true;
		}

		if (args.length == 0) {
			sender.sendMessage((ChatColor.RED + "Usage: /chunkview"));

			return true;
		}

		Player player = (Player) sender;
		if (!sender.hasPermission("chunkview.view")) {//|| sender.isOp()) { //|| !this.plugin.getConfig().getBoolean("requiresop")) {
			sender.sendMessage((ChatColor.RED + "You don't have permission to use this command"));

			return true;
		} else {

//			new BukkitRunnable() {
//				@Override
//				public void run() {

			Chunkview.viewers.add(player);
			Chunkview.viewerslocs.add(player.getLocation());
			Chunk chunk = player.getLocation().getChunk();
			Location corner1;
			chunk.getBlock(0, 0, 0).getLocation();
			Location corner2;
			chunk.getBlock(15, 0, 0).getLocation();
			Location corner3;
			chunk.getBlock(0, 0, 15).getLocation();
			Location corner4;
			chunk.getBlock(15, 0, 15).getLocation();
			int i = player.getLocation().getBlockY();
			int i2 = 0;
			for (i2 = 0; i2 < 15; i2++) {
				corner1 = chunk.getBlock(i2, i, 0).getLocation();
				corner2 = chunk.getBlock(15, i, i2).getLocation();
				corner3 = chunk.getBlock(15 - i2, i, 15).getLocation();
				corner4 = chunk.getBlock(0, i, 15 - i2).getLocation();
				if (corner1.getBlock().getType() == Material.AIR)
					player.sendBlockChange(corner1, Material.SEA_LANTERN, (byte) 0);
				if (corner2.getBlock().getType() == Material.AIR)
					player.sendBlockChange(corner2, Material.SEA_LANTERN, (byte) 0);
				if (corner3.getBlock().getType() == Material.AIR)
					player.sendBlockChange(corner3, Material.SEA_LANTERN, (byte) 0);
				if (corner4.getBlock().getType() == Material.AIR)
					player.sendBlockChange(corner4, Material.SEA_LANTERN, (byte) 0);

			}

//				}

//			}.runTaskAsynchronously(Chunkview.instance);
		}
		sender.sendMessage(ChatColor.GOLD + "Boarder blocks now showing. Toggle sneak to remove");
		return true;

	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		return null;
	}
}
