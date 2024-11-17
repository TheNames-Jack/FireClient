// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRenderer;
import net.minecraft.model.ModelBiped;
import net.minecraft.model.ModelChicken;
import net.minecraft.model.ModelCow;
import net.minecraft.model.ModelPig;
import net.minecraft.model.ModelSheep1;
import net.minecraft.model.ModelSheep2;
import net.minecraft.model.ModelSkeleton;
import net.minecraft.model.ModelSlime;
import net.minecraft.model.ModelSquid;
import net.minecraft.model.ModelWolf;
import net.minecraft.model.ModelZombie;
import net.minecraft.util.FontRenderer;
import net.minecraft.util.GameSettings;
import net.minecraft.util.MathHelper;
import net.minecraft.util.OpenGlHelper;
import net.minecraft.util.Render;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            EntitySpider, RenderSpider, EntityCaveSpider, EntityPig, 
//            RenderPig, ModelPig, EntitySheep, RenderSheep, 
//            ModelSheep2, ModelSheep1, EntityCow, RenderCow, 
//            ModelCow, EntityMooshroom, RenderMooshroom, EntityWolf, 
//            RenderWolf, ModelWolf, EntityChicken, RenderChicken, 
//            ModelChicken, EntitySilverfish, RenderSilverfish, EntityCreeper, 
//            RenderCreeper, EntityEnderman, RenderEnderman, EntitySnowman, 
//            RenderSnowMan, EntitySkeleton, RenderBiped, ModelSkeleton, 
//            EntityBlaze, RenderBlaze, EntityZombie, ModelZombie, 
//            EntitySlime, RenderSlime, ModelSlime, EntityMagmaCube, 
//            RenderMagmaCube, EntityPlayer, RenderPlayer, EntityGiantZombie, 
//            RenderGiantZombie, EntityGhast, RenderGhast, EntitySquid, 
//            RenderSquid, ModelSquid, EntityVillager, RenderVillager, 
//            EntityLiving, RenderLiving, ModelBiped, EntityDragon, 
//            RenderDragon, EntityEnderCrystal, RenderEnderCrystal, Entity, 
//            RenderEntity, EntityPainting, RenderPainting, EntityArrow, 
//            RenderArrow, EntitySnowball, RenderSnowball, Item, 
//            EntityEnderPearl, EntityEnderEye, EntityEgg, EntityPotion, 
//            EntityFireball, RenderFireball, EntitySmallFireball, EntityItem, 
//            RenderItem, EntityXPOrb, RenderXPOrb, EntityTNTPrimed, 
//            RenderTNTPrimed, EntityFallingSand, RenderFallingSand, EntityMinecart, 
//            RenderMinecart, EntityBoat, RenderBoat, EntityFishHook, 
//            RenderFish, EntityLightningBolt, RenderLightningBolt, Render, 
//            MathHelper, World, Block, GameSettings, 
//            OpenGlHelper, FontRenderer, RenderEngine, ItemRenderer

public class RenderManager {

	private Map entityRenderMap;
	public static RenderManager instance = new RenderManager();
	private FontRenderer fontRenderer;
	public static double renderPosX;
	public static double renderPosY;
	public static double renderPosZ;
	public RenderEngine renderEngine;
	public ItemRenderer itemRenderer;
	public World worldObj;
	public EntityLiving livingPlayer;
	public float playerViewY;
	public float playerViewX;
	public GameSettings options;
	public double field_1222_l;
	public double field_1221_m;
	public double field_1220_n;

