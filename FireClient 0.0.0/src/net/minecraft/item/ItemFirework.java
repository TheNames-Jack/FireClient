package net.minecraft.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPlayer;
import net.minecraft.util.Vec3D;
import net.minecraft.world.World;

public class ItemFirework extends Item {
	public int fireworkTick = 0;
	public boolean fireworkUsed = false;

	protected ItemFirework(int i) {
		super(i);
		maxStackSize = 64;
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if(entityplayer.isUsingElytra()) {
			if(!entityplayer.abilities.depleteBuckets) {
				itemstack.stackSize--;
			}
			world.playSoundAtEntity(entityplayer, "random.fizz", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			//Reset the timer and set the firework as used
			if(!world.multiplayerWorld) {
				fireworkTick = 60;
				fireworkUsed = true;
			}
		}
		return itemstack;
	}

	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
		super.onUpdate(itemstack, world, entity, i, flag);
		if(entity instanceof EntityPlayer player) {
			// Apply velocity during the firework timer
			if(fireworkUsed && fireworkTick > 0) {
				fireworkTick--;
				// Apply consistent velocity during the timer
				Vec3D lookVector = player.getLookVec();
				double maxSpeed = 1.5D;
				double currentSpeed = Math.sqrt(player.motionX * player.motionX + player.motionZ * player.motionZ);
				if(currentSpeed > maxSpeed) {
					currentSpeed = maxSpeed;
					player.setVelocity(lookVector.xCoord * currentSpeed, lookVector.yCoord * currentSpeed, lookVector.zCoord * currentSpeed);
				}else {
					player.addVelocity(lookVector.xCoord * 0.1, lookVector.yCoord * 0.1, lookVector.zCoord * 0.1); // Smaller adjustments for consistency
				}
				world.spawnParticle("cloud", player.posX, player.posY - player.getEyeHeight(), player.posZ - (lookVector.zCoord), 0F, 0F, 0F);
				if(fireworkTick == 0) {
					fireworkUsed = false; // Reset when the timer ends
				}
			}
			// Reset the firework state if the player is not using Elytra
			if(!player.isUsingElytra()) {
				fireworkTick = 0;
				fireworkUsed = false;
			}
		}
	}
}