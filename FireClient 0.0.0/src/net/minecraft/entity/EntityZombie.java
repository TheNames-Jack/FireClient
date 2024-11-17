// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.entity;

import java.util.Random;

import net.minecraft.item.Item;
import net.minecraft.util.EnumCreatureAttribute;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            EntityMob, World, MathHelper, Item, 
//            EnumCreatureAttribute

public class EntityZombie extends EntityMob
{

    public EntityZombie(World world)
    {
        super(world);
        texture = "/mob/zombie.png";
        moveSpeed = 0.5F;
        attackStrength = 4;
    }

    public int getMaxHealth()
    {
        return 20;
    }

    protected int func_40119_ar()
    {
        return 2;
    }

    public void onLivingUpdate()
    {
        if(worldObj.isDaytime() && !worldObj.multiplayerWorld)
        {
            float f = getEntityBrightness(1.0F);
            if(f > 0.5F && worldObj.canBlockSeeTheSky(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) && rand.nextFloat() * 30F < (f - 0.4F) * 2.0F)
            {
                func_40046_d(8);
            }
        }
        super.onLivingUpdate();
    }

    protected String getLivingSound()
    {
        return "mob.zombie";
    }

    protected String getHurtSound()
    {
        return "mob.zombiehurt";
    }

    protected String getDeathSound()
    {
        return "mob.zombiedeath";
    }

    protected int getDropItemId()
    {
        return Item.ROTTEN_FLESH.id;
    }

    public EnumCreatureAttribute func_40124_t()
    {
        return EnumCreatureAttribute.UNDEAD;
    }
}
