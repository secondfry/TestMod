package ru.secondfry.TestMod.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.secondfry.TestMod.tileentities.TileEntityFirework;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 22.09.13 2:31
 */
public class ContainerFirework extends Container {

	private TileEntityFirework tileEntityFirework;

	private void addSlotsForPlayerInventory(InventoryPlayer inventoryPlayer) {
		int x = 0, y;

		while (x < 9) {
			addSlotToContainer(new Slot(inventoryPlayer, x, 8 + 18 * x, 130));
			x++;
		}

		y = 0;
		while (y < 3) {
			x = 0;
			while (x < 9) {
				addSlotToContainer(new Slot(inventoryPlayer, x + y * 9 + 9, 8 + 18 * x, 72 + 18 * y));
				x++;
			}
			y++;
		}
	}

	public ContainerFirework(InventoryPlayer inventoryPlayer, TileEntityFirework tileEntityFirework) {
		int x = 0;

		this.tileEntityFirework = tileEntityFirework;

		addSlotsForPlayerInventory(inventoryPlayer);

		while (x < 3) {
			addSlotToContainer(new SlotRocket(tileEntityFirework, x, 8 + 18 * x, 17));
			x++;
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return tileEntityFirework.isUseableByPlayer(entityplayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int i) {
		return null; // TODO ShiftClick
	}

}
