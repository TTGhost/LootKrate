package com.caved_in.lootkrate;

import com.caved_in.lootkrate.loot.Crate;
import com.caved_in.lootkrate.loot.Kit;
import com.caved_in.lootkrate.loot.LootLocation;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.Map;

public class KrateManager {
	private static Map<String, Kit> kits = new HashMap<>();

	private static Map<LootLocation, Crate> crates = new HashMap<>();

	public static boolean isKit(String kitName) {
		return kits.containsKey(kitName);
	}

	public static void addKit(Kit kit) {
		kits.put(kit.getName(),kit);
	}

	public static Kit getKit(String name) {
		return kits.get(name);
	}

	public static void addLootCrate(Crate crate) {
		crates.put(crate.getLootLocation(),crate);
	}

	public static boolean isLootCrate(Block block) {
		return isLootCrate(block.getLocation());
	}

	public static boolean isLootCrate(Location loc) {
		for(Map.Entry<LootLocation, Crate> crateEntry : crates.entrySet()) {
			if (!crateEntry.equals(loc)) {
				continue;
			}
			return true;
		}
		return false;
	}
}
