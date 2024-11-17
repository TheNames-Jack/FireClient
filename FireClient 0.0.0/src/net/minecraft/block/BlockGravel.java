// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.block;

import java.util.Random;

import net.minecraft.item.Item;

// Referenced classes of package net.minecraft.src:
//            BlockSand, Item

public class BlockGravel extends BlockSand
{

    public BlockGravel(int i, int j)
    {
        super(i, j);
    }

    public int idDropped(int i, Random random, int j)
    {
        if(random.nextInt(10 - j * 3) == 0)
        {
            return Item.FLINT.id;
        } else
        {
            return blockID;
        }
    }
}
