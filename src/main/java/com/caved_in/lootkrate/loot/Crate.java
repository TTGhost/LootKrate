package com.caved_in.lootkrate.loot;

import org.simpleframework.xml.Element;

public class Crate {
	@Element(name = "loot-location", type = LootLocation.class)
	private LootLocation lootLocation;

	@Element(name = "kit")
	private String kitName;

	public Crate(@Element(name = "loot-location", type = LootLocation.class)LootLocation lootLocation, @Element(name = "kit")String kitName) {
		this.lootLocation = lootLocation;
		this.kitName = kitName;
	}

	public Kit getKit() {

	}

	public LootLocation getLootLocation() {
		return lootLocation;
	}
}
