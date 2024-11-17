package net.minecraft.util;

import net.minecraft.entity.player.IInventory;
import net.minecraft.gui.container.ContainerEnchantment;
import net.minecraft.item.ItemStack;

public class SlotEnchantment extends Slot {
	final ContainerEnchantment field_40443_a; /* synthetic field */

	public SlotEnchantment(ContainerEnchantment containerenchantment, IInventory iinventory, int i, int j, int k) {
		super(iinventory, i, j, k);
		field_40443_a = containerenchantment;
	}

	public boolean isItemValid(ItemStack itemstack) {
		return true;
	}
}