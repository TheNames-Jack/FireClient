package net.minecraft.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEnderCrystalBaseless;
import net.minecraft.entity.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.EnumDimension;
import net.minecraft.world.World;
import net.minecraft.world.end.DragonFightManager;

public class ItemEnderCrystal extends Item {
	public ItemEnderCrystal(int i) {
		super(i);
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int l) {
		if((world.getBlockId(x, y, z) == Block.BEDROCK.blockID || world.getBlockId(x, y, z) == Block.OBSIDIAN.blockID) && world.getBlockId(x, y + 1, z) == 0) {
			if(!world.multiplayerWorld) {
				// Spawn the Ender Crystal entity
				EntityEnderCrystalBaseless enderCrystal = new EntityEnderCrystalBaseless(world, x + 0.5F, y + 1, z + 0.5F);
				world.entityJoinedWorld(enderCrystal);
				// Check if all four crystals are present at the correct positions
				if(world.worldProvider.worldType == EnumDimension.TheEnd.dimensionID()) {
					boolean crystalAtTop = isCrystalAtPosition(world, 0, y + 1, 3, enderCrystal);
					boolean crystalAtBottom = isCrystalAtPosition(world, 0, y + 1, -3, enderCrystal);
					boolean crystalAtRight = isCrystalAtPosition(world, 3, y + 1, 0, enderCrystal);
					boolean crystalAtLeft = isCrystalAtPosition(world, -3, y + 1, 0, enderCrystal);
//					System.out.println(crystalAtTop + ": top");
//					System.out.println(crystalAtBottom + ": back");
//					System.out.println(crystalAtRight + ": right");
//					System.out.println(crystalAtLeft + ": left");
//					If all crystals are in place, initiate dragon respawn
					if(crystalAtTop && crystalAtBottom && crystalAtRight && crystalAtLeft) {
						if(!DragonFightManager.enderDragonExists) {
							DragonFightManager.respawnCrystals.add(enderCrystal);
							initiateDragonRespawn(world);
						}
					}
				}
			}
			// Decrease the item stack size
			itemstack.stackSize--;
			return true;
		}
		return false;
	}

	// Helper method to check if a crystal entity exists at a specific position
	private boolean isCrystalAtPosition(World world, int x, int y, int z, EntityEnderCrystalBaseless crystal) {
		List<Entity> entities = world.getEntitiesWithinAABB(EntityEnderCrystalBaseless.class, AxisAlignedBB.getBoundingBox(x - 0.5, y - 0.5, z - 0.5, x + 0.5, y + 0.5, z + 0.5));
		if(!entities.isEmpty()) {
			DragonFightManager.respawnCrystals.add(crystal);
		}
		return !entities.isEmpty();
	}

	// Method to initiate dragon respawn
	private void initiateDragonRespawn(World world) {
		DragonFightManager.respawnEnderdragon(world);
	}
}