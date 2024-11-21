package net.minecraft.util;

public enum EnumSound {
	ENDER_DRAGON_DEATH("mob.enderdragon.death"),
	ENDER_DRAGON_HURT("mob.enderdragon.hit"),
	ENDER_DRAGON_WING_FLAP("mob.enderdragon.wings"),
	ENDER_DRAGON_GROWL("mob.enderdragon.growl"),
	ENDER_DRAGON_RESPAWN("mob.enderdragon.respawn"),
	ENDER_DRAGON_BOSS_MUSIC("mob.enderdragon.boss_music");
	
	private String soundPath;
	
	EnumSound(String string) {
		this.soundPath = string;
	}
	
	public String getPath() {
		return soundPath;
	}
}