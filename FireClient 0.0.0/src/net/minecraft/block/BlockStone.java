package net.minecraft.block;

import java.util.Random;

import net.minecraft.block.material.Material;

public class BlockStone extends Block {
	public BlockStone(int i, int j) {
		super(i, j, Material.rock);
	}

	public int idDropped(int i, Random random, int j) {
		return Block.cobblestone.blockID;
	}
}
