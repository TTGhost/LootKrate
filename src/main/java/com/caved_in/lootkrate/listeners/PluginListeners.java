package com.caved_in.lootkrate.listeners;

import com.caved_in.commons.Commons;
import com.caved_in.commons.Messages;
import com.caved_in.commons.block.Blocks;
import com.caved_in.commons.location.Locations;
import com.caved_in.commons.player.Players;
import com.caved_in.lootkrate.KrateManager;
import com.caved_in.lootkrate.loot.Kit;
import com.caved_in.lootkrate.loot.LootLocation;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;

public class PluginListeners implements Listener {
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (e.isCancelled()) {
			return;
		}

		Block block = e.getBlock();

		if (block.getType() != Material.SPONGE || !KrateManager.isLootCrate(block)) {
			return;
		}

		e.setCancelled(true);
	}

	@EventHandler
	public void onEntityExplode(EntityExplodeEvent e) {
		if (e.isCancelled()) {
			return;
		}

		List<Block> blocksBroken = e.blockList();
		blocksBroken.stream().filter(KrateManager::isLootCrate).forEach(blocksBroken::remove);
	}

	@EventHandler
	public void onPlayerInteractBlock(PlayerInteractEvent e) {
		if (e.isCancelled() || !KrateManager.hasActiveLootCrate() || !e.hasBlock()) {
			return;
		}

		Block block = e.getClickedBlock();
		if (block.getType() != Material.SPONGE) {
			return;
		}

		Commons.debug(Messages.locationInfo(block.getLocation()));

		LootLocation activeCrate = KrateManager.getActiveLootLocation();
		Player player = e.getPlayer();

		if (!Locations.isPlayerInRadius(activeCrate.getLocation(), 4, player)) {
			return;
		}

		Block crateBlock = Blocks.getBlockAt(activeCrate.getLocation());
		Kit randomKit = KrateManager.getRandomKit();
		KrateManager.givePlayerKit(e.getPlayer(), randomKit);
		KrateManager.crateFound();
		block.breakNaturally();
		crateBlock.breakNaturally();
		Players.messageAll("&6The Loot Crate has been found!");
	}
}
