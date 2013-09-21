package ru.secondfry.TestMod.items;

import net.minecraft.item.ItemBlock;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 21.09.13 6:24
 */
public class ItemFirework extends ItemBlock {

	public ItemFirework(int itemID) {
		super(itemID);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int dmg) {
		return dmg;
	}

}
