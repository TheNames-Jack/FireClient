package net.minecraft.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.entity.Entity;

public class ModelEnderCrystalBaseless extends ModelBase {
	public ModelRenderer crystal;
	public ModelRenderer glass;

	public ModelEnderCrystalBaseless(float f) {
		glass = new ModelRenderer(this, "glass");
		glass.setTextureOffset(0, 0).addBox(-4F, -4F, -4F, 8, 8, 8);
		crystal = new ModelRenderer(this, "cube");
		crystal.setTextureOffset(32, 0).addBox(-4F, -4F, -4F, 8, 8, 8);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		GL11.glPushMatrix();
		GL11.glScalef(2.0F, 2.0F, 2.0F);
		GL11.glTranslatef(0.0F, -0.5F, 0.0F);
		GL11.glRotatef(f1, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(0.0F, 0.8F + f2, 0.0F);
		GL11.glRotatef(60F, 0.7071F, 0.0F, 0.7071F);
		glass.render(f5);
		float f6 = 0.875F;
		GL11.glScalef(f6, f6, f6);
		GL11.glRotatef(60F, 0.7071F, 0.0F, 0.7071F);
		GL11.glRotatef(f1, 0.0F, 1.0F, 0.0F);
		glass.render(f5);
		GL11.glScalef(f6, f6, f6);
		GL11.glRotatef(60F, 0.7071F, 0.0F, 0.7071F);
		GL11.glRotatef(f1, 0.0F, 1.0F, 0.0F);
		crystal.render(f5);
		GL11.glPopMatrix();
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5);
	}
}