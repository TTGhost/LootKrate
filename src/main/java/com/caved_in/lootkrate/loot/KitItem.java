package com.caved_in.lootkrate.loot;

import com.caved_in.commons.item.Items;
import com.caved_in.commons.utilities.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.List;

public class KitItem {

	@Attribute(name = "item-id")
	private int materialId = 256;

	@Attribute(name = "data-value", required = false)
	private int byteData = 0;

	@Element(name = "amount", required = false)
	private int amount = 1;

	@Element(name = "item-name", required = false)
	private String itemName = "";

	@ElementList(name = "lore", required = false, entry = "line")
	private List<String> loreLines = new ArrayList<>();

	public KitItem(@Attribute(name = "item-id") int materialId, @Attribute(name = "data-value") int byteData, @Element(name = "amount", required = false) int amount, @Element(name = "item-name", required = false) String itemName, @ElementList(name = "lore", required = false, entry = "line") List<String> loreLines) {
		this.materialId = materialId;
		this.byteData = byteData;
		this.amount = amount;
		this.itemName = itemName;
		this.loreLines = loreLines;
	}

	public KitItem() {
		loreLines.add("Not the shovel.");
	}

	public ItemStack getItemStack() {
		ItemStack itemStack;
		Material material = Items.getMaterialById(materialId);
		if (byteData != 0) {
			itemStack = Items.getMaterialData(material, byteData).toItemStack(amount);
		} else {
			itemStack = Items.makeItem(material);
			itemStack.setAmount(amount);
		}

		if (StringUtils.isNotEmpty(itemName)) {
			itemStack = Items.setName(itemStack, itemName);
		}

		if (loreLines.size() > 0) {
			itemStack = Items.setLore(itemStack, loreLines);
		}
		return itemStack;
	}

	@Override
	public String toString() {
		return String.format("Item Type: %s\nByte Data: %s\nItem Amount: %s\nItem Lore: %s\n", Items.getFormattedMaterialName(Items.getMaterialById(materialId)), byteData, amount, StringUtil.joinString(loreLines, "\n", 0));
	}
}
