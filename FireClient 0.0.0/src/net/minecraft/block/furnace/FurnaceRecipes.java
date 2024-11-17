package net.minecraft.block.furnace;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FurnaceRecipes {
	private static final FurnaceRecipes smeltingBase = new FurnaceRecipes();
	private Map smeltingList;

	public static final FurnaceRecipes smelting() {
		return smeltingBase;
	}

	private FurnaceRecipes() {
		smeltingList = new HashMap();
		addSmelting(Block.oreIron.blockID, new ItemStack(Item.IRON_INGOT));
		addSmelting(Block.oreGold.blockID, new ItemStack(Item.GOLD_NUGGET));
		addSmelting(Block.oreDiamond.blockID, new ItemStack(Item.DIAMOND));
		addSmelting(Block.sand.blockID, new ItemStack(Block.glass));
		addSmelting(Item.RAW_PORK.id, new ItemStack(Item.COOKED_PORK));
		addSmelting(Item.RAW_BEEF.id, new ItemStack(Item.COOKED_BEEF));
		addSmelting(Item.RAW_CHICKEN.id, new ItemStack(Item.COOCKED_CHICKEN));
		addSmelting(Item.RAW_FISH.id, new ItemStack(Item.COOKED_FISH));
		addSmelting(Block.cobblestone.blockID, new ItemStack(Block.stone));
		addSmelting(Item.CLAY.id, new ItemStack(Item.BRICK));
		addSmelting(Block.cactus.blockID, new ItemStack(Item.INK_SACK, 1, 2));
		addSmelting(Block.wood.blockID, new ItemStack(Item.coal, 1, 1));
		addSmelting(Block.oreCoal.blockID, new ItemStack(Item.coal));
		addSmelting(Block.oreRedstone.blockID, new ItemStack(Item.REDSTONE));
		addSmelting(Block.oreLapis.blockID, new ItemStack(Item.INK_SACK, 1, 4));
		addSmelting(Item.ROTTEN_FLESH.id, new ItemStack(Item.LEATHER));
	}

	public void addSmelting(int i, ItemStack itemstack) {
		smeltingList.put(Integer.valueOf(i), itemstack);
	}

	public ItemStack getSmeltingResult(int i) {
		return (ItemStack) smeltingList.get(Integer.valueOf(i));
	}

	public Map getSmeltingList() {
		return smeltingList;
	}

}
