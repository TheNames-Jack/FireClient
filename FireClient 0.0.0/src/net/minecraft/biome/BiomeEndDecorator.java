package net.minecraft.biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.enderdragon.EntityEnderdragon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldGenSpikes;
import net.minecraft.world.WorldGenerator;

public class BiomeEndDecorator extends BiomeDecorator {
	protected WorldGenerator spikeGen;
	
	public BiomeEndDecorator(BiomeGenBase biomegenbase) {
		super(biomegenbase);
		spikeGen = new WorldGenSpikes(Block.whiteStone.blockID);
	}

	protected void decorate_do() {
		generateOres();
		if(decoRNG.nextInt(5) == 0) {
			int i = chunk_X + decoRNG.nextInt(16) + 8;
			int j = chunk_Z + decoRNG.nextInt(16) + 8;
			int k = currentWorld.getTopSolidOrLiquidBlock(i, j);
			if(k <= 0);
			spikeGen.generate(currentWorld, decoRNG, i, k, j);
		}
		if(chunk_X == 0 && chunk_Z == 0) {
			EntityEnderdragon entitydragon = new EntityEnderdragon(currentWorld);
			entitydragon.setLocationAndAngles(0.0D, 128D, 0.0D, decoRNG.nextFloat() * 360F, 0.0F);
			currentWorld.entityJoinedWorld(entitydragon);
			
			int portalY = (currentWorld.worldYMax / 2) - 2; // Lower the portal by 2 blocks
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
								currentWorld.setBlockWithNotify(x, y, z, Block.bedrock.blockID);
							}
							continue;
						}
						if(y > portalY) {
							currentWorld.setBlockWithNotify(x, y, z, 0);
							continue;
						}
						if(d2 > (double) (portalRadius - 1) - 0.5D) {
							currentWorld.setBlockWithNotify(x, y, z, Block.bedrock.blockID);
						}else {
							currentWorld.setBlockWithNotify(x, y, z, 0);
						}
					}
				}
			}

			// Place the central bedrock column and decorations
			currentWorld.setBlockWithNotify(0, portalY + 0, 0, Block.bedrock.blockID);
			currentWorld.setBlockWithNotify(0, portalY + 1, 0, Block.bedrock.blockID);
			currentWorld.setBlockWithNotify(0, portalY + 2, 0, Block.bedrock.blockID);
			currentWorld.setBlockWithNotify(0, portalY + 3, 0, Block.bedrock.blockID);
		}
	}
}
