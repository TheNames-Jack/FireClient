package net.minecraft.util;

import net.minecraft.achievement.AchievementList;
import net.minecraft.entity.EntityPlayer;
import net.minecraft.entity.player.IInventory;
import net.minecraft.gui.container.ContainerBrewingStand;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotBrewingStandPotion extends Slot {
	private EntityPlayer field_40440_f;
	final ContainerBrewingStand field_40441_a; /* synthetic field */

	public SlotBrewingStandPotion(ContainerBrewingStand containerbrewingstand, EntityPlayer entityplayer, IInventory iinventory, int i, int j, int k) {
		super(iinventory, i, j, k);
		field_40441_a = containerbrewingstand;
		field_40440_f = entityplayer;
	}

	public boolean isItemValid(ItemStack itemstack) {
		return itemstack != null && (itemstack.itemID == Item.POTION.id || itemstack.itemID == Item.GLASS_BOTTLE.id);
	}

	public int getSlotStackLimit() {
		return 1;
	}

	public void onPickupFromSlot(ItemStack itemstack) {
		if(itemstack.itemID == Item.POTION.id && itemstack.getItemDamage() > 0) {
			field_40440_f.addStat(AchievementList.potion, 1);
		}
		super.onPickupFromSlot(itemstack);
	}
}
