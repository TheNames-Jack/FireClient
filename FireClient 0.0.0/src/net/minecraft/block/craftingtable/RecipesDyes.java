// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.block.craftingtable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCloth;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

// Referenced classes of package net.minecraft.src:
//            ItemStack, Block, BlockCloth, Item, 
//            CraftingManager

public class RecipesDyes {

	public RecipesDyes() {
	}

	public void addRecipes(CraftingManager craftingmanager) {
		for(int i = 0; i < 16; i++) {
			craftingmanager.addShapelessRecipe(new ItemStack(Block.cloth, 1, BlockCloth.getDyeFromBlock(i)), new Object[]{
					new ItemStack(Item.INK_SACK, 1, i), new ItemStack(Item.itemsList[Block.cloth.blockID], 1, 0)
			});
		}

		craftingmanager.addShapelessRecipe(new ItemStack(Item.INK_SACK, 2, 11), new Object[]{
				Block.plantYellow
		});
		craftingmanager.addShapelessRecipe(new ItemStack(Item.INK_SACK, 2, 1), new Object[]{
				Block.plantRed
		});
		craftingmanager.addShapelessRecipe(new ItemStack(Item.INK_SACK, 3, 15), new Object[]{
				Item.BONE
		});
		craftingmanager.addShapelessRecipe(new ItemStack(Item.INK_SACK, 2, 9), new Object[]{
				new ItemStack(Item.INK_SACK, 1, 1), new ItemStack(Item.INK_SACK, 1, 15)
		});
		craftingmanager.addShapelessRecipe(new ItemStack(Item.INK_SACK, 2, 14), new Object[]{
				new ItemStack(Item.INK_SACK, 1, 1), new ItemStack(Item.INK_SACK, 1, 11)
		});
		craftingmanager.addShapelessRecipe(new ItemStack(Item.INK_SACK, 2, 10), new Object[]{
				new ItemStack(Item.INK_SACK, 1, 2), new ItemStack(Item.INK_SACK, 1, 15)
		});
		craftingmanager.addShapelessRecipe(new ItemStack(Item.INK_SACK, 2, 8), new Object[]{
				new ItemStack(Item.INK_SACK, 1, 0), new ItemStack(Item.INK_SACK, 1, 15)
		});
		craftingmanager.addShapelessRecipe(new ItemStack(Item.INK_SACK, 2, 7), new Object[]{
				new ItemStack(Item.INK_SACK, 1, 8), new ItemStack(Item.INK_SACK, 1, 15)
		});
		craftingmanager.addShapelessRecipe(new ItemStack(Item.INK_SACK, 3, 7), new Object[]{
				new ItemStack(Item.INK_SACK, 1, 0), new ItemStack(Item.INK_SACK, 1, 15), new ItemStack(Item.INK_SACK, 1, 15)
		});
		craftingmanager.addShapelessRecipe(new ItemStack(Item.INK_SACK, 2, 12), new Object[]{
				new ItemStack(Item.INK_SACK, 1, 4), new ItemStack(Item.INK_SACK, 1, 15)
		});
		craftingmanager.addShapelessRecipe(new ItemStack(Item.INK_SACK, 2, 6), new Object[]{
				new ItemStack(Item.INK_SACK, 1, 4), new ItemStack(Item.INK_SACK, 1, 2)
		});
		craftingmanager.addShapelessRecipe(new ItemStack(Item.INK_SACK, 2, 5), new Object[]{
				new ItemStack(Item.INK_SACK, 1, 4), new ItemStack(Item.INK_SACK, 1, 1)
		});
		craftingmanager.addShapelessRecipe(new ItemStack(Item.INK_SACK, 2, 13), new Object[]{
				new ItemStack(Item.INK_SACK, 1, 5), new ItemStack(Item.INK_SACK, 1, 9)
		});
		craftingmanager.addShapelessRecipe(new ItemStack(Item.INK_SACK, 3, 13), new Object[]{
				new ItemStack(Item.INK_SACK, 1, 4), new ItemStack(Item.INK_SACK, 1, 1), new ItemStack(Item.INK_SACK, 1, 9)
		});
		craftingmanager.addShapelessRecipe(new ItemStack(Item.INK_SACK, 4, 13), new Object[]{
				new ItemStack(Item.INK_SACK, 1, 4), new ItemStack(Item.INK_SACK, 1, 1), new ItemStack(Item.INK_SACK, 1, 1), new ItemStack(Item.INK_SACK, 1, 15)
		});
	}
}
