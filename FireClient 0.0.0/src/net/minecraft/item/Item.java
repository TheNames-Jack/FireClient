package net.minecraft.item;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityPlayer;
import net.minecraft.item.map.ItemMap;
import net.minecraft.item.potion.ItemPotion;
import net.minecraft.item.potion.Potion;
import net.minecraft.item.potion.PotionHelper;
import net.minecraft.util.EnumAction;
import net.minecraft.util.EnumArmorMaterial;
import net.minecraft.util.EnumRarity;
import net.minecraft.util.EnumToolMaterial;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StatList;
import net.minecraft.util.StringTranslate;
import net.minecraft.util.Vec3D;
import net.minecraft.world.World;

public class Item {
	protected static Random itemRand = new Random();
	public static Item itemsList[] = new Item[32000];
	public static Item IRON_SHOVEL = (new ItemSpade(0, EnumToolMaterial.IRON)).setIconCoord(2, 5).setItemName("shovelIron");
	public static Item IRON_PICKAXE = (new ItemPickaxe(1, EnumToolMaterial.IRON)).setIconCoord(2, 6).setItemName("pickaxeIron");
	public static Item IRON_AXE = (new ItemAxe(2, EnumToolMaterial.IRON)).setIconCoord(2, 7).setItemName("hatchetIron");
	public static Item FLINT_AND_STEEL = (new ItemFlintAndSteel(3)).setIconCoord(5, 0).setItemName("flintAndSteel");
	public static Item APPLE = (new ItemFood(4, 4, 0.3F, false)).setIconCoord(10, 0).setItemName("apple");
	public static Item BOW = (new ItemBow(5)).setIconCoord(5, 1).setItemName("bow");
	public static Item ARROW = (new Item(6)).setIconCoord(5, 2).setItemName("arrow");
	public static Item coal = (new ItemCoal(7)).setIconCoord(7, 0).setItemName("coal");
	public static Item DIAMOND = (new Item(8)).setIconCoord(7, 3).setItemName("emerald");
	public static Item IRON_INGOT = (new Item(9)).setIconCoord(7, 1).setItemName("ingotIron");
	public static Item GOLD_NUGGET = (new Item(10)).setIconCoord(7, 2).setItemName("ingotGold");
	public static Item IRON_SWORD = (new ItemSword(11, EnumToolMaterial.IRON)).setIconCoord(2, 4).setItemName("swordIron");
	public static Item WOODEN_SWORD = (new ItemSword(12, EnumToolMaterial.WOOD)).setIconCoord(0, 4).setItemName("swordWood");
	public static Item WOODEN_SHOVEL = (new ItemSpade(13, EnumToolMaterial.WOOD)).setIconCoord(0, 5).setItemName("shovelWood");
	public static Item WOODEN_PICKAXE = (new ItemPickaxe(14, EnumToolMaterial.WOOD)).setIconCoord(0, 6).setItemName("pickaxeWood");
	public static Item WOODEN_AXE = (new ItemAxe(15, EnumToolMaterial.WOOD)).setIconCoord(0, 7).setItemName("hatchetWood");
	public static Item STONE_SWORD = (new ItemSword(16, EnumToolMaterial.STONE)).setIconCoord(1, 4).setItemName("swordStone");
	public static Item STONE_SHOVEL = (new ItemSpade(17, EnumToolMaterial.STONE)).setIconCoord(1, 5).setItemName("shovelStone");
	public static Item STONE_PICKAXE = (new ItemPickaxe(18, EnumToolMaterial.STONE)).setIconCoord(1, 6).setItemName("pickaxeStone");
	public static Item STONE_AXE = (new ItemAxe(19, EnumToolMaterial.STONE)).setIconCoord(1, 7).setItemName("hatchetStone");
	public static Item DIMAOND_SWORD = (new ItemSword(20, EnumToolMaterial.EMERALD)).setIconCoord(3, 4).setItemName("swordDiamond");
	public static Item DIAMOND_SHOVEL = (new ItemSpade(21, EnumToolMaterial.EMERALD)).setIconCoord(3, 5).setItemName("shovelDiamond");
	public static Item DIAMOND_PICKAXE = (new ItemPickaxe(22, EnumToolMaterial.EMERALD)).setIconCoord(3, 6).setItemName("pickaxeDiamond");
	public static Item DIAMOND_AXE = (new ItemAxe(23, EnumToolMaterial.EMERALD)).setIconCoord(3, 7).setItemName("hatchetDiamond");
	public static Item STICK = (new Item(24)).setIconCoord(5, 3).setFull3D().setItemName("stick");
	public static Item EMPTY_BOWl = (new Item(25)).setIconCoord(7, 4).setItemName("bowl");
	public static Item SOUP_BOWL = (new ItemSoup(26, 8)).setIconCoord(8, 4).setItemName("mushroomStew");
	public static Item swordGold = (new ItemSword(27, EnumToolMaterial.GOLD)).setIconCoord(4, 4).setItemName("swordGold");
	public static Item shovelGold = (new ItemSpade(28, EnumToolMaterial.GOLD)).setIconCoord(4, 5).setItemName("shovelGold");
	public static Item GOLD_PICKAXE = (new ItemPickaxe(29, EnumToolMaterial.GOLD)).setIconCoord(4, 6).setItemName("pickaxeGold");
	public static Item GOLD_AXE = (new ItemAxe(30, EnumToolMaterial.GOLD)).setIconCoord(4, 7).setItemName("hatchetGold");
	public static Item SILK = (new Item(31)).setIconCoord(8, 0).setItemName("string");
	public static Item FEATHER = (new Item(32)).setIconCoord(8, 1).setItemName("feather");
	public static Item GUNPOWDER = (new Item(33)).setIconCoord(8, 2).setItemName("sulphur").setPotionEffect(PotionHelper.field_40373_k);
	public static Item WOODEN_HOE = (new ItemHoe(34, EnumToolMaterial.WOOD)).setIconCoord(0, 8).setItemName("hoeWood");
	public static Item STONE_HOE = (new ItemHoe(35, EnumToolMaterial.STONE)).setIconCoord(1, 8).setItemName("hoeStone");
	public static Item IRON_HOE = (new ItemHoe(36, EnumToolMaterial.IRON)).setIconCoord(2, 8).setItemName("hoeIron");
	public static Item DIAMOND_HOE = (new ItemHoe(37, EnumToolMaterial.EMERALD)).setIconCoord(3, 8).setItemName("hoeDiamond");
	public static Item GOLD_HOE = (new ItemHoe(38, EnumToolMaterial.GOLD)).setIconCoord(4, 8).setItemName("hoeGold");
	public static Item SEEDS = (new ItemSeeds(39, Block.crops.blockID, Block.tilledField.blockID)).setIconCoord(9, 0).setItemName("seeds");
	public static Item WHEAT = (new Item(40)).setIconCoord(9, 1).setItemName("wheat");
	public static Item BREAD = (new ItemFood(41, 5, 0.6F, false)).setIconCoord(9, 2).setItemName("bread");
	public static Item LEATHER_HELMET = (new ItemArmor(42, EnumArmorMaterial.CLOTH, 0, 0)).setIconCoord(0, 0).setItemName("helmetCloth");
	public static Item LEATHER_CHESTPLATE = (new ItemArmor(43, EnumArmorMaterial.CLOTH, 0, 1)).setIconCoord(0, 1).setItemName("chestplateCloth");
	public static Item LEATHER_LEGGINGS = (new ItemArmor(44, EnumArmorMaterial.CLOTH, 0, 2)).setIconCoord(0, 2).setItemName("leggingsCloth");
	public static Item LEATHER_BOOTS = (new ItemArmor(45, EnumArmorMaterial.CLOTH, 0, 3)).setIconCoord(0, 3).setItemName("bootsCloth");
	public static Item CHAIN_HELMET = (new ItemArmor(46, EnumArmorMaterial.CHAIN, 1, 0)).setIconCoord(1, 0).setItemName("helmetChain");
	public static Item CHAIN_CHESTPLATE = (new ItemArmor(47, EnumArmorMaterial.CHAIN, 1, 1)).setIconCoord(1, 1).setItemName("chestplateChain");
	public static Item CHAIN_LEGGINGS = (new ItemArmor(48, EnumArmorMaterial.CHAIN, 1, 2)).setIconCoord(1, 2).setItemName("leggingsChain");
	public static Item CHAIN_BOOTS = (new ItemArmor(49, EnumArmorMaterial.CHAIN, 1, 3)).setIconCoord(1, 3).setItemName("bootsChain");
	public static Item IRON_HELMET = (new ItemArmor(50, EnumArmorMaterial.IRON, 2, 0)).setIconCoord(2, 0).setItemName("helmetIron");
	public static Item IRON_CHESTPLATE = (new ItemArmor(51, EnumArmorMaterial.IRON, 2, 1)).setIconCoord(2, 1).setItemName("chestplateIron");
	public static Item IRON_LEGGINGS = (new ItemArmor(52, EnumArmorMaterial.IRON, 2, 2)).setIconCoord(2, 2).setItemName("leggingsIron");
	public static Item IRON_BOOTS = (new ItemArmor(53, EnumArmorMaterial.IRON, 2, 3)).setIconCoord(2, 3).setItemName("bootsIron");
	public static Item DIAMOND_HELMET = (new ItemArmor(54, EnumArmorMaterial.DIAMOND, 3, 0)).setIconCoord(3, 0).setItemName("helmetDiamond");
	public static Item DIAMOND_CHESTPLATE = (new ItemArmor(55, EnumArmorMaterial.DIAMOND, 3, 1)).setIconCoord(3, 1).setItemName("chestplateDiamond");
	public static Item DIAMOND_LEGGINGS = (new ItemArmor(56, EnumArmorMaterial.DIAMOND, 3, 2)).setIconCoord(3, 2).setItemName("leggingsDiamond");
	public static Item DIAMOND_BOOTS = (new ItemArmor(57, EnumArmorMaterial.DIAMOND, 3, 3)).setIconCoord(3, 3).setItemName("bootsDiamond");
	public static Item GOLD_HELMET = (new ItemArmor(58, EnumArmorMaterial.GOLD, 4, 0)).setIconCoord(4, 0).setItemName("helmetGold");
	public static Item GOLD_CHESTPLATE = (new ItemArmor(59, EnumArmorMaterial.GOLD, 4, 1)).setIconCoord(4, 1).setItemName("chestplateGold");
	public static Item GOLD_LEGGINGS = (new ItemArmor(60, EnumArmorMaterial.GOLD, 4, 2)).setIconCoord(4, 2).setItemName("leggingsGold");
	public static Item GOLD_BOOTS = (new ItemArmor(61, EnumArmorMaterial.GOLD, 4, 3)).setIconCoord(4, 3).setItemName("bootsGold");
	public static Item FLINT = (new Item(62)).setIconCoord(6, 0).setItemName("flint");
	public static Item RAW_PORK = (new ItemFood(63, 3, 0.3F, true)).setIconCoord(7, 5).setItemName("porkchopRaw");
	public static Item COOKED_PORK = (new ItemFood(64, 8, 0.8F, true)).setIconCoord(8, 5).setItemName("porkchopCooked");
	public static Item PAINTING = (new ItemPainting(65)).setIconCoord(10, 1).setItemName("painting");
	public static Item GOLD_APPLE = (new ItemAppleGold(66, 10, 1.2F, false)).func_35424_o().setPotionEffect(Potion.potionRegeneration.id, 30, 0, 1.0F).setIconCoord(11, 0).setItemName("appleGold");
	public static Item SIGN = (new ItemSign(67)).setIconCoord(10, 2).setItemName("sign");
	public static Item WOODEN_DOOR = (new ItemDoor(68, Material.wood)).setIconCoord(11, 2).setItemName("doorWood");
	public static Item EMPTY_BUCKET = (new ItemBucket(69, 0)).setIconCoord(10, 4).setItemName("bucket");
	public static Item WATER_BUCKET = (new ItemBucket(70, Block.waterMoving.blockID)).setIconCoord(11, 4).setItemName("bucketWater").setContainerItem(EMPTY_BUCKET);
	public static Item LAVA_BUCKET = (new ItemBucket(71, Block.lavaMoving.blockID)).setIconCoord(12, 4).setItemName("bucketLava").setContainerItem(EMPTY_BUCKET);
	public static Item EMPTY_MINECART = (new ItemMinecart(72, 0)).setIconCoord(7, 8).setItemName("minecart");
	public static Item SADDLE = (new ItemSaddle(73)).setIconCoord(8, 6).setItemName("saddle");
	public static Item IRON_DOOR = (new ItemDoor(74, Material.iron)).setIconCoord(12, 2).setItemName("doorIron");
	public static Item REDSTONE = (new ItemRedstone(75)).setIconCoord(8, 3).setItemName("redstone").setPotionEffect(PotionHelper.field_40375_i);
	public static Item SNOWBALL = (new ItemSnowball(76)).setIconCoord(14, 0).setItemName("snowball");
	public static Item BOAT = (new ItemBoat(77)).setIconCoord(8, 8).setItemName("boat");
	public static Item LEATHER = (new Item(78)).setIconCoord(7, 6).setItemName("leather");
	public static Item MILK_BUCKET = (new ItemBucketMilk(79)).setIconCoord(13, 4).setItemName("milk").setContainerItem(EMPTY_BUCKET);
	public static Item BRICK = (new Item(80)).setIconCoord(6, 1).setItemName("brick");
	public static Item CLAY = (new Item(81)).setIconCoord(9, 3).setItemName("clay");
	public static Item SUGARCANE = (new ItemReed(82, Block.reed)).setIconCoord(11, 1).setItemName("reeds");
	public static Item PAPER = (new Item(83)).setIconCoord(10, 3).setItemName("paper");
	public static Item BOOK = (new Item(84)).setIconCoord(11, 3).setItemName("book");
	public static Item SLIMEBALL = (new Item(85)).setIconCoord(14, 1).setItemName("slimeball");
	public static Item CHEST_MINECART = (new ItemMinecart(86, 1)).setIconCoord(7, 9).setItemName("minecartChest");
	public static Item POWERED_MINECART = (new ItemMinecart(87, 2)).setIconCoord(7, 10).setItemName("minecartFurnace");
	public static Item EGG = (new ItemEgg(88)).setIconCoord(12, 0).setItemName("egg");
	public static Item COMPASS = (new Item(89)).setIconCoord(6, 3).setItemName("compass");
	public static Item FISHING_ROD = (new ItemFishingRod(90)).setIconCoord(5, 4).setItemName("fishingRod");
	public static Item CLOCK = (new Item(91)).setIconCoord(6, 4).setItemName("clock");
	public static Item GLOWSTONE_DUST = (new Item(92)).setIconCoord(9, 4).setItemName("yellowDust").setPotionEffect(PotionHelper.field_40372_j);
	public static Item RAW_FISH = (new ItemFood(93, 2, 0.3F, false)).setIconCoord(9, 5).setItemName("fishRaw");
	public static Item COOKED_FISH = (new ItemFood(94, 5, 0.6F, false)).setIconCoord(10, 5).setItemName("fishCooked");
	public static Item INK_SACK = (new ItemDye(95)).setIconCoord(14, 4).setItemName("dyePowder");
	public static Item BONE = (new Item(96)).setIconCoord(12, 1).setItemName("bone").setFull3D();
	public static Item SUGAR = (new Item(97)).setIconCoord(13, 0).setItemName("sugar").setPotionEffect(PotionHelper.field_40365_b);
	public static Item CAKE = (new ItemReed(98, Block.cake)).setMaxStackSize(1).setIconCoord(13, 1).setItemName("cake");
	public static Item BED = (new ItemBed(99)).setMaxStackSize(1).setIconCoord(13, 2).setItemName("bed");
	public static Item REDSTONE_REPEATER = (new ItemReed(100, Block.redstoneRepeaterIdle)).setIconCoord(6, 5).setItemName("diode");
	public static Item COOKIE = (new ItemFood(101, 1, 0.1F, false)).setIconCoord(12, 5).setItemName("cookie");
	public static ItemMap MAP = (ItemMap) (new ItemMap(102)).setIconCoord(12, 3).setItemName("map");
	public static ItemShears SHEARS = (ItemShears) (new ItemShears(103)).setIconCoord(13, 5).setItemName("shears");
	public static Item MELON = (new ItemFood(104, 2, 0.3F, false)).setIconCoord(13, 6).setItemName("melon");
	public static Item PUMPKIN_SEEDS = (new ItemSeeds(105, Block.pumpkinStem.blockID, Block.tilledField.blockID)).setIconCoord(13, 3).setItemName("seeds_pumpkin");
	public static Item MELON_SEEDS = (new ItemSeeds(106, Block.melonStem.blockID, Block.tilledField.blockID)).setIconCoord(14, 3).setItemName("seeds_melon");
	public static Item RAW_BEEF = (new ItemFood(107, 3, 0.3F, true)).setIconCoord(9, 6).setItemName("beefRaw");
	public static Item COOKED_BEEF = (new ItemFood(108, 8, 0.8F, true)).setIconCoord(10, 6).setItemName("beefCooked");
	public static Item RAW_CHICKEN = (new ItemFood(109, 2, 0.3F, true)).setPotionEffect(Potion.potionHunger.id, 30, 0, 0.3F).setIconCoord(9, 7).setItemName("chickenRaw");
	public static Item COOCKED_CHICKEN = (new ItemFood(110, 6, 0.6F, true)).setIconCoord(10, 7).setItemName("chickenCooked");
	public static Item ROTTEN_FLESH = (new ItemFood(111, 4, 0.1F, true)).setPotionEffect(Potion.potionHunger.id, 30, 0, 0.8F).setIconCoord(11, 5).setItemName("rottenFlesh");
	public static Item ENDER_PEARL = (new ItemEnderPearl(112)).setIconCoord(11, 6).setItemName("enderPearl");
	public static Item BLAZE_ROD = (new Item(113)).setIconCoord(12, 6).setItemName("blazeRod");
	public static Item GHAST_TEAR = (new Item(114)).setIconCoord(11, 7).setItemName("ghastTear").setPotionEffect(PotionHelper.field_40366_c);
	public static Item GOLD_INGOT = (new Item(115)).setIconCoord(12, 7).setItemName("goldNugget");
	public static Item NETHER_WART = (new ItemSeeds(116, Block.netherStalk.blockID, Block.slowSand.blockID)).setIconCoord(13, 7).setItemName("netherStalkSeeds").setPotionEffect("+4");
	public static ItemPotion POTION = (ItemPotion) (new ItemPotion(117)).setIconCoord(13, 8).setItemName("potion");
	public static Item GLASS_BOTTLE = (new ItemGlassBottle(118)).setIconCoord(12, 8).setItemName("glassBottle");
	public static Item SPIDER_EYE = (new ItemFood(119, 2, 0.8F, false)).setPotionEffect(Potion.potionPoison.id, 5, 0, 1.0F).setIconCoord(11, 8).setItemName("spiderEye").setPotionEffect(PotionHelper.field_40363_d);
	public static Item FERMENTED_SPIDER_EYE = (new Item(120)).setIconCoord(10, 8).setItemName("fermentedSpiderEye").setPotionEffect(PotionHelper.field_40364_e);
	public static Item BLAZE_POWDER = (new Item(121)).setIconCoord(13, 9).setItemName("blazePowder").setPotionEffect(PotionHelper.field_40362_g);
	public static Item MAGMA_CREAM = (new Item(122)).setIconCoord(13, 10).setItemName("magmaCream").setPotionEffect(PotionHelper.field_40374_h);
	public static Item BREWING_STAND = (new ItemReed(123, Block.brewingStand)).setIconCoord(12, 10).setItemName("brewingStand");
	public static Item CAULDRON = (new ItemReed(124, Block.cauldron)).setIconCoord(12, 9).setItemName("cauldron");
	public static Item ENDER_EYE = (new ItemEnderEye(125)).setIconCoord(11, 9).setItemName("eyeOfEnder");
	public static Item SPECKLED_MELON = (new Item(126)).setIconCoord(9, 8).setItemName("speckledMelon").setPotionEffect(PotionHelper.field_40361_f);
	public static Item ELYTRA = (new ItemElytra(127, EnumArmorMaterial.ELYTRA, 5, 1)).setIconCoord(6, 6).setItemName("elytra");
	public static Item FIREWORK = (new ItemFirework(128)).setIconCoord(6, 7).setItemName("firework");
	public static Item DISC_13 = (new ItemDisc(2000, "13")).setIconCoord(0, 15).setItemName("record");
	public static Item DISC_CAT = (new ItemDisc(2001, "cat")).setIconCoord(1, 15).setItemName("record");
	public static Item DISC_BLOCKS = (new ItemDisc(2002, "blocks")).setIconCoord(2, 15).setItemName("record");
	public static Item DISC_CHIRP = (new ItemDisc(2003, "chirp")).setIconCoord(3, 15).setItemName("record");
	public static Item DISC_FAR = (new ItemDisc(2004, "far")).setIconCoord(4, 15).setItemName("record");
	public static Item DISC_MALL = (new ItemDisc(2005, "mall")).setIconCoord(5, 15).setItemName("record");
	public static Item DISC_MELLOHI = (new ItemDisc(2006, "mellohi")).setIconCoord(6, 15).setItemName("record");
	public static Item DISC_STAL = (new ItemDisc(2007, "stal")).setIconCoord(7, 15).setItemName("record");
	public static Item DISC_STRAD = (new ItemDisc(2008, "strad")).setIconCoord(8, 15).setItemName("record");
	public static Item DISC_WARD = (new ItemDisc(2009, "ward")).setIconCoord(9, 15).setItemName("record");
	public static Item DISC_11 = (new ItemDisc(2010, "11")).setIconCoord(10, 15).setItemName("record");
	public final int id;
	protected int maxStackSize;
	private int durability;
	protected int iconIndex;
	protected boolean bFull3D;
	protected boolean hasSubtypes;
	private Item containerItem;
	private String potionEffect;
	private String itemName;

