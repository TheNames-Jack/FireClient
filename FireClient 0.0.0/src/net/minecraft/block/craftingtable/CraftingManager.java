package net.minecraft.block.craftingtable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CraftingManager {
	private static final CraftingManager instance = new CraftingManager();
	private List recipes;

	public static final CraftingManager getInstance() {
		return instance;
	}

	private CraftingManager() {
		recipes = new ArrayList();
		(new RecipesTools()).addRecipes(this);
		(new RecipesWeapons()).addRecipes(this);
		(new RecipesIngots()).addRecipes(this);
		(new RecipesFood()).addRecipes(this);
		(new RecipesCrafting()).addRecipes(this);
		(new RecipesArmor()).addRecipes(this);
		(new RecipesDyes()).addRecipes(this);
		addRecipe(new ItemStack(Item.PAPER, 3), new Object[]{
				"###", Character.valueOf('#'), Item.SUGARCANE
		});
		addRecipe(new ItemStack(Item.BOOK, 1), new Object[]{
				"#", "#", "#", Character.valueOf('#'), Item.PAPER
		});
		addRecipe(new ItemStack(Block.fence, 2), new Object[]{
				"###", "###", Character.valueOf('#'), Item.STICK
		});
		addRecipe(new ItemStack(Block.netherFence, 6), new Object[]{
				"###", "###", Character.valueOf('#'), Block.netherBrick
		});
		addRecipe(new ItemStack(Block.fenceGate, 1), new Object[]{
				"#W#", "#W#", Character.valueOf('#'), Item.STICK, Character.valueOf('W'), Block.planks
		});
		addRecipe(new ItemStack(Block.jukebox, 1), new Object[]{
				"###", "#X#", "###", Character.valueOf('#'), Block.planks, Character.valueOf('X'), Item.DIAMOND
		});
		addRecipe(new ItemStack(Block.music, 1), new Object[]{
				"###", "#X#", "###", Character.valueOf('#'), Block.planks, Character.valueOf('X'), Item.REDSTONE
		});
		addRecipe(new ItemStack(Block.bookShelf, 1), new Object[]{
				"###", "XXX", "###", Character.valueOf('#'), Block.planks, Character.valueOf('X'), Item.BOOK
		});
		addRecipe(new ItemStack(Block.blockSnow, 1), new Object[]{
				"##", "##", Character.valueOf('#'), Item.SNOWBALL
		});
		addRecipe(new ItemStack(Block.blockClay, 1), new Object[]{
				"##", "##", Character.valueOf('#'), Item.CLAY
		});
		addRecipe(new ItemStack(Block.brick, 1), new Object[]{
				"##", "##", Character.valueOf('#'), Item.BRICK
		});
		addRecipe(new ItemStack(Block.glowStone, 1), new Object[]{
				"##", "##", Character.valueOf('#'), Item.GLOWSTONE_DUST
		});
		addRecipe(new ItemStack(Block.cloth, 1), new Object[]{
				"##", "##", Character.valueOf('#'), Item.SILK
		});
		addRecipe(new ItemStack(Block.tnt, 1), new Object[]{
				"X#X", "#X#", "X#X", Character.valueOf('X'), Item.GUNPOWDER, Character.valueOf('#'), Block.sand
		});
		addRecipe(new ItemStack(Block.stairSingle, 3, 3), new Object[]{
				"###", Character.valueOf('#'), Block.cobblestone
		});
		addRecipe(new ItemStack(Block.stairSingle, 3, 0), new Object[]{
				"###", Character.valueOf('#'), Block.stone
		});
		addRecipe(new ItemStack(Block.stairSingle, 3, 1), new Object[]{
				"###", Character.valueOf('#'), Block.sandStone
		});
		addRecipe(new ItemStack(Block.stairSingle, 3, 2), new Object[]{
				"###", Character.valueOf('#'), Block.planks
		});
		addRecipe(new ItemStack(Block.stairSingle, 3, 4), new Object[]{
				"###", Character.valueOf('#'), Block.brick
		});
		addRecipe(new ItemStack(Block.stairSingle, 3, 5), new Object[]{
				"###", Character.valueOf('#'), Block.stoneBrick
		});
		addRecipe(new ItemStack(Block.ladder, 2), new Object[]{
				"# #", "###", "# #", Character.valueOf('#'), Item.STICK
		});
		addRecipe(new ItemStack(Item.WOODEN_DOOR, 1), new Object[]{
				"##", "##", "##", Character.valueOf('#'), Block.planks
		});
		addRecipe(new ItemStack(Block.trapdoor, 2), new Object[]{
				"###", "###", Character.valueOf('#'), Block.planks
		});
		addRecipe(new ItemStack(Item.IRON_DOOR, 1), new Object[]{
				"##", "##", "##", Character.valueOf('#'), Item.IRON_INGOT
		});
		addRecipe(new ItemStack(Item.SIGN, 1), new Object[]{
				"###", "###", " X ", Character.valueOf('#'), Block.planks, Character.valueOf('X'), Item.STICK
		});
		addRecipe(new ItemStack(Item.CAKE, 1), new Object[]{
				"AAA", "BEB", "CCC", Character.valueOf('A'), Item.MILK_BUCKET, Character.valueOf('B'), Item.SUGAR, Character.valueOf('C'), Item.WHEAT, Character.valueOf('E'), Item.EGG
		});
		addRecipe(new ItemStack(Item.SUGAR, 1), new Object[]{
				"#", Character.valueOf('#'), Item.SUGARCANE
		});
		addRecipe(new ItemStack(Block.planks, 4), new Object[]{
				"#", Character.valueOf('#'), Block.wood
		});
		addRecipe(new ItemStack(Item.STICK, 4), new Object[]{
				"#", "#", Character.valueOf('#'), Block.planks
		});
		addRecipe(new ItemStack(Block.torchWood, 4), new Object[]{
				"X", "#", Character.valueOf('X'), Item.coal, Character.valueOf('#'), Item.STICK
		});
		addRecipe(new ItemStack(Block.torchWood, 4), new Object[]{
				"X", "#", Character.valueOf('X'), new ItemStack(Item.coal, 1, 1), Character.valueOf('#'), Item.STICK
		});
		addRecipe(new ItemStack(Item.EMPTY_BOWl, 4), new Object[]{
				"# #", " # ", Character.valueOf('#'), Block.planks
		});
		addRecipe(new ItemStack(Item.GLASS_BOTTLE, 3), new Object[]{
				"# #", " # ", Character.valueOf('#'), Block.glass
		});
		addRecipe(new ItemStack(Block.rail, 16), new Object[]{
				"X X", "X#X", "X X", Character.valueOf('X'), Item.IRON_INGOT, Character.valueOf('#'), Item.STICK
		});
		addRecipe(new ItemStack(Block.railPowered, 6), new Object[]{
				"X X", "X#X", "XRX", Character.valueOf('X'), Item.GOLD_NUGGET, Character.valueOf('R'), Item.REDSTONE, Character.valueOf('#'), Item.STICK
		});
		addRecipe(new ItemStack(Block.railDetector, 6), new Object[]{
				"X X", "X#X", "XRX", Character.valueOf('X'), Item.IRON_INGOT, Character.valueOf('R'), Item.REDSTONE, Character.valueOf('#'), Block.pressurePlateStone
		});
		addRecipe(new ItemStack(Item.EMPTY_MINECART, 1), new Object[]{
				"# #", "###", Character.valueOf('#'), Item.IRON_INGOT
		});
		addRecipe(new ItemStack(Item.CAULDRON, 1), new Object[]{
				"# #", "# #", "###", Character.valueOf('#'), Item.IRON_INGOT
		});
		addRecipe(new ItemStack(Item.BREWING_STAND, 1), new Object[]{
				" B ", "###", Character.valueOf('#'), Block.cobblestone, Character.valueOf('B'), Item.BLAZE_ROD
		});
		addRecipe(new ItemStack(Block.pumpkinLantern, 1), new Object[]{
				"A", "B", Character.valueOf('A'), Block.pumpkin, Character.valueOf('B'), Block.torchWood
		});
		addRecipe(new ItemStack(Item.CHEST_MINECART, 1), new Object[]{
				"A", "B", Character.valueOf('A'), Block.chest, Character.valueOf('B'), Item.EMPTY_MINECART
		});
		addRecipe(new ItemStack(Item.POWERED_MINECART, 1), new Object[]{
				"A", "B", Character.valueOf('A'), Block.stoneOvenIdle, Character.valueOf('B'), Item.EMPTY_MINECART
		});
		addRecipe(new ItemStack(Item.BOAT, 1), new Object[]{
				"# #", "###", Character.valueOf('#'), Block.planks
		});
		addRecipe(new ItemStack(Item.EMPTY_BUCKET, 1), new Object[]{
				"# #", " # ", Character.valueOf('#'), Item.IRON_INGOT
		});
		addRecipe(new ItemStack(Item.FLINT_AND_STEEL, 1), new Object[]{
				"A ", " B", Character.valueOf('A'), Item.IRON_INGOT, Character.valueOf('B'), Item.FLINT
		});
		addRecipe(new ItemStack(Item.BREAD, 1), new Object[]{
				"###", Character.valueOf('#'), Item.WHEAT
		});
		addRecipe(new ItemStack(Block.stairCompactPlanks, 4), new Object[]{
				"#  ", "## ", "###", Character.valueOf('#'), Block.planks
		});
		addRecipe(new ItemStack(Item.FISHING_ROD, 1), new Object[]{
				"  #", " #X", "# X", Character.valueOf('#'), Item.STICK, Character.valueOf('X'), Item.SILK
		});
		addRecipe(new ItemStack(Block.stairCompactCobblestone, 4), new Object[]{
				"#  ", "## ", "###", Character.valueOf('#'), Block.cobblestone
		});
		addRecipe(new ItemStack(Block.stairsBrick, 4), new Object[]{
				"#  ", "## ", "###", Character.valueOf('#'), Block.brick
		});
		addRecipe(new ItemStack(Block.stairsStoneBrickSmooth, 4), new Object[]{
				"#  ", "## ", "###", Character.valueOf('#'), Block.stoneBrick
		});
		addRecipe(new ItemStack(Block.stairsNetherBrick, 4), new Object[]{
				"#  ", "## ", "###", Character.valueOf('#'), Block.netherBrick
		});
		addRecipe(new ItemStack(Item.PAINTING, 1), new Object[]{
				"###", "#X#", "###", Character.valueOf('#'), Item.STICK, Character.valueOf('X'), Block.cloth
		});
		addRecipe(new ItemStack(Item.GOLD_APPLE, 1), new Object[]{
				"###", "#X#", "###", Character.valueOf('#'), Block.blockGold, Character.valueOf('X'), Item.APPLE
		});
		addRecipe(new ItemStack(Block.lever, 1), new Object[]{
				"X", "#", Character.valueOf('#'), Block.cobblestone, Character.valueOf('X'), Item.STICK
		});
		addRecipe(new ItemStack(Block.torchRedstoneActive, 1), new Object[]{
				"X", "#", Character.valueOf('#'), Item.STICK, Character.valueOf('X'), Item.REDSTONE
		});
		addRecipe(new ItemStack(Item.REDSTONE_REPEATER, 1), new Object[]{
				"#X#", "III", Character.valueOf('#'), Block.torchRedstoneActive, Character.valueOf('X'), Item.REDSTONE, Character.valueOf('I'), Block.stone
		});
		addRecipe(new ItemStack(Item.CLOCK, 1), new Object[]{
				" # ", "#X#", " # ", Character.valueOf('#'), Item.GOLD_NUGGET, Character.valueOf('X'), Item.REDSTONE
		});
		addRecipe(new ItemStack(Item.COMPASS, 1), new Object[]{
				" # ", "#X#", " # ", Character.valueOf('#'), Item.IRON_INGOT, Character.valueOf('X'), Item.REDSTONE
		});
		addRecipe(new ItemStack(Item.MAP, 1), new Object[]{
				"###", "#X#", "###", Character.valueOf('#'), Item.PAPER, Character.valueOf('X'), Item.COMPASS
		});
		addRecipe(new ItemStack(Block.button, 1), new Object[]{
				"#", "#", Character.valueOf('#'), Block.stone
		});
		addRecipe(new ItemStack(Block.pressurePlateStone, 1), new Object[]{
				"##", Character.valueOf('#'), Block.stone
		});
		addRecipe(new ItemStack(Block.pressurePlatePlanks, 1), new Object[]{
				"##", Character.valueOf('#'), Block.planks
		});
		addRecipe(new ItemStack(Block.dispenser, 1), new Object[]{
				"###", "#X#", "#R#", Character.valueOf('#'), Block.cobblestone, Character.valueOf('X'), Item.BOW, Character.valueOf('R'), Item.REDSTONE
		});
		addRecipe(new ItemStack(Block.pistonBase, 1), new Object[]{
				"TTT", "#X#", "#R#", Character.valueOf('#'), Block.cobblestone, Character.valueOf('X'), Item.IRON_INGOT, Character.valueOf('R'), Item.REDSTONE, Character.valueOf('T'), Block.planks
		});
		addRecipe(new ItemStack(Block.pistonStickyBase, 1), new Object[]{
				"S", "P", Character.valueOf('S'), Item.SLIMEBALL, Character.valueOf('P'), Block.pistonBase
		});
		addRecipe(new ItemStack(Item.BED, 1), new Object[]{
				"###", "XXX", Character.valueOf('#'), Block.cloth, Character.valueOf('X'), Block.planks
		});
		addRecipe(new ItemStack(Block.enchantmentTable, 1), new Object[]{
				" B ", "D#D", "###", Character.valueOf('#'), Block.obsidian, Character.valueOf('B'), Item.BOOK, Character.valueOf('D'), Item.DIAMOND
		});
		addShapelessRecipe(new ItemStack(Item.ENDER_EYE, 1), new Object[]{
				Item.ENDER_PEARL, Item.BLAZE_POWDER
		});
		//Sponge Crafting Recipe
		addRecipe(new ItemStack(Block.sponge, 1), new Object[]{
				"###", "###", "###", Character.valueOf('#'), Block.web
		});
		//Cobweb Crafting Recipe
		addRecipe(new ItemStack(Block.web, 1), new Object[]{
				"###", "###", "###", Character.valueOf('#'), Item.SILK
		});
		Collections.sort(recipes, new RecipeSorter(this));
		System.out.println((new StringBuilder()).append(recipes.size()).append(" recipes").toString());
	}

	public void addRecipe(ItemStack itemstack, Object aobj[]) {
		String s = "";
		int i = 0;
		int j = 0;
		int k = 0;
		if(aobj[i] instanceof String[]) {
			String as[] = (String[]) aobj[i++];
			for(int l = 0; l < as.length; l++) {
				String s2 = as[l];
				k++;
				j = s2.length();
				s = (new StringBuilder()).append(s).append(s2).toString();
			}

		}else {
			while(aobj[i] instanceof String) {
				String s1 = (String) aobj[i++];
				k++;
				j = s1.length();
				s = (new StringBuilder()).append(s).append(s1).toString();
			}
		}
		HashMap hashmap = new HashMap();
		for(; i < aobj.length; i += 2) {
			Character character = (Character) aobj[i];
			ItemStack itemstack1 = null;
			if(aobj[i + 1] instanceof Item) {
				itemstack1 = new ItemStack((Item) aobj[i + 1]);
			}else if(aobj[i + 1] instanceof Block) {
				itemstack1 = new ItemStack((Block) aobj[i + 1], 1, -1);
			}else if(aobj[i + 1] instanceof ItemStack) {
				itemstack1 = (ItemStack) aobj[i + 1];
			}
			hashmap.put(character, itemstack1);
		}

		ItemStack aitemstack[] = new ItemStack[j * k];
		for(int i1 = 0; i1 < j * k; i1++) {
			char c = s.charAt(i1);
			if(hashmap.containsKey(Character.valueOf(c))) {
				aitemstack[i1] = ((ItemStack) hashmap.get(Character.valueOf(c))).copy();
			}else {
				aitemstack[i1] = null;
			}
		}

		recipes.add(new ShapedRecipes(j, k, aitemstack, itemstack));
	}

	public void addShapelessRecipe(ItemStack itemstack, Object aobj[]) {
		ArrayList arraylist = new ArrayList();
		Object aobj1[] = aobj;
		int i = aobj1.length;
		for(int j = 0; j < i; j++) {
			Object obj = aobj1[j];
			if(obj instanceof ItemStack) {
				arraylist.add(((ItemStack) obj).copy());
				continue;
			}
			if(obj instanceof Item) {
				arraylist.add(new ItemStack((Item) obj));
				continue;
			}
			if(obj instanceof Block) {
				arraylist.add(new ItemStack((Block) obj));
			}else {
				throw new RuntimeException("Invalid shapeless recipe!");
			}
		}

		recipes.add(new ShapelessRecipes(itemstack, arraylist));
	}

	public ItemStack findMatchingRecipe(InventoryCrafting inventorycrafting) {
		int i = 0;
		ItemStack itemstack = null;
		ItemStack itemstack1 = null;
		for(int j = 0; j < inventorycrafting.getSizeInventory(); j++) {
			ItemStack itemstack2 = inventorycrafting.getStackInSlot(j);
			if(itemstack2 == null) {
				continue;
			}
			if(i == 0) {
				itemstack = itemstack2;
			}
			if(i == 1) {
				itemstack1 = itemstack2;
			}
			i++;
		}

		if(i == 2 && itemstack.itemID == itemstack1.itemID && itemstack.stackSize == 1 && itemstack1.stackSize == 1 && Item.itemsList[itemstack.itemID].isDamageable()) {
			Item item = Item.itemsList[itemstack.itemID];
			int l = item.getMaxDamage() - itemstack.getItemDamageForDisplay();
			int i1 = item.getMaxDamage() - itemstack1.getItemDamageForDisplay();
			int j1 = l + i1 + (item.getMaxDamage() * 10) / 100;
			int k1 = item.getMaxDamage() - j1;
			if(k1 < 0) {
				k1 = 0;
			}
			return new ItemStack(itemstack.itemID, 1, k1);
		}
		for(int k = 0; k < recipes.size(); k++) {
			IRecipe irecipe = (IRecipe) recipes.get(k);
			if(irecipe.matches(inventorycrafting)) {
				return irecipe.getCraftingResult(inventorycrafting);
			}
		}

		return null;
	}

	public List getRecipeList() {
		return recipes;
	}

}
