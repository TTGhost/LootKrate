package com.caved_in.lootkrate.config;

import com.caved_in.lootkrate.loot.LootLocation;
import org.bukkit.Location;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "loot-spawn-locations")
public class LootLocations {

	@ElementList(name = "locations", type = LootLocation.class)
	private List<LootLocation> locations;

	public LootLocations(@ElementList(name = "locations", type = LootLocation.class)List<LootLocation> locations) {
		this.locations = locations;
	}

	public LootLocations() {
		this.locations = new ArrayList<>();
	}

	public void addLootLocation(LootLocation lootLocation) {
		locations.add(lootLocation);
	}

	public void addLootLocation(Location location) {
		addLootLocation(new LootLocation(location));
	}

	public List<LootLocation> getLootLocations() {
		return locations;
	}

	public void removeLootLocation(LootLocation lootLocation) {
		locations.remove(lootLocation);
	}
}
