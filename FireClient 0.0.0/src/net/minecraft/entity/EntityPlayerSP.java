package net.minecraft.entity;

import net.minecraft.achievement.Achievement;
import net.minecraft.achievement.AchievementList;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.IInventory;
import net.minecraft.gui.GuiBrewingStand;
import net.minecraft.gui.GuiChest;
import net.minecraft.gui.GuiCrafting;
import net.minecraft.gui.GuiDispenser;
import net.minecraft.gui.GuiEditSign;
import net.minecraft.gui.GuiEnchantment;
import net.minecraft.gui.GuiFurnace;
import net.minecraft.gui.GuiWinGame;
import net.minecraft.item.Item;
import net.minecraft.item.potion.Potion;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MouseFilter;
import net.minecraft.util.MovementInput;
import net.minecraft.util.Session;
import net.minecraft.util.StatBase;
import net.minecraft.world.World;

public class EntityPlayerSP extends EntityPlayer {
	public MovementInput movementInput;
	protected Minecraft mc;
	protected int sprintToggleTimer;
	public int sprintingTicksLeft;
	public float renderArmYaw;
	public float renderArmPitch;
	public float prevRenderArmYaw;
	public float prevRenderArmPitch;
	private MouseFilter mouseFilter1;
	private MouseFilter mouseFilter2;
	private MouseFilter mouseFilter3;

	public EntityPlayerSP(Minecraft minecraft, World world, Session session, int dimension) {
		super(world);
		sprintToggleTimer = 0;
		sprintingTicksLeft = 0;
		mouseFilter1 = new MouseFilter();
		mouseFilter2 = new MouseFilter();
		mouseFilter3 = new MouseFilter();
		mc = minecraft;
		this.dimension = dimension;
		username = session.username;
		if(session != null && session.username != null && session.username.length() > 0 && session.uuid != null) {
			this.playerUUID = session.uuid;
			this.skinURL = session.playerSkinURL;
		}
	}

	public void moveEntity(double d, double d1, double d2) {
		super.moveEntity(d, d1, d2);
	}

	public void updateEntityActionState() {
		super.updateEntityActionState();
		moveStrafing = movementInput.moveStrafe;
		moveForward = movementInput.moveForward;
		isJumping = movementInput.jump;
		prevRenderArmYaw = renderArmYaw;
		prevRenderArmPitch = renderArmPitch;
		renderArmPitch += (double) (rotationPitch - renderArmPitch) * 0.5D;
		renderArmYaw += (double) (rotationYaw - renderArmYaw) * 0.5D;
	}

