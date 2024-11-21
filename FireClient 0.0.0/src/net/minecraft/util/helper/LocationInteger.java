package net.minecraft.util.helper;

import java.util.Objects;

import net.minecraft.world.World;

public class LocationInteger {
	int x;
	int y;
	int z;
	float yaw;
	float pitch;
	World world;
	int dimension;
	public LocationInteger(World world, int x, int y, int z) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public LocationInteger(World world, int x, int y, int z, float yaw, float pitch) {
		this(world, x, y, z);
		this.yaw = yaw;
		this.pitch = pitch;
	}
	
	public LocationInteger(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int getPosX() {
		return x;
	}

	public int getPosY() {
		return y;
	}

	public int getPosZ() {
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
		LocationInteger location = (LocationInteger) obj;
		return Integer.compare(location.x, x) == 0 && Integer.compare(location.y, y) == 0 && Integer.compare(location.z, z) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}
}