// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.block.craftingtable;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

// Referenced classes of package net.minecraft.src:
//            Block, Item, ItemStack, CraftingManager

public class RecipesTools
{

    private String recipePatterns[][] = {
        {
            "XXX", " # ", " # "
        }, {
            "X", "#", "#"
        }, {
            "XX", "X#", " #"
        }, {
            "XX", " #", " #"
        }
    };
    private Object recipeItems[][];

    public RecipesTools()
    {
        recipeItems = (new Object[][] {
            new Object[] {
                Block.planks, Block.cobblestone, Item.IRON_INGOT, Item.DIAMOND, Item.GOLD_NUGGET
            }, new Object[] {
                Item.WOODEN_PICKAXE, Item.STONE_PICKAXE, Item.IRON_PICKAXE, Item.DIAMOND_PICKAXE, Item.GOLD_PICKAXE
            }, new Object[] {
                Item.WOODEN_SHOVEL, Item.STONE_SHOVEL, Item.IRON_SHOVEL, Item.DIAMOND_SHOVEL, Item.shovelGold
            }, new Object[] {
                Item.WOODEN_AXE, Item.STONE_AXE, Item.IRON_AXE, Item.DIAMOND_AXE, Item.GOLD_AXE
            }, new Object[] {
                Item.WOODEN_HOE, Item.STONE_HOE, Item.IRON_HOE, Item.DIAMOND_HOE, Item.GOLD_HOE
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

        craftingmanager.addRecipe(new ItemStack(Item.SHEARS), new Object[] {
            " #", "# ", Character.valueOf('#'), Item.IRON_INGOT
        });
    }
}
