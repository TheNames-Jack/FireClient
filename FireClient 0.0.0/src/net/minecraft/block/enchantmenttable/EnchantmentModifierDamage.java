package net.minecraft.block.enchantmenttable;

import net.minecraft.util.DamageSource;
import net.minecraft.util.Empty3;

public final class EnchantmentModifierDamage implements IEnchantmentModifier {

	public int damageModifier;
	public DamageSource damageSource;

	private EnchantmentModifierDamage() {
	}

	public void calculateModifier(Enchantment enchantment, int i) {
		damageModifier += enchantment.calcModifierDamage(i, damageSource);
	}

	EnchantmentModifierDamage(Empty3 empty3) {
		this();
	}
}