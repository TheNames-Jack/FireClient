// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.biome;

import java.util.List;

import net.minecraft.entity.EntityGhast;
import net.minecraft.entity.EntityMagmaCube;
import net.minecraft.entity.EntityPigZombie;
import net.minecraft.util.SpawnListEntry;

// Referenced classes of package net.minecraft.src:
//            BiomeGenBase, SpawnListEntry, EntityGhast, EntityPigZombie, 
//            EntityMagmaCube

public class BiomeGenHell extends BiomeGenBase
{

    public BiomeGenHell(int i)
    {
        super(i);
        spawnableMonsterList.clear();
        spawnableCreatureList.clear();
        spawnableWaterCreatureList.clear();
        spawnableMonsterList.add(new SpawnListEntry(net.minecraft.entity.EntityGhast.class, 50, 4, 4));
        spawnableMonsterList.add(new SpawnListEntry(net.minecraft.entity.EntityPigZombie.class, 100, 4, 4));
        spawnableMonsterList.add(new SpawnListEntry(net.minecraft.entity.EntityMagmaCube.class, 1, 4, 4));
    }
}
