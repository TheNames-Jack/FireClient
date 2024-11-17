package net.minecraft.block.craftingtable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityPlayer;
import net.minecraft.world.World;

public class BlockCraftingTable extends Block {
	public BlockCraftingTable(int i) {
		super(i, Material.wood);
		blockIndexInTexture = 59;
	}

	public int getBlockTextureFromSide(int i) {
		if(i == 1) {
			return blockIndexInTexture - 16;
		}
		if(i == 0) {
			return Block.planks.getBlockTextureFromSide(0);
		}
		if(i == 2 || i == 4) {
			return blockIndexInTexture + 1;
		}else {
			return blockIndexInTexture;
		}
	}

	public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer) {
		if(world.multiplayerWorld) {
			return true;
		}else {
			entityplayer.displayWorkbenchGUI(i, j, k);
			return true;
		}
	}
}