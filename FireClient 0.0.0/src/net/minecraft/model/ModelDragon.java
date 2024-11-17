package net.minecraft.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.enderdragon.EntityEnderdragon;

public class ModelDragon extends ModelBase {

	private ModelRenderer head;
	private ModelRenderer neck;
	private ModelRenderer jaw;
	private ModelRenderer body;
	private ModelRenderer rearLeg;
	private ModelRenderer frontLeg;
	private ModelRenderer rearLegTip;
	private ModelRenderer frontLegTip;
	private ModelRenderer rearFoot;
	private ModelRenderer frontFoot;
	private ModelRenderer wing;
	private ModelRenderer wingTip;
	private float field_40317_s;

	public ModelDragon(float f) {
		textureWidth = 256;
		textureHeight = 256;
		setTextureOffset("body.body", 0, 0);
		setTextureOffset("wing.skin", -56, 88);
		setTextureOffset("wingtip.skin", -56, 144);
		setTextureOffset("rearleg.main", 0, 0);
		setTextureOffset("rearfoot.main", 112, 0);
		setTextureOffset("rearlegtip.main", 196, 0);
		setTextureOffset("head.upperhead", 112, 30);
		setTextureOffset("wing.bone", 112, 88);
		setTextureOffset("head.upperlip", 176, 44);
		setTextureOffset("jaw.jaw", 176, 65);
		setTextureOffset("frontleg.main", 112, 104);
		setTextureOffset("wingtip.bone", 112, 136);
		setTextureOffset("frontfoot.main", 144, 104);
		setTextureOffset("neck.box", 192, 104);
		setTextureOffset("frontlegtip.main", 226, 138);
		setTextureOffset("body.scale", 220, 53);
		setTextureOffset("head.scale", 0, 0);
		setTextureOffset("neck.scale", 48, 0);
		setTextureOffset("head.nostril", 112, 0);
		float f1 = -16F;
		head = new ModelRenderer(this, "head");
		head.addBox("upperlip", -6F, -1F, -8F + f1, 12, 5, 16);
		head.addBox("upperhead", -8F, -8F, 6F + f1, 16, 16, 16);
		head.mirror = true;
		head.addBox("scale", -5F, -12F, 12F + f1, 2, 4, 6);
		head.addBox("nostril", -5F, -3F, -6F + f1, 2, 2, 4);
		head.mirror = false;
		head.addBox("scale", 3F, -12F, 12F + f1, 2, 4, 6);
		head.addBox("nostril", 3F, -3F, -6F + f1, 2, 2, 4);
		jaw = new ModelRenderer(this, "jaw");
		jaw.setRotationPoint(0.0F, 4F, 8F + f1);
		jaw.addBox("jaw", -6F, 0.0F, -16F, 12, 4, 16);
		head.addChild(jaw);
		neck = new ModelRenderer(this, "neck");
		neck.addBox("box", -5F, -5F, -5F, 10, 10, 10);
		neck.addBox("scale", -1F, -9F, -3F, 2, 4, 6);
		body = new ModelRenderer(this, "body");
		body.setRotationPoint(0.0F, 4F, 8F);
		body.addBox("body", -12F, 0.0F, -16F, 24, 24, 64);
		body.addBox("scale", -1F, -6F, -10F, 2, 6, 12);
		body.addBox("scale", -1F, -6F, 10F, 2, 6, 12);
		body.addBox("scale", -1F, -6F, 30F, 2, 6, 12);
		wing = new ModelRenderer(this, "wing");
		wing.setRotationPoint(-12F, 5F, 2.0F);
		wing.addBox("bone", -56F, -4F, -4F, 56, 8, 8);
		wing.addBox("skin", -56F, 0.0F, 2.0F, 56, 0, 56);
		wingTip = new ModelRenderer(this, "wingtip");
		wingTip.setRotationPoint(-56F, 0.0F, 0.0F);
		wingTip.addBox("bone", -56F, -2F, -2F, 56, 4, 4);
		wingTip.addBox("skin", -56F, 0.0F, 2.0F, 56, 0, 56);
		wing.addChild(wingTip);
		frontLeg = new ModelRenderer(this, "frontleg");
		frontLeg.setRotationPoint(-12F, 20F, 2.0F);
		frontLeg.addBox("main", -4F, -4F, -4F, 8, 24, 8);
		frontLegTip = new ModelRenderer(this, "frontlegtip");
		frontLegTip.setRotationPoint(0.0F, 20F, -1F);
		frontLegTip.addBox("main", -3F, -1F, -3F, 6, 24, 6);
		frontLeg.addChild(frontLegTip);
		frontFoot = new ModelRenderer(this, "frontfoot");
		frontFoot.setRotationPoint(0.0F, 23F, 0.0F);
		frontFoot.addBox("main", -4F, 0.0F, -12F, 8, 4, 16);
		frontLegTip.addChild(frontFoot);
		rearLeg = new ModelRenderer(this, "rearleg");
		rearLeg.setRotationPoint(-16F, 16F, 42F);
		rearLeg.addBox("main", -8F, -4F, -8F, 16, 32, 16);
		rearLegTip = new ModelRenderer(this, "rearlegtip");
		rearLegTip.setRotationPoint(0.0F, 32F, -4F);
		rearLegTip.addBox("main", -6F, -2F, 0.0F, 12, 32, 12);
		rearLeg.addChild(rearLegTip);
		rearFoot = new ModelRenderer(this, "rearfoot");
		rearFoot.setRotationPoint(0.0F, 31F, 4F);
		rearFoot.addBox("main", -9F, 0.0F, -20F, 18, 6, 24);
		rearLegTip.addChild(rearFoot);
	}

