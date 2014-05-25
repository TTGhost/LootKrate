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

	@ElementList (name = "items",type = KitItem.class)
	private List<KitItem> kitItems = new ArrayList<>();

	public Kit(@Attribute (name = "name")String kitName, @Attribute(name = "random-item-selection")boolean randomItemSelection, @ElementList (name = "items", type = KitItem.class)List<KitItem> kitItems) {
		this.kitName = kitName;
		this.randomItemSelection = randomItemSelection;
		this.kitItems.addAll(kitItems);
	}

	public Kit() {
		kitItems.add(new KitItem());
	}

	public String getName() {
		return kitName;
	}

	public boolean hasRandomSelection() {
		return randomItemSelection;
	}

	public List<KitItem> getKitItems() {
		return kitItems;
	}

	@Override
	public String toString() {
		return String.format("Kit Name: %s\n Has random item selection: %s\nItem Amount: %s",kitName,randomItemSelection ? "Yes" : "No", kitItems.size());
	}
}
