package ru.secondfry.TestMod.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import java.util.ArrayList;

@SideOnly(Side.CLIENT)
public class ModelRocket extends ModelBase {
	private ArrayList<ModelRenderer> parts = new ArrayList<ModelRenderer>();

	public ModelRocket() {
		textureWidth = 16;
		textureHeight = 32;

		for (int i = 0; i < 3; i++) {
			ModelRenderer side = new ModelRenderer(this, 0, 0);
			parts.add(side);

			side.addBox(-2, -8, 0.6F,
					4, 16, 1,
					0.0F);

			side.setRotationPoint(0, 0, 0);

			side.rotateAngleY = i * (float) Math.PI * 2F / 3;
		}

		for (int i = 0; i < 32; i++) {
			ModelRenderer anchor = new ModelRenderer(this, 0, 0);
			parts.add(anchor);
			ModelRenderer side = new ModelRenderer(this, 0, 17);
			anchor.addChild(side);

			side.addBox(-3, -3, 1.5F,
					6, 6, 1,
					0.0F);

			side.setRotationPoint(0, -4.5F, 0);

			anchor.rotateAngleY = i * (float) Math.PI * 2F / 32;
			side.rotateAngleX = (float) Math.PI / 4;
		}


	}


	public void render(Entity entity, float val1, float val2, float val3, float val4, float val5, float mult) {
		for (ModelRenderer part : parts) {
			part.render(mult);
		}
	}
}