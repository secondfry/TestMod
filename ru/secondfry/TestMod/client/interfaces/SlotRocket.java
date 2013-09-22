package ru.secondfry.TestMod.client.interfaces;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.secondfry.TestMod.blocks.BlockInfo;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 22.09.13 6:41
 */
public class SlotRocket extends Slot {
	public SlotRocket(IInventory inventory, int id, int x, int y) {
		super(inventory, id, x, y);
	}

	@Override
	public int getSlotStackLimit() {
		return BlockInfo.FIREWORK_TE_STACKLIMIT;
	}

	@Override
	public boolean isItemValid(ItemStack itemStack) {
		boolean isItemValid = false, q1, q2;

		q1 = itemStack.itemID == BlockInfo.FIREWORK_TE_ITEMID;
		if(getStack() != null)
			q2 = getStack().stackSize < getSlotStackLimit();
		else
			q2 = true;

		if(q1 && q2)
			isItemValid = true;

		return isItemValid; // FIXME Rocket not Anvil
	}
}
