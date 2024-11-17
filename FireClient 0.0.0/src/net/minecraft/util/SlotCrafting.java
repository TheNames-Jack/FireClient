// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.util;

import net.minecraft.achievement.AchievementList;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityPlayer;
import net.minecraft.entity.player.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

// Referenced classes of package net.minecraft.src:
//            Slot, EntityPlayer, ItemStack, Block, 
//            AchievementList, Item, IInventory

public class SlotCrafting extends Slot
{

    private final IInventory craftMatrix;
    private EntityPlayer thePlayer;

    public SlotCrafting(EntityPlayer entityplayer, IInventory iinventory, IInventory iinventory1, int i, int j, int k)
    {
        super(iinventory1, i, j, k);
        thePlayer = entityplayer;
        craftMatrix = iinventory;
    }

    public boolean isItemValid(ItemStack itemstack)
    {
        return false;
    }

    public void onPickupFromSlot(ItemStack itemstack)
    {
        itemstack.onCrafting(thePlayer.worldObj, thePlayer);
        if(itemstack.itemID == Block.workbench.blockID)
        {
            thePlayer.addStat(AchievementList.buildWorkBench, 1);
        } else
        if(itemstack.itemID == Item.WOODEN_PICKAXE.id)
        {
            thePlayer.addStat(AchievementList.buildPickaxe, 1);
        } else
        if(itemstack.itemID == Block.stoneOvenIdle.blockID)
        {
            thePlayer.addStat(AchievementList.buildFurnace, 1);
        } else
        if(itemstack.itemID == Item.WOODEN_HOE.id)
        {
            thePlayer.addStat(AchievementList.buildHoe, 1);
        } else
        if(itemstack.itemID == Item.BREAD.id)
        {
            thePlayer.addStat(AchievementList.makeBread, 1);
        } else
        if(itemstack.itemID == Item.CAKE.id)
        {
            thePlayer.addStat(AchievementList.bakeCake, 1);
        } else
        if(itemstack.itemID == Item.STONE_PICKAXE.id)
        {
            thePlayer.addStat(AchievementList.buildBetterPickaxe, 1);
        } else
        if(itemstack.itemID == Item.WOODEN_SWORD.id)
        {
            thePlayer.addStat(AchievementList.buildSword, 1);
        } else
        if(itemstack.itemID == Block.enchantmentTable.blockID)
        {
            thePlayer.addStat(AchievementList.enchantments, 1);
        } else
        if(itemstack.itemID == Block.bookShelf.blockID)
        {
            thePlayer.addStat(AchievementList.bookcase, 1);
        }
        for(int i = 0; i < craftMatrix.getSizeInventory(); i++)
        {
            ItemStack itemstack1 = craftMatrix.getStackInSlot(i);
            if(itemstack1 == null)
            {
                continue;
            }
            craftMatrix.decrStackSize(i, 1);
            if(itemstack1.getItem().hasContainerItem())
            {
                craftMatrix.setInventorySlotContents(i, new ItemStack(itemstack1.getItem().getContainerItem()));
            }
        }

    }
}