	private RenderManager() {
		entityRenderMap = new HashMap();
		entityRenderMap.put(net.minecraft.entity.EntitySpider.class, new RenderSpider());
		entityRenderMap.put(net.minecraft.entity.EntityCaveSpider.class, new RenderSpider());
		entityRenderMap.put(net.minecraft.entity.EntityPig.class, new RenderPig(new ModelPig(), new ModelPig(0.5F), 0.7F));
		entityRenderMap.put(net.minecraft.entity.EntitySheep.class, new RenderSheep(new ModelSheep2(), new ModelSheep1(), 0.7F));
		entityRenderMap.put(net.minecraft.entity.EntityCow.class, new RenderCow(new ModelCow(), 0.7F));
		entityRenderMap.put(net.minecraft.entity.EntityMooshroom.class, new RenderMooshroom(new ModelCow(), 0.7F));
		entityRenderMap.put(net.minecraft.entity.EntityWolf.class, new RenderWolf(new ModelWolf(), 0.5F));
		entityRenderMap.put(net.minecraft.entity.EntityChicken.class, new RenderChicken(new ModelChicken(), 0.3F));
		entityRenderMap.put(net.minecraft.entity.EntitySilverfish.class, new RenderSilverfish());
		entityRenderMap.put(net.minecraft.entity.EntityCreeper.class, new RenderCreeper());
		entityRenderMap.put(net.minecraft.entity.EntityEnderman.class, new RenderEnderman());
		entityRenderMap.put(net.minecraft.entity.EntitySnowman.class, new RenderSnowMan());
		entityRenderMap.put(net.minecraft.entity.EntitySkeleton.class, new RenderBiped(new ModelSkeleton(), 0.5F));
		entityRenderMap.put(net.minecraft.entity.EntityBlaze.class, new RenderBlaze());
		entityRenderMap.put(net.minecraft.entity.EntityZombie.class, new RenderBiped(new ModelZombie(), 0.5F));
		entityRenderMap.put(net.minecraft.entity.EntitySlime.class, new RenderSlime(new ModelSlime(16), new ModelSlime(0), 0.25F));
		entityRenderMap.put(net.minecraft.entity.EntityMagmaCube.class, new RenderMagmaCube());
		entityRenderMap.put(net.minecraft.entity.EntityPlayer.class, new RenderPlayer());
		entityRenderMap.put(net.minecraft.entity.EntityGiantZombie.class, new RenderGiantZombie(new ModelZombie(), 0.5F, 6F));
		entityRenderMap.put(net.minecraft.entity.EntityGhast.class, new RenderGhast());
		entityRenderMap.put(net.minecraft.entity.EntitySquid.class, new RenderSquid(new ModelSquid(), 0.7F));
		entityRenderMap.put(net.minecraft.entity.EntityVillager.class, new RenderVillager());
		entityRenderMap.put(net.minecraft.entity.EntityLiving.class, new RenderLiving(new ModelBiped(), 0.5F));
		entityRenderMap.put(net.minecraft.entity.enderdragon.EntityEnderdragon.class, new RenderDragon());
		entityRenderMap.put(net.minecraft.entity.EntityEnderCrystal.class, new RenderEnderCrystal());
		entityRenderMap.put(net.minecraft.entity.Entity.class, new RenderEntity());
		entityRenderMap.put(net.minecraft.entity.EntityPainting.class, new RenderPainting());
		entityRenderMap.put(net.minecraft.entity.EntityArrow.class, new RenderArrow());
		entityRenderMap.put(net.minecraft.entity.EntitySnowball.class, new RenderSnowball(Item.SNOWBALL.getIconFromDamage(0)));
		entityRenderMap.put(net.minecraft.entity.EntityEnderPearl.class, new RenderSnowball(Item.ENDER_PEARL.getIconFromDamage(0)));
		entityRenderMap.put(net.minecraft.entity.EntityEnderEye.class, new RenderSnowball(Item.ENDER_EYE.getIconFromDamage(0)));
		entityRenderMap.put(net.minecraft.entity.EntityEgg.class, new RenderSnowball(Item.EGG.getIconFromDamage(0)));
		entityRenderMap.put(net.minecraft.entity.EntityPotion.class, new RenderSnowball(154));
		entityRenderMap.put(net.minecraft.entity.EntityFireball.class, new RenderFireball(2.0F));
		entityRenderMap.put(net.minecraft.entity.EntitySmallFireball.class, new RenderFireball(0.5F));
		entityRenderMap.put(net.minecraft.entity.EntityItem.class, new RenderItem());
		entityRenderMap.put(net.minecraft.entity.EntityXPOrb.class, new RenderXPOrb());
		entityRenderMap.put(net.minecraft.entity.EntityTNTPrimed.class, new RenderTNTPrimed());
		entityRenderMap.put(net.minecraft.entity.EntityFallingSand.class, new RenderFallingSand());
		entityRenderMap.put(net.minecraft.entity.EntityMinecart.class, new RenderMinecart());
		entityRenderMap.put(net.minecraft.entity.EntityBoat.class, new RenderBoat());
		entityRenderMap.put(net.minecraft.entity.EntityFishHook.class, new RenderFish());
		entityRenderMap.put(net.minecraft.entity.EntityLightningBolt.class, new RenderLightningBolt());
		Render render;
		for(Iterator iterator = entityRenderMap.values().iterator(); iterator.hasNext(); render.setRenderManager(this)) {
			render = (Render) iterator.next();
		}

	}

