// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.entity;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.enderdragon.EntityEnderDragon;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            World, Entity, NBTTagCompound, EntityItem, 
//            EntityXPOrb, EntityPainting, EntityArrow, EntitySnowball, 
//            EntityFireball, EntitySmallFireball, EntityEnderPearl, EntityEnderEye, 
//            EntityTNTPrimed, EntityFallingSand, EntityMinecart, EntityBoat, 
//            EntityLiving, EntityMob, EntityCreeper, EntitySkeleton, 
//            EntitySpider, EntityGiantZombie, EntityZombie, EntitySlime, 
//            EntityGhast, EntityPigZombie, EntityEnderman, EntityCaveSpider, 
//            EntitySilverfish, EntityBlaze, EntityMagmaCube, EntityDragon, 
//            EntityPig, EntitySheep, EntityCow, EntityChicken, 
//            EntitySquid, EntityWolf, EntityMooshroom, EntitySnowman, 
//            EntityVillager, EntityEnderCrystal

public class EntityList {

	private static Map stringToClassMapping = new HashMap();
	private static Map classToStringMapping = new HashMap();
	private static Map IDtoClassMapping = new HashMap();
	private static Map classToIDMapping = new HashMap();

	public EntityList() {
	}

	private static void addMapping(Class class1, String s, int i) {
		stringToClassMapping.put(s, class1);
		classToStringMapping.put(class1, s);
		IDtoClassMapping.put(Integer.valueOf(i), class1);
		classToIDMapping.put(class1, Integer.valueOf(i));
	}

	public static Entity createEntityInWorld(String s, World world) {
		Entity entity = null;
		try {
			Class class1 = (Class) stringToClassMapping.get(s);
			if(class1 != null) {
				entity = (Entity) class1.getConstructor(new Class[]{
						net.minecraft.world.World.class
				}).newInstance(new Object[]{
						world
				});
			}
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return entity;
	}

	public static Entity createEntityFromNBT(NBTTagCompound nbttagcompound, World world) {
		Entity entity = null;
		try {
			Class class1 = (Class) stringToClassMapping.get(nbttagcompound.getString("id"));
			if(class1 != null) {
				entity = (Entity) class1.getConstructor(new Class[]{
						net.minecraft.world.World.class
				}).newInstance(new Object[]{
						world
				});
			}
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		if(entity != null) {
			entity.readFromNBT(nbttagcompound);
		}else {
			System.out.println((new StringBuilder()).append("Skipping Entity with id ").append(nbttagcompound.getString("id")).toString());
		}
		return entity;
	}

	public static Entity createEntity(int i, World world) {
		Entity entity = null;
		try {
			Class class1 = (Class) IDtoClassMapping.get(Integer.valueOf(i));
			if(class1 != null) {
				entity = (Entity) class1.getConstructor(new Class[]{
						net.minecraft.world.World.class
				}).newInstance(new Object[]{
						world
				});
			}
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		if(entity == null) {
			System.out.println((new StringBuilder()).append("Skipping Entity with id ").append(i).toString());
		}
		return entity;
	}

	public static int getEntityID(Entity entity) {
		return ((Integer) classToIDMapping.get(entity.getClass())).intValue();
	}

	public static String getEntityString(Entity entity) {
		return (String) classToStringMapping.get(entity.getClass());
	}

	static {
		addMapping(EntityItem.class, "Item", 1);
		addMapping(EntityXPOrb.class, "XPOrb", 2);
		addMapping(EntityPainting.class, "Painting", 9);
		addMapping(EntityArrow.class, "Arrow", 10);
		addMapping(EntitySnowball.class, "Snowball", 11);
		addMapping(EntityFireball.class, "Fireball", 12);
		addMapping(EntitySmallFireball.class, "SmallFireball", 13);
		addMapping(EntityEnderPearl.class, "ThrownEnderpearl", 14);
		addMapping(EntityEnderEye.class, "EyeOfEnderSignal", 15);
		addMapping(EntityTNTPrimed.class, "PrimedTnt", 20);
		addMapping(EntityFallingSand.class, "FallingSand", 21);
		addMapping(EntityMinecart.class, "Minecart", 40);
		addMapping(EntityBoat.class, "Boat", 41);
		addMapping(EntityLiving.class, "Mob", 48);
		addMapping(EntityMob.class, "Monster", 49);
		addMapping(EntityCreeper.class, "Creeper", 50);
		addMapping(EntitySkeleton.class, "Skeleton", 51);
		addMapping(EntitySpider.class, "Spider", 52);
		addMapping(EntityGiantZombie.class, "Giant", 53);
		addMapping(EntityZombie.class, "Zombie", 54);
		addMapping(EntitySlime.class, "Slime", 55);
		addMapping(EntityGhast.class, "Ghast", 56);
		addMapping(EntityPigZombie.class, "PigZombie", 57);
		addMapping(EntityEnderman.class, "Enderman", 58);
		addMapping(EntityCaveSpider.class, "CaveSpider", 59);
		addMapping(EntitySilverfish.class, "Silverfish", 60);
		addMapping(EntityBlaze.class, "Blaze", 61);
		addMapping(EntityMagmaCube.class, "LavaSlime", 62);
		addMapping(EntityEnderDragon.class, "EnderDragon", 63);
		addMapping(EntityPig.class, "Pig", 90);
		addMapping(EntitySheep.class, "Sheep", 91);
		addMapping(EntityCow.class, "Cow", 92);
		addMapping(EntityChicken.class, "Chicken", 93);
		addMapping(EntitySquid.class, "Squid", 94);
		addMapping(EntityWolf.class, "Wolf", 95);
		addMapping(EntityMooshroom.class, "MushroomCow", 96);
		addMapping(EntitySnowman.class, "SnowMan", 97);
		addMapping(EntityVillager.class, "Villager", 120);
		addMapping(EntityEnderCrystal.class, "EnderCrystal", 200);
		addMapping(EntityEnderCrystalBaseless.class, "EnderCrystalBaseless", 201);
	}
}
