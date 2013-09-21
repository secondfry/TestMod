package ru.secondfry.TestMod.proxies;

import cpw.mods.fml.client.registry.RenderingRegistry;
import ru.secondfry.TestMod.client.RenderRocket;
import ru.secondfry.TestMod.client.sounds.SoundHandler;
import ru.secondfry.TestMod.entities.EntityRocket;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 21.09.13 5:15
 */
public class ClientProxy extends CommonProxy {
	@Override
	public void initRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityRocket.class, new RenderRocket());
	}

	@Override
	public void initSounds() {
		new SoundHandler();
	}
}
