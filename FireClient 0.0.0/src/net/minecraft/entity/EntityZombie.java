package net.minecraft.entity;

import net.minecraft.item.Item;
import net.minecraft.util.EnumCreatureAttribute;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityZombie extends EntityMob {
	public EntityZombie(World world) {
		super(world);
		texture = "/mob/zombie.png";
		moveSpeed = 0.5F;
		attackStrength = 4;
	}

	public int getMaxHealth() {
		return 20;
	}

	protected int func_40119_ar() {
		return 2;
	}

	public void onLivingUpdate() {
		if(worldObj.isDaytime() && !worldObj.multiplayerWorld) {
			float f = getEntityBrightness(1.0F);
			if(f > 0.5F && worldObj.canBlockSeeTheSky(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) && rand.nextFloat() * 30F < (f - 0.4F) * 2.0F) {
				func_40046_d(8);
			}
		}
		super.onLivingUpdate();
	}

	protected String getLivingSound() {
		return "mob.zombie";
	}

	protected String getHurtSound() {
		return "mob.zombiehurt";
	}

	protected String getDeathSound() {
		return "mob.zombiedeath";
	}

	protected int getDropItemId() {
		return Item.ROTTEN_FLESH.id;
	}

	public EnumCreatureAttribute func_40124_t() {
		return EnumCreatureAttribute.UNDEAD;
	}
}
