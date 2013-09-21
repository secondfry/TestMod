package ru.secondfry.TestMod.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import ru.secondfry.TestMod.tileentities.TileEntityFirework;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 21.09.13 5:32
 */
public class BlockFirework extends BlockContainer {

	public BlockFirework(int blockID) {
		super(blockID, Material.iron);

		setCreativeTab(CreativeTabs.tabRedstone);
		setHardness(3F);
		setUnlocalizedName(BlockInfo.FIREWORK_UNLOCALIZED_NAME);
	}

	@SideOnly(Side.CLIENT)
	private Icon topIcon;

	@SideOnly(Side.CLIENT)
	private Icon botIcon;

	@SideOnly(Side.CLIENT)
	private Icon[] typeIcons; // LOL just Array

	@Override
	public Icon getIcon(int side, int meta) {
		switch (side) {
			case 0:
				return botIcon;
			case 1:
				return topIcon;
			default:
				return typeIcons[meta];
		}
	}

	@Override
	public void registerIcons(IconRegister register) {
		int i = 0;

		topIcon = register.registerIcon(BlockInfo.TEXTURE_LOCALITION + ":" + BlockInfo.FIREWORK_TOP);
		botIcon = register.registerIcon(BlockInfo.TEXTURE_LOCALITION + ":" + BlockInfo.FIREWORK_BOT);

		typeIcons = new Icon[BlockInfo.FIREWORK_SIDES.length];
		while (i < typeIcons.length) {
			typeIcons[i] = register.registerIcon(BlockInfo.TEXTURE_LOCALITION + ":" + BlockInfo.FIREWORK_SIDES[i]);
			i++;
		}
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public void getSubBlocks(int blockID, CreativeTabs creativeTab, List list) {
		int i = 0;
		while (i < BlockInfo.FIREWORK_SIDES.length) {
			ItemStack itemStack = new ItemStack(blockID, 1, i);
			list.add(itemStack);
			i++;
		}
	}


	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityFirework();
	}

}
