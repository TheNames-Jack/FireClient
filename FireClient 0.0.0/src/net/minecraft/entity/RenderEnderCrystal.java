package net.minecraft.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.model.ModelBase;
import net.minecraft.model.ModelEnderCrystal;
import net.minecraft.model.ModelEnderCrystalBaseless;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Render;

public class RenderEnderCrystal extends Render {
	public boolean shouldUseBase;
	private ModelBase enderCrystalModel;

	public RenderEnderCrystal(boolean flag) {
		shouldUseBase = flag;
		shadowSize = 0.5F;
	}

	public void handleEnderCrystalRendering(EntityEnderCrystal entityendercrystal, double d, double d1, double d2, float f, float f1) {
		if(shouldUseBase) {
			enderCrystalModel = new ModelEnderCrystal(0.0F);
		}else {
			enderCrystalModel = new ModelEnderCrystalBaseless(0.0F);
		}
		float f2 = (float) entityendercrystal.field_41032_a + f1;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d, (float) d1, (float) d2);
		loadTexture("/mob/enderdragon/crystal.png");
		float f3 = MathHelper.sin(f2 * 0.2F) / 2.0F + 0.5F;
		f3 = f3 * f3 + f3;
		enderCrystalModel.render(entityendercrystal, 0.0F, f2 * 3F, f3 * 0.2F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}

	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
		handleEnderCrystalRendering((EntityEnderCrystal) entity, d, d1, d2, f, f1);
	}
}
