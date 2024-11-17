package net.minecraft.util;

import java.io.File;
import java.util.List;

import net.minecraft.world.WorldInfo;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkLoader;

public class SaveHandlerMP implements ISaveHandler {
	public SaveHandlerMP() {
	}

	public WorldInfo loadWorldInfo() {
		return null;
	}

	public void checkSessionLock() {
	}

	public IChunkLoader getChunkLoader(WorldProvider worldprovider) {
		return null;
	}

	public void saveWorldInfoAndPlayer(WorldInfo worldinfo, List list) {
	}

	public void saveWorldInfo(WorldInfo worldinfo) {
	}

	public File getMapFile(String s) {
		return null;
	}

	public String func_40530_d() {
		return "none";
	}
}
