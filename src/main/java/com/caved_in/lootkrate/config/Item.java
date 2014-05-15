package com.caved_in.lootkrate.config;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class Item {

	@Attribute(name = "item-id")
	private int materialId = 1;

	@Attribute(name = "data-value")
	private short byteData = 0;

	@Element(name = "use-amount-range")
	private boolean rangeAmount = false;

	@Element(name = "amount", required = false)
	private int amount = 1;

	@Element(name = )
	private int minAmount = 0;

	private double maxAmount = 0;

}
