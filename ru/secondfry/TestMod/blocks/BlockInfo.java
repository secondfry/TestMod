package ru.secondfry.TestMod.blocks;

import net.minecraft.block.Block;
import ru.secondfry.TestMod.items.ItemInfo;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 21.09.13 5:37
 */
public class BlockInfo {

	public static int FIREWORK_ID;
	public static final String FIREWORK_KEY = "blockFirework";
	public static final int FIREWORK_DEFAULT = 2701;
	public static final String FIREWORK_UNLOCALIZED_NAME = "blockFirework";
	public static final String FIREWORK_NAME = "Firework for teh goods!";
	public static final String FIREWORK_TOP = "box_top";
	public static final String FIREWORK_BOT = "box_bot";
	public static final String[] FIREWORK_SIDES = {"box_side_type1", "box_side_type2", "box_side_type3"};

	public static final String FIREWORK_TE_KEY = "fireworkTileEntity";
	public static final int FIREWORK_TE_STACKLIMIT = 4;
	public static final String FIREWORK_TE_NAME = "Rocket Launcher";
	public static final int FIREWORK_TE_ITEMID = Block.anvil.blockID; //ItemInfo.ROCKET_ID;

}
