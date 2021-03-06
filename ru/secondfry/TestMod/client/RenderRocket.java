package ru.secondfry.TestMod.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.secondfry.TestMod.ModInformation;
import ru.secondfry.TestMod.entities.EntityRocket;

@SideOnly(Side.CLIENT)
public class RenderRocket extends Render {

	private int type = -1;
	private static final String TEXTURE_LOCATION = ModInformation.ID.toLowerCase();

	private static final ResourceLocation[] textures = new ResourceLocation[]{
			new ResourceLocation(TEXTURE_LOCATION, "textures/models/rocket1.png"),
			new ResourceLocation(TEXTURE_LOCATION, "textures/models/rocket2.png"),
			new ResourceLocation(TEXTURE_LOCATION, "textures/models/rocket3.png")
	};

	protected ModelRocket model;

	public RenderRocket() {
		shadowSize = 0.5F;
		model = new ModelRocket();
	}

	public void renderRocket(EntityRocket rocket, double x, double y, double z, float yaw, float partialTickTime) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(-1.0F, -1.0F, 1.0F);

		bindEntityTexture(rocket);

		model.render(rocket, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		GL11.glPopMatrix();
	}


	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTickTime) {
		this.renderRocket((EntityRocket) entity, x, y, z, yaw, partialTickTime);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		int type = ((EntityRocket) entity).getType();
		if (type >= 0 && type <= textures.length)
			return textures[type];
		else
			return textures[0];
	}
}