	public Render getEntityClassRenderObject(Class class1) {
		Render render = (Render) entityRenderMap.get(class1);
		if(render == null && class1 != (net.minecraft.entity.Entity.class)) {
			render = getEntityClassRenderObject(class1.getSuperclass());
			entityRenderMap.put(class1, render);
		}
		return render;
	}

	public Render getEntityRenderObject(Entity entity) {
		return getEntityClassRenderObject(entity.getClass());
	}

	public void cacheActiveRenderInfo(World world, RenderEngine renderengine, FontRenderer fontrenderer, EntityLiving entityliving, GameSettings gamesettings, float f) {
		worldObj = world;
		renderEngine = renderengine;
		options = gamesettings;
		livingPlayer = entityliving;
		fontRenderer = fontrenderer;
		if(entityliving.isPlayerSleeping()) {
			int i = world.getBlockId(MathHelper.floor_double(entityliving.posX), MathHelper.floor_double(entityliving.posY), MathHelper.floor_double(entityliving.posZ));
			if(i == Block.bed.blockID) {
				int j = world.getBlockMetadata(MathHelper.floor_double(entityliving.posX), MathHelper.floor_double(entityliving.posY), MathHelper.floor_double(entityliving.posZ));
				int k = j & 3;
				playerViewY = k * 90 + 180;
				playerViewX = 0.0F;
			}
		}else {
			playerViewY = entityliving.prevRotationYaw + (entityliving.rotationYaw - entityliving.prevRotationYaw) * f;
			playerViewX = entityliving.prevRotationPitch + (entityliving.rotationPitch - entityliving.prevRotationPitch) * f;
		}
		if(gamesettings.thirdPersonView == 2) {
			playerViewY += 180F;
		}
		field_1222_l = entityliving.lastTickPosX + (entityliving.posX - entityliving.lastTickPosX) * (double) f;
		field_1221_m = entityliving.lastTickPosY + (entityliving.posY - entityliving.lastTickPosY) * (double) f;
		field_1220_n = entityliving.lastTickPosZ + (entityliving.posZ - entityliving.lastTickPosZ) * (double) f;
	}

	public void renderEntity(Entity entity, float f) {
		double d = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double) f;
		double d1 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double) f;
		double d2 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double) f;
		float f1 = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * f;
		int i = entity.getEntityBrightnessForRender(f);
		int j = i % 0x10000;
		int k = i / 0x10000;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapEnabled, (float) j / 1.0F, (float) k / 1.0F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		renderEntityWithPosYaw(entity, d - renderPosX, d1 - renderPosY, d2 - renderPosZ, f1, f);
	}

	public void renderEntityWithPosYaw(Entity entity, double d, double d1, double d2, float f, float f1) {
		Render render = getEntityRenderObject(entity);
		if(render != null) {
			render.doRender(entity, d, d1, d2, f, f1);
			render.doRenderShadowAndFire(entity, d, d1, d2, f, f1);
		}
	}

	public void set(World world) {
		worldObj = world;
	}

	public double getDistanceToCamera(double d, double d1, double d2) {
		double d3 = d - field_1222_l;
		double d4 = d1 - field_1221_m;
		double d5 = d2 - field_1220_n;
		return d3 * d3 + d4 * d4 + d5 * d5;
	}

	public FontRenderer getFontRenderer() {
		return fontRenderer;
	}
}
