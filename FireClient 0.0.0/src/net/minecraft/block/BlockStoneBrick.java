package net.minecraft.block;

import net.minecraft.block.material.Material;

public class BlockStoneBrick extends Block {

	public BlockStoneBrick(int i) {
		super(i, 54, Material.rock);
	}

	public int getBlockTextureFromSideAndMetadata(int i, int j) {
		switch(j) {
			default:
				return 54;

			case 1: // '\001'
				return 100;

			case 2: // '\002'
				return 101;
		}
	}

	public int damageDropped(int i) {
		return i;
	}
}