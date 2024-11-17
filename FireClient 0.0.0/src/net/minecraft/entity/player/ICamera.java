package net.minecraft.entity.player;

import net.minecraft.util.math.AxisAlignedBB;

public interface ICamera {
	public abstract boolean isBoundingBoxInFrustum(AxisAlignedBB axisalignedbb);
	public abstract void setPosition(double d, double d1, double d2);
}
