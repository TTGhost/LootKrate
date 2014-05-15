package com.caved_in.lootkrate.commands;

import com.caved_in.commons.Messages;
import com.caved_in.commons.commands.CommandController;
import com.caved_in.commons.player.Players;
import com.caved_in.lootkrate.KrateManager;
import org.bukkit.entity.Player;

public class LootKrateCommand {
	@CommandController.CommandHandler(name = "lootkrate", usage = "/<command>", permission = "lootkrate.loot")
	public void onLootKrateCommand(Player player, String[] args) {

	}

	@CommandController.SubCommandHandler(name = "addspawn", parent = "lootkrate")
	public void onLootKrateAddSpawnCommand(Player player, String[] args) {

	}

	@CommandController.SubCommandHandler(name = "view", parent = "lootkrate")
	public void onLootCrateViewCommand(Player player, String[] args) {
		if (args.length < 2) {
			Players.sendMessage(player, Messages.invalidCommandUsage("kit name"));
			return;
		}

		String kitName = args[1];
		if (!KrateManager)
	}
}
