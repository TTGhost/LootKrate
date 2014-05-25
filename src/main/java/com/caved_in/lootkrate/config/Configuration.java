package com.caved_in.lootkrate.config;

import org.simpleframework.xml.Element;

public class Configuration {
	@Element(name = "crate-block-id")
	private int blockId = 19;

	@Element(name = "crate-spawn-minutes")
	private int crateSpawnTime = 20;

	@Element(name = "crate-despawn-minutes")
	private int crateDespawnTime = 8;

	public Configuration(@Element(name = "crate-block-id")int blockId, @Element(name = "crate-spawn-minutes")int crateSpawnTime, @Element(name = "crate-despawn-minutes")int crateDespawnTime) {
		this.blockId = blockId;
		this.crateSpawnTime = crateSpawnTime;
		this.crateDespawnTime = crateDespawnTime;
	}

	public Configuration() {

	}

	public int getCrateDespawnTime() {
		return crateDespawnTime;
	}

	public void setCrateDespawnTime(int crateDespawnTime) {
		this.crateDespawnTime = crateDespawnTime;
	}

	public int getCrateSpawnTime() {
		return crateSpawnTime;
	}

	public void setCrateSpawnTime(int crateSpawnTime) {
		this.crateSpawnTime = crateSpawnTime;
	}

	public int getBlockId() {
		return blockId;
	}

	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}
}
