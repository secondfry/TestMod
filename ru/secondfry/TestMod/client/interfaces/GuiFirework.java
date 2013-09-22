package ru.secondfry.TestMod.client.interfaces;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.secondfry.TestMod.ModInformation;
import ru.secondfry.TestMod.tileentities.TileEntityFirework;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 22.09.13 1:52
 */
@SideOnly(Side.CLIENT)
public class GuiFirework extends GuiContainer {

	public GuiFirework(InventoryPlayer inventoryPlayer, TileEntityFirework tileEntityFirework) {
		super(new ContainerFirework(inventoryPlayer, tileEntityFirework));

		xSize = 176;
		ySize = 154;
	}

	private static final ResourceLocation texture = new ResourceLocation(ModInformation.ID.toLowerCase(), "textures/gui/machine.png");

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1, 1, 1, 1);

		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
}