	public void onLivingUpdate() {
		if(sprintingTicksLeft > 0) {
			sprintingTicksLeft--;
			if(sprintingTicksLeft == 0) {
				setSprinting(false);
			}
		}
		
		if(sprintToggleTimer > 0) {
			sprintToggleTimer--;
		}
		if(mc.playerController.func_35643_e()) {
			posX = posZ = 0.5D;
			posX = 0.0D;
			posZ = 0.0D;
			rotationYaw = (float) ticksExisted / 12F;
			rotationPitch = 10F;
			posY = 68.5D;
			return;
		}
		if(!mc.statFileWriter.hasAchievementUnlocked(AchievementList.openInventory)) {
			mc.guiAchievement.queueAchievementInformation(AchievementList.openInventory);
		}
		prevTimeInPortal = timeInPortal;
		if(inPortal) {
			if(!worldObj.multiplayerWorld && ridingEntity != null) {
				mountEntity(null);
			}
			if(mc.currentScreen != null) {
				mc.displayGuiScreen(null);
			}
			if(timeInPortal == 0.0F) {
				mc.sndManager.playSoundFX("portal.trigger", 1.0F, rand.nextFloat() * 0.4F + 0.8F);
			}
			timeInPortal += 0.0125F;
			if(timeInPortal >= 1.0F) {
				timeInPortal = 1.0F;
				if(!worldObj.multiplayerWorld) {
					timeUntilPortal = 10;
					mc.sndManager.playSoundFX("portal.travel", 1.0F, rand.nextFloat() * 0.4F + 0.8F);
					byte byte0 = 0;
					if(dimension == -1) {
						byte0 = 0;
					}else {
						byte0 = -1;
					}
					mc.usePortal(byte0);
					triggerAchievement(AchievementList.portal);
				}
			}
			inPortal = false;
		}else if(isPotionActive(Potion.potionConfusion) && getActivePotionEffect(Potion.potionConfusion).getDuration() > 60) {
			timeInPortal += 0.006666667F;
			if(timeInPortal > 1.0F) {
				timeInPortal = 1.0F;
			}
		}else {
			if(timeInPortal > 0.0F) {
				timeInPortal -= 0.05F;
			}
			if(timeInPortal < 0.0F) {
				timeInPortal = 0.0F;
			}
		}
		if(timeUntilPortal > 0) {
			timeUntilPortal--;
		}
		boolean flag = movementInput.jump;
		float f = 0.8F;
		boolean flag1 = movementInput.moveForward >= f;
		movementInput.updatePlayerMoveState(this);
		if(isUsingItem()) {
			movementInput.moveStrafe *= 0.2F;
			movementInput.moveForward *= 0.2F;
			sprintToggleTimer = 0;
		}
		if(movementInput.sneak && ySize < 0.2F) {
			ySize = 0.2F;
		}
		pushOutOfBlocks(posX - (double) width * 0.34999999999999998D, boundingBox.minY + 0.5D, posZ + (double) width * 0.34999999999999998D);
		pushOutOfBlocks(posX - (double) width * 0.34999999999999998D, boundingBox.minY + 0.5D, posZ - (double) width * 0.34999999999999998D);
		pushOutOfBlocks(posX + (double) width * 0.34999999999999998D, boundingBox.minY + 0.5D, posZ - (double) width * 0.34999999999999998D);
		pushOutOfBlocks(posX + (double) width * 0.34999999999999998D, boundingBox.minY + 0.5D, posZ + (double) width * 0.34999999999999998D);
		boolean flag2 = (float) getFoodStats().getFoodLevel() > 6F;
		if(isSneaking()) {
			sprintToggleTimer = 0;
		}
		if(isSprinting() && (movementInput.moveForward < f || isCollidedHorizontally || !flag2)) {
			setSprinting(false);
		}

		if(onGround && !flag1 && movementInput.moveForward >= f && !isSprinting() && flag2 && !isUsingItem() && !isPotionActive(Potion.potionBlindness)) {
			if(sprintToggleTimer == 0) {
				sprintToggleTimer = 7;
			}else {
				setSprinting(true);
				sprintToggleTimer = 0;
			}
		}
		if(abilities.allowFlying && !flag && movementInput.jump) {
			if(flyToggleTimer == 0) {
				flyToggleTimer = 7;
			}else {
				abilities.isFlying = !abilities.isFlying;
				flyToggleTimer = 0;
			}
		}
		if(abilities.isFlying) {
			if(movementInput.sneak) {
				motionY -= 0.14999999999999999D;
			}
			if(movementInput.jump) {
				motionY += 0.14999999999999999D;
			}
		}
		super.onLivingUpdate();
		if(onGround && abilities.isFlying && isSneaking()) {
			abilities.isFlying = false;
		}
	}

	public void func_40182_b(int i) {
		if(!worldObj.multiplayerWorld) {
			if(dimension == 1 && i == 1) {
				triggerAchievement(AchievementList.theEnd2);
				mc.displayGuiScreen(new GuiWinGame());
			}else {
				triggerAchievement(AchievementList.theEnd);
				mc.sndManager.playSoundFX("portal.travel", 1.0F, rand.nextFloat() * 0.4F + 0.8F);
				mc.usePortal(1);
			}
		}
	}

