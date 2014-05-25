package com.caved_in.lootkrate;

import com.caved_in.commons.Messages;
import com.caved_in.commons.location.Locations;
import org.bukkit.Location;

import static java.lang.String.format;

public class PluginMessages {
	private static final String PREFIX = "&6[Loot Krate]&r ";
	public static final String NO_CRATE_LOCATIONS = format("%s&eThere are no Crate locations active. Please add some in-order for Loot Krate to function.", PREFIX);
	public static final String ALREADY_CRATE_LOCATION = format("%s&eThe location you're standing at is already a loot crate spawn.",PREFIX);
	public static final String NO_ACTIVE_CRATE = format("%sThere's no active loot-crate.",PREFIX);

	public static String lootLocationAdded(Location loc) {
		return String.format("&aAdded %s to the list of potential loot-crate locations!", Messages.locationCoords(loc));
	}

	public static String[] lootCrateAnnouncement(Location loc) {
		int[] xyz = Locations.getXYZ(loc);

		return new String[] {
				"&6---&c[&rLoot Crate&c]&6---",
				"A new loot crate has spawned at",
				format("X: %s, Y: %s, Z: %s",xyz[0],xyz[1],xyz[2]),
				"First to click it will claim a reward!",
				"&6--------------------------"
		};
	}

	public static String invalidKit(String kitName) {
		return String.format("&e%s&c isn't a valid kit name, please try again.",kitName);
	}

}
