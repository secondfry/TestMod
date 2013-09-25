package ru.secondfry.TestMod.client.interfaces;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.secondfry.TestMod.ModInformation;
import ru.secondfry.TestMod.network.PacketHandler;
import ru.secondfry.TestMod.network.PacketInfo;
import ru.secondfry.TestMod.tileentities.TileEntityFirework;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 22.09.13 1:52
 */
@SideOnly(Side.CLIENT)
public class GuiFirework extends GuiContainer {

	private TileEntityFirework tileEntityFirework;

	private GuiButton disableButton;
	private GuiButton enableButton;

	public GuiFirework(InventoryPlayer inventoryPlayer, TileEntityFirework tileEntityFirework) {
		super(new ContainerFirework(inventoryPlayer, tileEntityFirework));

		this.tileEntityFirework = tileEntityFirework;

		xSize = 176;
		ySize = 154;
	}

	private static final ResourceLocation texture = new ResourceLocation(ModInformation.ID.toLowerCase(), "textures/gui/machine.png");

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		int type = tileEntityFirework.getType();
		int srcX = 20 * type;
		int srcY = ySize;

		GL11.glColor4f(1, 1, 1, 1);

		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		drawTexturedModalRect(guiLeft + 16, guiTop + 42, srcX, srcY, 20, 20);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		int type = tileEntityFirework.getType();
		String infoText = "";

		fontRenderer.drawString("Firework", 8, 6, 0x404040);

		switch (type) {
			case 0:
				infoText = "Red rocket will be launched";
				break;
			case 1:
				infoText = "Green rocket will be launched";
				break;
			case 2:
				infoText = "Blue rocket will be launched";
				break;
		}
		fontRenderer.drawSplitString(infoText, 45, 44, 100, 0x404040);
	}

	@Override
	public void initGui() {
		super.initGui();
		enableButton = new GuiButton(GuiInfo.BUTTON_ENABLE_ID, guiLeft + 100, guiTop + 14, 60, 20, GuiInfo.BUTTON_ENABLE_TEXT);
		disableButton = new GuiButton(GuiInfo.BUTTON_DISABLE_ID, guiLeft + 100, guiTop + 14, 60, 20, GuiInfo.BUTTON_DISABLE_TEXT);
		buttonList.clear();

		if (tileEntityFirework.isSuspended()) {
			buttonList.add(enableButton);
		} else {
			buttonList.add(disableButton);
		}
	}

	@Override
	protected void actionPerformed(GuiButton guiButton) {
		buttonList.clear();
		switch (guiButton.id) {
			case GuiInfo.BUTTON_DISABLE_ID:
				tileEntityFirework.setSuspended(true);
				PacketHandler.sendButtonPacket(PacketInfo.SEND_BUTTON, guiButton.id);
				buttonList.add(enableButton);
				break;
			case GuiInfo.BUTTON_ENABLE_ID:
				tileEntityFirework.setSuspended(false);
				PacketHandler.sendButtonPacket(PacketInfo.SEND_BUTTON, guiButton.id);
				buttonList.add(disableButton);
				break;
		}
	}
}
