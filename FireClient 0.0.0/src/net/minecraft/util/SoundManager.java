package net.minecraft.util;

import java.io.File;
import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.MathHelper;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;

public class SoundManager {
	public static SoundSystem sndSystem;
	private SoundPool soundPoolSounds;
	private SoundPool soundPoolStreaming;
	private SoundPool soundPoolRandomPlayMusic;
	private SoundPool soundPoolPlayMusic;
	private int latestSoundID;
	private GameSettings options;
	private static boolean loaded = false;
	private Random rand;
	private int ticksBeforeMusic;

	public SoundManager() {
		soundPoolSounds = new SoundPool();
		soundPoolStreaming = new SoundPool();
		soundPoolRandomPlayMusic = new SoundPool();
		soundPoolPlayMusic = new SoundPool();
		latestSoundID = 0;
		rand = new Random();
		ticksBeforeMusic = rand.nextInt(12000);
	}

	public void loadSoundSettings(GameSettings gamesettings) {
		soundPoolStreaming.isGetRandomSound = false;
		options = gamesettings;
		if(!loaded && (gamesettings == null || gamesettings.soundVolume != 0.0F || gamesettings.musicVolume != 0.0F)) {
			tryToSetLibraryAndCodecs();
		}
	}

	private void tryToSetLibraryAndCodecs() {
		try {
			float f = options.soundVolume;
			float f1 = options.musicVolume;
			options.soundVolume = 0.0F;
			options.musicVolume = 0.0F;
			options.saveOptions();
			SoundSystemConfig.addLibrary(paulscode.sound.libraries.LibraryLWJGLOpenAL.class);
			SoundSystemConfig.setCodec("ogg", paulscode.sound.codecs.CodecJOrbis.class);
			SoundSystemConfig.setCodec("mus", net.minecraft.util.CodecMus.class);
			SoundSystemConfig.setCodec("wav", paulscode.sound.codecs.CodecWav.class);
			sndSystem = new SoundSystem();
			options.soundVolume = f;
			options.musicVolume = f1;
			options.saveOptions();
		}catch(Throwable throwable) {
			throwable.printStackTrace();
			System.err.println("error linking with the LibraryJavaSound plug-in");
		}
		loaded = true;
	}

	public void onSoundOptionsChanged() {
		if(!loaded && (options.soundVolume != 0.0F || options.musicVolume != 0.0F)) {
			tryToSetLibraryAndCodecs();
		}
		if(loaded) {
			if(options.musicVolume == 0.0F) {
				sndSystem.stop(EnumSoundType.BgMusic.name());
			}else {
				sndSystem.setVolume(EnumSoundType.BgMusic.name(), options.musicVolume);
			}
			sndSystem.setVolume(EnumSoundType.BgMusicKeepAlive.name(), options.musicVolume);
		}
	}

	public void closeMinecraft() {
		if(loaded) {
			sndSystem.cleanup();
		}
	}

	public void addSound(String name, File file) {
		soundPoolSounds.addSound(name, file);
	}

	public void addStreaming(String name, File file) {
		soundPoolStreaming.addSound(name, file);
	}

	public void addRandomPlayMusic(String name, File file) {
		soundPoolRandomPlayMusic.addSound(name, file);
	}
	
	public void addPlayMusic(String name, File file) {
		soundPoolPlayMusic.addSound(name, file);
	}
	
	public void playMusic(EnumSound soundName, float volume, float pitch, boolean shouldLoop) {
		if(!loaded || options.musicVolume == 0.0F) {
			return;
		}
		if(soundName == null) {
			return;
		}
		SoundPoolEntry soundEntry = soundPoolPlayMusic.getRandomSoundFromSoundPool(soundName.getPath());
		if(soundEntry != null && volume > 0.0F) {
			if(sndSystem.playing(EnumSoundType.BgMusic.name())) {
				sndSystem.stop(EnumSoundType.BgMusic.name());
			}else if(sndSystem.playing(EnumSoundType.BgMusicKeepAlive.name())) {
				sndSystem.stop(EnumSoundType.BgMusicKeepAlive.name());
			}
			sndSystem.backgroundMusic(EnumSoundType.BgMusicKeepAlive.name(), soundEntry.soundUrl, soundEntry.soundName, shouldLoop);
			sndSystem.setVolume(EnumSoundType.BgMusicKeepAlive.name(), volume * options.soundVolume);
			sndSystem.play(EnumSoundType.BgMusicKeepAlive.name());
		}
	}

