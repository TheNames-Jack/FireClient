package net.minecraft.block.material;

import net.minecraft.item.map.MapColor;

class MaterialWeb extends Material {
	MaterialWeb(MapColor mapcolor) {
		super(mapcolor);
	}

	public boolean getIsSolid() {
		return false;
	}
}
