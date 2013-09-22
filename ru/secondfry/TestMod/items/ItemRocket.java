package ru.secondfry.TestMod.items;

import net.minecraft.item.Item;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 22.09.13 1:37
 */
public class ItemRocket extends Item {

	public ItemRocket(int itemID) {
		super(itemID);
		setMaxStackSize(ItemInfo.ROCKET_MAXSTACK);
	}

}
