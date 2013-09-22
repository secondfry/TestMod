package ru.secondfry.TestMod.tileentities;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import ru.secondfry.TestMod.blocks.BlockInfo;
import ru.secondfry.TestMod.entities.EntityRocket;
import ru.secondfry.TestMod.network.PacketHandler;
import ru.secondfry.TestMod.network.PacketInfo;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 21.09.13 7:20
 */
public class TileEntityFirework extends TileEntity implements IInventory {

	private static final int CLOCK = 60;
	private static final int FIRE_ON = 3;

	private short timer;
	private byte level;
	private int type;
	private int rocketEntityID;

	private ItemStack[] items;

	public TileEntityFirework() {
		timer = CLOCK;
		level = 0;

		items = new ItemStack[3];
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public int getRocketEntityID() {
		return rocketEntityID;
	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (timer == 0 && level < FIRE_ON) {
				timer = CLOCK;
				level++;
				PacketHandler.sendPacket(this, worldObj.provider.dimensionId, PacketInfo.SEND_PARTICLE);
			} else if (timer == 0 && level == FIRE_ON) {
				timer = CLOCK;
				level = 0;
				EntityRocket rocket = new EntityRocket(worldObj);
				rocket.setType(getType());
				rocket.setStart(yCoord);
				rocket.posX = xCoord + 0.5F;
				rocket.posY = yCoord;
				rocket.posZ = zCoord + 0.5F;
				worldObj.spawnEntityInWorld(rocket);
				this.rocketEntityID = rocket.entityId;
				PacketHandler.sendPacket(this, worldObj.provider.dimensionId, PacketInfo.SEND_ROCKET_INFO);
			}
			timer--;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);

		timer = compound.getShort("Timer");
		level = compound.getByte("Level");
		rocketEntityID = compound.getInteger("RocketEntityID");
		type = compound.getInteger("Type");
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		compound.setShort("Timer", timer);
		compound.setByte("Level", level);
		compound.setInteger("RocketEntityID", rocketEntityID);
		compound.setInteger("Type", type);
	}

	// Inventory part
	@Override
	public int getSizeInventory() {
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return items[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int count) {
		ItemStack itemstack = getStackInSlot(i);

		if (itemstack != null) {
			if (itemstack.stackSize <= count) {
				setInventorySlotContents(i, null);
			} else {
				itemstack = itemstack.splitStack(count);
				onInventoryChanged();
			}
		}

		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack itemStack = getStackInSlot(i);
		setInventorySlotContents(i, null);
		return itemStack;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		items[i] = itemstack;

		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}

		onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return BlockInfo.FIREWORK_TE_NAME;
	}

	@Override
	public boolean isInvNameLocalized() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return BlockInfo.FIREWORK_TE_STACKLIMIT;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return entityplayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
	}

	@Override
	public void openChest() { // Used only by chests
	}

	@Override
	public void closeChest() { // Used only by chests
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return itemstack.itemID == Block.anvil.blockID;
	}

}
