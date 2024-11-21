package net.minecraft.biome;

import net.minecraft.entity.enderdragon.EntityEnderDragon;
import net.minecraft.world.WorldGenerator;
import net.minecraft.world.end.DragonFightManager;
import net.minecraft.world.end.WorldGenEndPillars;

public class BiomeEndDecorator extends BiomeDecorator {
	protected WorldGenerator obsidianPillarGen;
	
	public BiomeEndDecorator(BiomeGenBase biomegenbase) {
		super(biomegenbase);
		obsidianPillarGen = new WorldGenEndPillars();
	}

	protected void doDecorations() {
		generateOres();
		if(decoRNG.nextInt(5) == 0) {
			int i = chunk_X + decoRNG.nextInt(16) + 8;
			int j = chunk_Z + decoRNG.nextInt(16) + 8;
			int k = currentWorld.getTopSolidOrLiquidBlock(i, j);
			if(k <= 0);
			obsidianPillarGen.generate(currentWorld, decoRNG, i, k, j);
		}
		if(chunk_X == 0 && chunk_Z == 0) {
			EntityEnderDragon entitydragon = new EntityEnderDragon(currentWorld);
			entitydragon.setLocationAndAngles(0.0D, 128D, 0.0D, decoRNG.nextFloat() * 360F, 0.0F);
			currentWorld.entityJoinedWorld(entitydragon);
			DragonFightManager.setUnlitEndPortal(currentWorld, true);
		}
	}
}
