package ru.secondfry.TestMod.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 22.09.13 1:37
 */
public class ItemRocket extends Item {

	public ItemRocket(int itemID) {
		super(itemID);
		setMaxStackSize(ItemInfo.ROCKET_MAXSTACK);
		setCreativeTab(CreativeTabs.tabRedstone);
		setUnlocalizedName(ItemInfo.DYE_UNLOCALIZED_NAME);
	}

	@SideOnly(Side.CLIENT)
	private Icon icon;

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		//register.registerIcon(ModInformation.ID + ":" + ""); // FIXME no icon file
	}
}
