package ru.secondfry.TestMod.config;

import net.minecraftforge.common.Configuration;
import ru.secondfry.TestMod.blocks.BlockInfo;
import ru.secondfry.TestMod.items.ItemInfo;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 21.09.13 5:43
 */
public class ConfigHandler {

	public static void init(File file) {
		Configuration config = new Configuration(file);

		config.load();

		BlockInfo.FIREWORK_ID = config.getBlock(BlockInfo.FIREWORK_KEY, BlockInfo.FIREWORK_DEFAULT).getInt();

		ItemInfo.DYE_ID = config.getItem(ItemInfo.DYE_KEY, ItemInfo.DYE_DEFAULT).getInt() - 256;
		ItemInfo.ROCKET_ID = config.getItem(ItemInfo.ROCKET_KEY, ItemInfo.ROCKET_DEFAULT).getInt() - 256;

		config.save();
	}

}
