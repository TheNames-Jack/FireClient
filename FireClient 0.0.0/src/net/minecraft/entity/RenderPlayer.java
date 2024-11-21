package net.minecraft.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemElytra;
import net.minecraft.item.ItemStack;
import net.minecraft.model.ModelBase;
import net.minecraft.model.ModelBiped;
import net.minecraft.model.ModelElytra;
import net.minecraft.model.ModelPlayer;
import net.minecraft.util.EnumAction;
import net.minecraft.util.FontRenderer;
import net.minecraft.util.Tessellator;
import net.minecraft.util.Vec3D;
import net.minecraft.util.math.MathHelper;

public class RenderPlayer extends RenderLiving {
	private ModelPlayer modelPlayer;
	private ModelBiped modelArmorChestplate;
	private ModelBiped modelArmor;
	private ModelElytra modelElytra;
	private boolean displayCape;

	private static final String armorFilenamePrefix[] = {
			"cloth", "chain", "iron", "diamond", "gold", "elytra"
	};

	public RenderPlayer() {
		super(new ModelPlayer(0.0F), 0.5F);
		modelPlayer = (ModelPlayer) mainModel;
		modelArmorChestplate = new ModelBiped(1.0F);
		modelArmor = new ModelBiped(0.5F);
		modelElytra = new ModelElytra(1.0F);
	}

	protected int setArmorModel(EntityPlayer entityplayer, int i, float f) {
		ItemStack itemstack = entityplayer.inventory.armorItemInSlot(3 - i);
		if(itemstack != null) {
			Item item = itemstack.getItem();
			if(item instanceof ItemArmor && !(item instanceof ItemElytra)) {
				ItemArmor itemarmor = (ItemArmor) item;
				loadTexture((new StringBuilder()).append("/armor/").append(armorFilenamePrefix[itemarmor.renderIndex]).append("_").append(i != 2 ? 1 : 2).append(".png").toString());
				ModelBiped modelbiped = i != 2 ? modelArmorChestplate : modelArmor;
				modelbiped.head.showModel = i == 0;
				modelbiped.hat.showModel = i == 0;
				modelbiped.body.showModel = i == 1 || i == 2;
				modelbiped.rightArm.showModel = i == 1;
				modelbiped.leftArm.showModel = i == 1;
				modelbiped.rightLeg.showModel = i == 2 || i == 3;
				modelbiped.leftLeg.showModel = i == 2 || i == 3;
				setRenderPassModel(modelbiped);
				return !itemstack.isItemEnchanted() ? 1 : 15;
			}else if(item instanceof ItemElytra) {
				// For dynamic elytra textures
//				if(!loadDownloadableImageTexture(entityplayer.playerCapeURL, null)) {
//					
//				}
				// END
				loadTexture((new StringBuilder()).append("/armor/elytra.png").toString());
				ModelElytra modelElytra = this.modelElytra;
				modelElytra.showElytra = i == 1 || i == 2;
				setRenderPassModel(modelElytra);
				return !itemstack.isItemEnchanted() ? 1 : 15;
			}
		}else {
			displayCape = true;
		}
		return -1;
	}

	public void renderPlayer(EntityPlayer entityplayer, double d, double d1, double d2, float f, float f1) {
		if(entityplayer.skinURL != null) {
			modelPlayer.useOverlays = true;
		}
		
		ItemStack itemstack = entityplayer.inventory.getCurrentItem();
		modelArmorChestplate.heldItemRight = modelArmor.heldItemRight = modelPlayer.heldItemRight = itemstack == null ? 0 : 1;
		if(itemstack != null && entityplayer.func_35205_Y() > 0) {
			EnumAction enumaction = itemstack.getItemUseAction();
			if(enumaction == EnumAction.block) {
				modelArmorChestplate.heldItemRight = modelArmor.heldItemRight = modelPlayer.heldItemRight = 3;
			}else if(enumaction == EnumAction.bow) {
				modelArmorChestplate.aimedBow = modelArmor.aimedBow = modelPlayer.aimedBow = true;
			}
		}
		modelArmorChestplate.isSneak = modelArmor.isSneak = modelPlayer.isSneak = entityplayer.isSneaking();
		double d3 = d1 - (double) entityplayer.yOffset;
		if(entityplayer.isSneaking() && !(entityplayer instanceof EntityPlayerSP)) {
			d3 -= 0.125D;
		}
		super.doRenderLiving(entityplayer, d, d3, d2, f, f1);
		modelArmorChestplate.aimedBow = modelArmor.aimedBow = modelPlayer.aimedBow = false;
		modelArmorChestplate.isSneak = modelArmor.isSneak = modelPlayer.isSneak = false;
		modelArmorChestplate.heldItemRight = modelArmor.heldItemRight = modelPlayer.heldItemRight = 0;
	}

