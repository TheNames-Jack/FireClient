package net.minecraft.item.map;

import java.util.*;

import net.minecraft.util.SpawnListEntry;
import net.minecraft.world.structure.StructureNetherBridgeStart;
import net.minecraft.world.structure.StructureStart;

public class MapGenNetherBridge extends MapGenStructure {
	private List field_40486_a;

	public MapGenNetherBridge() {
		field_40486_a = new ArrayList();
		field_40486_a.add(new SpawnListEntry(net.minecraft.entity.EntityBlaze.class, 10, 2, 3));
		field_40486_a.add(new SpawnListEntry(net.minecraft.entity.EntityPigZombie.class, 10, 4, 4));
		field_40486_a.add(new SpawnListEntry(net.minecraft.entity.EntityMagmaCube.class, 3, 4, 4));
	}

	public List func_40485_b() {
		return field_40486_a;
	}

	protected boolean canSpawnStructureAtCoords(int i, int j) {
		int k = i >> 4;
		int l = j >> 4;
		rand.setSeed((long) (k ^ l << 4) ^ worldObj.getWorldSeed());
		rand.nextInt();
		if(rand.nextInt(3) != 0) {
			return false;
		}
		if(i != (k << 4) + 4 + rand.nextInt(8)) {
			return false;
		}
		return j == (l << 4) + 4 + rand.nextInt(8);
	}

	protected StructureStart getStructureStart(int i, int j) {
		return new StructureNetherBridgeStart(worldObj, rand, i, j);
	}
}
