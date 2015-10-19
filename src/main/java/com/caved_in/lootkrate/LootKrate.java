package com.caved_in.lootkrate;

import com.caved_in.commons.Commons;
import com.caved_in.commons.command.CommandController;
import com.caved_in.commons.file.Folder;
import com.caved_in.commons.plugin.Plugins;
import com.caved_in.commons.threading.RunnableManager;
import com.caved_in.commons.time.TimeHandler;
import com.caved_in.commons.time.TimeType;
import com.caved_in.lootkrate.commands.LootKrateCommand;
import com.caved_in.lootkrate.config.Configuration;
import com.caved_in.lootkrate.config.LootLocations;
import com.caved_in.lootkrate.listeners.PluginListeners;
import com.caved_in.lootkrate.loot.Kit;
import com.caved_in.lootkrate.threads.CrateSpawnThread;
import org.apache.commons.io.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.Collection;

public class LootKrate extends JavaPlugin {
	public static RunnableManager threads;
	private static Serializer serializer = new Persister();
	private static String CONFIG_FILE_LOCATION = "plugins/CarePackages/Config.xml";
	private static String KIT_FOLDER_LOCATION = "plugins/CarePackages/Kits/";
	private static String CRATE_FOLDER_LOCATION = "plugins/CarePackages/Crates/";
	private static String LOOT_LOCATION_FILE = "plugins/CarePackages/CP-Locations.xml";


	public static LootLocations lootLocations;
	public static Configuration config = null;

	@Override
	public void onEnable() {
		Plugins.makeDataFolder(this);
		threads = new RunnableManager(this);

		initConfig();

		if (config == null) {
			Commons.debug("Disabling CarePackages, please assure the config isn't corrupt.");
			Plugins.disablePlugin(this);
			return;
		}

		//Initialize the loot locations
		initLootLocationsConfig();

		//Initialize the kits data
		initKitsConfig();

		//Initialize the krate manager
		KrateManager.init();

		registerCommands();

		Plugins.registerListeners(this,
				new PluginListeners()
		);

		threads.registerSynchRepeatTask("Crate Spawn Thread", CrateSpawnThread.getInstance(), TimeHandler.getTimeInTicks(10, TimeType.SECOND), TimeHandler.getTimeInTicks(config.getCrateSpawnTime(), TimeType.MINUTE));
	}

	@Override
	public void onDisable() {
		saveLootLocationsConfig();
		Plugins.unregisterHooks(this);
	}

	private void registerCommands() {
		CommandController.registerCommands(this, new LootKrateCommand());
	}

	private void initConfig() {
		File configFile = new File(CONFIG_FILE_LOCATION);
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();
				serializer.write(new Configuration(), configFile);
				Commons.debug("Created default config for CarePackages");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			config = serializer.read(Configuration.class, configFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void initLootLocationsConfig() {
		File lootConfig = new File(LOOT_LOCATION_FILE);
		if (lootConfig.exists()) {
			try {
				lootLocations = serializer.read(LootLocations.class, lootConfig);
				Commons.debug("[CarePackage] Loaded CarePackage locations from cp-locations.xml");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			lootLocations = new LootLocations();
			Commons.debug("[CarePackage] Created default CarePackage locations file @ '" + LOOT_LOCATION_FILE + "'");
			try {
				lootConfig.createNewFile();
				serializer.write(lootLocations, lootConfig);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void saveLootLocationsConfig() {
		File lootConfig = new File(LOOT_LOCATION_FILE);
		try {
			serializer.write(lootLocations, lootConfig);
			Commons.debug("[CarePackage] Saved cp-locations data to file.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initKitsConfig() {
		File kitFolder = new File(KIT_FOLDER_LOCATION);
		if (!kitFolder.exists()) {
			Commons.debug(kitFolder.mkdirs() ? "[CarePackage] Created default Kits folder" : "[CarePackage] Unable to create the kits folder");
			try {
				serializer.write(new Kit(), new File(kitFolder, "Starter Kit.xml"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Collection<File> kitFiles = FileUtils.listFiles(kitFolder, null ,false);
		Commons.debug("Loading Kits from folder; " + kitFiles.size() + " files to parse");

		for(File file : kitFiles) {
			try {
				Kit kit = serializer.read(Kit.class,file);
				KrateManager.addKit(kit);
				Commons.debug(kit.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
