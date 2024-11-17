package net.minecraft.gui;

import net.minecraft.util.EnumOptions;
import net.minecraft.util.GameSettings;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringTranslate;

public class GuiOptions extends GuiScreen {
	private GuiScreen parentScreen;
	protected String screenTitle;
	private GameSettings options;
	private static EnumOptions relevantOptions[];

	public GuiOptions(GuiScreen guiscreen, GameSettings gamesettings) {
		screenTitle = "Options";
		parentScreen = guiscreen;
		options = gamesettings;
	}

	public void initGui() {
		StringTranslate stringtranslate = StringTranslate.getInstance();
		screenTitle = stringtranslate.translateKey("options.title");
		int i = 0;
		EnumOptions aenumoptions[] = relevantOptions;
		int j = aenumoptions.length;
		for(int k = 0; k < j; k++) {
			EnumOptions enumoptions = aenumoptions[k];
			if(!enumoptions.getEnumFloat()) {
				GuiSmallButton guismallbutton = new GuiSmallButton(enumoptions.returnEnumOrdinal(), (width / 2 - 155) + (i % 2) * 160, height / 6 + 24 * (i >> 1), enumoptions, options.getKeyBinding(enumoptions));
				if(enumoptions == EnumOptions.DIFFICULTY && mc.theWorld != null && mc.theWorld.getWorldInfo().isHardcoreModeEnabled()) {
					guismallbutton.enabled = false;
					guismallbutton.displayString = (new StringBuilder()).append(StatCollector.translateToLocal("options.difficulty")).append(": ").append(StatCollector.translateToLocal("options.difficulty.hardcore")).toString();
				}
				controlList.add(guismallbutton);
			}else {
				controlList.add(new GuiSlider(enumoptions.returnEnumOrdinal(), (width / 2 - 155) + (i % 2) * 160, height / 6 + 24 * (i >> 1), enumoptions, options.getKeyBinding(enumoptions), options.getOptionFloatValue(enumoptions)));
			}
			i++;
		}

		controlList.add(new GuiButton(101, width / 2 - 100, height / 6 + 96 + 12, stringtranslate.translateKey("options.video")));
		controlList.add(new GuiButton(100, width / 2 - 100, height / 6 + 120 + 12, stringtranslate.translateKey("options.controls")));
		controlList.add(new GuiButton(200, width / 2 - 100, height / 6 + 168, stringtranslate.translateKey("gui.done")));
	}

	protected void actionPerformed(GuiButton guibutton) {
		if(!guibutton.enabled) {
			return;
		}
		if(guibutton.id < 100 && (guibutton instanceof GuiSmallButton)) {
			options.setOptionValue(((GuiSmallButton) guibutton).returnEnumOptions(), 1);
			guibutton.displayString = options.getKeyBinding(EnumOptions.getEnumOptions(guibutton.id));
		}
		if(guibutton.id == 101) {
			mc.gameSettings.saveOptions();
			mc.displayGuiScreen(new GuiVideoSettings(this, options));
		}
		if(guibutton.id == 100) {
			mc.gameSettings.saveOptions();
			mc.displayGuiScreen(new GuiControls(this, options));
		}
		if(guibutton.id == 200) {
			mc.gameSettings.saveOptions();
			mc.displayGuiScreen(parentScreen);
		}
	}

	@Override
	public void drawScreen(int i, int j, float f) {
		drawDefaultBackground();
		drawCenteredString(fontRenderer, screenTitle, width / 2, 20, 0xffffff);
		super.drawScreen(i, j, f);
	}

	static {
		relevantOptions = (new EnumOptions[]{
				EnumOptions.MUSIC, EnumOptions.SOUND, EnumOptions.INVERT_MOUSE, EnumOptions.SENSITIVITY, EnumOptions.FOV, EnumOptions.DIFFICULTY
		});
	}
}