	public float getFOVMultiplier() {
		float f = 1.0F;
		if(abilities.isFlying) {
			f *= 1.1F;
		}
		f *= ((landMovementFactor * func_35166_t_()) / speedOnGround + 1.0F) / 2.0F;
		if(isUsingItem() && getItemInUse().itemID == Item.BOW.id) {
			int i = getItemInUseDuration();
			float f1 = (float) i / 20F;
			if(f1 > 1.0F) {
				f1 = 1.0F;
			}else {
				f1 *= f1;
			}
			f *= 1.0F - f1 * 0.15F;
		}
		return f;
	}

	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setInteger("Score", score);
	}

	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		score = nbttagcompound.getInteger("Score");
	}

	public void closeScreen() {
		super.closeScreen();
		mc.displayGuiScreen(null);
	}

	public void displayGUIEditSign(TileEntitySign tileentitysign) {
		mc.displayGuiScreen(new GuiEditSign(tileentitysign));
	}

	public void displayGUIChest(IInventory iinventory) {
		mc.displayGuiScreen(new GuiChest(inventory, iinventory));
	}

	public void displayWorkbenchGUI(int i, int j, int k) {
		mc.displayGuiScreen(new GuiCrafting(inventory, worldObj, i, j, k));
	}

	public void func_40181_c(int i, int j, int k) {
		mc.displayGuiScreen(new GuiEnchantment(inventory, worldObj, i, j, k));
	}

	public void displayGUIFurnace(TileEntityFurnace tileentityfurnace) {
		mc.displayGuiScreen(new GuiFurnace(inventory, tileentityfurnace));
	}

	public void func_40180_a(TileEntityBrewingStand tileentitybrewingstand) {
		mc.displayGuiScreen(new GuiBrewingStand(inventory, tileentitybrewingstand));
	}

	public void displayGUIDispenser(TileEntityDispenser tileentitydispenser) {
		mc.displayGuiScreen(new GuiDispenser(inventory, tileentitydispenser));
	}

	public void onCriticalHit(Entity entity) {
		mc.effectRenderer.addEffect(new EntityCrit2FX(mc.theWorld, entity));
	}

	public void func_40183_c(Entity entity) {
		EntityCrit2FX entitycrit2fx = new EntityCrit2FX(mc.theWorld, entity, "magicCrit");
		mc.effectRenderer.addEffect(entitycrit2fx);
	}

	public void onItemPickup(Entity entity, int i) {
		mc.effectRenderer.addEffect(new EntityPickupFX(mc.theWorld, entity, this, -0.5F));
	}

	public int getPlayerArmorValue() {
		return inventory.getTotalArmorValue();
	}

	public void sendChatMessage(String s) {
	}

	public boolean isSneaking() {
		return movementInput.sneak && !sleeping;
	}

	public void setHealth(int i) {
		int j = getEntityHealth() - i;
		if(j <= 0) {
			setEntityHealth(i);
			if(j < 0) {
				heartsLife = heartsHalvesLife / 2;
			}
		}else {
			naturalArmorRating = j;
			setEntityHealth(getEntityHealth());
			heartsLife = heartsHalvesLife;
			damageEntity(DamageSource.generic, j);
			hurtTime = maxHurtTime = 10;
		}
	}

	public void respawnPlayer() {
		mc.respawn(false, 0, false);
	}

	public void func_6420_o() {
	}

	@Override
	public void addChatMessage(String s) {
		mc.ingameGUI.addChatMessageTranslate(s);
	}

	public void addStat(StatBase statbase, int i) {
		if(statbase == null) {
			return;
		}
		if(statbase.isAchievement()) {
			Achievement achievement = (Achievement) statbase;
			if(achievement.parentAchievement == null || mc.statFileWriter.hasAchievementUnlocked(achievement.parentAchievement)) {
				if(!mc.statFileWriter.hasAchievementUnlocked(achievement)) {
					mc.guiAchievement.queueTakenAchievement(achievement);
				}
				mc.statFileWriter.readStat(statbase, i);
			}
		}else {
			mc.statFileWriter.readStat(statbase, i);
		}
	}

	private boolean isBlockTranslucent(int i, int j, int k) {
		return worldObj.isBlockNormalCube(i, j, k);
	}

	protected boolean pushOutOfBlocks(double d, double d1, double d2) {
		int i = MathHelper.floor_double(d);
		int j = MathHelper.floor_double(d1);
		int k = MathHelper.floor_double(d2);
		double d3 = d - (double) i;
		double d4 = d2 - (double) k;
		if(isBlockTranslucent(i, j, k) || isBlockTranslucent(i, j + 1, k)) {
			boolean flag = !isBlockTranslucent(i - 1, j, k) && !isBlockTranslucent(i - 1, j + 1, k);
			boolean flag1 = !isBlockTranslucent(i + 1, j, k) && !isBlockTranslucent(i + 1, j + 1, k);
			boolean flag2 = !isBlockTranslucent(i, j, k - 1) && !isBlockTranslucent(i, j + 1, k - 1);
			boolean flag3 = !isBlockTranslucent(i, j, k + 1) && !isBlockTranslucent(i, j + 1, k + 1);
			byte byte0 = -1;
			double d5 = 9999D;
			if(flag && d3 < d5) {
				d5 = d3;
				byte0 = 0;
			}
			if(flag1 && 1.0D - d3 < d5) {
				d5 = 1.0D - d3;
				byte0 = 1;
			}
			if(flag2 && d4 < d5) {
				d5 = d4;
				byte0 = 4;
			}
			if(flag3 && 1.0D - d4 < d5) {
				double d6 = 1.0D - d4;
				byte0 = 5;
			}
			float f = 0.1F;
			if(byte0 == 0) {
				motionX = -f;
			}
			if(byte0 == 1) {
				motionX = f;
			}
			if(byte0 == 4) {
				motionZ = -f;
			}
			if(byte0 == 5) {
				motionZ = f;
			}
		}
		return false;
	}

	public void setSprinting(boolean flag) {
		super.setSprinting(flag);
		if(!flag) {
			sprintingTicksLeft = 0;
		}else {
			sprintingTicksLeft = 600;
		}
	}

	public void setXPStats(float f, int i, int j) {
		currentXP = f;
		totalXP = i;
		playerLevel = j;
	}
}