	public void setLivingAnimations(EntityLiving entityliving, float f, float f1, float f2) {
		field_40317_s = f2;
	}

	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		GL11.glPushMatrix();
		EntityEnderdragon entitydragon = (EntityEnderdragon) entity;
		float f6 = entitydragon.field_40173_aw + (entitydragon.field_40172_ax - entitydragon.field_40173_aw) * field_40317_s;

		// Jaw movement (more subtle and natural)
		jaw.rotateAngleX = (float) (Math.sin(f6 * Math.PI * 2.0F) + 1.0D) * 0.15F;

		// Body motion for lifelike undulation
		float undulation = (float) Math.sin(f6 * Math.PI * 2.0F) * 1.2F;
		GL11.glTranslatef(0.0F, undulation - 2.0F, -3F);
		GL11.glRotatef(undulation * 10.0F, 1.0F, 0.0F, 0.0F);

		float baseHeight = -10F;
		float baseForward = 22F;
		float baseSide = 0.0F;
		double[] neckPositions = entitydragon.func_40160_a(6, field_40317_s);

		float neckYaw = updateRotations(entitydragon.func_40160_a(5, field_40317_s)[0] - entitydragon.func_40160_a(10, field_40317_s)[0]);
		float neckYawMid = updateRotations(entitydragon.func_40160_a(5, field_40317_s)[0] + (double) (neckYaw / 2.0F));
		baseHeight += 2.0F;

		// Smooth neck undulation
		float neckAngleModulation = 0.0F;
		float neckAnimationOffset = (float) (f6 * Math.PI * 5.0F);
		baseHeight = 20F;
		baseForward = -12F;

		for(int i = 0; i < 5; i++) {
			double[] currentNeckPosition = entitydragon.func_40160_a(5 - i, field_40317_s);
			neckAngleModulation = (float) Math.cos((float) i * 0.45F + neckAnimationOffset) * 0.1F;

			neck.rotateAngleY = (float) ((updateRotations(currentNeckPosition[0] - neckPositions[0]) * Math.PI) / 180F);
			neck.rotateAngleX = (float) (neckAngleModulation + (((float) (currentNeckPosition[1] - neckPositions[1]) * Math.PI) / 180F));
			neck.rotateAngleZ = (float) ((-updateRotations(currentNeckPosition[0] - neckYawMid) * Math.PI) / 180F);

			neck.rotationPointY = baseHeight;
			neck.rotationPointZ = baseForward;
			neck.rotationPointX = baseSide;

			baseHeight = (float) ((double) baseHeight + Math.sin(neck.rotateAngleX) * 10D);
			baseForward = (float) ((double) baseForward - Math.cos(neck.rotateAngleY) * Math.cos(neck.rotateAngleX) * 10D);
			baseSide = (float) ((double) baseSide - Math.sin(neck.rotateAngleY) * Math.cos(neck.rotateAngleX) * 10D);

			neck.render(scaleFactor);
		}

		head.rotationPointY = baseHeight;
		head.rotationPointZ = baseForward;
		head.rotationPointX = baseSide;

		double[] headPositions = entitydragon.func_40160_a(0, field_40317_s);
		head.rotateAngleY = (float) ((updateRotations(headPositions[0] - neckPositions[0]) * Math.PI) / 180F);
		head.rotateAngleZ = (float) ((-updateRotations(headPositions[0] - neckYawMid) * Math.PI) / 180F);
		head.render(scaleFactor);

		GL11.glPushMatrix();
		GL11.glTranslatef(0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-neckYaw * 1.0F, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(0.0F, -1F, 0.0F);

		body.rotateAngleZ = undulation * 0.5F;
		body.render(scaleFactor);

		for(int j = 0; j < 2; j++) {
			GL11.glEnable(GL11.GL_CULL_FACE);
			float wingFlap = (float) (f6 * Math.PI * 3.0F);

			// Wing animations
			wing.rotateAngleX = 0.125F - (float) Math.cos(wingFlap) * 0.2F;
			wing.rotateAngleY = 0.25F;
			wing.rotateAngleZ = (float) (Math.sin(wingFlap) + 0.125D) * 0.9F;
			wingTip.rotateAngleZ = -(float) (Math.sin(wingFlap + 2.0F) + 0.5D) * 0.75F;

			// Leg animations
			rearLeg.rotateAngleX = 1.0F + undulation * 0.1F;
			rearLegTip.rotateAngleX = 0.5F + undulation * 0.1F;
			rearFoot.rotateAngleX = 0.75F + undulation * 0.1F;
			frontLeg.rotateAngleX = 1.3F + undulation * 0.1F;
			frontLegTip.rotateAngleX = -0.5F - undulation * 0.1F;
			frontFoot.rotateAngleX = 0.75F + undulation * 0.1F;

			wing.render(scaleFactor);
			frontLeg.render(scaleFactor);
			rearLeg.render(scaleFactor);

			GL11.glScalef(-1F, 1.0F, 1.0F);
			if(j == 0) {
				GL11.glCullFace(GL11.GL_FRONT);
			}
		}

		GL11.glPopMatrix();
		GL11.glCullFace(GL11.GL_BACK);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5);
	}

	private float updateRotations(double d) {
		for(; d >= 180D; d -= 360D) {
		}
		for(; d < -180D; d += 360D) {
		}
		return (float) d;
	}
}