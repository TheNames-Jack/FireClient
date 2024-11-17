package net.minecraft.util;

public class ChatLine {
	public String message;
	public int updateCounter;
	public ChatLine(String s) {
		message = s;
		updateCounter = 0;
	}
}
