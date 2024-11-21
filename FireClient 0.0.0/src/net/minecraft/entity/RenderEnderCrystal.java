package net.minecraft.entity;

import org.lwjgl.opengl.GL11;
import net.minecraft.model.ModelBase;
import net.minecraft.model.ModelEnderCrystal;
import net.minecraft.model.ModelEnderCrystalBaseless;
import net.minecraft.util.Render;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.end.DragonFightManager;

public class RenderEnderCrystal extends Render {
	// Re-added integer value since it seems to limit the rendering of ender crystals so no duplicates
	private int initializeEnderCrystal;
	// END
	private ModelBase modelEnderCrystal;
	private boolean shouldUseBase = true;

	public RenderEnderCrystal(boolean shouldUseBase) {
		initializeEnderCrystal = -1;
		shadowSize = 0.5F;
		this.shouldUseBase = shouldUseBase;
	}

	public void handleEnderCrystalModel(EntityEnderCrystal entityendercrystal, double d, double d1, double d2, float f, float f1) {
		if(initializeEnderCrystal != 1 && shouldUseBase) {
			modelEnderCrystal = new ModelEnderCrystal(0.0F);
			initializeEnderCrystal = 1;
		}else if(initializeEnderCrystal != 1 && !shouldUseBase) {
			modelEnderCrystal = new ModelEnderCrystalBaseless(0.0F);
			initializeEnderCrystal = 1;
		}
		float f2 = (float) entityendercrystal.field_41032_a + f1;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d, (float) d1, (float) d2);
		loadTexture("/mob/enderdragon/crystal.png");
		float f3 = MathHelper.sin(f2 * 0.2F) / 2.0F + 0.5F;
		f3 = f3 * f3 + f3;
		if(DragonFightManager.isRespawning 
				&& entityendercrystal.isRespawnCrystal) {
			float timerProgress = (float) DragonFightManager.respawnTimer / DragonFightManager.MAX_RESPAWN_TIMER;
			float interpolatedScale = 1 + (-1 - 1) * timerProgress / 2;
			GL11.glScalef(interpolatedScale, interpolatedScale, interpolatedScale);
		}
		modelEnderCrystal.render(entityendercrystal, 0.0F, f2 * 3F, f3 * 0.2F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}

	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
		handleEnderCrystalModel((EntityEnderCrystal) entity, d, d1, d2, f, f1);
	}
}