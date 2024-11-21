package net.minecraft.entity;

import java.util.Random;
import org.lwjgl.opengl.GL11;

import net.minecraft.entity.enderdragon.EntityEnderDragon;
import net.minecraft.model.ModelDragon;
import net.minecraft.util.OpenGlHelper;
import net.minecraft.util.Tessellator;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.end.DragonFightManager;

public class RenderDragon extends RenderLiving {

	public static EntityEnderDragon entityDragon;
	private static int initializeModel = 0;
	protected ModelDragon field_40285_c;

	public RenderDragon() {
		super(new ModelDragon(0.0F), 0.5F);
		field_40285_c = (ModelDragon) mainModel;
		setRenderPassModel(mainModel);
	}

	protected void rotateDragonBody(EntityEnderDragon entitydragon, float f, float f1, float f2) {
		float f3 = (float) entitydragon.func_40160_a(7, f2)[0];
		float f4 = (float) (entitydragon.func_40160_a(5, f2)[1] - entitydragon.func_40160_a(10, f2)[1]);
		GL11.glRotatef(-f3, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(f4 * 10F, 1.0F, 0.0F, 0.0F);
		GL11.glTranslatef(0.0F, 0.0F, 1.0F);
		if(entitydragon.deathTime > 0) {
			float f5 = ((((float) entitydragon.deathTime + f2) - 1.0F) / 20F) * 1.6F;
			f5 = MathHelper.sqrt_float(f5);
			if(f5 > 1.0F) {
				f5 = 1.0F;
			}
			GL11.glRotatef(f5 * getDeathMaxRotation(entitydragon), 0.0F, 0.0F, 1.0F);
		}
	}

	protected void render(EntityEnderDragon entitydragon, float f, float f1, float f2, float f3, float f4, float f5) {
		if(DragonFightManager.isDying) {
			float f6 = (float) entitydragon.deathUpdateTimer / 200F;
			GL11.glDepthFunc(515);
			GL11.glEnable(3008 /* GL_ALPHA_TEST */);
			GL11.glAlphaFunc(516, f6);
			loadDownloadableImageTexture(entitydragon.skinURL, "/mob/enderdragon/shuffle.png");
			mainModel.render(entitydragon, f, f1, f2, f3, f4, f5);
			GL11.glAlphaFunc(516, 0.1F);
			GL11.glDepthFunc(514);
		}
		loadDownloadableImageTexture(entitydragon.skinURL, entitydragon.getEntityTexture());
		mainModel.render(entitydragon, f, f1, f2, f3, f4, f5);
		if(entitydragon.hurtTime > 0) {
			GL11.glDepthFunc(514);
			GL11.glDisable(3553 /* GL_TEXTURE_2D */);
			GL11.glEnable(3042 /* GL_BLEND */);
			GL11.glBlendFunc(770, 771);
			GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.5F);
			mainModel.render(entitydragon, f, f1, f2, f3, f4, f5);
			GL11.glEnable(3553 /* GL_TEXTURE_2D */);
			GL11.glDisable(3042 /* GL_BLEND */);
			GL11.glDepthFunc(515);
		}
	}