	protected Item(int i) {
		maxStackSize = 64;
		durability = 0;
		bFull3D = false;
		hasSubtypes = false;
		containerItem = null;
		potionEffect = null;
		id = 256 + i;
		if(itemsList[256 + i] != null) {
			System.out.println((new StringBuilder()).append("CONFLICT @ ").append(i).toString());
		}
		itemsList[256 + i] = this;
	}

	public Item setIconIndex(int i) {
		iconIndex = i;
		return this;
	}

	public Item setMaxStackSize(int i) {
		maxStackSize = i;
		return this;
	}

	public Item setIconCoord(int i, int j) {
		iconIndex = i + j * 16;
		return this;
	}

	public int getIconFromDamage(int i) {
		return iconIndex;
	}

	public final int getIconIndex(ItemStack itemstack) {
		return getIconFromDamage(itemstack.getItemDamage());
	}

	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l) {
		return false;
	}

	public float getStrVsBlock(ItemStack itemstack, Block block) {
		return 1.0F;
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		return itemstack;
	}

	public ItemStack onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		return itemstack;
	}

	public int getItemStackLimit() {
		return maxStackSize;
	}

	public int getPlacedBlockMetadata(int i) {
		return 0;
	}

	public boolean getHasSubtypes() {
		return hasSubtypes;
	}

	protected Item setHasSubtypes(boolean flag) {
		hasSubtypes = flag;
		return this;
	}

	public int getMaxDamage() {
		return durability;
	}

	protected Item setMaxDamage(int i) {
		durability = i;
		return this;
	}

	public boolean isDamageable() {
		return durability > 0 && !hasSubtypes;
	}

	public boolean hitEntity(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
		return false;
	}

	public boolean onBlockDestroyed(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving) {
		return false;
	}

	public int getDamageVsEntity(Entity entity) {
		return 1;
	}

	public boolean canHarvestBlock(Block block) {
		return false;
	}

	public void useItemOnEntity(ItemStack itemstack, EntityLiving entityliving) {
	}

	public Item setFull3D() {
		bFull3D = true;
		return this;
	}

	public boolean isFull3D() {
		return bFull3D;
	}

	public boolean shouldRotateAroundWhenRendering() {
		return false;
	}

	public Item setItemName(String s) {
		itemName = (new StringBuilder()).append("item.").append(s).toString();
		return this;
	}

	public String getLocalItemName(ItemStack itemstack) {
		String s = getItemNameIS(itemstack);
		if(s == null) {
			return "";
		}else {
			return StatCollector.translateToLocal(s);
		}
	}

	public String getItemName() {
		return itemName;
	}

	public String getItemNameIS(ItemStack itemstack) {
		return itemName;
	}

	public Item setContainerItem(Item item) {
		if(maxStackSize > 1) {
			throw new IllegalArgumentException("Max stack size must be 1 for items with crafting results");
		}else {
			containerItem = item;
			return this;
		}
	}

	public Item getContainerItem() {
		return containerItem;
	}

	public boolean hasContainerItem() {
		return containerItem != null;
	}

	public String getStatName() {
		return StatCollector.translateToLocal((new StringBuilder()).append(getItemName()).append(".name").toString());
	}

	public int getColorFromDamage(int i) {
		return 0xffffff;
	}

	public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {}

	public void onCreated(ItemStack itemstack, World world, EntityPlayer entityplayer) {
	}

	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.none;
	}

	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 0;
	}

	public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer entityplayer, int i) {
	}

	protected Item setPotionEffect(String s) {
		potionEffect = s;
		return this;
	}

	public String getPotionEffect() {
		return potionEffect;
	}

	public boolean isPotionIngredient() {
		return potionEffect != null;
	}

	//Extra lines to item info bar
	public void addInformation(ItemStack itemstack, List list) {
		//Check if the item has durability
		if(itemstack.getItem().getMaxDamage() > 0) {
			list.add("Durability: " + (itemstack.getItem().getMaxDamage() - itemstack.getItemDamage()) + "/" + itemstack.getItem().getMaxDamage());
		}
	}

	public String getItemDisplayName(ItemStack itemstack) {
		String s = (new StringBuilder()).append("").append(StringTranslate.getInstance().translateNamedKey(getLocalItemName(itemstack))).toString().trim();
		return s;
	}

	public boolean hasEffect(ItemStack itemstack) {
		return itemstack.isItemEnchanted();
	}

	public EnumRarity getRarity(ItemStack itemstack) {
		if(itemstack.isItemEnchanted()) {
			return EnumRarity.rare;
		}else {
			return EnumRarity.common;
		}
	}

	public boolean isItemTool(ItemStack itemstack) {
		return getItemStackLimit() == 1 && isDamageable();
	}

	protected MovingObjectPosition func_40402_a(World world, EntityPlayer entityplayer, boolean flag) {
		float f = 1.0F;
		float f1 = entityplayer.prevRotationPitch + (entityplayer.rotationPitch - entityplayer.prevRotationPitch) * f;
		float f2 = entityplayer.prevRotationYaw + (entityplayer.rotationYaw - entityplayer.prevRotationYaw) * f;
		double d = entityplayer.prevPosX + (entityplayer.posX - entityplayer.prevPosX) * (double) f;
		double d1 = (entityplayer.prevPosY + (entityplayer.posY - entityplayer.prevPosY) * (double) f + 1.6200000000000001D) - (double) entityplayer.yOffset;
		double d2 = entityplayer.prevPosZ + (entityplayer.posZ - entityplayer.prevPosZ) * (double) f;
		Vec3D vec3d = Vec3D.createVector(d, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
		float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
		float f5 = -MathHelper.cos(-f1 * 0.01745329F);
		float f6 = MathHelper.sin(-f1 * 0.01745329F);
		float f7 = f4 * f5;
		float f8 = f6;
		float f9 = f3 * f5;
		double d3 = 5D;
		Vec3D vec3d1 = vec3d.addVector((double) f7 * d3, (double) f8 * d3, (double) f9 * d3);
		MovingObjectPosition movingobjectposition = world.rayTraceBlocks_do_do(vec3d, vec3d1, flag, !flag);
		return movingobjectposition;
	}

	public int getItemEnchantability() {
		return 0;
	}

	static {
		StatList.initStats();
	}
}
