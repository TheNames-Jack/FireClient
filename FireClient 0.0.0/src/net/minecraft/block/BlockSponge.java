package net.minecraft.block;

import java.util.LinkedList;
import java.util.Queue;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockSponge extends Block {
    private static final int ABSORPTION_RADIUS = 6; // Depth of water absorption
    private static final int MAX_WATER_ABSORBED = 64; // Maximum water blocks absorbed

    protected BlockSponge(int id) {
        super(id, Material.sponge);
        blockIndexInTexture = 48; // Texture index
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        absorbWater(world, x, y, z);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborBlockId) {
        absorbWater(world, x, y, z);
        super.onNeighborBlockChange(world, x, y, z, neighborBlockId);
    }

    private void absorbWater(World world, int spongeX, int spongeY, int spongeZ) {
        Queue<BlockPosition> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[ABSORPTION_RADIUS * 2 + 1][ABSORPTION_RADIUS * 2 + 1][ABSORPTION_RADIUS * 2 + 1];
        int waterAbsorbed = 0;
        queue.add(new BlockPosition(spongeX, spongeY, spongeZ, 0));
        while (!queue.isEmpty() && waterAbsorbed < MAX_WATER_ABSORBED) {
            BlockPosition current = queue.poll();
            for (int offsetX = -1; offsetX <= 1; offsetX++) {
                for (int offsetY = -1; offsetY <= 1; offsetY++) {
                    for (int offsetZ = -1; offsetZ <= 1; offsetZ++) {
                        int neighborX = current.x + offsetX;
                        int neighborY = current.y + offsetY;
                        int neighborZ = current.z + offsetZ;
                        // Check bounds and visited state
                        if(Math.abs(neighborX - spongeX) <= ABSORPTION_RADIUS &&
                            Math.abs(neighborY - spongeY) <= ABSORPTION_RADIUS &&
                            Math.abs(neighborZ - spongeZ) <= ABSORPTION_RADIUS &&
                            !visited[neighborX - spongeX + ABSORPTION_RADIUS][neighborY - spongeY + ABSORPTION_RADIUS][neighborZ - spongeZ + ABSORPTION_RADIUS]) {
                            visited[neighborX - spongeX + ABSORPTION_RADIUS][neighborY - spongeY + ABSORPTION_RADIUS][neighborZ - spongeZ + ABSORPTION_RADIUS] = true;
                            // Check if block is water
                            if(world.getBlockMaterial(neighborX, neighborY, neighborZ) == Material.water) {
                                world.setBlockWithNotify(neighborX, neighborY, neighborZ, 0); //Remove water block
                                waterAbsorbed++;
                                queue.add(new BlockPosition(neighborX, neighborY, neighborZ, current.depth + 1));
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Utility class for tracking positions and depth during absorption.
     */
    private static class BlockPosition {
        final int x, y, z, depth;
        BlockPosition(int x, int y, int z, int depth) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.depth = depth;
        }
    }

    @Override
    public void onBlockRemoval(World world, int x, int y, int z) {
        // Notify neighbors when sponge is removed
        for (int dx = -ABSORPTION_RADIUS; dx <= ABSORPTION_RADIUS; dx++) {
            for (int dy = -ABSORPTION_RADIUS; dy <= ABSORPTION_RADIUS; dy++) {
                for (int dz = -ABSORPTION_RADIUS; dz <= ABSORPTION_RADIUS; dz++) {
                    world.notifyBlocksOfNeighborChange(x + dx, y + dy, z + dz, world.getBlockId(x + dx, y + dy, z + dz));
                }
            }
        }
    }
}