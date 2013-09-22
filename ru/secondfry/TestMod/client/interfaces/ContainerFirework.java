package ru.secondfry.TestMod.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import ru.secondfry.TestMod.tileentities.TileEntityFirework;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 22.09.13 2:31
 */
public class ContainerFirework extends Container {

	private TileEntityFirework tileEntityFirework;

	public ContainerFirework(InventoryPlayer inventoryPlayer, TileEntityFirework tileEntityFirework) {
		this.tileEntityFirework = tileEntityFirework;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return tileEntityFirework.isUseableByPlayer(entityplayer);
	}
}
