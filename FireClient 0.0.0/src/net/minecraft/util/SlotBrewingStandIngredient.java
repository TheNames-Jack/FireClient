package net.minecraft.util;

import net.minecraft.entity.player.IInventory;
import net.minecraft.gui.container.ContainerBrewingStand;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotBrewingStandIngredient extends Slot {
	final ContainerBrewingStand field_40442_a; /* synthetic field */

	public SlotBrewingStandIngredient(ContainerBrewingStand containerbrewingstand, IInventory iinventory, int i, int j, int k) {
		super(iinventory, i, j, k);
		field_40442_a = containerbrewingstand;
	}

	public boolean isItemValid(ItemStack itemstack) {
		if(itemstack != null) {
			return Item.itemsList[itemstack.itemID].isPotionIngredient();
		}else {
			return false;
		}
	}

	public int getSlotStackLimit() {
		return 64;
	}
}
