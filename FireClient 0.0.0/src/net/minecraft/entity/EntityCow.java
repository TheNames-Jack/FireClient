// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.entity;

import java.util.Random;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            EntityAnimal, Item, EntityPlayer, InventoryPlayer, 
//            ItemStack, World, NBTTagCompound

public class EntityCow extends EntityAnimal
{

    public EntityCow(World world)
    {
        super(world);
        texture = "/mob/cow.png";
        setSize(0.9F, 1.3F);
    }

    public int getMaxHealth()
    {
        return 10;
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
    }

    protected String getLivingSound()
    {
        return "mob.cow";
    }

    protected String getHurtSound()
    {
        return "mob.cowhurt";
    }

    protected String getDeathSound()
    {
        return "mob.cowhurt";
    }

    protected float getSoundVolume()
    {
        return 0.4F;
    }

    protected int getDropItemId()
    {
        return Item.LEATHER.id;
    }

    protected void dropFewItems(boolean flag, int i)
    {
        int j = rand.nextInt(3) + rand.nextInt(1 + i);
        for(int k = 0; k < j; k++)
        {
            dropItem(Item.LEATHER.id, 1);
        }

        j = rand.nextInt(3) + 1 + rand.nextInt(1 + i);
        for(int l = 0; l < j; l++)
        {
            if(isBurning())
            {
                dropItem(Item.COOKED_BEEF.id, 1);
            } else
            {
                dropItem(Item.RAW_BEEF.id, 1);
            }
        }

    }

    public boolean interact(EntityPlayer entityplayer)
    {
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();
        if(itemstack != null && itemstack.itemID == Item.EMPTY_BUCKET.id)
        {
            entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, new ItemStack(Item.MILK_BUCKET));
            return true;
        } else
        {
            return super.interact(entityplayer);
        }
    }

    protected EntityAnimal func_40145_a(EntityAnimal entityanimal)
    {
        return new EntityCow(worldObj);
    }
}
