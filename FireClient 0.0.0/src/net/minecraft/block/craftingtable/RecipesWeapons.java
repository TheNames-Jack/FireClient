// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.block.craftingtable;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

// Referenced classes of package net.minecraft.src:
//            Block, Item, ItemStack, CraftingManager

public class RecipesWeapons
{

    private String recipePatterns[][] = {
        {
            "X", "X", "#"
        }
    };
    private Object recipeItems[][];

    public RecipesWeapons()
    {
        recipeItems = (new Object[][] {
            new Object[] {
                Block.planks, Block.cobblestone, Item.IRON_INGOT, Item.DIAMOND, Item.GOLD_NUGGET
            }, new Object[] {
                Item.WOODEN_SWORD, Item.STONE_SWORD, Item.IRON_SWORD, Item.DIAMOND_SWORD, Item.swordGold
            }
        });
    }

    public void addRecipes(CraftingManager craftingmanager)
    {
        for(int i = 0; i < recipeItems[0].length; i++)
        {
            Object obj = recipeItems[0][i];
            for(int j = 0; j < recipeItems.length - 1; j++)
            {
                Item item = (Item)recipeItems[j + 1][i];
                craftingmanager.addRecipe(new ItemStack(item), new Object[] {
                    recipePatterns[j], Character.valueOf('#'), Item.STICK, Character.valueOf('X'), obj
                });
            }

        }

        craftingmanager.addRecipe(new ItemStack(Item.BOW, 1), new Object[] {
            " #X", "# X", " #X", Character.valueOf('X'), Item.SILK, Character.valueOf('#'), Item.STICK
        });
        craftingmanager.addRecipe(new ItemStack(Item.ARROW, 4), new Object[] {
            "X", "#", "Y", Character.valueOf('Y'), Item.FEATHER, Character.valueOf('X'), Item.FLINT, Character.valueOf('#'), Item.STICK
        });
    }
}
