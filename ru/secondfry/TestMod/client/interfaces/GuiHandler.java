package ru.secondfry.TestMod.client.interfaces;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.secondfry.TestMod.TestMod;
import ru.secondfry.TestMod.tileentities.TileEntityFirework;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 22.09.13 3:08
 */
public class GuiHandler implements IGuiHandler {

	public GuiHandler() {
		NetworkRegistry.instance().registerGuiHandler(TestMod.instance, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case GuiInfo.GUI_FIREWORK_ID:
				TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
				if (tileEntity != null && tileEntity instanceof TileEntityFirework) {
					return new ContainerFirework(player.inventory, (TileEntityFirework) tileEntity);
				}
				break;
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case GuiInfo.GUI_FIREWORK_ID:
				TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
				if (tileEntity != null && tileEntity instanceof TileEntityFirework) {
					return new GuiFirework(player.inventory, (TileEntityFirework) tileEntity);
				}
				break;
		}

		return null;
	}
}
