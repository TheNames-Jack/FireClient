// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.block.craftingtable;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

// Referenced classes of package net.minecraft.src:
//            Block, ItemStack, Item, CraftingManager

public class RecipesIngots
{

    private Object recipeItems[][];

    public RecipesIngots()
    {
        recipeItems = (new Object[][] {
            new Object[] {
                Block.blockGold, new ItemStack(Item.GOLD_NUGGET, 9)
            }, new Object[] {
                Block.blockSteel, new ItemStack(Item.IRON_INGOT, 9)
            }, new Object[] {
                Block.blockDiamond, new ItemStack(Item.DIAMOND, 9)
            }, new Object[] {
                Block.blockLapis, new ItemStack(Item.INK_SACK, 9, 4)
            }
        });
    }

    public void addRecipes(CraftingManager craftingmanager)
    {
        for(int i = 0; i < recipeItems.length; i++)
        {
            Block block = (Block)recipeItems[i][0];
            ItemStack itemstack = (ItemStack)recipeItems[i][1];
            craftingmanager.addRecipe(new ItemStack(block), new Object[] {
                "###", "###", "###", Character.valueOf('#'), itemstack
            });
            craftingmanager.addRecipe(itemstack, new Object[] {
                "#", Character.valueOf('#'), block
            });
        }

        craftingmanager.addRecipe(new ItemStack(Item.GOLD_NUGGET), new Object[] {
            "###", "###", "###", Character.valueOf('#'), Item.GOLD_INGOT
        });
        craftingmanager.addRecipe(new ItemStack(Item.GOLD_INGOT, 9), new Object[] {
            "#", Character.valueOf('#'), Item.GOLD_NUGGET
        });
    }
}
