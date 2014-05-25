package com.caved_in.lootkrate.threads;

import com.caved_in.commons.Commons;
import com.caved_in.commons.player.Players;
import com.caved_in.commons.time.TimeHandler;
import com.caved_in.commons.time.TimeType;
import com.caved_in.lootkrate.KrateManager;
import com.caved_in.lootkrate.LootKrate;
import com.caved_in.lootkrate.PluginMessages;

public class CrateSpawnThread implements Runnable {
	private static CrateSpawnThread instance;

	protected CrateSpawnThread() {

	}

	public static CrateSpawnThread getInstance() {
		if (instance == null) {
			instance = new CrateSpawnThread();
		}
		return instance;
	}

	@Override
	public void run() {
		if (KrateManager.hasActiveLootCrate()) {
			return;
		}
		KrateManager.spawnLootKrate();
		Players.messageAll(PluginMessages.lootCrateAnnouncement(KrateManager.getActiveLootLocation().getLocation()));
		Commons.threadManager.runTaskLater(CrateDespawnThread.getInstance(), TimeHandler.getTimeInTicks(LootKrate.config.getCrateDespawnTime(), TimeType.MINUTE));
	}
}
