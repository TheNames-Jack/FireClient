package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityEnderCrystalBaseless;
import net.minecraft.entity.EntityPlayer;
import net.minecraft.world.World;

public class ItemEnderCrystal extends Item {
	public ItemEnderCrystal(int i) {
		super(i);
	}
	
	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int l) {
		if(world.getBlockId(x, y, z) == Block.bedrock.blockID || world.getBlockId(x, y, z) == Block.obsidian.blockID) {
			if(!world.multiplayerWorld) {
				world.entityJoinedWorld(new EntityEnderCrystalBaseless(world, x + 0.5F, y + 1, z + 0.5F));
			}
			itemstack.stackSize--;
			return true;
		}
		return false;
	}
}