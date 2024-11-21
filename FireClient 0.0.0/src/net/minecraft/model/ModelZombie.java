package net.minecraft.model;

import net.minecraft.util.math.MathHelper;

public class ModelZombie extends ModelBiped {
	public ModelZombie() {
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5);
		float f6 = MathHelper.sin(onGround * 3.141593F);
		float f7 = MathHelper.sin((1.0F - (1.0F - onGround) * (1.0F - onGround)) * 3.141593F);
		rightArm.rotateAngleZ = 0.0F;
		leftArm.rotateAngleZ = 0.0F;
		rightArm.rotateAngleY = -(0.1F - f6 * 0.6F);
		leftArm.rotateAngleY = 0.1F - f6 * 0.6F;
		rightArm.rotateAngleX = -1.570796F;
		leftArm.rotateAngleX = -1.570796F;
		rightArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
		leftArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
		rightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		leftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		rightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
		leftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
	}
}