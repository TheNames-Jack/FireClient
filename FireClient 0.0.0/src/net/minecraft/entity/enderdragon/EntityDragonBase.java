package net.minecraft.entity.enderdragon;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDragonBase extends EntityLiving {
	protected int maxHealth;

	public EntityDragonBase(World world) {
		super(world);
		maxHealth = 100;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public boolean func_40156_a(DragonPart dragonpart, DamageSource damagesource, int i) {
		return attackEntityFrom(damagesource, i);
	}

	public boolean attackEntityFrom(DamageSource damagesource, int i) {
		return false;
	}

	protected boolean func_40155_e(DamageSource damagesource, int i) {
		return super.attackEntityFrom(damagesource, i);
	}
}