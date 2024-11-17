package net.minecraft.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArrow;
import net.minecraft.entity.EntityDamageSource;
import net.minecraft.entity.EntityDamageSourceIndirect;
import net.minecraft.entity.EntityFireball;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityPlayer;

public class DamageSource {
	public static DamageSource flyIntoWall = (new DamageSource("flyIntoWall"));
	public static DamageSource inFire = (new DamageSource("inFire")).func_40546_j();
	public static DamageSource onFire = (new DamageSource("onFire")).setDamageBypassesArmor().func_40546_j();
	public static DamageSource lava = (new DamageSource("lava")).func_40546_j();
	public static DamageSource inWall = (new DamageSource("inWall")).setDamageBypassesArmor();
	public static DamageSource drown = (new DamageSource("drown")).setDamageBypassesArmor();
	public static DamageSource starve = (new DamageSource("starve")).setDamageBypassesArmor();
	public static DamageSource cactus = new DamageSource("cactus");
	public static DamageSource fall = (new DamageSource("fall")).setDamageBypassesArmor();
	public static DamageSource outOfWorld = (new DamageSource("outOfWorld")).setDamageBypassesArmor().setDamageAllowedInCreativeMode();
	public static DamageSource generic = (new DamageSource("generic")).setDamageBypassesArmor();
	public static DamageSource explosion = new DamageSource("explosion");
	public static DamageSource magic = (new DamageSource("magic")).setDamageBypassesArmor();
	private boolean isBlockable;
	private boolean isDamageAllowedInCreativeMode;
	private float hungerDamage;
	private boolean field_40549_q;
	private boolean field_40548_r;
	public String damageType;

	public static DamageSource causeMobDamage(EntityLiving entityliving) {
		return new EntityDamageSource("mob", entityliving);
	}

	public static DamageSource causePlayerDamage(EntityPlayer entityplayer) {
		return new EntityDamageSource("player", entityplayer);
	}

	public static DamageSource causeArrowDamage(EntityArrow entityarrow, Entity entity) {
		return (new EntityDamageSourceIndirect("arrow", entityarrow, entity)).func_40544_c();
	}

	public static DamageSource causeFireballDamage(EntityFireball entityfireball, Entity entity) {
		return (new EntityDamageSourceIndirect("fireball", entityfireball, entity)).func_40546_j().func_40544_c();
	}

	public static DamageSource causeThrownDamage(Entity entity, Entity entity1) {
		return (new EntityDamageSourceIndirect("thrown", entity, entity1)).func_40544_c();
	}

	public static DamageSource func_40542_b(Entity entity, Entity entity1) {
		return (new EntityDamageSourceIndirect("indirectMagic", entity, entity1)).setDamageBypassesArmor();
	}

	public boolean func_40547_b() {
		return field_40548_r;
	}

	public DamageSource func_40544_c() {
		field_40548_r = true;
		return this;
	}

	public boolean unblockable() {
		return isBlockable;
	}

	public float getHungerDamage() {
		return hungerDamage;
	}

	public boolean canHarmInCreative() {
		return isDamageAllowedInCreativeMode;
	}

	protected DamageSource(String s) {
		isBlockable = false;
		isDamageAllowedInCreativeMode = false;
		hungerDamage = 0.3F;
		damageType = s;
	}

	public Entity getSourceOfDamage() {
		return getEntity();
	}

	public Entity getEntity() {
		return null;
	}

	protected DamageSource setDamageBypassesArmor() {
		isBlockable = true;
		hungerDamage = 0.0F;
		return this;
	}

	protected DamageSource setDamageAllowedInCreativeMode() {
		isDamageAllowedInCreativeMode = true;
		return this;
	}

	protected DamageSource func_40546_j() {
		field_40549_q = true;
		return this;
	}

	public boolean func_40543_k() {
		return field_40549_q;
	}

	public String func_40545_l() {
		return damageType;
	}

}
