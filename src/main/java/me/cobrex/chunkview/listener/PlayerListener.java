package me.cobrex.chunkview.listener;

import lombok.Getter;
import me.cobrex.chunkview.Chunkview;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class PlayerListener implements Listener {
	private Chunkview plugin;

	@Getter
	private static final PlayerListener instance = new PlayerListener();

	public PlayerListener() {
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		System.out.println("Joined player: " + event.getPlayer().getName());

	}

	@EventHandler
	public void onToggle(PlayerToggleSneakEvent event) {

		Player player = event.getPlayer();
		if (Chunkview.viewers.contains(player)) {
			Location loc = player.getLocation();
			World world = loc.getWorld();
			int index = Chunkview.viewers.indexOf(player);
			Location prevloc = Chunkview.viewerslocs.get(index);
			Chunk chunk = prevloc.getChunk();
			if (loc.getX() != ((Location) Chunkview.viewerslocs.get(index)).getX() || loc.getY() != ((Location) Chunkview.viewerslocs.get(index)).getY() || loc.getZ() != ((Location) Chunkview.viewerslocs.get(index)).getZ()) {
				Location corner1;
				chunk.getBlock(0, 0, 0).getLocation();
				Location corner2;
				chunk.getBlock(15, 0, 0).getLocation();
				Location corner3;
				chunk.getBlock(0, 0, 15).getLocation();
				Location corner4;
				chunk.getBlock(15, 0, 15).getLocation();
				int i = 0;
				int i2 = 0;
				for (i = 0; i < 127; i++) {
					for (i2 = 0; i2 < 15; i2++) {
						corner1 = chunk.getBlock(i2, i, 0).getLocation();
						corner2 = chunk.getBlock(15, i, i2).getLocation();
						corner3 = chunk.getBlock(15 - i2, i, 15).getLocation();
						corner4 = chunk.getBlock(0, i, 15 - i2).getLocation();
						if (corner1.getBlock().getType() == Material.AIR)
							player.sendBlockChange(corner1, Material.AIR.createBlockData());
//							player.sendBlockChange(corner1, Material.AIR, (byte) 0);
						if (corner2.getBlock().getType() == Material.AIR)
							player.sendBlockChange(corner2, Material.AIR.createBlockData());
//							player.sendBlockChange(corner2, Material.AIR, (byte) 0);
						if (corner3.getBlock().getType() == Material.AIR)
							player.sendBlockChange(corner3, Material.AIR.createBlockData());
//							player.sendBlockChange(corner3, Material.AIR, (byte) 0);
						if (corner4.getBlock().getType() == Material.AIR)
							player.sendBlockChange(corner4, Material.AIR.createBlockData());
//							player.sendBlockChange(corner4, Material.AIR, (byte) 0);
					}
				}

//				player.sendMessage(ChatColor.GOLD + "Boarder blocks removed.");
				Chunkview.viewers.remove(player);
				Chunkview.viewerslocs.remove(index);

			}
		}
	}
}