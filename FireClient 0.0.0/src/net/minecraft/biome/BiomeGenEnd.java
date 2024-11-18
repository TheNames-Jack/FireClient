package net.minecraft.biome;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityEnderman;
import net.minecraft.util.SpawnListEntry;

public class BiomeGenEnd extends BiomeGenBase {
	public BiomeGenEnd(int i) {
		super(i);
		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableMonsterList.add(new SpawnListEntry(net.minecraft.entity.EntityEnderman.class, 10, 4, 4));
		topBlock = (byte) Block.dirt.blockID;
		fillerBlock = (byte) Block.dirt.blockID;
		biomeDecorator = new BiomeEndDecorator(this);
	}

	public int getSkyColorByTemp(float f) {
		return 0;
	}
}
