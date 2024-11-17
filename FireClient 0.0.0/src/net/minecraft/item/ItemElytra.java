package net.minecraft.item;

import net.minecraft.util.EnumArmorMaterial;

public class ItemElytra extends ItemArmor {
	private final EnumArmorMaterial material;
	public ItemElytra(int i, EnumArmorMaterial enumarmormaterial, int j, int k) {
		super(i, enumarmormaterial, j, k);
		maxStackSize = 1;
		setMaxDamage(1024);
		this.material = enumarmormaterial;
	}

	@Override
	public int getItemEnchantability() {
		return material.getEnchantability();
	}
}