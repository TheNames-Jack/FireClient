package net.minecraft.util;

import net.minecraft.block.Block;
import net.minecraft.entity.player.IInventory;
import net.minecraft.gui.container.ContainerPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class SlotArmor extends Slot {
	final int armorType; /* synthetic field */
	final ContainerPlayer inventory; /* synthetic field */

	public SlotArmor(ContainerPlayer containerplayer, IInventory iinventory, int i, int j, int k, int l) {
		super(iinventory, i, j, k);
		inventory = containerplayer;
		armorType = l;
	}

	public int getSlotStackLimit() {
		return 1;
	}

	public boolean isItemValid(ItemStack itemstack) {
		if(itemstack.getItem() instanceof ItemArmor) {
			return ((ItemArmor) itemstack.getItem()).armorType == armorType;
		}
		if(itemstack.getItem().id == Block.pumpkin.blockID) {
			return armorType == 0;
		}else {
			return false;
		}
	}
}
