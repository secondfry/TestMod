package ru.secondfry.TestMod.entities;

import cpw.mods.fml.common.registry.EntityRegistry;
import ru.secondfry.TestMod.TestMod;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 21.09.13 7:46
 */
public class Entities {

	public static void init() {
		int i = 0;
		EntityRegistry.registerModEntity(EntityRocket.class, EntityInfo.ROCKET_KEY, i++, TestMod.instance, 80, 3, true);// magic 80, 3, true
	}

}