	public void playRandomMusicIfReady() {
		if(!loaded || options.musicVolume == 0.0F) {
			return;
		}
		if(!sndSystem.playing(EnumSoundType.BgMusic.name()) && !sndSystem.playing(EnumSoundType.streaming.name())) {
			if(ticksBeforeMusic > 0) {
				ticksBeforeMusic--;
				return;
			}
			SoundPoolEntry soundpoolentry = soundPoolRandomPlayMusic.getRandomSound();
			if(soundpoolentry != null) {
				ticksBeforeMusic = rand.nextInt(12000) + 12000;
				sndSystem.backgroundMusic(EnumSoundType.BgMusic.name(), soundpoolentry.soundUrl, soundpoolentry.soundName, false);
				sndSystem.setVolume(EnumSoundType.BgMusic.name(), options.musicVolume);
				sndSystem.play(EnumSoundType.BgMusic.name());
			}
		}
	}

	public void func_338_a(EntityLiving entityliving, float f) {
		if(!loaded || options.soundVolume == 0.0F) {
			return;
		}
		if(entityliving == null) {
			return;
		}else {
			float f1 = entityliving.prevRotationYaw + (entityliving.rotationYaw - entityliving.prevRotationYaw) * f;
			double d = entityliving.prevPosX + (entityliving.posX - entityliving.prevPosX) * (double) f;
			double d1 = entityliving.prevPosY + (entityliving.posY - entityliving.prevPosY) * (double) f;
			double d2 = entityliving.prevPosZ + (entityliving.posZ - entityliving.prevPosZ) * (double) f;
			float f2 = MathHelper.cos(-f1 * 0.01745329F - 3.141593F);
			float f3 = MathHelper.sin(-f1 * 0.01745329F - 3.141593F);
			float f4 = -f3;
			float f5 = 0.0F;
			float f6 = -f2;
			float f7 = 0.0F;
			float f8 = 1.0F;
			float f9 = 0.0F;
			sndSystem.setListenerPosition((float) d, (float) d1, (float) d2);
			sndSystem.setListenerOrientation(f4, f5, f6, f7, f8, f9);
			return;
		}
	}

	public void playStreaming(String s, float f, float f1, float f2, float f3, float f4) {
		if(!loaded || options.soundVolume == 0.0F) {
			return;
		}
		String s1 = EnumSoundType.streaming.name();
		if(sndSystem.playing(EnumSoundType.streaming.name())) {
			sndSystem.stop(EnumSoundType.streaming.name());
		}
		if(s == null) {
			return;
		}
		SoundPoolEntry soundpoolentry = soundPoolStreaming.getRandomSoundFromSoundPool(s);
		if(soundpoolentry != null && f3 > 0.0F) {
			if(sndSystem.playing(EnumSoundType.BgMusic.name())) {
				sndSystem.stop(EnumSoundType.BgMusic.name());
			}
			float f5 = 16F;
			sndSystem.newStreamingSource(true, s1, soundpoolentry.soundUrl, soundpoolentry.soundName, false, f, f1, f2, 2, f5 * 4F);
			sndSystem.setVolume(s1, 0.5F * options.soundVolume);
			sndSystem.play(s1);
		}
	}

	public void playSound(String soundName, float f, float f1, float f2, float volume, float pitch) {
		if(!loaded || options.soundVolume == 0.0F) {
			return;
		}
		SoundPoolEntry soundpoolentry = soundPoolSounds.getRandomSoundFromSoundPool(soundName);
		if(soundpoolentry != null && volume > 0.0F) {
			latestSoundID = (latestSoundID + 1) % 256;
			String soundFileName = (new StringBuilder()).append("sound_").append(latestSoundID).toString();
			float f5 = 16F;
			if(volume > 1.0F) {
				f5 *= volume;
			}
			sndSystem.newSource(volume > 1.0F, soundFileName, soundpoolentry.soundUrl, soundpoolentry.soundName, false, f, f1, f2, 2, f5);
			sndSystem.setPitch(soundFileName, pitch);
			if(volume > 1.0F) {
				volume = 1.0F;
			}
			sndSystem.setVolume(soundFileName, volume * options.soundVolume);
			sndSystem.play(soundFileName);
		}
	}

	public void playSoundFX(String s, float f, float f1) {
		if(!loaded || options.soundVolume == 0.0F) {
			return;
		}
		SoundPoolEntry soundpoolentry = soundPoolSounds.getRandomSoundFromSoundPool(s);
		if(soundpoolentry != null) {
			latestSoundID = (latestSoundID + 1) % 256;
			String s1 = (new StringBuilder()).append("sound_").append(latestSoundID).toString();
			sndSystem.newSource(false, s1, soundpoolentry.soundUrl, soundpoolentry.soundName, false, 0.0F, 0.0F, 0.0F, 0, 0.0F);
			if(f > 1.0F) {
				f = 1.0F;
			}
			f *= 0.25F;
			sndSystem.setPitch(s1, f1);
			sndSystem.setVolume(s1, f * options.soundVolume);
			sndSystem.play(s1);
		}
	}
}
