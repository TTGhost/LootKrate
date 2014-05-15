package com.caved_in.lootkrate;

import com.caved_in.commons.Commons;
import com.caved_in.commons.file.Folder;
import com.caved_in.lootkrate.loot.Crate;
import com.caved_in.lootkrate.loot.Kit;
import org.bukkit.plugin.java.JavaPlugin;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;

public class LootKrate extends JavaPlugin {
	private static Serializer serializer = new Persister();
	private static String KIT_FOLDER_LOCATION = "plugins/LootKrate/Kits/";
	private static String CRATE_FOLDER_LOCATION = "plugins/LootKrate/Crates/";


	@Override
	public void onEnable() {

	}

	@Override
	public void onDisable() {

	}

	private void initKitsConfig() {
		Folder kitsFolder = new Folder(KIT_FOLDER_LOCATION);
		Commons.debug("Loading Kits from folder");

		for (String s : kitsFolder.getFiles()) {
			try {
				Kit kit = serializer.read(Kit.class, new File(s));
				KrateManager.addKit(kit);
				Commons.debug(kit.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void initCratesConfig() {
		Folder cratesFolder = new Folder(CRATE_FOLDER_LOCATION);
		Commons.debug("Loading Crates from folder");
		for(String crateFileLocation : cratesFolder.getFiles()) {
			try {
				Crate crate = serializer.read(Crate.class,new File(crateFileLocation));
				KrateManager.addLootCrate(crate);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
