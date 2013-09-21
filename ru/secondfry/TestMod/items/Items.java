package ru.secondfry.TestMod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 21.09.13 6:39
 */
public class Items {

	public static Item itemDye;

	public static void init() {
		itemDye = new ItemDye(ItemInfo.DYE_ID);
	}

	public static void addNames() {
		int i = 0;

		while (i < ItemInfo.DYE_NAMES.length) {
			LanguageRegistry.addName(new ItemStack(itemDye, 1, i), ItemInfo.DYE_NAMES[i]);
			i++;
		}
	}

	public static void registerRecipes() {
		int i = 0, next = 1, size = ItemInfo.DYE_NAMES.length;
		while (i < size) {
			if (i == size - 1) next = 0;
			GameRegistry.addShapelessRecipe(new ItemStack(itemDye, 1, next), new ItemStack(itemDye, 1, i));
			i++;
			next++;
		}
	}

}
