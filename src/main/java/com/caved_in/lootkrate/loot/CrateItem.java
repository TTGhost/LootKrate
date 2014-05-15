package com.caved_in.lootkrate.loot;

import com.caved_in.commons.item.ItemBuilder;
import com.caved_in.commons.item.Items;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.List;

public class CrateItem {

	@Attribute(name = "item-id")
	private int materialId = 1;

	@Attribute(name = "data-value", required = false)
	private int byteData = 0;

	@Element(name = "amount", required = false)
	private int amount = 1;

	@Element(name = "item-name", required = false)
	private String itemName = "";

	@ElementList(name = "lore", required = false, entry = "line")
	private List<String> loreLines = new ArrayList<>();

	private ItemStack itemStack;

	public CrateItem(@Attribute(name = "item-id") int materialId, @Attribute(name = "data-value") short byteData, @Element(name = "amount", required = false) int amount, @Element(name = "item-name", required = false) String itemName, @ElementList(name = "lore", required = false, entry = "line") List<String> loreLines) {
		this.materialId = materialId;
		this.byteData = byteData;
		this.amount = amount;
		this.itemName = itemName;
		this.loreLines = loreLines;
	}

	public CrateItem() {

	}

	public ItemStack getItemStack() {
		if (itemStack == null) {

			Material material = Items.getMaterialById(materialId);
			if (byteData != 0) {
				itemStack = Items.getMaterialData(material,byteData).toItemStack(amount);
			} else {
				itemStack = Items.makeItem(Items.getMaterialById(materialId));
				itemStack.setAmount(amount);
			}

			if (StringUtils.isNotEmpty(itemName)) {
				itemStack = Items.setName(itemStack,itemName);
			}

			if (loreLines.size() > 0) {
				itemStack = Items.setLore(itemStack,loreLines);
			}
		}
		return itemStack;
	}
}
