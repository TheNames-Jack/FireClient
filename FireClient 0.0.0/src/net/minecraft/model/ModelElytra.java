package net.minecraft.model;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3D;

public class ModelElytra extends ModelBiped {
	ModelRenderer leftWing;
	ModelRenderer rightWing;
	public boolean showElytra;

	public ModelElytra() {
		this(0.0F);
	}

	public ModelElytra(float padding) {
		this(padding, 0.0F);
	}

	public ModelElytra(float padding, float yOffset) {
		rightWing = new ModelRenderer(this, 22, 0);
		rightWing.addBox(-10.0F, 0.0F, 0.3F, 10, 20, 2, 1.0F);
		leftWing = new ModelRenderer(this, 22, 0);
		leftWing.mirror = true;
		leftWing.addBox(0F, 0.0F, 0.3F, 10, 20, 2, 1.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		setRotationAngles(entity, f, f1, f2, f3, f4, f5);
		leftWing.render(f5);
		rightWing.render(f5);
	}

	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
		float f = 0.2617994F;
		float f1 = -0.2617994F;
		float f2 = 0.0F;
		float f3 = 0.0F;
		// Bobbing effect
		float bobAmount = 0.05F; // Adjust this value to change the intensity of the bob
		float bob = MathHelper.sin(limbSwing * 1.0F) * bobAmount; // Oscillation

		this.rightWing.rotationPointX = 5.0F;
		this.rightWing.rotationPointY = f2;

		if(entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			if(player.isUsingElytra()) {
				float f4 = 1.0F;
				if(entity.motionY < 0.0D) {
					Vec3D vec3d = (new Vec3D(entity.motionX, entity.motionY, entity.motionZ)).normalize();
					f4 = 1.0F - (float) Math.pow(-vec3d.yCoord, 1.5D);
				}
				f = f4 * 0.34906584F + (1.0F - f4) * f;
				f1 = f4 * -((float) Math.PI / 2F) + (1.0F - f4) * f1;
				player.rotateElytraX = (float) ((double) player.rotateElytraX + (double) (f - player.rotateElytraX) * 0.1D);
				player.rotateElytraY = (float) ((double) player.rotateElytraZ + (double) (f3 - player.rotateElytraZ) * 0.9D);
				player.rotateElytraZ = (float) ((double) player.rotateElytraZ + (double) (f1 - player.rotateElytraZ) * 0.1D);
				this.rightWing.rotateAngleX = player.rotateElytraX;
				this.rightWing.rotateAngleY = player.rotateElytraY;
				this.rightWing.rotateAngleZ = player.rotateElytraZ;
			}else if(player.isSneaking()) {
				f = ((float) Math.PI * 2F / 9F);
				f1 = -((float) Math.PI / 4.8F);
				f2 = 3.0F;
				f3 = 0.08726646F;
				this.rightWing.rotateAngleX = f;
				this.rightWing.rotateAngleZ = f1;
				this.rightWing.rotateAngleY = f3;
			}else {
				this.rightWing.rotateAngleX = (float) Math.max(0.22536719F, limbSwingAmount) + bob + (float)player.motionX;
				this.rightWing.rotateAngleZ = Math.min(-0.25463282F, -limbSwingAmount - 0.25F) + bob + (float)player.motionY;
				this.rightWing.rotateAngleY = bob + 0.25F + (float)player.motionZ;
			}
		}
		this.leftWing.rotationPointX = -this.rightWing.rotationPointX;
		this.leftWing.rotateAngleY = -this.rightWing.rotateAngleY;
		this.leftWing.rotationPointY = this.rightWing.rotationPointY;
		this.leftWing.rotateAngleX = this.rightWing.rotateAngleX;
		this.leftWing.rotateAngleZ = -this.rightWing.rotateAngleZ;
	}

	@Override
	public void setLivingAnimations(EntityLiving entityliving, float f, float f1, float f2) {
		super.setLivingAnimations(entityliving, f, f1, f2);
	}
}