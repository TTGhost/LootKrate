package com.caved_in.lootkrate.commands;

import com.caved_in.commons.Messages;
import com.caved_in.commons.command.Command;
import com.caved_in.commons.command.SubCommand;
import com.caved_in.commons.menu.HelpScreen;
import com.caved_in.commons.menu.ItemFormat;
import com.caved_in.commons.menu.Menus;
import com.caved_in.commons.menu.PageDisplay;
import com.caved_in.commons.player.Players;
import com.caved_in.lootkrate.KrateManager;
import com.caved_in.lootkrate.LootKrate;
import com.caved_in.lootkrate.PluginMessages;
import com.caved_in.lootkrate.config.LootLocations;
import com.caved_in.lootkrate.loot.Kit;
import com.caved_in.lootkrate.loot.KitItem;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LootKrateCommand {
	private static HelpScreen lootKrateHelpMenu = null;
	@Command(name = "lootkrate", usage = "/<command>", permission = "lootkrate.loot")
	public void onLootKrateCommand(Player player, String[] args) {
		if (args.length == 0) {
			if (lootKrateHelpMenu == null) {
				lootKrateHelpMenu = Menus.generateHelpScreen("Loot Krate Help", PageDisplay.DEFAULT, ItemFormat.SINGLE_DASH, ChatColor.GREEN);
				lootKrateHelpMenu.setEntry("/lootkrate locate", "View the location of the active loot crate");
				lootKrateHelpMenu.setEntry("/lootkrate addspawn", "Add a spawn location to the list of possible locations (Admins)");
				lootKrateHelpMenu.setEntry("/lootkrate view <kit>", "View the contents of a kit (Admins)");
			}
			lootKrateHelpMenu.sendTo(player,1,3);
		}
	}

	@SubCommand(name = "locate",parent = "lootkrate")
	public void onLootCrateLocateCommand(Player player, String[] args) {
		if (!KrateManager.hasActiveLootCrate()) {
			Players.sendMessage(player,PluginMessages.NO_ACTIVE_CRATE);
			return;
		}

		Location location = KrateManager.getActiveLootLocation().getLocation();
		Players.sendMessage(player, PluginMessages.lootLocationAdded(location));
	}

	@SubCommand(name = "addspawn", parent = "lootkrate", permission = "lootkrate.addspawn")
	public void onLootKrateAddSpawnCommand(Player player, String[] args) {
		Location playerLoc = player.getLocation();
		if (KrateManager.isLootCrate(playerLoc)) {
			Players.sendMessage(player, PluginMessages.ALREADY_CRATE_LOCATION);
			return;
		}

		LootLocations lootLocations = LootKrate.lootLocations;
		lootLocations.addLootLocation(playerLoc);
		Players.sendMessage(player, PluginMessages.lootLocationAdded(playerLoc));
	}

	@SubCommand(name = "view", parent = "lootkrate")
	public void onLootCrateViewCommand(Player player, String[] args) {
		if (args.length < 2) {
			Players.sendMessage(player, Messages.invalidCommandUsage("kit name"));
			return;
		}

		String kitName = args[1];
		if (!KrateManager.isKit(kitName)) {
			Players.sendMessage(player, PluginMessages.invalidKit(kitName));
			return;
		}

		Kit kit = KrateManager.getKit(kitName);

		List<KitItem> kitItems = kit.getKitItems();

		if (kitItems.size() == 0) {
			Players.sendMessage(player, "&eThere are no items in &c%s&e, please add some then try again.");
			return;
		}

		for(KitItem item : kitItems) {
			Players.sendMessage(player,item.toString());
		}
	}
}
