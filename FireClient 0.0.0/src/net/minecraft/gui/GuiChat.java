package net.minecraft.gui;

import org.lwjgl.input.Keyboard;

import net.minecraft.util.ChatAllowedCharacters;

public class GuiChat extends GuiScreen {

	protected String message;
	private int updateCounter;
	private static final String allowedCharacters;

	public GuiChat() {
		message = "";
		updateCounter = 0;
	}
	
	@Override
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
	}
	
	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}
	
	@Override
	public void updateScreen() {
		updateCounter++;
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	protected void keyTyped(char c, int i) {
		if(i == 1) {
			mc.displayGuiScreen(null);
			return;
		}
		if(i == 28) {
			String s = message.trim();
			if(s.length() > 0) {
				String s1 = message.trim();
				if(!mc.lineIsCommand(s1)) {
					mc.thePlayer.sendChatMessage(s1);
				}
			}
			mc.displayGuiScreen(null);
			return;
		}
		if(i == 14 && message.length() > 0) {
			message = message.substring(0, message.length() - 1);
		}
		if(allowedCharacters.indexOf(c) >= 0 && message.length() < 100) {
			message += c;
		}
	}
	
	@Override
	public void drawScreen(int i, int j, float f) {
		drawRect(2, height - 14, width - 2, height - 2, 0x80000000);
		drawString(fontRenderer, (new StringBuilder()).append("> ").append(message).append((updateCounter / 6) % 2 != 0 ? "" : "_").toString(), 4, height - 12, 0xe0e0e0);
		super.drawScreen(i, j, f);
	}
	
	@Override
	protected void mouseClicked(int i, int j, int k) {
		super.mouseClicked(i, j, k);
		if(k != 0) {
			return;
		}
		if(mc.ingameGUI.field_933_a == null) {
			return;
		}
		if(message.length() > 0 && !message.endsWith(" ")) {
			message += " ";
		}
		message += mc.ingameGUI.field_933_a;
		byte byte0 = 100;
		if(message.length() > byte0) {
			message = message.substring(0, byte0);
		}
	}

	static {
		allowedCharacters = ChatAllowedCharacters.allowedCharacters;
	}
}
