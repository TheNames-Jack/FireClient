package net.minecraft.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetClientHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.packet.Packet101CloseWindow;
import net.minecraft.packet.Packet10Flying;
import net.minecraft.packet.Packet11PlayerPosition;
import net.minecraft.packet.Packet12PlayerLook;
import net.minecraft.packet.Packet13PlayerLookMove;
import net.minecraft.packet.Packet14BlockDig;
import net.minecraft.packet.Packet18Animation;
import net.minecraft.packet.Packet19EntityAction;
import net.minecraft.packet.Packet3Chat;
import net.minecraft.packet.Packet9Respawn;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Session;
import net.minecraft.util.StatBase;
import net.minecraft.world.World;

public class EntityClientPlayerMP extends EntityPlayerSP {
	public NetClientHandler sendQueue;
	private int inventoryUpdateTickCounter;
	private boolean hasSetHealth;
	private double oldPosX;
	private double field_9378_bz;
	private double oldPosY;
	private double oldPosZ;
	private float oldRotationYaw;
	private float oldRotationPitch;
	private boolean wasOnGround;
	private boolean shouldStopSneaking;
	private boolean wasSneaking;
	private int timeSinceMoved;
	private boolean wasGliding;

	public EntityClientPlayerMP(Minecraft minecraft, World world, Session session, NetClientHandler netclienthandler) {
		super(minecraft, world, session, 0);
		inventoryUpdateTickCounter = 0;
		hasSetHealth = false;
		wasOnGround = false;
		shouldStopSneaking = false;
		wasSneaking = false;
		timeSinceMoved = 0;
		sendQueue = netclienthandler;
	}

	public boolean attackEntityFrom(DamageSource damagesource, int i) {
		return false;
	}

	public void heal(int i) {
	}

	public void onUpdate() {
		if(!worldObj.blockExists(MathHelper.floor_double(posX), worldObj.worldYMax / 2, MathHelper.floor_double(posZ))) {
			return;
		}else {
			super.onUpdate();
			onUpdate2();
			return;
		}
	}

	public void onUpdate2() {
		if(inventoryUpdateTickCounter++ == 20) {
			sendInventoryChanged();
			inventoryUpdateTickCounter = 0;
		}

		// Update Elytra state
		boolean isUsingElytraFlag = isUsingElytra();
		if(isUsingElytraFlag != wasGliding) {
			if(isUsingElytraFlag) {
				sendQueue.addToSendQueue(new Packet19EntityAction(this, 6));
			}else {
				sendQueue.addToSendQueue(new Packet19EntityAction(this, 7));
			}
			wasGliding = isUsingElytraFlag;
		}

		boolean flag = isSprinting();
		if(flag != wasSneaking) {
			if(flag) {
				sendQueue.addToSendQueue(new Packet19EntityAction(this, 4));
			}else {
				sendQueue.addToSendQueue(new Packet19EntityAction(this, 5));
			}
			wasSneaking = flag;
		}

		boolean flag1 = isSneaking();
		if(flag1 != shouldStopSneaking) {
			if(flag1) {
				sendQueue.addToSendQueue(new Packet19EntityAction(this, 1));
			}else {
				sendQueue.addToSendQueue(new Packet19EntityAction(this, 2));
			}
			shouldStopSneaking = flag1;
		}

		// Movement calculation
		double d = posX - oldPosX;
		double d1 = boundingBox.minY - field_9378_bz;
		double d2 = posY - oldPosY;
		double d3 = posZ - oldPosZ;
		double d4 = rotationYaw - oldRotationYaw;
		double d5 = rotationPitch - oldRotationPitch;

		boolean flag2 = d1 != 0.0D || d2 != 0.0D || d != 0.0D || d3 != 0.0D;
		boolean flag3 = d4 != 0.0D || d5 != 0.0D;

		if(ridingEntity != null) {
			if(flag3) {
				sendQueue.addToSendQueue(new Packet11PlayerPosition(motionX, -999D, -999D, motionZ, onGround));
			}else {
				sendQueue.addToSendQueue(new Packet13PlayerLookMove(motionX, -999D, -999D, motionZ, rotationYaw, rotationPitch, onGround));
			}
			flag2 = false;
		}else if(flag2 && flag3) {
			sendQueue.addToSendQueue(new Packet13PlayerLookMove(posX, boundingBox.minY, posY, posZ, rotationYaw, rotationPitch, onGround));
			timeSinceMoved = 0;
		}else if(flag2) {
			sendQueue.addToSendQueue(new Packet11PlayerPosition(posX, boundingBox.minY, posY, posZ, onGround));
			timeSinceMoved = 0;
		}else if(flag3) {
			sendQueue.addToSendQueue(new Packet12PlayerLook(rotationYaw, rotationPitch, onGround));
			timeSinceMoved = 0;
		}else {
			sendQueue.addToSendQueue(new Packet10Flying(onGround));
			if(wasOnGround != onGround || timeSinceMoved > 200) {
				timeSinceMoved = 0;
			}else {
				timeSinceMoved++;
			}
		}

		wasOnGround = onGround;
		if(flag2) {
			oldPosX = posX;
			field_9378_bz = boundingBox.minY;
			oldPosY = posY;
			oldPosZ = posZ;
		}
		if(flag3) {
			oldRotationYaw = rotationYaw;
			oldRotationPitch = rotationPitch;
		}
	}

	private boolean isWearingElytra() {
		ItemStack chestSlot = inventory.armorItemInSlot(2);
		return chestSlot != null && chestSlot.getItem() == Item.ELYTRA;
	}

	public void dropCurrentItem() {
		sendQueue.addToSendQueue(new Packet14BlockDig(4, 0, 0, 0, 0));
	}

	public void sendInventoryChanged() {
	}

	protected void joinEntityItemWithWorld(EntityItem entityitem) {
	}

	public void sendChatMessage(String s) {
		sendQueue.addToSendQueue(new Packet3Chat(s));
	}

	public void swingItem() {
		super.swingItem();
		sendQueue.addToSendQueue(new Packet18Animation(this, 1));
	}

	public void respawnPlayer() {
		sendInventoryChanged();
		sendQueue.addToSendQueue(new Packet9Respawn((byte) dimension, (byte) worldObj.difficultySetting, worldObj.getWorldSeed(), worldObj.worldYMax, 0));
	}

	protected void damageEntity(DamageSource damagesource, int i) {
		setEntityHealth(getEntityHealth() - i);
	}

	public void closeScreen() {
		sendQueue.addToSendQueue(new Packet101CloseWindow(craftingInventory.windowId));
		inventory.setItemStack(null);
		super.closeScreen();
	}

	public void setHealth(int i) {
		if(hasSetHealth) {
			super.setHealth(i);
		}else {
			setEntityHealth(i);
			hasSetHealth = true;
		}
	}

	public void addStat(StatBase statbase, int i) {
		if(statbase == null) {
			return;
		}
		if(statbase.isIndependent) {
			super.addStat(statbase, i);
		}
	}

	public void func_27027_b(StatBase statbase, int i) {
		if(statbase == null) {
			return;
		}
		if(!statbase.isIndependent) {
			super.addStat(statbase, i);
		}
	}
}