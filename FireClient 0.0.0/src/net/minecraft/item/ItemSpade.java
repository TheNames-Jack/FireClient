// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.util.EnumToolMaterial;

// Referenced classes of package net.minecraft.src:
//            ItemTool, Block, EnumToolMaterial

public class ItemSpade extends ItemTool
{

    private static Block blocksEffectiveAgainst[];

    public ItemSpade(int i, EnumToolMaterial enumtoolmaterial)
    {
        super(i, 1, enumtoolmaterial, blocksEffectiveAgainst);
    }

    public boolean canHarvestBlock(Block block)
    {
        if(block == Block.snow)
        {
            return true;
        }
        return block == Block.SNOW_BLOCK;
    }

    static 
    {
        blocksEffectiveAgainst = (new Block[] {
            Block.grass, Block.dirt, Block.sand, Block.gravel, Block.snow, Block.SNOW_BLOCK, Block.CLAY_BLOCK, Block.tilledField, Block.SOUL_SAND, Block.mycelium
        });
    }
}
