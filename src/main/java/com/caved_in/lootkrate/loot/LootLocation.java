package com.caved_in.lootkrate.loot;

import com.caved_in.commons.location.Locations;
import com.caved_in.commons.world.Worlds;
import org.bukkit.Location;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "loot-location")
public class LootLocation {
	@Attribute(name = "world")
	private String worldName;

	@Attribute(name = "x")
	private int x;

	@Attribute(name = "y")
	private int y;

	@Attribute(name = "z")
	private int z;

	private Location location;

	public LootLocation(Location location) {
		this(Worlds.getWorldName(location),location.getBlockX(),location.getBlockY(),location.getBlockZ());
	}

	public LootLocation(@Attribute(name = "world")String worldName, @Attribute(name = "x")int x, @Attribute(name = "y")int y, @Attribute(name = "z")int z) {
		this.worldName = worldName;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Location getLocation() {
		if (location == null) {
			location = Locations.getLocation(worldName, x, y, z);
		}
		return location;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (o instanceof Location) {
			Location loc = (Location)o;
			//Check If all the co-ords match and the world is the same
			return loc.getBlockX() == x && loc.getBlockX() == y && loc.getBlockZ() == z;
		}

		if (o instanceof LootLocation) {
			LootLocation lootLocation = (LootLocation)o;
			return lootLocation.getX() == x && lootLocation.getY() == y && lootLocation.getZ() == z && lootLocation.getWorldName().equalsIgnoreCase(worldName);
		}

		return false;
	}

	public String getWorldName() {
		return worldName;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}
}
