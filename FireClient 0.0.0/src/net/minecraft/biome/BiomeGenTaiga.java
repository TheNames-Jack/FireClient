// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.biome;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityWolf;
import net.minecraft.util.SpawnListEntry;
import net.minecraft.world.WorldGenTaiga1;
import net.minecraft.world.WorldGenTaiga2;
import net.minecraft.world.WorldGenerator;

// Referenced classes of package net.minecraft.src:
//            BiomeGenBase, SpawnListEntry, EntityWolf, BiomeDecorator, 
//            WorldGenTaiga1, WorldGenTaiga2, WorldGenerator

public class BiomeGenTaiga extends BiomeGenBase
{

    public BiomeGenTaiga(int i)
    {
        super(i);
        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.entity.EntityWolf.class, 8, 4, 4));
        biomeDecorator.treesPerChunk = 10;
        biomeDecorator.grassPerChunk = 1;
    }

    public WorldGenerator getRandomWorldGenForTrees(Random random)
    {
        if(random.nextInt(3) == 0)
        {
            return new WorldGenTaiga1();
        } else
        {
            return new WorldGenTaiga2(false);
        }
    }
}
