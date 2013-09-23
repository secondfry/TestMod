package ru.secondfry.TestMod.blocks;

import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import ru.secondfry.TestMod.ModInformation;
import ru.secondfry.TestMod.TestMod;
import ru.secondfry.TestMod.client.interfaces.GuiInfo;
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

		topIcon = register.registerIcon(ModInformation.ID + ":" + BlockInfo.FIREWORK_TOP);
		botIcon = register.registerIcon(ModInformation.ID + ":" + BlockInfo.FIREWORK_BOT);

		typeIcons = new Icon[BlockInfo.FIREWORK_SIDES.length];
		while (i < typeIcons.length) {
			typeIcons[i] = register.registerIcon(ModInformation.ID + ":" + BlockInfo.FIREWORK_SIDES[i]);
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
	public TileEntity createTileEntity(World world, int meta) {
		TileEntityFirework tileEntityFirework = new TileEntityFirework();
		tileEntityFirework.setWorldObj(world);
		tileEntityFirework.setType(meta);
		return tileEntityFirework;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			FMLNetworkHandler.openGui(player, TestMod.instance, GuiInfo.FIREWORK_ID, world, x, y, z);
		}

		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int blockID, int meta) {
		int i = 0;
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity != null && tileEntity instanceof IInventory) {
			IInventory inventory = (IInventory) tileEntity;

			while (i < inventory.getSizeInventory()) {
				ItemStack itemStack = inventory.getStackInSlotOnClosing(i);
				if (itemStack != null) {
					float spawnX = x + world.rand.nextFloat();
					float spawnY = y + world.rand.nextFloat();
					float spawnZ = z + world.rand.nextFloat();

					EntityItem droppedItem = new EntityItem(world, spawnX, spawnY, spawnZ, itemStack);

					float mult = 0.05F;

					droppedItem.motionX = (-0.5F + world.rand.nextFloat()) * mult;
					droppedItem.motionY = (2 + world.rand.nextFloat()) * mult;
					droppedItem.motionZ = (-0.5F + world.rand.nextFloat()) * mult;

					world.spawnEntityInWorld(droppedItem);
				}
				i++;
			}
		}

		super.breakBlock(world, x, y, z, blockID, meta);
	}
}
