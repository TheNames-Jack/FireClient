package net.minecraft.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockWeb extends Block {

	public BlockWeb(int i, int j) {
		super(i, j, Material.web);
	}

	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		entity.setInWeb();
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		return null;
	}

	public int getRenderType() {
		return 1;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int idDropped(int i, Random random, int j) {
		return Item.SILK.id;
	}
}
