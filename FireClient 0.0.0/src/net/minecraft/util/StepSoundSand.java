package net.minecraft.util;

public class StepSoundSand extends StepSound {
	public StepSoundSand(String s, float f, float f1) {
		super(s, f, f1);
	}

	public String stepSoundDir() {
		return "step.gravel";
	}
}
