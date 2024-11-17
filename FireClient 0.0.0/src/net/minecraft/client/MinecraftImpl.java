// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.client;

import java.awt.*;

import net.minecraft.util.PanelCrashReport;
import net.minecraft.util.UnexpectedThrowable;

// Referenced classes of package net.minecraft.src:
//            PanelCrashReport, UnexpectedThrowable

public class MinecraftImpl extends Minecraft
{

    final Frame mcFrame; /* synthetic field */

    public MinecraftImpl(Component component, Canvas canvas, MinecraftApplet minecraftapplet, int i, int j, boolean flag, Frame frame)
    {
        super(component, canvas, minecraftapplet, i, j, flag);
        mcFrame = frame;
    }

    public void displayUnexpectedThrowable(UnexpectedThrowable unexpectedthrowable)
    {
        mcFrame.removeAll();
        mcFrame.add(new PanelCrashReport(unexpectedthrowable), "Center");
        mcFrame.validate();
    }
}
