package net.minecraft.entity.enderdragon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEnderCrystal;
import net.minecraft.entity.EntityFireball;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityPlayer;
import net.minecraft.entity.EntityXPOrb;
import net.minecraft.item.Item;
import net.minecraft.item.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Profiler;
import net.minecraft.util.Vec3D;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.end.DragonFightManager;

public class EntityEnderDragon extends EntityDragonBase {
	public double targetX;
	public double targetY;
	public double targetZ;
	public double field_40162_d[][];
	public int field_40164_e;
	public DragonPart dragonPartArray[];
	public DragonPart dragonPartHead;
	public DragonPart field_40171_aq;
	public DragonPart field_40170_ar;
	public DragonPart field_40169_as;
	public DragonPart field_40168_at;
	public DragonPart field_40175_au;
	public DragonPart field_40174_av;
	public float animationSpeed1;
	public float animationSpeed2;
	public boolean shouldChangeBehviour;
	public boolean field_40161_az;
	private EntityPlayer playerEntity;
	public int deathUpdateTimer;
	public List<EntityEnderCrystal> enderCrystals = new ArrayList<EntityEnderCrystal>();
	World world;
	public int maxDeathUpdateTimer;

	public EntityEnderDragon(World world) {
		super(world);
		this.world = world;
		field_40162_d = new double[64][3];
		field_40164_e = -1;
		animationSpeed1 = 0.0F;
		animationSpeed2 = 0.0F;
		shouldChangeBehviour = false;
		field_40161_az = false;
		deathUpdateTimer = 0;
		dragonPartArray = (new DragonPart[]{
				dragonPartHead = new DragonPart(this, "head", 6F, 6F), field_40171_aq = new DragonPart(this, "body", 8F, 8F), field_40170_ar = new DragonPart(this, "tail", 4F, 4F), field_40169_as = new DragonPart(this, "tail", 4F, 4F),
				field_40168_at = new DragonPart(this, "tail", 4F, 4F), field_40175_au = new DragonPart(this, "wing", 4F, 4F), field_40174_av = new DragonPart(this, "wing", 4F, 4F)
		});
		maxHealth = 1024;
		maxDeathUpdateTimer = 200;
		setEntityHealth(maxHealth);
		if(DragonFightManager.isOriginalEnderDragon || rand.nextInt(10000) == 0) {
			texture = "/mob/enderdragon/ender.png";
		}else {
			texture = "/mob/enderdragon/skeleton_enderdragon.png";
		}
		setSize(16F, 8F);
		// Changed so dragon doesn't phase through island
		noClip = false;
		// END
		isImmuneToFire = true;
		targetY = 100D;
		ignoreFrustumCheck = true;
		noClip = true;
		DragonFightManager.enderDragonExists = true;
		if(DragonFightManager.dragon == null) {
			DragonFightManager.dragon = this;
		}
	}

	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Integer(maxHealth));
	}

	public double[] func_40160_a(int i, float f) {
		if(health <= 0) {
			f = 0.0F;
		}
		f = 1.0F - f;
		int j = field_40164_e - i * 1 & 0x3f;
		int k = field_40164_e - i * 1 - 1 & 0x3f;
		double ad[] = new double[3];
		double d = field_40162_d[j][0];
		double d1;
		for(d1 = field_40162_d[k][0] - d; d1 < -180D; d1 += 360D) {
		}
		for(; d1 >= 180D; d1 -= 360D) {
		}
		ad[0] = d + d1 * (double) f;
		d = field_40162_d[j][1];
		d1 = field_40162_d[k][1] - d;
		ad[1] = d + d1 * (double) f;
		ad[2] = field_40162_d[j][2] + (field_40162_d[k][2] - field_40162_d[j][2]) * (double) f;
		return ad;
	}

	public void onLivingUpdate() {
		if(!DragonFightManager.isRespawning) {
			DragonFightManager.handleDragonFight();
			// TODO TESTING REMOVE WHEN DONE
//			health = 0;
			// END
			animationSpeed1 = animationSpeed2;
			updateDragonHealthData();
			if(DragonFightManager.isDying) {
				float f = (rand.nextFloat() - 0.5F) * 8F;
				float f2 = (rand.nextFloat() - 0.5F) * 4F;
				float f4 = (rand.nextFloat() - 0.5F) * 8F;
				worldObj.spawnParticle("largeexplode", posX + (double) f, posY + 2D + (double) f2, posZ + (double) f4, 0.0D, 0.0D, 0.0D);
				return;
			}
			updateDragonEnderCrystal();
			float f1 = 0.2F / (MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ) * 10F + 1.0F);
			f1 *= (float) Math.pow(2D, motionY);
			if(field_40161_az) {
				animationSpeed2 += f1 * 0.5F;
			}else {
				animationSpeed2 += f1;
			}
			for(; rotationYaw >= 180F; rotationYaw -= 360F) {
			}
			for(; rotationYaw < -180F; rotationYaw += 360F) {
			}
			if(field_40164_e < 0) {
				for(int i = 0; i < field_40162_d.length; i++) {
					field_40162_d[i][0] = rotationYaw;
					field_40162_d[i][1] = posY;
				}
			}
			if(++field_40164_e == field_40162_d.length) {
				field_40164_e = 0;
			}
			field_40162_d[field_40164_e][0] = rotationYaw;
			field_40162_d[field_40164_e][1] = posY;
			if(worldObj.multiplayerWorld) {
				if(newPosRotationIncrements > 0) {
					double d = posX + (newPosX - posX) / (double) newPosRotationIncrements;
					double d2 = posY + (newPosY - posY) / (double) newPosRotationIncrements;
					double d4 = posZ + (newPosZ - posZ) / (double) newPosRotationIncrements;
					double d6;
					for(d6 = newRotationYaw - (double) rotationYaw; d6 < -180D; d6 += 360D) {
					}
					for(; d6 >= 180D; d6 -= 360D) {
					}
					rotationYaw += d6 / (double) newPosRotationIncrements;
					rotationPitch += (newRotationPitch - (double) rotationPitch) / (double) newPosRotationIncrements;
					newPosRotationIncrements--;
					setPosition(d, d2, d4);
					setRotation(rotationYaw, rotationPitch);
				}
			}else {
				double d1 = targetX - posX;
				double d3 = targetY - posY;
				double d5 = targetZ - posZ;
				double d7 = d1 * d1 + d3 * d3 + d5 * d5;
				if(playerEntity != null) {
					targetX = playerEntity.posX;
					targetZ = playerEntity.posZ;
					double d8 = targetX - posX;
					double d10 = targetZ - posZ;
					double d12 = Math.sqrt(d8 * d8 + d10 * d10);
					double d13 = (0.40000000596046448D + d12 / 80D) - 1.0D;
					if(d13 > 10D) {
						d13 = 10D;
					}
					targetY = playerEntity.boundingBox.minY + d13;
				}else {
					targetX += rand.nextGaussian() * 2D;
					targetZ += rand.nextGaussian() * 2D;
				}
				if(shouldChangeBehviour || d7 < 100D || d7 > 22500D || isCollidedHorizontally || isCollidedVertically) {
					if(!DragonFightManager.isPerching && DragonFightManager.canTeleport && rand.nextInt(DragonFightManager.TELEPORT_RND_BOUND) == 0) {
						teleportRandomly();
					}
					determineBehaviour();
				}
				d3 /= MathHelper.sqrt_double(d1 * d1 + d5 * d5);
				float f10 = 0.6F;
				if(d3 < (double) (-f10)) {
					d3 = -f10;
				}
				if(d3 > (double) f10) {
					d3 = f10;
				}
				motionY += d3 * 0.10000000149011612D;
				for(; rotationYaw < -180F; rotationYaw += 360F) {
				}
				for(; rotationYaw >= 180F; rotationYaw -= 360F) {
				}
				double d9 = 180D - (Math.atan2(d1, d5) * 180D) / 3.1415927410125732D;
				double d11;
				for(d11 = d9 - (double) rotationYaw; d11 < -180D; d11 += 360D) {
				}
				for(; d11 >= 180D; d11 -= 360D) {
				}
				if(d11 > 50D) {
					d11 = 50D;
				}
				if(d11 < -50D) {
					d11 = -50D;
				}
				Vec3D vec3d = Vec3D.createVector(targetX - posX, targetY - posY, targetZ - posZ).normalize();
				Vec3D vec3d1 = Vec3D.createVector(MathHelper.sin((rotationYaw * 3.141593F) / 180F), motionY, -MathHelper.cos((rotationYaw * 3.141593F) / 180F)).normalize();
				float f18 = (float) (vec3d1.dotProduct(vec3d) + 0.5D) / 1.5F;
				if(f18 < 0.0F) {
					f18 = 0.0F;
				}
				randomYawVelocity *= 0.8F;
				float f19 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ) * 1.0F + 1.0F;
				double d14 = Math.sqrt(motionX * motionX + motionZ * motionZ) * 1.0D + 1.0D;
				if(d14 > 40D) {
					d14 = 40D;
				}
				randomYawVelocity += d11 * (0.69999998807907104D / d14 / (double) f19);
				rotationYaw += randomYawVelocity * 0.1F;
				float f20 = (float) (2D / (d14 + 1.0D));
				// Original Speed: 0.06
				float movementSpeed = 0.1F;
				// END

				handleBehviour(f18, f20, movementSpeed);

				if(field_40161_az) {
					moveEntity(motionX * 0.80000001192092896D, motionY * 0.80000001192092896D, motionZ * 0.80000001192092896D);
				}else if(!DragonFightManager.isPerching) {
					moveEntity(motionX, motionY, motionZ);
				}

				if(health <= 0) {
					DragonFightManager.isPerching = true;
				}

				Vec3D vec3d2 = Vec3D.createVector(motionX, motionY, motionZ).normalize();
				float f22 = (float) (vec3d2.dotProduct(vec3d1) + 1.0D) / 2.0F;
				f22 = 0.8F + 0.15F * f22;
				motionX *= f22;
				motionZ *= f22;
				motionY *= 0.9100000262260437D;
			}
			renderYawOffset = rotationYaw;
			dragonPartHead.width = dragonPartHead.height = 3F;
			field_40170_ar.width = field_40170_ar.height = 2.0F;
			field_40169_as.width = field_40169_as.height = 2.0F;
			field_40168_at.width = field_40168_at.height = 2.0F;
			field_40171_aq.height = 3F;
			field_40171_aq.width = 5F;
			field_40175_au.height = 2.0F;
			field_40175_au.width = 4F;
			field_40174_av.height = 3F;
			field_40174_av.width = 4F;
			float f3 = (((float) (func_40160_a(5, 1.0F)[1] - func_40160_a(10, 1.0F)[1]) * 10F) / 180F) * 3.141593F;
			float f5 = MathHelper.cos(f3);
			float f6 = -MathHelper.sin(f3);
			float f7 = (rotationYaw * 3.141593F) / 180F;
			float f8 = MathHelper.sin(f7);
			float f9 = MathHelper.cos(f7);
			field_40171_aq.onUpdate();
			field_40171_aq.setLocationAndAngles(posX + (double) (f8 * 0.5F), posY, posZ - (double) (f9 * 0.5F), 0.0F, 0.0F);
			field_40175_au.onUpdate();
			field_40175_au.setLocationAndAngles(posX + (double) (f9 * 4.5F), posY + 2D, posZ + (double) (f8 * 4.5F), 0.0F, 0.0F);
			field_40174_av.onUpdate();
			field_40174_av.setLocationAndAngles(posX - (double) (f9 * 4.5F), posY + 2D, posZ - (double) (f8 * 4.5F), 0.0F, 0.0F);
			if(!worldObj.multiplayerWorld) {
				func_41007_az();
			}
			if(!worldObj.multiplayerWorld && maxHurtTime == 0) {
				collideWithEntities(worldObj.getEntitiesWithinAABBExcludingEntity(this, field_40175_au.boundingBox.expand(4D, 2D, 4D).offset(0.0D, -2D, 0.0D)));
				collideWithEntities(worldObj.getEntitiesWithinAABBExcludingEntity(this, field_40174_av.boundingBox.expand(4D, 2D, 4D).offset(0.0D, -2D, 0.0D)));
				attackEntitiesInList(worldObj.getEntitiesWithinAABBExcludingEntity(this, dragonPartHead.boundingBox.expand(1.0D, 1.0D, 1.0D)));
			}
			if(maxHurtTime > 0) {
				maxHurtTime--;
			}
			double ad[] = func_40160_a(5, 1.0F);
			double ad1[] = func_40160_a(0, 1.0F);
			float f11 = MathHelper.sin((rotationYaw * 3.141593F) / 180F - randomYawVelocity * 0.01F);
			float f12 = MathHelper.cos((rotationYaw * 3.141593F) / 180F - randomYawVelocity * 0.01F);
			dragonPartHead.onUpdate();
			dragonPartHead.setLocationAndAngles(posX + (double) (f11 * 5.5F * f5), posY + (ad1[1] - ad[1]) * 1.0D + (double) (f6 * 5.5F), posZ - (double) (f12 * 5.5F * f5), 0.0F, 0.0F);
			for(int j = 0; j < 3; j++) {
				DragonPart dragonpart = null;
				if(j == 0) {
					dragonpart = field_40170_ar;
				}
				if(j == 1) {
					dragonpart = field_40169_as;
				}
				if(j == 2) {
					dragonpart = field_40168_at;
				}
				double ad2[] = func_40160_a(12 + j * 2, 1.0F);
				float f13 = (rotationYaw * 3.141593F) / 180F + ((func_40159_b(ad2[0] - ad[0]) * 3.141593F) / 180F) * 1.0F;
				float f14 = MathHelper.sin(f13);
				float f15 = MathHelper.cos(f13);
				float f16 = 1.5F;
				float f17 = (float) (j + 1) * 2.0F;
				dragonpart.onUpdate();
				dragonpart.setLocationAndAngles(posX - (double) ((f8 * f16 + f14 * f17) * f5), ((posY + (ad2[1] - ad[1]) * 1.0D) - (double) ((f17 + f16) * f6)) + 1.5D, posZ + (double) ((f9 * f16 + f15 * f17) * f5), 0.0F, 0.0F);
			}

			if(!worldObj.multiplayerWorld) {
				field_40161_az = canDestroyBlockInAABB(dragonPartHead.boundingBox) | canDestroyBlockInAABB(field_40171_aq.boundingBox);
			}
		}else {
			onRespawnUpdate();
		}
	}

	// Handle behaviour movement and statements
	private void handleBehviour(float f18, float f20, float f21) {
		// Get the highest block at (0, 0)
		int highestBlockY = worldObj.getHeightValue(0, 0);
		// Calculate the direction vector towards (0, highestBlockY, 0)
		double targetX = 0.5D; // Centered on the block
		double targetY = highestBlockY + 1; // Hover slightly above the block
		double targetZ = 0.5D; // Centered on the block

		if(!DragonFightManager.isPerching) {
			moveFlying(0.0F, -1F, f21 * (f18 * f20 + (1.0F - f20)));
			if(!DragonFightManager.isFollowingPlayer && rand.nextInt(DragonFightManager.PERCH_RND_BOUND) == 0) {
				DragonFightManager.isPerching = true;
				DragonFightManager.reachedPerchPosition = false;
			}
		}

		// If the dragon is perching
		if(DragonFightManager.isPerching) {
			if(DragonFightManager.reachedPerchPosition && DragonFightManager.perchTimer < DragonFightManager.MAX_PERCH_TIME) {
				DragonFightManager.perchTimer++;
			}else if(DragonFightManager.perchTimer >= DragonFightManager.MAX_PERCH_TIME) {
				DragonFightManager.perchTimer = 0;
				DragonFightManager.isPerching = false;
				DragonFightManager.reachedPerchPosition = false;
			}

			if(!DragonFightManager.reachedPerchPosition) {
				double dx = targetX - posX;
				double dy = targetY - posY;
				double dz = targetZ - posZ;

				// Normalize the direction vector to get unit vectors
				double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

				// Check if the dragon has reached the target position
				double stoppingDistance = 1.5D; // Radius for "reached" condition
				if(distance <= stoppingDistance) {
					DragonFightManager.reachedPerchPosition = true;
				}else {
					// Continue moving towards the target
					if(distance > 0.0D) {
						dx /= distance;
						dy /= distance;
						dz /= distance;
					}

					// Use the direction vector for rotation
					rotationYaw = (float) (Math.atan2(dz, dx) * (180.0D / Math.PI)) + 90.0F;
					rotationPitch = (float) -(Math.atan2(dy, Math.sqrt(dx * dx + dz * dz)) * (180.0D / Math.PI));

					// Move towards the target using moveFlying
					moveFlying(0.2F, -1.0F, f21);
					if(posY > targetY) {
						moveEntity(motionX, -f21 * 6, motionZ);
					}else if(posY < targetY) {
						moveEntity(motionX, f21 * 6, motionZ);
					}
				}
			}else {
				setPosition(targetX, targetY, targetZ);
			}

			if(health <= 0) {
				DragonFightManager.isDying = true;
			}
		}

		if(DragonFightManager.canShootFireBall && rand.nextInt(DragonFightManager.FIREBALL_RND_BOUND) == 0) {
			shootFireBall();
		}
	}

	private void shootFireBall() {
		if(playerEntity != null) {
			// Calculate the direction from the dragon to the player
			double dX = playerEntity.posX - posX;
			double dY = (playerEntity.boundingBox.minY + (double) (playerEntity.height / 2.0F)) - (posY + (double) (height / 2.0F));
			double dZ = playerEntity.posZ - posZ;

			// Normalize the direction vector
			double distance = Math.sqrt(dX * dX + dY * dY + dZ * dZ);
			if(distance == 0.0) return; // Avoid division by zero
			dX /= distance;
			dY /= distance;
			dZ /= distance;

			// Play sound effect for fireball launch
			worldObj.playAuxSFXAtEntity(null, 1008, (int) posX, (int) posY, (int) posZ, 0);

			// Create the fireball entity
			EntityFireball entityFireball = new EntityFireball(worldObj, this, dX, dY, dZ);

			// Position the fireball at the dragon's head or another appropriate location
			double fireballStartOffset = 4.0D; // Distance from the dragon
			entityFireball.posX = posX + dX * fireballStartOffset;
			entityFireball.posY = posY + (double) (height / 3.0F);
			entityFireball.posZ = posZ + dZ * fireballStartOffset;

			// Set the velocity of the fireball based on the direction
			double fireballSpeed = 4.0D; // Adjust speed as needed
			entityFireball.motionX = dX * fireballSpeed;
			entityFireball.motionY = dY * fireballSpeed;
			entityFireball.motionZ = dZ * fireballSpeed;

			// Spawn the fireball in the world
			if(!world.multiplayerWorld) {
				worldObj.entityJoinedWorld(entityFireball);
			}
		}
	}

	private boolean teleportRandomly() {
		if(posX > -50 && posX < 50 && posY > 60 && posY < 128 && posZ > -50 && posZ < 50) {
			double positionX = posX + (rand.nextDouble() - 0.5D) * 64D;
			double positionY = posY + (double) (rand.nextInt(128) - 32);
			double positionZ = posZ + (rand.nextDouble() - 0.5D) * 64D;
			return teleportTo(positionX, positionY, positionZ);
		}else {
			return false;
		}
	}

	private boolean teleportTo(double tpX, double tpY, double tpZ) {
		double originalX = posX;
		double originalY = posY;
		double originalZ = posZ;
		boolean shouldTeleport = false;
		for(int attempt = 0; attempt < 10; attempt++) { // Retry up to 10 times
			setPosition(tpX, tpY, tpZ);
			// Check if the area within the dragon's AABB is clear
			AxisAlignedBB aabb = this.boundingBox.offset(tpX - posX, tpY - posY, tpZ - posZ);
			boolean isAreaClear = true;
			for(int x = MathHelper.floor_double(aabb.minX); x <= MathHelper.floor_double(aabb.maxX); x++) {
				for(int y = MathHelper.floor_double(aabb.minY); y <= MathHelper.floor_double(aabb.maxY); y++) {
					for(int z = MathHelper.floor_double(aabb.minZ); z <= MathHelper.floor_double(aabb.maxZ); z++) {
						int blockID = worldObj.getBlockId(x, y, z);
						if(blockID != 0) { // Not air
							isAreaClear = false;
							break;
						}
					}
					if(!isAreaClear) break;
				}
				if(!isAreaClear) break;
			}

			if(isAreaClear) {
				shouldTeleport = true;
				break;
			}else {
				// Adjust coordinates and try again
				tpX += (rand.nextDouble() - 0.5D) * 16.0D; // Random offset
				tpY += (rand.nextDouble() - 0.5D) * 8.0D;
				tpZ += (rand.nextDouble() - 0.5D) * 16.0D;
			}
		}

		if(!shouldTeleport) {
			// Reset to original position if no valid spot is found
			posX = originalX;
			posY = originalY;
			posZ = originalZ;
			return false;
		}

		// Play teleportation effects
		int particles = 4096;
		for(int j = 0; j < particles; j++) {
			double progress = (double) j / ((double) particles - 1.0D);
			float offsetX = (rand.nextFloat() - 0.5F) * 10.2F;
			float offsetY = (rand.nextFloat() - 0.5F) * 10.2F;
			float offsetZ = (rand.nextFloat() - 0.5F) * 10.2F;
			double particleX = originalX + (posX - originalX) * progress + (rand.nextDouble() - 0.5D) * (double) width * 2.0D;
			double particleY = originalY + (posY - originalY) * progress + rand.nextDouble() * (double) height;
			double particleZ = originalZ + (posZ - originalZ) * progress + (rand.nextDouble() - 0.5D) * (double) width * 2.0D;
			worldObj.spawnParticle("cloud", particleX, particleY, particleZ, offsetX, offsetY, offsetZ);
			worldObj.spawnParticle("portal", particleX, particleY, particleZ, offsetX, offsetY, offsetZ);
		}
		worldObj.playSoundEffect(originalX, originalY, originalZ, "mob.enderdragon.teleport", 1.0F, 1.0F);
		worldObj.playSoundAtEntity(this, "mob.enderdragon.teleport", 1.0F, 1.0F);
		return true;
	}

	private void updateDragonHealthData() {
		if(!worldObj.multiplayerWorld) {
			dataWatcher.updateObject(16, Integer.valueOf(health));
		}
	}

	private void updateDragonEnderCrystal() {
		if(!DragonFightManager.isRespawning) {
			if(enderCrystals != null) {
				// Check if the dragon is connected to crystals and heal
				for(Iterator<EntityEnderCrystal> iterator = enderCrystals.iterator(); iterator.hasNext();) {
					EntityEnderCrystal crystal = iterator.next();
					if(crystal.isDead) {
						if(!worldObj.multiplayerWorld) {
							shouldDamage(dragonPartHead, DamageSource.explosion, 10);
						}
						iterator.remove(); // Remove dead crystals from the list
					}else if(ticksExisted % 5 == 0 && health < maxHealth) {
						health++;
					}
				}
			}
			// Randomly choose two crystals to heal from
			if(rand.nextInt(40) == 0) {
				float range = 32F;
				List<Entity> nearbyEntities = worldObj.getEntitiesWithinAABB(EntityEnderCrystal.class, boundingBox.expand(range, range, range));
				// Filter for only EntityEnderCrystal instances
				List<EntityEnderCrystal> nearbyCrystals = new ArrayList<>();
				for(Entity entity : nearbyEntities) {
					if(entity instanceof EntityEnderCrystal) {
						nearbyCrystals.add((EntityEnderCrystal) entity);
					}
				}
				// If there are crystals nearby, choose two at random
				if(!nearbyCrystals.isEmpty()) {
					nearbyEntities.clear();
					Collections.shuffle(nearbyCrystals); // Shuffle the list for randomness
					// Clear previous crystals and select new ones
					enderCrystals = new ArrayList<>();
					for(int i = 0; i < Math.min(2, nearbyCrystals.size()); i++) {
						enderCrystals.add(nearbyCrystals.get(i));
					}
				}
			}
		}else {
			// Sequentially target crystals during the respawn process
			if(DragonFightManager.respawnTimer > 0) { // Check if in respawn phase
				float range = 256F;
				// Retrieve crystals in range
				List<Entity> nearbyEntities = worldObj.getEntitiesWithinAABB(EntityEnderCrystal.class, boundingBox.expand(range, range, range));
				List<EntityEnderCrystal> nearbyCrystals = new ArrayList<>();
				for(Entity entity : nearbyEntities) {
					if(entity instanceof EntityEnderCrystal) {
						nearbyCrystals.add((EntityEnderCrystal) entity);
					}
				}

				if(!nearbyCrystals.isEmpty()) {
					// Calculate how long each crystal will follow during the respawn process
					int maxRespawnTime = 200; // Total respawn duration
					int timePerCrystal = maxRespawnTime / nearbyCrystals.size();

					// Determine the current crystal index based on respawn timer
					int currentCrystalIndex = (maxRespawnTime - DragonFightManager.respawnTimer) / timePerCrystal;

					// Ensure the index is valid and add crystals progressively
					if(currentCrystalIndex < nearbyCrystals.size()) {
						// Initialize enderCrystals if null
						if(enderCrystals == null) {
							enderCrystals = new ArrayList<>();
						}

						// Add new crystals to the list one by one over time
						EntityEnderCrystal currentCrystal = nearbyCrystals.get(currentCrystalIndex);
						if(!enderCrystals.contains(currentCrystal)) {
							enderCrystals.add(currentCrystal);
						}
					}
				}
			}
		}
	}

	private void func_41007_az() {
		if(ticksExisted % 20 == 0) {
			Vec3D vec3d = getLook(1.0F);
			double d = 0.0D;
			double d1 = -1D;
			double d2 = 0.0D;
		}
	}

	private void collideWithEntities(List list) {
		double d = (field_40171_aq.boundingBox.minX + field_40171_aq.boundingBox.maxX) / 2D;
		double d1 = (field_40171_aq.boundingBox.minZ + field_40171_aq.boundingBox.maxZ) / 2D;
		Iterator iterator = list.iterator();
		do {
			if(!iterator.hasNext()) {
				break;
			}
			Entity entity = (Entity) iterator.next();
			if(entity instanceof EntityLiving) {
				double d2 = entity.posX - d;
				double d3 = entity.posZ - d1;
				double d4 = d2 * d2 + d3 * d3;
				entity.addVelocity((d2 / d4) * 4D, 0.20000000298023224D, (d3 / d4) * 4D);
			}
		}while(true);
	}

	private void attackEntitiesInList(List list) {
		for(int i = 0; i < list.size(); i++) {
			Entity entity = (Entity) list.get(i);
			if(entity instanceof EntityLiving) {
				entity.attackEntityFrom(DamageSource.causeMobDamage(this), 10);
			}
		}
	}

	// Handles random wardering, and following player statements
	private void determineBehaviour() {
		shouldChangeBehviour = false;
		if(!DragonFightManager.isPerching) {
			if(rand.nextInt(2) == 0 && worldObj.playerEntities.size() > 0) {
				playerEntity = (EntityPlayer) worldObj.playerEntities.get(rand.nextInt(worldObj.playerEntities.size()));
				DragonFightManager.isFollowingPlayer = true;
			}else {
				boolean validLocation = false;
				DragonFightManager.isFollowingPlayer = false;
				do {
					targetX = 0.0D;
					targetY = 70F + rand.nextFloat() * 50F;
					targetZ = 0.0D;
					targetX += rand.nextFloat() * 120F - 60F;
					targetZ += rand.nextFloat() * 120F - 60F;
					double d = posX - targetX;
					double d1 = posY - targetY;
					double d2 = posZ - targetZ;
					validLocation = d * d + d1 * d1 + d2 * d2 > 50D;
				}while(!validLocation);
				playerEntity = null;
			}
		}
	}
	// END

	private float func_40159_b(double d) {
		for(; d >= 180D; d -= 360D) {
		}
		for(; d < -180D; d += 360D) {
		}
		return (float) d;
	}

	private boolean canDestroyBlockInAABB(AxisAlignedBB axisalignedbb) {
		int minX = MathHelper.floor_double(axisalignedbb.minX);
		int minY = MathHelper.floor_double(axisalignedbb.minY);
		int minZ = MathHelper.floor_double(axisalignedbb.minZ);
		int maxX = MathHelper.floor_double(axisalignedbb.maxX);
		int maxY = MathHelper.floor_double(axisalignedbb.maxY);
		int maxZ = MathHelper.floor_double(axisalignedbb.maxZ);
		boolean flag = false;
		boolean flag1 = false;
		for(int k1 = minX; k1 <= maxX; k1++) {
			for(int l1 = minY; l1 <= maxY; l1++) {
				for(int i2 = minZ; i2 <= maxZ; i2++) {
					int j2 = worldObj.getBlockId(k1, l1, i2);
					if(j2 == 0) {
						continue;
					}
					if(j2 == Block.OBSIDIAN.blockID || j2 == Block.TORCH.blockID || j2 == Block.END_STONE.blockID || j2 == Block.BEDROCK.blockID || j2 == Block.IRON_BARS.blockID) {
						flag = true;
					}else {
						flag1 = true;
						worldObj.setBlockWithNotify(k1, l1, i2, 0);
					}
				}
			}
		}
		if(flag1) {
			double d = axisalignedbb.minX + (axisalignedbb.maxX - axisalignedbb.minX) * (double) rand.nextFloat();
			double d1 = axisalignedbb.minY + (axisalignedbb.maxY - axisalignedbb.minY) * (double) rand.nextFloat();
			double d2 = axisalignedbb.minZ + (axisalignedbb.maxZ - axisalignedbb.minZ) * (double) rand.nextFloat();
			worldObj.spawnParticle("largeexplode", d, d1, d2, 0.0D, 0.0D, 0.0D);
		}
		return flag;
	}

	public boolean shouldDamage(DragonPart dragonpart, DamageSource damagesource, int damageAmount) {
		if(dragonpart != dragonPartHead) {
			damageAmount = (damageAmount / 2);
		}
		float f = (rotationYaw * 3.141593F) / 180F;
		float f1 = MathHelper.sin(f);
		float f2 = MathHelper.cos(f);
		targetX = posX + (double) (f1 * 5F) + (double) ((rand.nextFloat() - 0.5F) * 2.0F);
		targetY = posY + (double) (rand.nextFloat() * 3F) + 1.0D;
		targetZ = (posZ - (double) (f2 * 5F)) + (double) ((rand.nextFloat() - 0.5F) * 2.0F);
		playerEntity = null;
		if((damagesource.getSourceOfDamage() instanceof EntityPlayer) || damagesource == DamageSource.explosion) {
			func_40155_e(damagesource, damageAmount);
		}
		return true;
	}

	public void onRespawnUpdate() {
		float timerProgress = (float) DragonFightManager.respawnTimer / DragonFightManager.MAX_RESPAWN_TIMER;
		if(DragonFightManager.respawnTimer < DragonFightManager.MAX_RESPAWN_TIMER) {
			DragonFightManager.respawnTimer++;
			// Generate particle effects
			float f = (rand.nextFloat() - 0.5F) * 8F;
			float f1 = (rand.nextFloat() - 0.5F) * 4F;
			float f2 = (rand.nextFloat() - 0.5F) * 8F;
			// Update each Ender Crystal
			for(Entity entity : DragonFightManager.respawnCrystals) {
				if(entity instanceof EntityEnderCrystal crystal) {
					// Define the center of rotation
					double centerX = 0; // Replace with actual center X-coordinate
					double centerZ = 0; // Replace with actual center Z-coordinate
					// Vertical floating logic
					double targetY = 80; // Desired Y level
					double interpolatedY = crystal.posY + (targetY - crystal.posY) * timerProgress / 4;
					// Calculate the initial offset from the center
					double offsetX = crystal.posX - centerX;
					double offsetZ = crystal.posZ - centerZ;
					// Compute the radius and current angle based on the initial offset
					double radius = Math.sqrt(offsetX * offsetX + offsetZ * offsetZ);
					double initialAngle = Math.atan2(offsetZ, offsetX);
					// Adjust the angle using consistent rotation speed
					double angle = initialAngle + ((timerProgress / 16) * (timerProgress / 16)) * Math.PI * 2 * 5;
					// Calculate the new rotated position
					double newX = centerX + radius * Math.cos(angle);
					double newZ = centerZ + radius * Math.sin(angle);
					// Update the crystal's position

					// crystal.setPosition(newX, interpolatedY, newZ);

					crystal.setPosition(crystal.posX, interpolatedY, crystal.posZ);

					// Prevent the crystal from taking damage
					crystal.disableDamage = true;
					crystal.isRespawnCrystal = true;
				}
			}
		}
		updateDragonEnderCrystal();
		// Linearly increase health to maxHealth based on progress
		health = Math.min(maxHealth, (int) (maxHealth * timerProgress));
		updateDragonHealthData();
		// End respawn process when the timer reaches 200
		if(DragonFightManager.respawnTimer >= DragonFightManager.MAX_RESPAWN_TIMER) {
			DragonFightManager.isRespawning = false;
			for(Entity entity : DragonFightManager.respawnCrystals) {
				if(entity instanceof EntityEnderCrystal crystal) {
					worldObj.createExplosion(crystal, crystal.posX, crystal.posY, crystal.posZ, 0.2F);
					crystal.setEntityDead();
					if(enderCrystals != null) {
						enderCrystals = null;
					}
				}
			}
		}
		moveEntity(0.0D, -0.12D, 0.0D);
	}

	protected void onDeathUpdate() {
		if(!DragonFightManager.isRespawning) {
			if(deathUpdateTimer < maxDeathUpdateTimer) {
				deathUpdateTimer++;
			}
			enderCrystals = null;
			if(deathUpdateTimer >= 180 && deathUpdateTimer <= maxDeathUpdateTimer) {
				float f = (rand.nextFloat() - 0.5F) * 8F;
				float f1 = (rand.nextFloat() - 0.5F) * 4F;
				float f2 = (rand.nextFloat() - 0.5F) * 8F;
				worldObj.spawnParticle("hugeexplosion", posX + (double) f, posY + 2D + (double) f1, posZ + (double) f2, 0.0D, 0.0D, 0.0D);
			}
			if(!worldObj.multiplayerWorld && deathUpdateTimer > 150 && deathUpdateTimer % 5 == 0) {
				for(int i = 1000; i > 0;) {
					int k = EntityXPOrb.getXPSplit(i);
					i -= k;
					worldObj.entityJoinedWorld(new EntityXPOrb(worldObj, posX, posY, posZ, k));
				}
			}
			moveEntity(0.0D, 0.10000000149011612D, 0.0D);
			renderYawOffset = rotationYaw += 20F;
			if(deathUpdateTimer >= maxDeathUpdateTimer) {
				for(int j = 10000; j > 0;) {
					int l = EntityXPOrb.getXPSplit(j);
					j -= l;
					worldObj.entityJoinedWorld(new EntityXPOrb(worldObj, posX, posY, posZ, l));
				}
				int i1 = (5 + rand.nextInt(2) * 2) - 1;
				int j1 = (5 + rand.nextInt(2) * 2) - 1;
				if(rand.nextInt(2) == 0) {
					i1 = 0;
				}else {
					j1 = 0;
				}
				DragonFightManager.handleDragonDeath(world);
				onEntityDeath();
				setEntityDead();
			}
		}
	}

	protected void despawnEntity() {
	}

	public Entity[] func_40048_X() {
		return dragonPartArray;
	}

	public boolean canBeCollidedWith() {
		return false;
	}

	public int currentHealth() {
		return dataWatcher.getWatchableObjectInt(16);
	}

	@Override
	protected void dropFewItems(boolean flag, int i) {
		dropItem(Item.ARROW.id, 1);
	}

	@Override
	protected String getDeathSound() {
		return "mob.enderdragon.death";
	}

	@Override
	protected String getHurtSound() {
		return "mob.enderdragon.hit";
	}

	@Override
	public int getTalkInterval() {
		return 40;
	}

	@Override
	protected String getLivingSound() {
		return (rand.nextBoolean() ? "mob.enderdragon.growl" : "mob.enderdragon.wings");
	}

	@Override
	public void onEntityUpdate() {
		prevSwingProgress = swingProgress;
		super.onEntityUpdate();
		Profiler.startSection("mobBaseTick");
		if(rand.nextInt(20) < this.livingSoundTime++) {
			livingSoundTime = -getTalkInterval();
			playLivingSound();
		}
		if(isEntityAlive() && isEntityInsideOpaqueBlock()) {
			if(!attackEntityFrom(DamageSource.inWall, 1));
		}
		if(func_40047_D() || worldObj.multiplayerWorld) {
			func_40045_B();
		}
		if(isEntityAlive() && isInsideOfMaterial(Material.water) && !canBreatheUnderwater() && !activePotionsMap.containsKey(Integer.valueOf(Potion.potionWaterBreathing.id))) {
			func_41003_g(func_40116_f(func_41001_Z()));
			if(func_41001_Z() == -20) {
				func_41003_g(0);
				for(int i = 0; i < 8; i++) {
					float f = rand.nextFloat() - rand.nextFloat();
					float f1 = rand.nextFloat() - rand.nextFloat();
					float f2 = rand.nextFloat() - rand.nextFloat();
					worldObj.spawnParticle("bubble", posX + (double) f, posY + (double) f1, posZ + (double) f2, motionX, motionY, motionZ);
				}

				attackEntityFrom(DamageSource.drown, 2);
			}
			func_40045_B();
		}else {
			func_41003_g(300);
		}
		prevCameraPitch = cameraPitch;
		if(attackTime > 0) {
			attackTime--;
		}
		if(hurtTime > 0) {
			hurtTime--;
		}
		if(heartsLife > 0) {
			heartsLife--;
		}
		if(DragonFightManager.isDying) {
			onDeathUpdate();
		}
		if(field_34905_c > 0) {
			field_34905_c--;
		}else {
			player = null;
		}
		updatePotionEffects();
		field_9359_x = field_9360_w;
		prevRenderYawOffset = renderYawOffset;
		prevRotationYaw = rotationYaw;
		prevRotationPitch = rotationPitch;
		Profiler.endSection();
	}

	@Override
	public boolean canBePushed() {
		return false;
	}
}