package me.cobrex.chunkview.command;

import me.cobrex.chunkview.Chunkview;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ChunkviewParticleCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player to send this command!");

			return true;
		}

		Player player = (Player) sender;
		Plugin plugin = Chunkview.getPlugin(Chunkview.class);
		if (!sender.hasPermission("chunkviewparticle.view")) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("lang.messages.reload_permission")));

			return true;
		} else {

			new BukkitRunnable() {
				int count = 0;

				@Override
				public void run() {
					if (count >= 5) {
						cancel();
					}
					count++;

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
					for (i = player.getLocation().getBlockY(); i < (player.getLocation().getBlockY() + 10); i++) {
						int i2 = 0;
						for (i2 = 0; i2 < 15; i2++) {
							corner1 = chunk.getBlock(i2, i, 0).getLocation().add(1, 0, 0);
							corner2 = chunk.getBlock(15, i, i2).getLocation().add(1, 0, 1);
							corner3 = chunk.getBlock(15 - i2, i, 15).getLocation().add(0, 0, 1);
							corner4 = chunk.getBlock(0, i, 15 - i2).getLocation();
							if (corner1.getBlock().getType() == Material.AIR)
								player.spawnParticle(Particle.valueOf(plugin.getConfig().getString("particle.type")), corner1, 1);
							if (corner2.getBlock().getType() == Material.AIR)
								player.spawnParticle(Particle.valueOf(plugin.getConfig().getString("particle.type")), corner2, 1);
							if (corner3.getBlock().getType() == Material.AIR)
								player.spawnParticle(Particle.valueOf(plugin.getConfig().getString("particle.type")), corner3, 1);
							if (corner4.getBlock().getType() == Material.AIR)
								player.spawnParticle(Particle.valueOf(plugin.getConfig().getString("particle.type")), corner4, 1);
						}
					}
				}
			}.runTaskTimer(Chunkview.instance, 0L, 60L);
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("lang.messages.particles")));
			return true;
		}
	}
}


