package ru.secondfry.TestMod.entities;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import ru.secondfry.TestMod.blocks.BlockInfo;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 21.09.13 7:41
 */
public class EntityRocket extends Entity {

	private final double start;
	private int type = -1;

	public EntityRocket(World world) {
		super(world);
		motionY = 0.5;
		start = posY;
		System.out.println(start);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (!worldObj.isRemote) {
			if (posY - start >= 10) {
				setDead();
			}
		} else {
			int xCoord = (int) (posX - 0.5F);
			int yCoord = (int) posY;
			int zCoord = (int) (posZ - 0.5F);
			if (type == -1 && worldObj.getBlockId(xCoord, yCoord, zCoord) == BlockInfo.FIREWORK_ID) {
				type = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
			}
		}

		setPosition(posX + motionX, posY + motionY, posZ + motionZ);
	}

	public int getType() {
		return type;
	}

	@Override
	protected void entityInit() {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		type = compound.getByte("Type");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setByte("Type", (byte) type);
	}
}
