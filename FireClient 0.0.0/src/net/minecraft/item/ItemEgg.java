package net.minecraft.item;

import net.minecraft.entity.EntityEgg;
import net.minecraft.entity.EntityPlayer;
import net.minecraft.world.World;

public class ItemEgg extends Item {
	public ItemEgg(int i) {
		super(i);
		maxStackSize = 16;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if(!entityplayer.abilities.depleteBuckets) {
			itemstack.stackSize--;
		}
		world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if(!world.multiplayerWorld) {
			world.entityJoinedWorld(new EntityEgg(world, entityplayer));
		}
		return itemstack;
	}
}