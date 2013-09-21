package ru.secondfry.TestMod.client.sounds;

import net.minecraft.client.Minecraft;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 21.09.13 19:08
 */
public enum ESound {
	ROCKET_FIRED("fire");

	public static final String SOUNDS_LOCATION = "testmod";
	private String name;

	ESound(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void play(double x, double y, double z, float volume, float pitch) {
		Minecraft.getMinecraft().sndManager.playSound(SOUNDS_LOCATION + ":" + name, (float) x, (float) y, (float) z, volume, pitch);
	}

}
