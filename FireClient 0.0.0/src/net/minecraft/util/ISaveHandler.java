package net.minecraft.util;

import java.io.File;
import java.util.List;

import net.minecraft.world.WorldInfo;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkLoader;

public interface ISaveHandler {
	public abstract WorldInfo loadWorldInfo();
	public abstract void checkSessionLock();
	public abstract IChunkLoader getChunkLoader(WorldProvider worldprovider);
	public abstract void saveWorldInfoAndPlayer(WorldInfo worldinfo, List list);
	public abstract void saveWorldInfo(WorldInfo worldinfo);
	public abstract File getMapFile(String s);
	public abstract String func_40530_d();
}