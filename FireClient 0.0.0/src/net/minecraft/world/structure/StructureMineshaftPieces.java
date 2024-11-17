// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.world.structure;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

// Referenced classes of package net.minecraft.src:
//            ComponentMineshaftCross, ComponentMineshaftStairs, ComponentMineshaftCorridor, StructureComponent, 
//            StructureBoundingBox, StructurePieceTreasure, Item, Block

public class StructureMineshaftPieces {

	private static final StructurePieceTreasure lootArray[];

	public StructureMineshaftPieces() {
	}

	private static StructureComponent getRandomComponent(List list, Random random, int i, int j, int k, int l, int i1) {
		int j1 = random.nextInt(100);
		if(j1 >= 80) {
			StructureBoundingBox structureboundingbox = ComponentMineshaftCross.func_35071_a(list, random, i, j, k, l);
			if(structureboundingbox != null) {
				return new ComponentMineshaftCross(i1, random, structureboundingbox, l);
			}
		}else if(j1 >= 70) {
			StructureBoundingBox structureboundingbox1 = ComponentMineshaftStairs.func_35027_a(list, random, i, j, k, l);
			if(structureboundingbox1 != null) {
				return new ComponentMineshaftStairs(i1, random, structureboundingbox1, l);
			}
		}else {
			StructureBoundingBox structureboundingbox2 = ComponentMineshaftCorridor.func_35066_a(list, random, i, j, k, l);
			if(structureboundingbox2 != null) {
				return new ComponentMineshaftCorridor(i1, random, structureboundingbox2, l);
			}
		}
		return null;
	}

	private static StructureComponent getNextMineShaftComponent(StructureComponent structurecomponent, List list, Random random, int i, int j, int k, int l, int i1) {
		if(i1 > 8) {
			return null;
		}
		if(Math.abs(i - structurecomponent.getBoundingBox().minX) > 80 || Math.abs(k - structurecomponent.getBoundingBox().minZ) > 80) {
			return null;
		}
		StructureComponent structurecomponent1 = getRandomComponent(list, random, i, j, k, l, i1 + 1);
		if(structurecomponent1 != null) {
			list.add(structurecomponent1);
			structurecomponent1.buildComponent(structurecomponent, list, random);
		}
		return structurecomponent1;
	}

	public static StructureComponent getNextComponent(StructureComponent structurecomponent, List list, Random random, int i, int j, int k, int l, int i1) {
		return getNextMineShaftComponent(structurecomponent, list, random, i, j, k, l, i1);
	}

	public static StructurePieceTreasure[] getTreasurePieces() {
		return lootArray;
	}

	static {
		lootArray = (new StructurePieceTreasure[]{
				new StructurePieceTreasure(Item.IRON_INGOT.id, 0, 1, 5, 10), new StructurePieceTreasure(Item.GOLD_NUGGET.id, 0, 1, 3, 5), new StructurePieceTreasure(Item.REDSTONE.id, 0, 4, 9, 5), new StructurePieceTreasure(Item.INK_SACK.id, 4, 4, 9, 5),
				new StructurePieceTreasure(Item.DIAMOND.id, 0, 1, 2, 3), new StructurePieceTreasure(Item.coal.id, 0, 3, 8, 10), new StructurePieceTreasure(Item.BREAD.id, 0, 1, 3, 15), new StructurePieceTreasure(Item.IRON_PICKAXE.id, 0, 1, 1, 1),
				new StructurePieceTreasure(Block.rail.blockID, 0, 4, 8, 1), new StructurePieceTreasure(Item.MELON_SEEDS.id, 0, 2, 4, 10), new StructurePieceTreasure(Item.PUMPKIN_SEEDS.id, 0, 2, 4, 10)
		});
	}
}
