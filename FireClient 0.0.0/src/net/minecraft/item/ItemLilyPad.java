// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityPlayer;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            ItemColored, MovingObjectPosition, EnumMovingObjectType, World, 
//            EntityPlayer, Material, Block, PlayerCapabilities, 
//            ItemStack

public class ItemLilyPad extends ItemColored
{

    public ItemLilyPad(int i)
    {
        super(i, false);
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        MovingObjectPosition movingobjectposition = func_40402_a(world, entityplayer, true);
        if(movingobjectposition == null)
        {
            return itemstack;
        }
        if(movingobjectposition.typeOfHit == EnumMovingObjectType.TILE)
        {
            int i = movingobjectposition.blockX;
            int j = movingobjectposition.blockY;
            int k = movingobjectposition.blockZ;
            if(!world.canMineBlock(entityplayer, i, j, k))
            {
                return itemstack;
            }
            if(!entityplayer.func_35190_e(i, j, k))
            {
                return itemstack;
            }
            if(world.getBlockMaterial(i, j, k) == Material.water && world.getBlockMetadata(i, j, k) == 0 && world.isAirBlock(i, j + 1, k))
            {
                world.setBlockWithNotify(i, j + 1, k, Block.LILY_PAD.blockID);
                if(!entityplayer.abilities.depleteBuckets)
                {
                    itemstack.stackSize--;
                }
            }
        }
        return itemstack;
    }

    public int getColorFromDamage(int i)
    {
        return Block.LILY_PAD.getRenderColor(i);
    }
}
