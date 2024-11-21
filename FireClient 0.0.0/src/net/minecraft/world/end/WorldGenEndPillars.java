package net.minecraft.world.end;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.WorldGenerator;

public class WorldGenEndPillars extends WorldGenerator {
	public WorldGenEndPillars() {
	}

	public boolean generate(World world, Random random, int worldX, int worldY, int worldZ) {
		if(DragonFightManager.pillarsGenerated && !world.isNewWorld) {
			return false;
		}
		DragonFightManager.generatePillars(world, random, true);
		DragonFightManager.pillarsGenerated = true;
		return true;
	}
}