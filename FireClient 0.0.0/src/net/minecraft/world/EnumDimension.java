package net.minecraft.world;

public enum EnumDimension {
	Overworld(0),
	Nether(-1),
	TheEnd(1);
	
	int dimensionID;
	
	EnumDimension(int i) {
		dimensionID = i;
	}
	
	public int dimensionID() {
		return dimensionID;
	}
}