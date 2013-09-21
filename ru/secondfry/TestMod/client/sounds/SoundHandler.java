package ru.secondfry.TestMod.client.sounds;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 21.09.13 19:12
 */

@SideOnly(Side.CLIENT)
public class SoundHandler {

	public SoundHandler() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@ForgeSubscribe
	public void onSoundsLoad(SoundLoadEvent event) {
		for (ESound sound : ESound.values()) {
			addSound(event, sound);
		}
	}

	private void addSound(SoundLoadEvent event, ESound sound) {
		event.manager.addSound(ESound.SOUNDS_LOCATION + ":" + sound.getName() + ".ogg");
	}
}
