package net.minecraft.block.craftingtable;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipesArmor {

	private String recipePatterns[][] = {
			{
					"XXX", "X X"
			}, {
					"X X", "XXX", "XXX"
			}, {
					"XXX", "X X", "X X"
			}, {
					"X X", "X X"
			}
	};
	private Object recipeItems[][];

	public RecipesArmor() {
		recipeItems = (new Object[][]{
				new Object[]{
						Item.LEATHER, Block.fire, Item.IRON_INGOT, Item.DIAMOND, Item.GOLD_NUGGET
				}, new Object[]{
						Item.LEATHER_HELMET, Item.CHAIN_HELMET, Item.IRON_HELMET, Item.DIAMOND_HELMET, Item.GOLD_HELMET
				}, new Object[]{
						Item.LEATHER_CHESTPLATE, Item.CHAIN_CHESTPLATE, Item.IRON_CHESTPLATE, Item.DIAMOND_CHESTPLATE, Item.GOLD_CHESTPLATE
				}, new Object[]{
						Item.LEATHER_LEGGINGS, Item.CHAIN_LEGGINGS, Item.IRON_LEGGINGS, Item.DIAMOND_LEGGINGS, Item.GOLD_LEGGINGS
				}, new Object[]{
						Item.LEATHER_BOOTS, Item.CHAIN_BOOTS, Item.IRON_BOOTS, Item.DIAMOND_BOOTS, Item.GOLD_BOOTS
				}
		});
	}

	public void addRecipes(CraftingManager craftingmanager) {
		for(int i = 0; i < recipeItems[0].length; i++) {
			Object obj = recipeItems[0][i];
			for(int j = 0; j < recipeItems.length - 1; j++) {
				Item item = (Item) recipeItems[j + 1][i];
				craftingmanager.addRecipe(new ItemStack(item), new Object[]{
						recipePatterns[j], Character.valueOf('X'), obj
				});
			}
		}
	}
}
