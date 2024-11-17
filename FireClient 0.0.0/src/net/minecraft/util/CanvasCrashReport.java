package net.minecraft.util;

import java.awt.Canvas;
import java.awt.Dimension;

public class CanvasCrashReport extends Canvas {
	public CanvasCrashReport(int i) {
		setPreferredSize(new Dimension(i, i));
		setMinimumSize(new Dimension(i, i));
	}
}