package net.minecraft.util.helper;

import java.util.Objects;

import net.minecraft.world.World;

public class LocationDouble {
	double x;
	double y;
	double z;
	float yaw;
	float pitch;
	World world;
	int dimension;
	public LocationDouble(World world, double x, double y, double z) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public LocationDouble(World world, double x, double y, double z, float yaw, float pitch) {
		this(world, x, y, z);
		this.yaw = yaw;
		this.pitch = pitch;
	}
	
	public LocationDouble(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getPosX() {
		return x;
	}

	public double getPosY() {
		return y;
	}

	public double getPosZ() {
		return z;
	}

	public float getYaw() {
		return yaw;
	}

	public float getPitch() {
		return pitch;
	}

	public World getWorld() {
		return world;
	}

	public int getDimension() {
		return dimension;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		LocationDouble location = (LocationDouble) obj;
		return Double.compare(location.x, x) == 0 && Double.compare(location.y, y) == 0 && Double.compare(location.z, z) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}
}