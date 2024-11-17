package net.minecraft.util;

import net.minecraft.client.Minecraft;

public class StatStringFormatKeyInv implements IStatStringFormat {

	final Minecraft mc; /* synthetic field */

	public StatStringFormatKeyInv(Minecraft minecraft) {
		mc = minecraft;
		// super();
	}

	public String formatString(String s) {
		return String.format(s, new Object[]{
				GameSettings.func_41085_c(mc.gameSettings.keyBindInventory.keyCode)
		});
	}
}
