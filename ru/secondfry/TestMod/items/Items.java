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
	public static Item itemRocket;

	public static void init() {
		itemDye = new ItemDye(ItemInfo.DYE_ID);
		itemRocket = new ItemRocket(ItemInfo.ROCKET_ID);
	}

	public static void addNames() {
		int i = 0;

		while (i < ItemInfo.DYE_NAMES.length) {
			LanguageRegistry.addName(new ItemStack(itemDye, 1, i), ItemInfo.DYE_NAMES[i]);
			i++;
		}
		LanguageRegistry.addName(itemRocket, ItemInfo.ROCKET_NAME);
	}

	public static void registerRecipes() {
		int i = 0, next = 1, dyeSize = ItemInfo.DYE_NAMES.length;

		while (i < dyeSize) {
			if (i == dyeSize - 1) next = 0;
			GameRegistry.addShapelessRecipe(new ItemStack(itemDye, 1, next), new ItemStack(itemDye, 1, i));
			i++;
			next++;
		}

		// TODO add rocket recipe
	}

}
