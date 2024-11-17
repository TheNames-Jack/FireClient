package net.minecraft.util;

import java.io.IOException;
import java.io.InputStream;

import paulscode.sound.codecs.CodecJOrbis;

public class CodecMus extends CodecJOrbis {
	public CodecMus() {
	}

	protected InputStream openInputStream() {
		try {
			return new MusInputStream(this, url, urlConnection.getInputStream());
		}catch(IOException e) {
			return null;
		}
	}
}
