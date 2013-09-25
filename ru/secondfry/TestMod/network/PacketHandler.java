package ru.secondfry.TestMod.network;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import ru.secondfry.TestMod.ModInformation;
import ru.secondfry.TestMod.client.interfaces.ContainerFirework;
import ru.secondfry.TestMod.client.sounds.ESound;
import ru.secondfry.TestMod.entities.EntityRocket;
import ru.secondfry.TestMod.tileentities.TileEntityFirework;

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
		double xCoord = 0, yCoord = -1, zCoord = 0;
		int someID = -1;

		ByteArrayDataInput reader = ByteStreams.newDataInput(packet.data);
		World world = ((EntityPlayer) player).worldObj;

		// Common header
		int packetID = reader.readByte();

		// Specific header
		switch (packetID) {
			case PacketInfo.SEND_PARTICLE:
			case PacketInfo.SEND_ROCKET_INFO:
				xCoord = reader.readFloat();
				yCoord = reader.readFloat();
				zCoord = reader.readFloat();
				break;
			case PacketInfo.SEND_BUTTON:
				int buttonID = reader.readInt();
				someID = buttonID;
				break;
		}

		// Information and action
		switch (packetID) {
			case PacketInfo.SEND_PARTICLE:
				world.spawnParticle("reddust", xCoord + 0.5F, yCoord + 1F, zCoord + 0.5F, 0.0D, 0.0D, 0.0D);
				break;
			case PacketInfo.SEND_ROCKET_INFO:
				Random myRandom = new Random();
				float pitch = myRandom.nextFloat() - myRandom.nextFloat();
				ESound.ROCKET_FIRED.play(xCoord, yCoord, zCoord, 1, pitch);

				int rocketEntityID = reader.readInt();
				int type = reader.readInt();
				((EntityRocket) world.getEntityByID(rocketEntityID)).setType(type);
				break;
			case PacketInfo.SEND_BUTTON:
				Container container = ((EntityPlayer) player).openContainer;
				if (container != null && container instanceof ContainerFirework) {
					TileEntityFirework tileEntityFirework = ((ContainerFirework) container).getTileEntityFirework();
					tileEntityFirework.receiveButtonEvent(someID);
				}
			default:
				System.err.append("Unknown packet received!");
		}
	}

	public static void sendFireworkPacket(byte packetID, TileEntityFirework tileEntityFirework, int dimID) {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream dataStream = new DataOutputStream(byteStream);

		try {
			// Common header
			dataStream.writeByte(packetID);

			// Specific header
			switch (packetID) {
				case PacketInfo.SEND_PARTICLE:
				case PacketInfo.SEND_ROCKET_INFO:
					dataStream.writeFloat(tileEntityFirework.xCoord);
					dataStream.writeFloat(tileEntityFirework.yCoord);
					dataStream.writeFloat(tileEntityFirework.zCoord);
					break;
				case PacketInfo.SEND_BUTTON:
					break;
			}

			// Information
			switch (packetID) {
				case PacketInfo.SEND_ROCKET_INFO:
					dataStream.writeInt(tileEntityFirework.getRocketEntityID());
					dataStream.writeInt(tileEntityFirework.getType());
					break;
				default:
					break;
			}

			Packet myPacket = PacketDispatcher.getPacket(ModInformation.CHANNEL, byteStream.toByteArray());
			PacketDispatcher.sendPacketToAllAround(tileEntityFirework.xCoord, tileEntityFirework.yCoord, tileEntityFirework.zCoord, 16D, dimID, myPacket);
		} catch (IOException exception) {
			System.err.append("Cant send particle packet! E: " + exception);
		}
	}

	public static void sendButtonPacket(byte packetID, int buttonID) {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream dataStream = new DataOutputStream(byteStream);

		try {
			// Common header
			dataStream.writeByte(packetID);

			// Information
			switch (packetID) {
				case PacketInfo.SEND_BUTTON:
					dataStream.writeInt(buttonID);
					break;
			}

			Packet myPacket = PacketDispatcher.getPacket(ModInformation.CHANNEL, byteStream.toByteArray());
			PacketDispatcher.sendPacketToServer(myPacket);
		} catch (IOException exception) {
			System.err.append("Cant send particle packet! E: " + exception);
		}
	}

}
