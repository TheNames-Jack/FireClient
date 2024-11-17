// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.block.craftingtable;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

// Referenced classes of package net.minecraft.src:
//            ItemStack, Item, Block, CraftingManager

public class RecipesFood
{

    public RecipesFood()
    {
    }

    public void addRecipes(CraftingManager craftingmanager)
    {
        craftingmanager.addShapelessRecipe(new ItemStack(Item.SOUP_BOWL), new Object[] {
            Block.mushroomBrown, Block.mushroomRed, Item.EMPTY_BOWl
        });
        craftingmanager.addRecipe(new ItemStack(Item.COOKIE, 8), new Object[] {
            "#X#", Character.valueOf('X'), new ItemStack(Item.INK_SACK, 1, 3), Character.valueOf('#'), Item.WHEAT
        });
        craftingmanager.addRecipe(new ItemStack(Block.melon), new Object[] {
            "MMM", "MMM", "MMM", Character.valueOf('M'), Item.MELON
        });
        craftingmanager.addRecipe(new ItemStack(Item.MELON_SEEDS), new Object[] {
            "M", Character.valueOf('M'), Item.MELON
        });
        craftingmanager.addRecipe(new ItemStack(Item.PUMPKIN_SEEDS, 4), new Object[] {
            "M", Character.valueOf('M'), Block.pumpkin
        });
        craftingmanager.addShapelessRecipe(new ItemStack(Item.FERMENTED_SPIDER_EYE), new Object[] {
            Item.SPIDER_EYE, Block.mushroomBrown, Item.SUGAR
        });
        craftingmanager.addShapelessRecipe(new ItemStack(Item.SPECKLED_MELON), new Object[] {
            Item.MELON, Item.GOLD_INGOT
        });
        craftingmanager.addShapelessRecipe(new ItemStack(Item.BLAZE_POWDER, 2), new Object[] {
            Item.BLAZE_ROD
        });
        craftingmanager.addShapelessRecipe(new ItemStack(Item.MAGMA_CREAM), new Object[] {
            Item.BLAZE_POWDER, Item.SLIMEBALL
        });
    }
}
