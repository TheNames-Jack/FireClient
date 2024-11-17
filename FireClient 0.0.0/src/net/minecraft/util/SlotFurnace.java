// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.util;

import net.minecraft.achievement.AchievementList;
import net.minecraft.entity.EntityPlayer;
import net.minecraft.entity.player.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

// Referenced classes of package net.minecraft.src:
//            Slot, EntityPlayer, ItemStack, Item, 
//            AchievementList, IInventory

public class SlotFurnace extends Slot
{

    private EntityPlayer thePlayer;

    public SlotFurnace(EntityPlayer entityplayer, IInventory iinventory, int i, int j, int k)
    {
        super(iinventory, i, j, k);
        thePlayer = entityplayer;
    }

    public boolean isItemValid(ItemStack itemstack)
    {
        return false;
    }

    public void onPickupFromSlot(ItemStack itemstack)
    {
        itemstack.onCrafting(thePlayer.worldObj, thePlayer);
        if(itemstack.itemID == Item.IRON_INGOT.id)
        {
            thePlayer.addStat(AchievementList.acquireIron, 1);
        }
        if(itemstack.itemID == Item.COOKED_FISH.id)
        {
            thePlayer.addStat(AchievementList.cookFish, 1);
        }
        super.onPickupFromSlot(itemstack);
    }
}
