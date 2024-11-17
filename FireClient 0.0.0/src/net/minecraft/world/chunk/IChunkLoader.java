package net.minecraft.world.chunk;

import java.io.IOException;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public interface IChunkLoader {
	public abstract Chunk loadChunk(World world, int i, int j) throws IOException;
	public abstract void saveChunk(World world, Chunk chunk) throws IOException;
	public abstract void saveExtraChunkData(World world, Chunk chunk) throws IOException;
	public abstract void chunkTick();
	public abstract void saveExtraData();
}