	public void renderDragon(EntityEnderDragon entitydragon, double d, double d1, double d2, float f, float f1) {
		entityDragon = entitydragon;
		if(initializeModel != 4) {
			mainModel = new ModelDragon(0.0F);
			initializeModel = 4;
		}
		super.doRenderLiving(entitydragon, d, d1, d2, f, f1);
		if(entitydragon.enderCrystals != null && !entitydragon.enderCrystals.isEmpty() && !DragonFightManager.isRespawning) {
			for(EntityEnderCrystal crystal : entitydragon.enderCrystals) {
				if(crystal != null) {
					float beamAnimationProgress = (float) crystal.field_41032_a + f1;
					float waveOffset = MathHelper.sin(beamAnimationProgress * 0.2F) / 2.0F + 0.5F;
					waveOffset = (waveOffset * waveOffset + waveOffset) * 0.2F;
					// Calculate positions and offsets
					float offsetX = (float) (crystal.posX - entitydragon.posX - (entitydragon.prevPosX - entitydragon.posX) * (1.0F - f1));
					float offsetY = (float) ((crystal.posY + waveOffset) - 1.0D - entitydragon.posY - (entitydragon.prevPosY - entitydragon.posY) * (1.0F - f1));
					float offsetZ = (float) (crystal.posZ - entitydragon.posZ - (entitydragon.prevPosZ - entitydragon.posZ) * (1.0F - f1));
					float distanceXY = MathHelper.sqrt_float(offsetX * offsetX + offsetZ * offsetZ);
					float distanceXYZ = MathHelper.sqrt_float(offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ);
					// Calculate the shrinking scale factor based on respawnUpdateTimer
					float scale = 1.0F;
					GL11.glPushMatrix();
					GL11.glTranslatef((float) d, (float) d1 + 2.0F, (float) d2);
					GL11.glRotatef(((float) (-Math.atan2(offsetZ, offsetX)) * 180F) / 3.141593F - 90F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(((float) (-Math.atan2(distanceXY, offsetY)) * 180F) / 3.141593F - 90F, 1.0F, 0.0F, 0.0F);
					// Apply shrinking effect to beam length
					float scaledDistance = distanceXYZ * scale;
					// Render beam
					Tessellator tessellator = Tessellator.instance;
					RenderHelper.disableStandardItemLighting();
					GL11.glDisable(2884 /* GL_CULL_FACE */);
					loadTexture("/mob/enderdragon/beam.png");
					GL11.glShadeModel(7425 /* GL_SMOOTH */);
					float beamStart = 0.0F - ((float) entitydragon.ticksExisted + f1) * 0.01F;
					float beamEnd = scaledDistance / 32F - ((float) entitydragon.ticksExisted + f1) * 0.01F;
					tessellator.startDrawing(5);
					int segments = 8;
					for(int i = 0; i <= segments; i++) {
						float sinWave = MathHelper.sin((i % segments) * (float) Math.PI * 2.0F / segments) * 0.75F * scale; // Scale sine wave
						float cosWave = MathHelper.cos((i % segments) * (float) Math.PI * 2.0F / segments) * 0.75F * scale; // Scale cosine wave
						float textureProgress = (float) i / (float) segments;
						tessellator.setColorOpaque_I(0);
						tessellator.addVertexWithUV(sinWave * 0.2F, cosWave * 0.2F, 0.0D, textureProgress, beamEnd);
						tessellator.setColorOpaque_I(0xffffff);
						tessellator.addVertexWithUV(sinWave, cosWave, scaledDistance, textureProgress, beamStart);
					}
					tessellator.draw();
					GL11.glEnable(2884 /* GL_CULL_FACE */);
					GL11.glShadeModel(7424 /* GL_FLAT */);
					RenderHelper.enableStandardItemLighting();
					GL11.glPopMatrix();
				}
			}
		}else if(entitydragon.enderCrystals != null && !entitydragon.enderCrystals.isEmpty()) {
			for(EntityEnderCrystal crystal : entitydragon.enderCrystals) {
				if(crystal != null) {
					float beamAnimationProgress = (float) crystal.field_41032_a + f1;
					float waveOffset = MathHelper.sin(beamAnimationProgress * 0.2F) / 2.0F + 0.5F;
					waveOffset = (waveOffset * waveOffset + waveOffset) * 0.2F;
					// Calculate positions and offsets
					float offsetX = (float) (crystal.posX - entitydragon.posX - (entitydragon.prevPosX - entitydragon.posX) * (1.0F - f1));
					float offsetY = (float) ((crystal.posY + waveOffset) - 1.0D - entitydragon.posY - (entitydragon.prevPosY - entitydragon.posY) * (1.0F - f1));
					float offsetZ = (float) (crystal.posZ - entitydragon.posZ - (entitydragon.prevPosZ - entitydragon.posZ) * (1.0F - f1));
					float distanceXY = MathHelper.sqrt_float(offsetX * offsetX + offsetZ * offsetZ);
					float distanceXYZ = MathHelper.sqrt_float(offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ);
					// Calculate the shrinking scale factor based on respawnUpdateTimer
					float maxRespawnTime = DragonFightManager.MAX_RESPAWN_TIMER; // Total respawn duration
					float scale = (DragonFightManager.MAX_RESPAWN_TIMER / maxRespawnTime); // Scale decreases over time
					GL11.glPushMatrix();
					GL11.glTranslatef((float) d, (float) d1 + 2.0F, (float) d2);
					GL11.glRotatef(((float) (-Math.atan2(offsetZ, offsetX)) * 180F) / 3.141593F - 90F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(((float) (-Math.atan2(distanceXY, offsetY)) * 180F) / 3.141593F - 90F, 1.0F, 0.0F, 0.0F);
					// Apply shrinking effect to beam length
					float scaledDistance = distanceXYZ * scale;
					// Render beam
					Tessellator tessellator = Tessellator.instance;
					RenderHelper.disableStandardItemLighting();
					GL11.glDisable(2884 /* GL_CULL_FACE */);
					loadTexture("/mob/enderdragon/beam.png");
					GL11.glShadeModel(7425 /* GL_SMOOTH */);
					float beamStart = 0.0F - ((float) entitydragon.ticksExisted + f1) * 0.01F;
					float beamEnd = scaledDistance / 32F - ((float) entitydragon.ticksExisted + f1) * 0.01F;
					tessellator.startDrawing(5);
					int segments = 8;
					for(int i = 0; i <= segments; i++) {
						float sinWave = MathHelper.sin((i % segments) * (float) Math.PI * 2.0F / segments) * 0.75F * scale; // Scale sine wave
						float cosWave = MathHelper.cos((i % segments) * (float) Math.PI * 2.0F / segments) * 0.75F * scale; // Scale cosine wave
						float textureProgress = (float) i / (float) segments;
						tessellator.setColorOpaque_I(0);
						tessellator.addVertexWithUV(sinWave * 0.2F, cosWave * 0.2F, 0.0D, textureProgress, beamEnd);
						tessellator.setColorOpaque_I(0xffffff);
						tessellator.addVertexWithUV(sinWave, cosWave, scaledDistance, textureProgress, beamStart);
					}
					tessellator.draw();
					GL11.glEnable(2884 /* GL_CULL_FACE */);
					GL11.glShadeModel(7424 /* GL_FLAT */);
					RenderHelper.enableStandardItemLighting();
					GL11.glPopMatrix();
				}
			}
		}
	}

	protected void renderDragonDying(EntityEnderDragon entitydragon, float f) {
		super.renderEquippedItems(entitydragon, f);
		Tessellator tessellator = Tessellator.instance;
		if(DragonFightManager.isDying || DragonFightManager.isRespawning) {
			RenderHelper.disableStandardItemLighting();
			float f1 = ((float) entitydragon.deathUpdateTimer + f) / 200F;
			float f2 = 0.0F;
			if(f1 > 0.8F) {
				f2 = (f1 - 0.8F) / 0.2F;
			}
			Random random = new Random(432L);
			GL11.glDisable(3553 /* GL_TEXTURE_2D */);
			GL11.glShadeModel(7425 /* GL_SMOOTH */);
			GL11.glEnable(3042 /* GL_BLEND */);
			GL11.glBlendFunc(770, 1);
			GL11.glDisable(3008 /* GL_ALPHA_TEST */);
			GL11.glEnable(2884 /* GL_CULL_FACE */);
			GL11.glDepthMask(false);
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, -1F, -2F);
			for(int i = 0; (float) i < ((f1 + f1 * f1) / 2.0F) * 60F; i++) {
				GL11.glRotatef(random.nextFloat() * 360F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(random.nextFloat() * 360F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(random.nextFloat() * 360F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(random.nextFloat() * 360F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(random.nextFloat() * 360F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(random.nextFloat() * 360F + f1 * 90F, 0.0F, 0.0F, 1.0F);
				tessellator.startDrawing(6);
				float f3 = random.nextFloat() * 20F + 5F + f2 * 10F;
				float f4 = random.nextFloat() * 2.0F + 1.0F + f2 * 2.0F;
				tessellator.setColorRGBA_I(0xffffff, (int) (255F * (1.0F - f2)));
				tessellator.addVertex(0.0D, 0.0D, 0.0D);
				tessellator.setColorRGBA_I(0xff00ff, 0);
				tessellator.addVertex(-0.86599999999999999D * (double) f4, f3, -0.5F * f4);
				tessellator.addVertex(0.86599999999999999D * (double) f4, f3, -0.5F * f4);
				tessellator.addVertex(0.0D, f3, 1.0F * f4);
				tessellator.addVertex(-0.86599999999999999D * (double) f4, f3, -0.5F * f4);
				tessellator.draw();
			}
			GL11.glPopMatrix();
			GL11.glDepthMask(true);
			GL11.glDisable(2884 /* GL_CULL_FACE */);
			GL11.glDisable(3042 /* GL_BLEND */);
			GL11.glShadeModel(7424 /* GL_FLAT */);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glEnable(3553 /* GL_TEXTURE_2D */);
			GL11.glEnable(3008 /* GL_ALPHA_TEST */);
			RenderHelper.enableStandardItemLighting();
		}
	}

	private void renderDragonRespawning(EntityEnderDragon entitydragon, float f) {
		if(DragonFightManager.respawnTimer > 0 && DragonFightManager.respawnTimer < DragonFightManager.MAX_RESPAWN_TIMER) { // Adjusted condition
			super.renderEquippedItems(entitydragon, f);
			Tessellator tessellator = Tessellator.instance;
			RenderHelper.disableStandardItemLighting();
			float f1 = ((float) DragonFightManager.respawnTimer + f) / DragonFightManager.MAX_RESPAWN_TIMER;
			float f2 = 0.0F;
			if(f1 > 0.8F) {
				f2 = (f1 - 0.8F) / 0.2F;
			}
			Random random = new Random(432L);
			GL11.glDisable(3553 /* GL_TEXTURE_2D */);
			GL11.glShadeModel(7425 /* GL_SMOOTH */);
			GL11.glEnable(3042 /* GL_BLEND */);
			GL11.glBlendFunc(770, 1);
			GL11.glDisable(3008 /* GL_ALPHA_TEST */);
			GL11.glEnable(2884 /* GL_CULL_FACE */);
			GL11.glDepthMask(false);
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, -1F, -2F);
			for(int i = 0; (float) i < ((f1 + f1 * f1) / 2.0F) * 60F; i++) {
				GL11.glRotatef(random.nextFloat() * 360F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(random.nextFloat() * 360F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(random.nextFloat() * 360F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(random.nextFloat() * 360F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(random.nextFloat() * 360F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(random.nextFloat() * 360F + f1 * 90F, 0.0F, 0.0F, 1.0F);
				tessellator.startDrawing(6);
				float f3 = random.nextFloat() * 20F + 5F + f2 * 10F;
				float f4 = random.nextFloat() * 2.0F + 1.0F + f2 * 2.0F;
				tessellator.setColorRGBA_I(0xffffff, (int) (255F * (1.0F - f2)));
				tessellator.addVertex(0.0D, 0.0D, 0.0D);
				tessellator.setColorRGBA_I(0xff00ff, 0);
				tessellator.addVertex(-0.86599999999999999D * (double) f4, f3, -0.5F * f4);
				tessellator.addVertex(0.86599999999999999D * (double) f4, f3, -0.5F * f4);
				tessellator.addVertex(0.0D, f3, 1.0F * f4);
				tessellator.addVertex(-0.86599999999999999D * (double) f4, f3, -0.5F * f4);
				tessellator.draw();
			}
			GL11.glPopMatrix();
			GL11.glDepthMask(true);
			GL11.glDisable(2884 /* GL_CULL_FACE */);
			GL11.glDisable(3042 /* GL_BLEND */);
			GL11.glShadeModel(7424 /* GL_FLAT */);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glEnable(3553 /* GL_TEXTURE_2D */);
			GL11.glEnable(3008 /* GL_ALPHA_TEST */);
			RenderHelper.enableStandardItemLighting();
		}
	}

	protected int getNextComponent(EntityEnderDragon entitydragon, int i, float f) {
		if(i == 1) {
			GL11.glDepthFunc(515);
		}
		if(i != 0) {
			return -1;
		}else {
			loadTexture("/mob/enderdragon/ender_eyes.png");
			float f1 = 1.0F;
			GL11.glEnable(3042 /* GL_BLEND */);
			GL11.glDisable(3008 /* GL_ALPHA_TEST */);
			GL11.glBlendFunc(1, 1);
			GL11.glDisable(2896 /* GL_LIGHTING */);
			GL11.glDepthFunc(514);
			int j = 61680;
			int k = j % 0x10000;
			int l = j / 0x10000;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapEnabled, (float) k / 1.0F, (float) l / 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glEnable(2896 /* GL_LIGHTING */);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);
			return 1;
		}
	}

	protected int shouldRenderPass(EntityLiving entityliving, int i, float f) {
		return getNextComponent((EntityEnderDragon) entityliving, i, f);
	}

	protected void renderEquippedItems(EntityLiving entityliving, float f) {
		renderDragonDying((EntityEnderDragon) entityliving, f);
		renderDragonRespawning((EntityEnderDragon) entityliving, f);
	}

	protected void rotateCorpse(EntityLiving entityliving, float f, float f1, float f2) {
		rotateDragonBody((EntityEnderDragon) entityliving, f, f1, f2);
	}

	protected void renderModel(EntityLiving entityliving, float f, float f1, float f2, float f3, float f4, float f5) {
		render((EntityEnderDragon) entityliving, f, f1, f2, f3, f4, f5);
	}

	public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2, float f, float f1) {
		renderDragon((EntityEnderDragon) entityliving, d, d1, d2, f, f1);
	}

	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
		renderDragon((EntityEnderDragon) entity, d, d1, d2, f, f1);
	}

}
