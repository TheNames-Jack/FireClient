// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.entity;

import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

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

public class EntityList
{

    private static Map stringToClassMapping = new HashMap();
    private static Map classToStringMapping = new HashMap();
    private static Map IDtoClassMapping = new HashMap();
    private static Map classToIDMapping = new HashMap();

    public EntityList()
    {
    }

    private static void addMapping(Class class1, String s, int i)
    {
        stringToClassMapping.put(s, class1);
        classToStringMapping.put(class1, s);
        IDtoClassMapping.put(Integer.valueOf(i), class1);
        classToIDMapping.put(class1, Integer.valueOf(i));
    }

    public static Entity createEntityInWorld(String s, World world)
    {
        Entity entity = null;
        try
        {
            Class class1 = (Class)stringToClassMapping.get(s);
            if(class1 != null)
            {
                entity = (Entity)class1.getConstructor(new Class[] {
                    net.minecraft.world.World.class
                }).newInstance(new Object[] {
                    world
                });
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return entity;
    }

    public static Entity createEntityFromNBT(NBTTagCompound nbttagcompound, World world)
    {
        Entity entity = null;
        try
        {
            Class class1 = (Class)stringToClassMapping.get(nbttagcompound.getString("id"));
            if(class1 != null)
            {
                entity = (Entity)class1.getConstructor(new Class[] {
                    net.minecraft.world.World.class
                }).newInstance(new Object[] {
                    world
                });
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        if(entity != null)
        {
            entity.readFromNBT(nbttagcompound);
        } else
        {
            System.out.println((new StringBuilder()).append("Skipping Entity with id ").append(nbttagcompound.getString("id")).toString());
        }
        return entity;
    }

    public static Entity createEntity(int i, World world)
    {
        Entity entity = null;
        try
        {
            Class class1 = (Class)IDtoClassMapping.get(Integer.valueOf(i));
            if(class1 != null)
            {
                entity = (Entity)class1.getConstructor(new Class[] {
                    net.minecraft.world.World.class
                }).newInstance(new Object[] {
                    world
                });
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        if(entity == null)
        {
            System.out.println((new StringBuilder()).append("Skipping Entity with id ").append(i).toString());
        }
        return entity;
    }

    public static int getEntityID(Entity entity)
    {
        return ((Integer)classToIDMapping.get(entity.getClass())).intValue();
    }

    public static String getEntityString(Entity entity)
    {
        return (String)classToStringMapping.get(entity.getClass());
    }

    static 
    {
        addMapping(net.minecraft.entity.EntityItem.class, "Item", 1);
        addMapping(net.minecraft.entity.EntityXPOrb.class, "XPOrb", 2);
        addMapping(net.minecraft.entity.EntityPainting.class, "Painting", 9);
        addMapping(net.minecraft.entity.EntityArrow.class, "Arrow", 10);
        addMapping(net.minecraft.entity.EntitySnowball.class, "Snowball", 11);
        addMapping(net.minecraft.entity.EntityFireball.class, "Fireball", 12);
        addMapping(net.minecraft.entity.EntitySmallFireball.class, "SmallFireball", 13);
        addMapping(net.minecraft.entity.EntityEnderPearl.class, "ThrownEnderpearl", 14);
        addMapping(net.minecraft.entity.EntityEnderEye.class, "EyeOfEnderSignal", 15);
        addMapping(net.minecraft.entity.EntityTNTPrimed.class, "PrimedTnt", 20);
        addMapping(net.minecraft.entity.EntityFallingSand.class, "FallingSand", 21);
        addMapping(net.minecraft.entity.EntityMinecart.class, "Minecart", 40);
        addMapping(net.minecraft.entity.EntityBoat.class, "Boat", 41);
        addMapping(net.minecraft.entity.EntityLiving.class, "Mob", 48);
        addMapping(net.minecraft.entity.EntityMob.class, "Monster", 49);
        addMapping(net.minecraft.entity.EntityCreeper.class, "Creeper", 50);
        addMapping(net.minecraft.entity.EntitySkeleton.class, "Skeleton", 51);
        addMapping(net.minecraft.entity.EntitySpider.class, "Spider", 52);
        addMapping(net.minecraft.entity.EntityGiantZombie.class, "Giant", 53);
        addMapping(net.minecraft.entity.EntityZombie.class, "Zombie", 54);
        addMapping(net.minecraft.entity.EntitySlime.class, "Slime", 55);
        addMapping(net.minecraft.entity.EntityGhast.class, "Ghast", 56);
        addMapping(net.minecraft.entity.EntityPigZombie.class, "PigZombie", 57);
        addMapping(net.minecraft.entity.EntityEnderman.class, "Enderman", 58);
        addMapping(net.minecraft.entity.EntityCaveSpider.class, "CaveSpider", 59);
        addMapping(net.minecraft.entity.EntitySilverfish.class, "Silverfish", 60);
        addMapping(net.minecraft.entity.EntityBlaze.class, "Blaze", 61);
        addMapping(net.minecraft.entity.EntityMagmaCube.class, "LavaSlime", 62);
        addMapping(net.minecraft.entity.enderdragon.EntityEnderdragon.class, "EnderDragon", 63);
        addMapping(net.minecraft.entity.EntityPig.class, "Pig", 90);
        addMapping(net.minecraft.entity.EntitySheep.class, "Sheep", 91);
        addMapping(net.minecraft.entity.EntityCow.class, "Cow", 92);
        addMapping(net.minecraft.entity.EntityChicken.class, "Chicken", 93);
        addMapping(net.minecraft.entity.EntitySquid.class, "Squid", 94);
        addMapping(net.minecraft.entity.EntityWolf.class, "Wolf", 95);
        addMapping(net.minecraft.entity.EntityMooshroom.class, "MushroomCow", 96);
        addMapping(net.minecraft.entity.EntitySnowman.class, "SnowMan", 97);
        addMapping(net.minecraft.entity.EntityVillager.class, "Villager", 120);
        addMapping(net.minecraft.entity.EntityEnderCrystal.class, "EnderCrystal", 200);
    }
}
