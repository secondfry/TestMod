package ru.secondfry.TestMod.network;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.logging.LogAgent;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import ru.secondfry.TestMod.ModInformation;
import ru.secondfry.TestMod.client.sounds.ESound;
import ru.secondfry.TestMod.tileentities.TileEntityFirework;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 21.09.13 5:23
 */
public class PacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		ByteArrayDataInput reader = ByteStreams.newDataInput(packet.data);

		int packetID = reader.readByte();
		double xCoord = reader.readDouble();
		double yCoord = reader.readDouble();
		double zCoord = reader.readDouble();

		switch(packetID) {
			case 0:
				((EntityPlayer) player).worldObj.spawnParticle("reddust", xCoord + 0.5D, yCoord + 1D, zCoord + 0.5D, 0.0D, 0.0D, 0.0D);
				break;
			case 1:
				Random myRandom = new Random();
				float pitch = myRandom.nextFloat() - myRandom.nextFloat();
				ESound.ROCKET_FIRED.play(xCoord, yCoord, zCoord, 1, pitch);
				break;
			default:
				System.err.append("Unknown packet received!");
		}
	}

	public static void sendPacket(TileEntityFirework tileEntityFirework, int dimID, byte packetID) {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream dataStream = new DataOutputStream(byteStream);

		try {
			dataStream.writeByte(packetID);
			dataStream.writeDouble(tileEntityFirework.xCoord);
			dataStream.writeDouble(tileEntityFirework.yCoord);
			dataStream.writeDouble(tileEntityFirework.zCoord);

			Packet myPacket = PacketDispatcher.getPacket(ModInformation.CHANNEL, byteStream.toByteArray());
			PacketDispatcher.sendPacketToAllAround(tileEntityFirework.xCoord, tileEntityFirework.yCoord, tileEntityFirework.zCoord, 16D, dimID, myPacket);
		} catch (IOException exception) {
			System.err.append("Cant send particle packet! E: " + exception);
		}
	}

}
