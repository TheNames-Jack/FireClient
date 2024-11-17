package net.minecraft.world.chunk;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.minecraft.util.Empty2;

public class ChunkFolderPattern implements FileFilter {
	public static final Pattern folderRegexPattern = Pattern.compile("[0-9a-z]|([0-9a-z][0-9a-z])");

	private ChunkFolderPattern() {
	}

	public boolean accept(File file) {
		if(file.isDirectory()) {
			Matcher matcher = folderRegexPattern.matcher(file.getName());
			return matcher.matches();
		}else {
			return false;
		}
	}

	public ChunkFolderPattern(Empty2 empty2) {
		this();
	}

}
