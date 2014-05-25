package com.caved_in.lootkrate.threads;

import com.caved_in.commons.block.Blocks;
import com.caved_in.commons.player.Players;
import com.caved_in.lootkrate.KrateManager;
import com.caved_in.lootkrate.loot.LootLocation;
import org.bukkit.Location;

public class CrateDespawnThread implements Runnable {
	private static CrateDespawnThread instance;

	public static CrateDespawnThread getInstance() {
		if (instance == null) {
			instance = new CrateDespawnThread();
		}
		return instance;
	}

	protected CrateDespawnThread() {}

	@Override
	public void run() {
		if (!KrateManager.hasActiveLootCrate()) {
			return;
		}

		Location location = KrateManager.getActiveLootLocation().getLocation();
		Blocks.breakBlockAt(location,false,true);
		Players.messageAll("&eThe loot crate has despawned.");
	}
}
