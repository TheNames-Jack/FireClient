package net.minecraft.model;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelPlayer extends ModelBase {
	public ModelRenderer head;
	public ModelRenderer body;
	public ModelRenderer rightArm;
	public ModelRenderer leftArm;
	public ModelRenderer rightLeg;
	public ModelRenderer leftLeg;
	public ModelRenderer ears;
	public ModelRenderer cape;
	public ModelRenderer headOverlay;
	public ModelRenderer bodyOverlay;
	public ModelRenderer rightArmOverlay;
	public ModelRenderer leftArmOverlay;
	public ModelRenderer rightLegOverlay;
	public ModelRenderer leftLegOverlay;
	public int field_1279_h;
	public int heldItemRight;
	public boolean isSneak;
	public boolean aimedBow;
	public boolean usingElytra = false;
	public boolean useOverlays = false;
	
	public ModelPlayer() {
		this(0.0F);
	}

	public ModelPlayer(float f) {
		this(f, 0.0F);
	}

	public ModelPlayer(float width, float height) {
		field_1279_h = 0;
		heldItemRight = 0;
		isSneak = false;
		aimedBow = false;
		cape = new ModelRenderer(this, 0, 0);
		cape.addBox(-5F, 0.0F, -1F, 10, 16, 1, width);
		ears = new ModelRenderer(this, 24, 0);
		ears.addBox(-3F, -6F, -1F, 6, 6, 1, width);
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 8, 8, 8, width);
		head.setRotationPoint(0.0F, 0.0F + height, 0.0F);
		headOverlay = new ModelRenderer(this, 32, 0);
		headOverlay.addBox(-4F, -8F, -4F, 8, 8, 8, width + 0.5F);
		headOverlay.setRotationPoint(0.0F, 0.0F + height, 0.0F);
		body = new ModelRenderer(this, 16, 16);
		body.addBox(-4F, 0.0F, -2F, 8, 12, 4, width);
		body.setRotationPoint(0.0F, 0.0F + height, 0.0F);
		rightArm = new ModelRenderer(this, 40, 16);
		rightArm.addBox(-3F, -2F, -2F, 4, 12, 4, width);
		rightArm.setRotationPoint(-5F, 2.0F + height, 0.0F);
		leftArm = new ModelRenderer(this, 40, 16);
		leftArm.mirror = true;
		leftArm.addBox(-1F, -2F, -2F, 4, 12, 4, width);
		leftArm.setRotationPoint(5F, 2.0F + height, 0.0F);
		rightLeg = new ModelRenderer(this, 0, 16);
		rightLeg.addBox(-2F, 0.0F, -2F, 4, 12, 4, width);
		rightLeg.setRotationPoint(-2F, 12F + height, 0.0F);
		leftLeg = new ModelRenderer(this, 0, 16);
		leftLeg.mirror = false;
		leftLeg.addBox(-2F, 0.0F, -2F, 4, 12, 4, width);
		leftLeg.setRotationPoint(2.0F, 12F + height, 0.0F);
		
//		this.headOverlay = new ModelRenderer(this, 32, 0);
//		this.headOverlay.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, width + 0.5F);
//		this.headOverlay.setRotationPoint(0.0F, 0.0F + height, 0.0F);
//		this.bodyOverlay = new ModelRenderer(this, 16, 32);
//		this.bodyOverlay.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, width + 0.5F);
//		this.bodyOverlay.setRotationPoint(0.0F, 0.0F + height, 0.0F);
//		this.rightArmOverlay = new ModelRenderer(this, 40, 32);
//		this.rightArmOverlay.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, width + 0.5F);
//		this.rightArmOverlay.setRotationPoint(-5.0F, 2.0F + height, 0.0F);
//		this.leftArmOverlay = new ModelRenderer(this, 48, 48);
//		this.leftArmOverlay.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, width + 0.5F);
//		this.leftArmOverlay.setRotationPoint(5.0F, 2.0F + height, 0.0F);
//		
//		if(Main.modelType == IModelType.classic) {
//			
//		}else if(Main.modelType == IModelType.slim) {
//			this.rightArm = new ModelRenderer(textureWidth, textureHeight, 40, 16);
//			this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 3, 12, 4, width);
//			this.rightArm.setRotationPoint(-5.0F, 2.0F + height, 0.0F);
//			this.leftArm = new ModelRenderer(textureWidth, textureHeight, 32, 48);
//			this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, width);
//			this.leftArm.setRotationPoint(5.0F, 2.0F + height, 0.0F);
//			this.rightArmOverlay = new ModelRenderer(textureWidth, textureHeight, 40, 32);
//			this.rightArmOverlay.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, width + 0.5F);
//			this.rightArmOverlay.setRotationPoint(-5.0F, 2.0F + height, 0.0F);
//			this.leftArmOverlay = new ModelRenderer(textureWidth, textureHeight, 48, 48);
//			this.leftArmOverlay.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, width + 0.5F);
//			this.leftArmOverlay.setRotationPoint(5.0F, 2.0F + height, 0.0F);
//		}
//		this.rightLegOverlay = new ModelRenderer(this, 0, 32);
//		this.rightLegOverlay.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, width + 0.5F);
//		this.rightLegOverlay.setRotationPoint(-2.0F, 12.0F + height, 0.0F);
//		this.leftLegOverlay = new ModelRenderer(this, 0, 48);
//		this.leftLegOverlay.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, width + 0.5F);
//		this.leftLegOverlay.setRotationPoint(2.0F, 12.0F + height, 0.0F);
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		setRotationAngles(f, f1, f2, f3, f4, f5);
		head.render(f5);
		body.render(f5);
		rightArm.render(f5);
		leftArm.render(f5);
		rightLeg.render(f5);
		leftLeg.render(f5);
		headOverlay.render(f5);
//		if(useOverlays) {
//			headOverlay.render(f5);
//			bodyOverlay.render(f5);
//			rightArmOverlay.render(f5);
//			leftArmOverlay.render(f5);
//			rightLegOverlay.render(f5);
//			leftLegOverlay.render(f5);	
//		}
	}
	
	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
		head.rotateAngleY = f3 / 57.29578F;
		head.rotateAngleX = f4 / 57.29578F;
		rightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 2.0F * f1 * 0.5F;
		leftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
		rightArm.rotateAngleZ = 0.0F;
		leftArm.rotateAngleZ = 0.0F;
		rightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		leftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
		rightLeg.rotateAngleY = 0.0F;
		leftLeg.rotateAngleY = 0.0F;
		if(isRiding) {
			rightArm.rotateAngleX += -0.6283185F;
			leftArm.rotateAngleX += -0.6283185F;
			rightLeg.rotateAngleX = -1.256637F;
			leftLeg.rotateAngleX = -1.256637F;
			rightLeg.rotateAngleY = 0.3141593F;
			leftLeg.rotateAngleY = -0.3141593F;
		}
		if(field_1279_h != 0) {
			leftArm.rotateAngleX = leftArm.rotateAngleX * 0.5F - 0.3141593F * (float) field_1279_h;
		}
		if(heldItemRight != 0) {
			rightArm.rotateAngleX = rightArm.rotateAngleX * 0.5F - 0.3141593F * (float) heldItemRight;
		}
		rightArm.rotateAngleY = 0.0F;
		leftArm.rotateAngleY = 0.0F;
		if(onGround > -9990F) {
			float f6 = onGround;
			body.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * 3.141593F * 2.0F) * 0.2F;
			rightArm.rotationPointZ = MathHelper.sin(body.rotateAngleY) * 5F;
			rightArm.rotationPointX = -MathHelper.cos(body.rotateAngleY) * 5F;
			leftArm.rotationPointZ = -MathHelper.sin(body.rotateAngleY) * 5F;
			leftArm.rotationPointX = MathHelper.cos(body.rotateAngleY) * 5F;
			rightArm.rotateAngleY += body.rotateAngleY;
			leftArm.rotateAngleY += body.rotateAngleY;
			leftArm.rotateAngleX += body.rotateAngleY;
			f6 = 1.0F - onGround;
			f6 *= f6;
			f6 *= f6;
			f6 = 1.0F - f6;
			float f8 = MathHelper.sin(f6 * 3.141593F);
			float f10 = MathHelper.sin(onGround * 3.141593F) * -(head.rotateAngleX - 0.7F) * 0.75F;
			rightArm.rotateAngleX -= (double) f8 * 1.2D + (double) f10;
			rightArm.rotateAngleY += body.rotateAngleY * 2.0F;
			rightArm.rotateAngleZ = MathHelper.sin(onGround * 3.141593F) * -0.4F;
		}
		if(isSneak) {
			body.rotateAngleX = 0.5F;
			rightLeg.rotateAngleX -= 0.0F;
			leftLeg.rotateAngleX -= 0.0F;
			rightArm.rotateAngleX += 0.4F;
			leftArm.rotateAngleX += 0.4F;
			rightLeg.rotationPointZ = 4F;
			leftLeg.rotationPointZ = 4F;
			rightLeg.rotationPointY = 9F;
			leftLeg.rotationPointY = 9F;
			head.rotationPointY = 1.0F;
		}else {
			body.rotateAngleX = 0.0F;
			rightLeg.rotationPointZ = 0.0F;
			leftLeg.rotationPointZ = 0.0F;
			rightLeg.rotationPointY = 12F;
			leftLeg.rotationPointY = 12F;
			head.rotationPointY = 0.0F;
		}
		rightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		leftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		rightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
		leftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
		if(aimedBow) {
			float f7 = 0.0F;
			float f9 = 0.0F;
			rightArm.rotateAngleZ = 0.0F;
			leftArm.rotateAngleZ = 0.0F;
			rightArm.rotateAngleY = -(0.1F - f7 * 0.6F) + head.rotateAngleY;
			leftArm.rotateAngleY = (0.1F - f7 * 0.6F) + head.rotateAngleY + 0.4F;
			rightArm.rotateAngleX = -1.570796F + head.rotateAngleX;
			leftArm.rotateAngleX = -1.570796F + head.rotateAngleX;
			rightArm.rotateAngleX -= f7 * 1.2F - f9 * 0.4F;
			leftArm.rotateAngleX -= f7 * 1.2F - f9 * 0.4F;
			rightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
			leftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
			rightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
			leftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
		}
		CopyModelAngles(head, headOverlay);
//		CopyModelAngles(body, bodyOverlay);
//		CopyModelAngles(rightArm, rightArmOverlay);
//		CopyModelAngles(leftArm, leftArmOverlay);
//		CopyModelAngles(rightLeg, rightLegOverlay);
//		CopyModelAngles(leftLeg, leftLegOverlay);
	}
	
	public void renderEars(float f) {
		ears.rotateAngleY = head.rotateAngleY;
		ears.rotateAngleX = head.rotateAngleX;
		ears.rotationPointX = 0.0F;
		ears.rotationPointY = 0.0F;
		ears.render(f);
	}

	public void renderCape(float f) {
		cape.render(f);
	}
}