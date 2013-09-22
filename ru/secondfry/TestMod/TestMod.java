package ru.secondfry.TestMod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import ru.secondfry.TestMod.blocks.Blocks;
import ru.secondfry.TestMod.client.interfaces.GuiHandler;
import ru.secondfry.TestMod.config.ConfigHandler;
import ru.secondfry.TestMod.entities.Entities;
import ru.secondfry.TestMod.items.Items;
import ru.secondfry.TestMod.network.PacketHandler;
import ru.secondfry.TestMod.proxies.CommonProxy;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 21.09.13 5:08
 */
@Mod(modid = ModInformation.ID, name = ModInformation.NAME, version = ModInformation.VERSION)
@NetworkMod(channels = {ModInformation.CHANNEL}, clientSideRequired = true, serverSideRequired = true, packetHandler = PacketHandler.class)
public class TestMod {

	@Mod.Instance
	public static TestMod instance;

	@SidedProxy(clientSide = ModInformation.CLIENT_PROXY, serverSide = ModInformation.COMMON_PROXY)
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		Blocks.init();
		Items.init();
		Entities.init();

		proxy.initSounds();
		proxy.initRenderers();

		new GuiHandler();
	}

	@Mod.EventHandler
	public void load(FMLInitializationEvent event) {
		Blocks.addNames();
		Items.addNames();

		Blocks.registerRecipes();
		Items.registerRecipes();

		Blocks.registerTileEntities();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

}
