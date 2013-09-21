package ru.secondfry.TestMod.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import ru.secondfry.TestMod.entities.EntityRocket;
import ru.secondfry.TestMod.network.PacketHandler;
import ru.secondfry.TestMod.network.PacketInfo;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 21.09.13 7:20
 */
public class TileEntityFirework extends TileEntity {

	private static final int CLOCK = 60;
	private static final int FIRE_ON = 3;

	private short timer;
	private byte level;

	public TileEntityFirework() {
		timer = CLOCK;
		level = 0;
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
				rocket.posX = xCoord + 0.5F;
				rocket.posY = yCoord;
				rocket.posZ = zCoord + 0.5F;
				worldObj.spawnEntityInWorld(rocket);
				PacketHandler.sendPacket(this, worldObj.provider.dimensionId, PacketInfo.SEND_ROCKET_SOUND);
			}
			timer--;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);

		timer = compound.getShort("Timer");
		level = compound.getByte("Level");
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		compound.setShort("Timer", timer);
		compound.setByte("Level", level);
	}


}
