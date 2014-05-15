package com.caved_in.lootkrate.config;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "loot-location")
public class LootLocation {
	@Attribute(name = "world")
	private String worldName;

	@Attribute(name = "x")
	private int x;

	private int y;

	private int z;

	private
}
