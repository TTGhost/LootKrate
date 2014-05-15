package com.caved_in.lootkrate.loot;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.List;

public class Kit {

	@Attribute (name = "name")
	private String kitName = "Starter Kit";

	@Attribute(name = "random-item-selection")
	private boolean randomItemSelection = false;

	@ElementList (name = "items", inline = true)
	private List<CrateItem> kitItems = new ArrayList<>();

	public Kit(@Attribute (name = "name")String kitName, @Attribute(name = "random-item-selection")boolean randomItemSelection, @ElementList (name = "items", inline = true)List<CrateItem> kitItems) {
		this.kitName = kitName;
		this.randomItemSelection = randomItemSelection;
		this.kitItems = kitItems;
	}

	public String getName() {
		return kitName;
	}

	public boolean hasRandomSelection() {
		return randomItemSelection;
	}

	public List<CrateItem> getCrateItems() {
		return kitItems;
	}

	@Override
	public String toString() {
		return String.format("Kit Name: %s\n Has random item selection: %s\nItem Amount: %s",kitName,randomItemSelection ? "Yes" : "No", kitItems.size());
	}
}
