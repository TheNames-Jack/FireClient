package net.minecraft.entity.player;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Session;
import net.minecraft.world.World;

public class PlayerControllerCreative extends PlayerController {
	private int field_35647_c;

	public PlayerControllerCreative(Minecraft minecraft) {
		super(minecraft);
		isInTestMode = true;
	}

	public static void func_35646_d(EntityPlayer entityplayer) {
		entityplayer.abilities.allowFlying = true;
		entityplayer.abilities.depleteBuckets = true;
		entityplayer.abilities.disableDamage = true;
	}

	public static void func_35645_e(EntityPlayer entityplayer) {
		entityplayer.abilities.allowFlying = false;
		entityplayer.abilities.isFlying = false;
		entityplayer.abilities.depleteBuckets = false;
		entityplayer.abilities.disableDamage = false;
	}

	public void func_6473_b(EntityPlayer entityplayer) {
		func_35646_d(entityplayer);
		for(int i = 0; i < 9; i++) {
			if(entityplayer.inventory.mainInventory[i] == null) {
				entityplayer.inventory.mainInventory[i] = new ItemStack((Block) Session.registeredBlocksList.get(i));
			}
		}
	}

	public static void func_35644_a(Minecraft minecraft, PlayerController playercontroller, int i, int j, int k, int l) {
		minecraft.theWorld.onBlockHit(minecraft.thePlayer, i, j, k, l);
		playercontroller.sendBlockRemoved(i, j, k, l);
	}

	public boolean sendPlaceBlock(EntityPlayer entityplayer, World world, ItemStack itemstack, int i, int j, int k, int l) {
		int i1 = world.getBlockId(i, j, k);
		if(i1 > 0 && Block.blocksList[i1].blockActivated(world, i, j, k, entityplayer)) {
			return true;
		}
		if(itemstack == null) {
			return false;
		}else {
			int j1 = itemstack.getItemDamage();
			int k1 = itemstack.stackSize;
			boolean flag = itemstack.useItem(entityplayer, world, i, j, k, l);
			itemstack.setItemDamage(j1);
			itemstack.stackSize = k1;
			return flag;
		}
	}

	public void clickBlock(int i, int j, int k, int l) {
		func_35644_a(mc, this, i, j, k, l);
		field_35647_c = 5;
	}

	public void sendBlockRemoving(int i, int j, int k, int l) {
		field_35647_c--;
		if(field_35647_c <= 0) {
			field_35647_c = 5;
			func_35644_a(mc, this, i, j, k, l);
		}
	}

	public void resetBlockRemoving() {
	}

	public boolean shouldDrawHUD() {
		return false;
	}

	public void onWorldChange(World world) {
		super.onWorldChange(world);
	}

	public float getBlockReachDistance() {
		return 5F;
	}

	public boolean func_35641_g() {
		return false;
	}

	public boolean isInCreativeMode() {
		return true;
	}

	public boolean extendedReach() {
		return true;
	}
}