	protected void renderName(EntityPlayer entityplayer, double d, double d1, double d2) {
		if(Minecraft.isGuiEnabled() && entityplayer != renderManager.livingPlayer) {
			float f = 1.6F;
			float f1 = 0.01666667F * f;
			float f2 = entityplayer.getDistanceToEntity(renderManager.livingPlayer);
			float f3 = entityplayer.isSneaking() ? 32F : 64F;
			if(f2 < f3) {
				String s = entityplayer.username;
				if(!entityplayer.isSneaking()) {
					if(entityplayer.isPlayerSleeping()) {
						renderLivingLabel(entityplayer, s, d, d1 - 1.5D, d2, 64);
					}else {
						renderLivingLabel(entityplayer, s, d, d1, d2, 64);
					}
				}else {
					FontRenderer fontrenderer = getFontRendererFromRenderManager();
					GL11.glPushMatrix();
					GL11.glTranslatef((float) d + 0.0F, (float) d1 + 2.3F, (float) d2);
					GL11.glNormal3f(0.0F, 1.0F, 0.0F);
					GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
					GL11.glScalef(-f1, -f1, f1);
					GL11.glDisable(2896 /* GL_LIGHTING */);
					GL11.glTranslatef(0.0F, 0.25F / f1, 0.0F);
					GL11.glDepthMask(false);
					GL11.glEnable(3042 /* GL_BLEND */);
					GL11.glBlendFunc(770, 771);
					Tessellator tessellator = Tessellator.instance;
					GL11.glDisable(3553 /* GL_TEXTURE_2D */);
					tessellator.startDrawingQuads();
					int i = fontrenderer.getStringWidth(s) / 2;
					tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
					tessellator.addVertex(-i - 1, -1D, 0.0D);
					tessellator.addVertex(-i - 1, 8D, 0.0D);
					tessellator.addVertex(i + 1, 8D, 0.0D);
					tessellator.addVertex(i + 1, -1D, 0.0D);
					tessellator.draw();
					GL11.glEnable(3553 /* GL_TEXTURE_2D */);
					GL11.glDepthMask(true);
					fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, 0, 0x20ffffff);
					GL11.glEnable(2896 /* GL_LIGHTING */);
					GL11.glDisable(3042 /* GL_BLEND */);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					GL11.glPopMatrix();
				}
			}
		}
	}

	protected void renderSpecials(EntityPlayer entityplayer, float f) {
		super.renderEquippedItems(entityplayer, f);
		ItemStack itemstack = entityplayer.inventory.armorItemInSlot(3);
		if(itemstack != null && itemstack.getItem().id < 256) {
			GL11.glPushMatrix();
			modelPlayer.head.postRender(0.0625F);
			if(RenderBlocks.renderItemIn3d(Block.blocksList[itemstack.itemID].getRenderType())) {
				float f1 = 0.625F;
				GL11.glTranslatef(0.0F, -0.25F, 0.0F);
				GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(f1, -f1, f1);
			}
			renderManager.itemRenderer.renderItem(entityplayer, itemstack, 0);
			GL11.glPopMatrix();
		}

		ItemStack chestItem = entityplayer.inventory.armorItemInSlot(2); 
		if(chestItem != null && chestItem.getItem() instanceof ItemElytra) {
			displayCape = false;
		}else {
			displayCape = true;
		}

		if(entityplayer.username.equals("deadmau5") && loadDownloadableImageTexture(entityplayer.skinURL, null)) {
			for(int i = 0; i < 2; i++) {
				float f2 = (entityplayer.prevRotationYaw + (entityplayer.rotationYaw - entityplayer.prevRotationYaw) * f) - (entityplayer.prevRenderYawOffset + (entityplayer.renderYawOffset - entityplayer.prevRenderYawOffset) * f);
				float f3 = entityplayer.prevRotationPitch + (entityplayer.rotationPitch - entityplayer.prevRotationPitch) * f;
				GL11.glPushMatrix();
				GL11.glRotatef(f2, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(f3, 1.0F, 0.0F, 0.0F);
				GL11.glTranslatef(0.375F * (float) (i * 2 - 1), 0.0F, 0.0F);
				GL11.glTranslatef(0.0F, -0.375F, 0.0F);
				GL11.glRotatef(-f3, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(-f2, 0.0F, 1.0F, 0.0F);
				float f8 = 1.333333F;
				GL11.glScalef(f8, f8, f8);
				modelPlayer.renderEars(0.0625F);
				GL11.glPopMatrix();
			}
		}
		if(loadDownloadableImageTexture(entityplayer.playerCapeURL, null) && displayCape) {
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, 0.0F, 0.125F);
			double d = (entityplayer.field_20066_r + (entityplayer.field_20063_u - entityplayer.field_20066_r) * (double) f) - (entityplayer.prevPosX + (entityplayer.posX - entityplayer.prevPosX) * (double) f);
			double d1 = (entityplayer.field_20065_s + (entityplayer.field_20062_v - entityplayer.field_20065_s) * (double) f) - (entityplayer.prevPosY + (entityplayer.posY - entityplayer.prevPosY) * (double) f);
			double d2 = (entityplayer.field_20064_t + (entityplayer.field_20061_w - entityplayer.field_20064_t) * (double) f) - (entityplayer.prevPosZ + (entityplayer.posZ - entityplayer.prevPosZ) * (double) f);
			float f12 = entityplayer.prevRenderYawOffset + (entityplayer.renderYawOffset - entityplayer.prevRenderYawOffset) * f;
			double d3 = MathHelper.sin((f12 * 3.141593F) / 180F);
			double d4 = -MathHelper.cos((f12 * 3.141593F) / 180F);
			float f13 = (float) d1 * 10F;
			if(f13 < -6F) {
				f13 = -6F;
			}
			if(f13 > 32F) {
				f13 = 32F;
			}
			float f14 = (float) (d * d3 + d2 * d4) * 100F;
			float f15 = (float) (d * d4 - d2 * d3) * 100F;
			if(f14 < 0.0F) {
				f14 = 0.0F;
			}
			float f16 = entityplayer.prevCameraYaw + (entityplayer.cameraYaw - entityplayer.prevCameraYaw) * f;
			f13 += MathHelper.sin((entityplayer.prevDistanceWalkedModified + (entityplayer.distanceWalkedModified - entityplayer.prevDistanceWalkedModified) * f) * 6F) * 32F * f16;
			if(entityplayer.isSneaking()) {
				f13 += 25F;
			}
			GL11.glRotatef(6F + f14 / 2.0F + f13, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(f15 / 2.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(-f15 / 2.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
			modelPlayer.renderCape(0.0625F);
			GL11.glPopMatrix();
		}
		ItemStack itemstack1 = entityplayer.inventory.getCurrentItem();
		if(itemstack1 != null) {
			GL11.glPushMatrix();
			modelPlayer.rightArm.postRender(0.0625F);
			GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
			if(entityplayer.fishEntity != null) {
				itemstack1 = new ItemStack(Item.STICK);
			}
			EnumAction enumaction = null;
			if(entityplayer.func_35205_Y() > 0) {
				enumaction = itemstack1.getItemUseAction();
			}
			if(itemstack1.itemID < 256 && RenderBlocks.renderItemIn3d(Block.blocksList[itemstack1.itemID].getRenderType())) {
				float f4 = 0.5F;
				GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
				f4 *= 0.75F;
				GL11.glRotatef(20F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(f4, -f4, f4);
			}else if(itemstack1.itemID == Item.BOW.id) {
				float f5 = 0.625F;
				GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
				GL11.glRotatef(-20F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(f5, -f5, f5);
				GL11.glRotatef(-100F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
			}else if(Item.itemsList[itemstack1.itemID].isFull3D()) {
				float f6 = 0.625F;
				if(Item.itemsList[itemstack1.itemID].shouldRotateAroundWhenRendering()) {
					GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
					GL11.glTranslatef(0.0F, -0.125F, 0.0F);
				}
				if(entityplayer.func_35205_Y() > 0 && enumaction == EnumAction.block) {
					GL11.glTranslatef(0.05F, 0.0F, -0.1F);
					GL11.glRotatef(-50F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(-10F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(-60F, 0.0F, 0.0F, 1.0F);
				}
				GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
				GL11.glScalef(f6, -f6, f6);
				GL11.glRotatef(-100F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
			}else {
				float f7 = 0.375F;
				GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
				GL11.glScalef(f7, f7, f7);
				GL11.glRotatef(60F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(20F, 0.0F, 0.0F, 1.0F);
			}
			if(itemstack1.itemID == Item.POTION.id) {
				int j = itemstack1.getItem().getColorFromDamage(itemstack1.getItemDamage());
				float f9 = (float) (j >> 16 & 0xff) / 255F;
				float f10 = (float) (j >> 8 & 0xff) / 255F;
				float f11 = (float) (j & 0xff) / 255F;
				GL11.glColor4f(f9, f10, f11, 1.0F);
				renderManager.itemRenderer.renderItem(entityplayer, itemstack1, 0);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				renderManager.itemRenderer.renderItem(entityplayer, itemstack1, 1);
			}else {
				renderManager.itemRenderer.renderItem(entityplayer, itemstack1, 0);
			}
			GL11.glPopMatrix();
		}
	}

	protected void renderPlayerScale(EntityPlayer entityplayer, float f) {
		float f1 = 0.9375F;
		GL11.glScalef(f1, f1, f1);
	}

	public void drawFirstPersonHand() {
		modelPlayer.onGround = 0.0F;
		modelPlayer.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		modelPlayer.rightArm.render(0.0625F);
	}

	protected void renderPlayerSleep(EntityPlayer entityplayer, double d, double d1, double d2) {
		if(entityplayer.isEntityAlive() && entityplayer.isPlayerSleeping()) {
			super.renderLivingAt(entityplayer, d + (double) entityplayer.field_22063_x, d1 + (double) entityplayer.field_22062_y, d2 + (double) entityplayer.field_22061_z);
		}else {
			super.renderLivingAt(entityplayer, d, d1, d2);
		}
	}

	protected void rotatePlayer(EntityPlayer entityplayer, float a, float a1, float partialTicks) {
		if(entityplayer.isEntityAlive() && entityplayer.isPlayerSleeping()) {
			GL11.glRotatef(entityplayer.getBedOrientationInDegrees(), 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(getDeathMaxRotation(entityplayer), 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(270F, 0.0F, 1.0F, 0.0F);
		}else if(entityplayer.isUsingElytra()) {
			super.rotateCorpse(entityplayer, a, a1, partialTicks);
			GL11.glTranslatef(0, (entityplayer.height / 2), 0);
			float f = (float) entityplayer.GetElytraFlyTicks() + partialTicks;
			float f1 = MathHelper.ClampFloat(f * f / 100.0F, 0.0F, 1.0F);
			GL11.glRotatef(f1 * (-90.0F - entityplayer.rotationPitch), 1.0F, 0.0F, 0.0F);
			Vec3D vec3d = entityplayer.getLook(partialTicks);
			double d0 = entityplayer.motionX * entityplayer.motionX + entityplayer.motionZ * entityplayer.motionZ;
			double d1 = vec3d.xCoord * vec3d.xCoord + vec3d.zCoord * vec3d.zCoord;
			if(d0 > 0.0D && d1 > 0.0D) {
				double d2 = (entityplayer.motionX * vec3d.xCoord + entityplayer.motionZ * vec3d.zCoord) / (Math.sqrt(d0) * Math.sqrt(d1));
				double d3 = entityplayer.motionX * vec3d.zCoord - entityplayer.motionZ * vec3d.xCoord;
				GL11.glRotatef((float) (Math.signum(d3) * Math.acos(d2)) * 180.0F / (float) Math.PI, 0.0F, 1.0F, 0.0F);
			}
		}else {
			super.rotateCorpse(entityplayer, a, a1, partialTicks);
		}
	}

	protected void passSpecialRender(EntityLiving entityliving, double d, double d1, double d2) {
		renderName((EntityPlayer) entityliving, d, d1, d2);
	}

	protected void preRenderCallback(EntityLiving entityliving, float f) {
		renderPlayerScale((EntityPlayer) entityliving, f);
	}

	protected int shouldRenderPass(EntityLiving entityliving, int i, float f) {
		return setArmorModel((EntityPlayer) entityliving, i, f);
	}

	protected void renderEquippedItems(EntityLiving entityliving, float f) {
		renderSpecials((EntityPlayer) entityliving, f);
	}

	protected void rotateCorpse(EntityLiving entityliving, float f, float f1, float f2) {
		rotatePlayer((EntityPlayer) entityliving, f, f1, f2);
	}

	protected void renderLivingAt(EntityLiving entityliving, double d, double d1, double d2) {
		renderPlayerSleep((EntityPlayer) entityliving, d, d1, d2);
	}

	public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2, float f, float f1) {
		renderPlayer((EntityPlayer) entityliving, d, d1, d2, f, f1);
	}

	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
		renderPlayer((EntityPlayer) entity, d, d1, d2, f, f1);
	}
}