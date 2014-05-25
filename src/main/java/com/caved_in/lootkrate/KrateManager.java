package com.caved_in.lootkrate;

import com.caved_in.commons.Commons;
import com.caved_in.commons.block.Blocks;
import com.caved_in.commons.utilities.NumberUtil;
import com.caved_in.lootkrate.config.LootLocations;
import com.caved_in.lootkrate.exceptions.InsufficientLootLocationsException;
import com.caved_in.lootkrate.loot.Kit;
import com.caved_in.lootkrate.loot.KitItem;
import com.caved_in.lootkrate.loot.LootLocation;
import com.google.common.collect.Lists;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import java.util.*;

public class KrateManager {
	private static LootLocations lootLocations;
	private static Map<String, Kit> kits = new HashMap<>();
	private static LootLocation activeLootLocation;

	public static void init() {
		lootLocations = LootKrate.lootLocations;
	}

	public static boolean isKit(String kitName) {
		return kits.containsKey(kitName);
	}

	public static void addKit(Kit kit) {
		kits.put(kit.getName(), kit);
	}

	public static Kit getKit(String name) {
		return kits.get(name);
	}

	public static boolean isLootCrate(Block block) {
		return isLootCrate(block.getLocation());
	}

	public static boolean isLootCrate(Location loc) {
		for (LootLocation lootLocation : lootLocations.getLootLocations()) {
			if (!lootLocation.equals(loc)) {
				continue;
			}
			return true;
		}
		return false;
	}

	public static LootLocation getActiveLootLocation() {
		return activeLootLocation;
	}

	public static void setActiveLootLocation(LootLocation activeLootLocation) {
		KrateManager.activeLootLocation = activeLootLocation;
	}

	public static void crateFound() {
		setActiveLootLocation(null);
	}

	public static boolean hasActiveLootCrate() {
		return activeLootLocation != null;
	}

	public static boolean isActiveLootCrate(Location location) {
		return activeLootLocation != null && activeLootLocation.equals(location);
	}

	public static boolean isActiveLootCrate(Block block) {
		return isActiveLootCrate(block.getLocation());
	}

	public static boolean spawnLootKrate() {
		if (hasActiveLootCrate()) {
			return false;
		}

		LootLocation lootLocation;
		try {
			lootLocation = getRandomLootLocation();
		} catch (InsufficientLootLocationsException e) {
			e.printStackTrace();
			return false;
		}

		Location spawnLoc = lootLocation.getLocation();
		Blocks.setBlock(spawnLoc, Material.SPONGE);
		setActiveLootLocation(lootLocation);
		return true;
	}

	public static LootLocation getRandomLootLocation() throws InsufficientLootLocationsException {
		List<LootLocation> locationList = lootLocations.getLootLocations();
		int listSize = locationList.size();

		if (listSize < 2) {
			throw new InsufficientLootLocationsException("Insufficient loot locations for Loot-Krate. Please add some locations in-order for this plugin to function");
		}

		Random random = new Random();
		return locationList.get(random.nextInt(listSize));
	}

	public static void givePlayerKit(Player player, Kit kit) {
		PlayerInventory playerInventory = player.getInventory();
		List<KitItem> kitItems = kit.getKitItems();
		Set<KitItem> addedItems = new HashSet<>();
		Random random = new Random();
		for (KitItem item : kitItems) {
			if (playerInventory.firstEmpty() == -1) {
				break;
			}

			if (kit.hasRandomSelection() && random.nextBoolean()) {
				playerInventory.addItem(item.getItemStack());
			} else {
				playerInventory.addItem(item.getItemStack());
			}
			addedItems.add(item);
		}


		if (kitItems.size() == addedItems.size()) {
			return;
		}

		World world = player.getWorld();
		Location playerLoc = player.getLocation();
		//Filter the kitItems where they've not already been added, and drop the remaining ones at the players feet
		kitItems.stream().filter(item -> !addedItems.contains(item)).forEach(item -> world.dropItemNaturally(playerLoc, item.getItemStack()));
	}

	public static Kit getRandomKit() {
		List<Kit> kitList = Lists.newArrayList(kits.values());
		Commons.debug("Kit amount: " + kitList.size());
		Random random = new Random();
		return kitList.get(random.nextInt(kitList.size()));
	}
}
