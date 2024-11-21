package net.minecraft.world.end;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEnderCrystal;
import net.minecraft.entity.enderdragon.EntityEnderDragon;
import net.minecraft.util.EnumSound;
import net.minecraft.util.EnumSoundType;
import net.minecraft.util.helper.LocationInteger;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class DragonFightManager {
	public static List<EntityEnderCrystal> pillarCrystals = new ArrayList<EntityEnderCrystal>();
	public static List<EntityEnderCrystal> respawnCrystals = new ArrayList<EntityEnderCrystal>();
	public static List<LocationInteger> pillarCystalLocations = new ArrayList<LocationInteger>();
	public static boolean pillarsGenerated = false;
	public static boolean bossMusicPlayed = false;
	public static final float MAX_RESPAWN_TIMER = 200F;
	public static int respawnTimer = 0;
	private static Minecraft minecraft;

	public static boolean isOriginalEnderDragon = true;
	public static boolean enderDragonExists = false;
	public static boolean isRespawning = false;
	public static boolean isDying = false;
	public static boolean isFollowingPlayer = false;
	public static boolean isPearching = false;

	public static void generatePillars(World world, Random random, boolean firstGeneration) {
		// Define pillar data
		int[][] pillarData = {
				{
						3, 76, 1596
				}, // Radius, Height, Total blocks
				{
						3, 79, 1659
				}, {
						3, 82, 1722
				}, {
						4, 85, 3145
				}, {
						4, 88, 3256
				}, {
						4, 91, 3367
				}, {
						5, 94, 5358
				}, {
						5, 97, 5529
				}, {
						5, 100, 5700
				}, {
						6, 103, 9167
				}
		};
		int maxPillars = pillarData.length;
		int radiusFromCenter = 43;
		for(int i = 0; i < maxPillars; i++) {
			// Calculate pillar position in a circular arrangement
			double angle = 2 * Math.PI * i / maxPillars;
			int startX = (int) (Math.cos(angle) * radiusFromCenter);
			int startZ = (int) (Math.sin(angle) * radiusFromCenter);
			int pillarRadius = pillarData[i][0];
			int pillarHeight = pillarData[i][1];
			// Generate the pillar structure
			for(int y = 0; y < pillarHeight && y < world.worldYMax; y++) {
				for(int xOffset = startX - pillarRadius; xOffset <= startX + pillarRadius; xOffset++) {
					for(int zOffset = startZ - pillarRadius; zOffset <= startZ + pillarRadius; zOffset++) {
						int deltaX = xOffset - startX;
						int deltaZ = zOffset - startZ;
						if(deltaX * deltaX + deltaZ * deltaZ <= pillarRadius * pillarRadius + 1) {
							world.setBlockWithNotify(xOffset, y, zOffset, Block.OBSIDIAN.blockID);
						}
					}
				}
			}
			// Add iron bars for specific pillars (alternate pattern)
			if(i % 5 == 1) {
				int barHeight = pillarHeight - 3; // Adjust iron bar height
				// Generate the iron bar walls
				for(int xOffset = startX - 2; xOffset <= startX + 2; xOffset++) {
					for(int zOffset = startZ - 2; zOffset <= startZ + 2; zOffset++) {
						if(Math.abs(xOffset - startX) == 2 || Math.abs(zOffset - startZ) == 2) {
							for(int yOffset = barHeight + 3; yOffset < barHeight + 6; yOffset++) {
								world.setBlockWithNotify(xOffset, yOffset, zOffset, Block.IRON_BARS.blockID);
							}
						}
					}
				}
				// Generate the iron bar roof
				for(int xOffset = startX - 2; xOffset <= startX + 2; xOffset++) {
					for(int zOffset = startZ - 2; zOffset <= startZ + 2; zOffset++) {
						world.setBlockWithNotify(xOffset, barHeight + 6, zOffset, Block.IRON_BARS.blockID);
					}
				}
			}

			// EntityEnderCrystal enderCrystal = new EntityEnderCrystal(world);
			// enderCrystal.setLocationAndAngles(startX + 0.5F, pillarHeight, startZ + 0.5F, random.nextFloat() * 360F, 0.0F);
			// // Place the Ender Crystal on top of the pillar
			// if(!pillarCystalLocations.contains(new LocationInteger(startX, pillarHeight, startZ))) {
			// pillarCystalLocations.add(new LocationInteger(startX, pillarHeight, startZ));
			// pillarCrystals.add(enderCrystal);
			// world.entityJoinedWorld(enderCrystal);
			// removeInvalidEnderCrystals(world, pillarCrystals);
			// }

			// boolean crystalExists = false;
			// for(Entity entity : world.getLoadedEntityList()) {
			// if(entity instanceof EntityEnderCrystal) {
			// EntityEnderCrystal crystal = (EntityEnderCrystal) entity;
			// if(Math.abs(crystal.posX - (startX + 0.5F)) < 1 && Math.abs(crystal.posZ - (startZ + 0.5F)) < 1 && Math.abs(crystal.posY - pillarHeight) < 1) {
			// crystalExists = true;
			// break;
			// }
			// }
			// }
			//
			// if(!crystalExists) {
			// EntityEnderCrystal enderCrystal = new EntityEnderCrystal(world);
			// enderCrystal.setLocationAndAngles(startX + 0.5F, pillarHeight, startZ + 0.5F, random.nextFloat() * 360F, 0.0F);
			// world.entityJoinedWorld(enderCrystal);
			// // Ensure the location and crystal list are up to date
			// if(!pillarCystalLocations.contains(new LocationInteger(startX, pillarHeight, startZ))) {
			// pillarCystalLocations.add(new LocationInteger(startX, pillarHeight, startZ));
			// pillarCrystals.add(enderCrystal);
			// }
			// }
			// pillarCrystals.removeIf(crystal -> crystal.isDead);
			// pillarCystalLocations.removeIf(
			// location -> world.getLoadedEntityList().stream().noneMatch(entity -> entity instanceof EntityEnderCrystal && Math.abs(entity.posX - location.getPosX()) < 1 && Math.abs(entity.posZ - location.getPosZ()) < 1 && Math.abs(entity.posY - location.getPosY()) < 1));

			// Check if a crystal already exists at this pillar location
			boolean crystalExists = false;
			for(Entity entity : world.getLoadedEntityList()) {
				if(entity instanceof EntityEnderCrystal) {
					EntityEnderCrystal crystal = (EntityEnderCrystal) entity;
					// Check if the crystal is at the same position (with a small margin)
					if(Math.abs(crystal.posX - (startX + 0.5F)) < 1 && Math.abs(crystal.posZ - (startZ + 0.5F)) < 1 && Math.abs(crystal.posY - pillarHeight) < 1) {
						crystalExists = true;
						break;
					}
				}
			}

			// If no crystal exists, spawn a new one
			if(!crystalExists) {
				EntityEnderCrystal enderCrystal = new EntityEnderCrystal(world);
				enderCrystal.setLocationAndAngles(startX + 0.5F, pillarHeight, startZ + 0.5F, random.nextFloat() * 360F, 0.0F);
				world.entityJoinedWorld(enderCrystal);

				// Add the crystal to the list and location tracker
				if(!pillarCystalLocations.contains(new LocationInteger(startX, pillarHeight, startZ))) {
					pillarCystalLocations.add(new LocationInteger(startX, pillarHeight, startZ));
					pillarCrystals.add(enderCrystal);
				}
			}

			// Set block at the pillar location to bedrock
			world.setBlockWithNotify(startX, pillarHeight, startZ, Block.BEDROCK.blockID);
		}
	}

	// Method to remove all Ender Crystals not at the middle or in the pillarCrystals list
	private static void removeInvalidEnderCrystals(World world, List<EntityEnderCrystal> pillarCrystals) {
		// Define the middle position of the End portal
		double middleX = 0.5;
		double middleZ = 0.5;
		double middleRadius = 3.0; // Allow some margin for the middle crystal(s)
		// Get all Ender Crystal entities in the world
		List<Entity> entities = world.getLoadedEntityList();
		for(Entity entity : entities) {
			if(entity instanceof EntityEnderCrystal) {
				EntityEnderCrystal crystal = (EntityEnderCrystal) entity;
				// Check if the crystal is in the middle or in the list of valid pillar crystals
				boolean isMiddleCrystal = crystal.getDistanceSq(middleX, crystal.posY, middleZ) <= middleRadius * middleRadius;
				boolean isPillarCrystal = pillarCrystals.contains(crystal);
				// Remove the crystal if it's not valid
				if(!isMiddleCrystal && !isPillarCrystal) {
					crystal.setEntityDead();
				}
			}
		}
	}

	public static void setEndPortal(World worldObj) {
		// Find the next available bedrock block from Y=0 to Y=127
		int portalY = -1;
		for(int y = 0; y < 128; y++) {
			int blockID = worldObj.getBlockId(0, y, 0); // Check the block at the center (X=0, Z=0)
			if(blockID == Block.BEDROCK.blockID) { // Check if it's a bedrock block
				boolean portalExists = false;
				// Check if there's already an End Portal at this level
				for(int x = -4; x <= 4; x++) {
					for(int z = -4; z <= 4; z++) {
						if(worldObj.getBlockId(x, y + 2, z) == Block.endPortal.blockID) {
							portalExists = true;
							break;
						}
					}
					if(portalExists) break;
				}
				if(!portalExists) {
					portalY = y + 1; // Use this Y level and lower it by 2
					break;
				}
			}
		}

		// Ensure we found a valid Y level
		if(portalY < 0) {
			throw new IllegalStateException("No available bedrock block for portal generation.");
		}

		BlockEndPortal.canBePlaced = true;
		int portalRadius = 4; // Radius of the portal structure
		for(int y = portalY - 1; y <= portalY + 32; y++) {
			for(int x = -portalRadius; x <= portalRadius; x++) { // Center at X=0
				for(int z = -portalRadius; z <= portalRadius; z++) { // Center at Z=0
					double d = x;
					double d1 = z;
					double d2 = MathHelper.sqrt_double(d * d + d1 * d1);

					if(d2 > (double) portalRadius - 0.5D) {
						continue;
					}
					if(y < portalY) {
						if(d2 <= (double) (portalRadius - 1) - 0.5D) {
							worldObj.setBlockWithNotify(x, y, z, Block.BEDROCK.blockID);
						}
						continue;
					}
					if(y > portalY) {
						worldObj.setBlockWithNotify(x, y, z, 0);
						continue;
					}
					if(d2 > (double) (portalRadius - 1) - 0.5D) {
						worldObj.setBlockWithNotify(x, y, z, Block.BEDROCK.blockID);
					}else {
						worldObj.setBlockWithNotify(x, y, z, Block.endPortal.blockID);
					}
				}
			}
		}

		// Place the central bedrock column and decorations
		worldObj.setBlock(0, portalY + 0, 0, Block.BEDROCK.blockID);
		worldObj.setBlock(0, portalY + 1, 0, Block.BEDROCK.blockID);
		worldObj.setBlock(0, portalY + 2, 0, Block.BEDROCK.blockID);
		worldObj.setBlock(-1, portalY + 2, 0, Block.torchWood.blockID);
		worldObj.setBlock(1, portalY + 2, 0, Block.torchWood.blockID);
		worldObj.setBlock(0, portalY + 2, -1, Block.torchWood.blockID);
		worldObj.setBlock(0, portalY + 2, 1, Block.torchWood.blockID);
		worldObj.setBlock(0, portalY + 3, 0, Block.BEDROCK.blockID);
		worldObj.setBlock(0, portalY + 4, 0, Block.ENDER_DRAGON_EGG.blockID); // Dragon egg block
		BlockEndPortal.canBePlaced = false;
	}

	public static void setUnlitEndPortal(World currentWorld, boolean isFirstGeneration) {
		int portalY;
		if(isFirstGeneration) {
			// First generation: Find the first solid block starting from the top of the world
			portalY = -1;
			for(int y = currentWorld.worldYMax - 1; y >= 0; y--) {
				int blockID = currentWorld.getBlockId(0, y, 0); // Check the block at the center (X=0, Z=0)
				if(blockID != 0) { // If it's a solid block
					portalY = y - 2; // Subtract 2 for the portal position
					break;
				}
			}
		}else {
			// Regeneration: Find the Y level of the existing portal
			portalY = -1;
			for(int y = 0; y < currentWorld.worldYMax; y++) {
				if(currentWorld.getBlockId(0, y, 0) == Block.BEDROCK.blockID) {
					boolean portalExists = false;
					for(int x = -4; x <= 4; x++) {
						for(int z = -4; z <= 4; z++) {
							if(currentWorld.getBlockId(x, y + 1, z) == Block.endPortal.blockID) {
								portalExists = true;
								break;
							}
						}
						if(portalExists) break;
					}
					if(portalExists) {
						portalY = y + 1; // Use this Y level for regeneration
						break;
					}
				}
			}
		}

		// Ensure we found a valid Y level
		if(portalY < 0) {
			throw new IllegalStateException("No valid position found for portal generation.");
		}

		int portalRadius = 4; // Radius of the portal structure
		for(int y = portalY - 1; y <= portalY + 32; y++) {
			for(int x = -portalRadius; x <= portalRadius; x++) { // Center at X=0
				for(int z = -portalRadius; z <= portalRadius; z++) { // Center at Z=0
					double d = x;
					double d1 = z;
					double d2 = MathHelper.sqrt_double(d * d + d1 * d1);
					if(d2 > (double) portalRadius - 0.5D) {
						continue;
					}
					if(y < portalY) {
						if(d2 <= (double) (portalRadius - 1) - 0.5D) {
							currentWorld.setBlockWithNotify(x, y, z, Block.BEDROCK.blockID);
						}
						continue;
					}
					if(y > portalY) {
						currentWorld.setBlockWithNotify(x, y, z, 0); // Clear old blocks
						continue;
					}
					if(d2 > (double) (portalRadius - 1) - 0.5D) {
						currentWorld.setBlockWithNotify(x, y, z, Block.BEDROCK.blockID);
					}else {
						currentWorld.setBlockWithNotify(x, y, z, 0);
					}
				}
			}
		}

		// Place the central bedrock column and decorations
		currentWorld.setBlock(0, portalY + 0, 0, Block.BEDROCK.blockID);
		currentWorld.setBlock(0, portalY + 1, 0, Block.BEDROCK.blockID);
		currentWorld.setBlock(0, portalY + 2, 0, Block.BEDROCK.blockID);
		currentWorld.setBlock(0, portalY + 3, 0, Block.BEDROCK.blockID);
		currentWorld.setBlock(-1, portalY + 2, 0, Block.torchWood.blockID);
		currentWorld.setBlock(1, portalY + 2, 0, Block.torchWood.blockID);
		currentWorld.setBlock(0, portalY + 2, -1, Block.torchWood.blockID);
		currentWorld.setBlock(0, portalY + 2, 1, Block.torchWood.blockID);
	}

	public static void handleDragonFight() {
		DragonFightManager.respawnCrystals.clear();
	}

	public static void respawnEnderdragon(World world) {
		enderDragonExists = true;
		isRespawning = true;
		EntityEnderDragon dragon = new EntityEnderDragon(world);
		world.entityJoinedWorld(dragon);
		dragon.setPosition(0, 128, 0);
		generatePillars(world, new Random(), false);
		setUnlitEndPortal(world, false);
		minecraft.sndManager.playSoundFX(EnumSound.ENDER_DRAGON_RESPAWN.getPath(), 1F, 1F);
	}

	public static void handleDragonDeath(World world) {
		setEndPortal(world);
		isDying = false;
		respawnTimer = 0;
		isRespawning = false;
		isOriginalEnderDragon = false;
		enderDragonExists = false;
		if(bossMusicPlayed) {
			minecraft.sndManager.sndSystem.stop(EnumSoundType.BgMusicKeepAlive.name());
			bossMusicPlayed = false;
		}
	}

	public static void update(Minecraft minecraftInstance) {
		if(minecraft == null) {
			minecraft = minecraftInstance;
		}

		if(!bossMusicPlayed) {
			minecraft.sndManager.playMusic(EnumSound.ENDER_DRAGON_BOSS_MUSIC, 1F, 1F, false);
			bossMusicPlayed = true;
		}
	}
}