package ru.secondfry.TestMod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import ru.secondfry.TestMod.items.ItemFirework;
import ru.secondfry.TestMod.tileentities.TileEntityFirework;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 21.09.13 5:41
 */
public class Blocks {

	public static Block blockFirework;

	public static void init() {
		blockFirework = new BlockFirework(BlockInfo.FIREWORK_ID);
		GameRegistry.registerBlock(blockFirework, ItemFirework.class, BlockInfo.FIREWORK_KEY);
	}

	public static void addNames() {
		LanguageRegistry.addName(blockFirework, BlockInfo.FIREWORK_NAME);
	}

	public static void registerRecipes() {
		int i = 0, next = 1, size = BlockInfo.FIREWORK_SIDES.length;
		while (i < size) {
			if (i == size - 1) next = 0;
			GameRegistry.addShapelessRecipe(new ItemStack(blockFirework, 1, next), new ItemStack(blockFirework, 1, i));
			i++;
			next++;
		}
	}

	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityFirework.class, BlockInfo.FIREWORK_TE_KEY);
	}

}
