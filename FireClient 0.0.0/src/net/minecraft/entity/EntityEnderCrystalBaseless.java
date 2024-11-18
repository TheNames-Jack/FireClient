package net.minecraft.entity;

import net.minecraft.world.World;

public class EntityEnderCrystalBaseless extends EntityEnderCrystal {
	public EntityEnderCrystalBaseless(World world) {
		super(world);
	}

	public EntityEnderCrystalBaseless(World world, double d, double d1, double d2) {
		this(world);
		setPosition(d, d1, d2);
	}
}