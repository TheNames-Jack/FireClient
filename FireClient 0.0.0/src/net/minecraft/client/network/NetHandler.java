package net.minecraft.client.network;

import net.minecraft.packet.Packet;
import net.minecraft.packet.Packet0KeepAlive;
import net.minecraft.packet.Packet100OpenWindow;
import net.minecraft.packet.Packet101CloseWindow;
import net.minecraft.packet.Packet102WindowClick;
import net.minecraft.packet.Packet103SetSlot;
import net.minecraft.packet.Packet104WindowItems;
import net.minecraft.packet.Packet105UpdateProgressbar;
import net.minecraft.packet.Packet106Transaction;
import net.minecraft.packet.Packet107CreativeSetSlot;
import net.minecraft.packet.Packet108EnchantItem;
import net.minecraft.packet.Packet10Flying;
import net.minecraft.packet.Packet130UpdateSign;
import net.minecraft.packet.Packet131MapData;
import net.minecraft.packet.Packet14BlockDig;
import net.minecraft.packet.Packet15Place;
import net.minecraft.packet.Packet16BlockItemSwitch;
import net.minecraft.packet.Packet17Sleep;
import net.minecraft.packet.Packet18Animation;
import net.minecraft.packet.Packet19EntityAction;
import net.minecraft.packet.Packet1Login;
import net.minecraft.packet.Packet200Statistic;
import net.minecraft.packet.Packet201PlayerInfo;
import net.minecraft.packet.Packet20NamedEntitySpawn;
import net.minecraft.packet.Packet21PickupSpawn;
import net.minecraft.packet.Packet22Collect;
import net.minecraft.packet.Packet23VehicleSpawn;
import net.minecraft.packet.Packet24MobSpawn;
import net.minecraft.packet.Packet254ServerPing;
import net.minecraft.packet.Packet255KickDisconnect;
import net.minecraft.packet.Packet25EntityPainting;
import net.minecraft.packet.Packet26EntityExpOrb;
import net.minecraft.packet.Packet27Position;
import net.minecraft.packet.Packet28EntityVelocity;
import net.minecraft.packet.Packet29DestroyEntity;
import net.minecraft.packet.Packet2Handshake;
import net.minecraft.packet.Packet30Entity;
import net.minecraft.packet.Packet34EntityTeleport;
import net.minecraft.packet.Packet38EntityStatus;
import net.minecraft.packet.Packet39AttachEntity;
import net.minecraft.packet.Packet3Chat;
import net.minecraft.packet.Packet40EntityMetadata;
import net.minecraft.packet.Packet41EntityEffect;
import net.minecraft.packet.Packet42RemoveEntityEffect;
import net.minecraft.packet.Packet43Experience;
import net.minecraft.packet.Packet4UpdateTime;
import net.minecraft.packet.Packet50PreChunk;
import net.minecraft.packet.Packet51MapChunk;
import net.minecraft.packet.Packet52MultiBlockChange;
import net.minecraft.packet.Packet53BlockChange;
import net.minecraft.packet.Packet54PlayNoteBlock;
import net.minecraft.packet.Packet5PlayerInventory;
import net.minecraft.packet.Packet60Explosion;
import net.minecraft.packet.Packet61DoorChange;
import net.minecraft.packet.Packet6SpawnPosition;
import net.minecraft.packet.Packet70Bed;
import net.minecraft.packet.Packet71Weather;
import net.minecraft.packet.Packet7UseEntity;
import net.minecraft.packet.Packet8UpdateHealth;
import net.minecraft.packet.Packet9Respawn;

public abstract class NetHandler {

	public NetHandler() {
	}

	public abstract boolean isServerHandler();

	public void handleMapChunk(Packet51MapChunk packet51mapchunk) {
	}

	public void registerPacket(Packet packet) {
	}

	public void handleErrorMessage(String s, Object aobj[]) {
	}

	public void handleKickDisconnect(Packet255KickDisconnect packet255kickdisconnect) {
		registerPacket(packet255kickdisconnect);
	}

	public void handleLogin(Packet1Login packet1login) {
		registerPacket(packet1login);
	}

	public void handleFlying(Packet10Flying packet10flying) {
		registerPacket(packet10flying);
	}

	public void handleMultiBlockChange(Packet52MultiBlockChange packet52multiblockchange) {
		registerPacket(packet52multiblockchange);
	}

	public void handleBlockDig(Packet14BlockDig packet14blockdig) {
		registerPacket(packet14blockdig);
	}

	public void handleBlockChange(Packet53BlockChange packet53blockchange) {
		registerPacket(packet53blockchange);
	}

	public void handlePreChunk(Packet50PreChunk packet50prechunk) {
		registerPacket(packet50prechunk);
	}

	public void handleNamedEntitySpawn(Packet20NamedEntitySpawn packet20namedentityspawn) {
		registerPacket(packet20namedentityspawn);
	}

	public void handleEntity(Packet30Entity packet30entity) {
		registerPacket(packet30entity);
	}

	public void handleEntityTeleport(Packet34EntityTeleport packet34entityteleport) {
		registerPacket(packet34entityteleport);
	}

	public void handlePlace(Packet15Place packet15place) {
		registerPacket(packet15place);
	}

	public void handleBlockItemSwitch(Packet16BlockItemSwitch packet16blockitemswitch) {
		registerPacket(packet16blockitemswitch);
	}

	public void handleDestroyEntity(Packet29DestroyEntity packet29destroyentity) {
		registerPacket(packet29destroyentity);
	}

	public void handlePickupSpawn(Packet21PickupSpawn packet21pickupspawn) {
		registerPacket(packet21pickupspawn);
	}

	public void handleCollect(Packet22Collect packet22collect) {
		registerPacket(packet22collect);
	}

	public void handleChat(Packet3Chat packet3chat) {
		registerPacket(packet3chat);
	}

	public void handleVehicleSpawn(Packet23VehicleSpawn packet23vehiclespawn) {
		registerPacket(packet23vehiclespawn);
	}

	public void handleArmAnimation(Packet18Animation packet18animation) {
		registerPacket(packet18animation);
	}

	public void handleEntityAction(Packet19EntityAction packet19entityaction) {
		registerPacket(packet19entityaction);
	}

	public void handleHandshake(Packet2Handshake packet2handshake) {
		registerPacket(packet2handshake);
	}

	public void handleMobSpawn(Packet24MobSpawn packet24mobspawn) {
		registerPacket(packet24mobspawn);
	}

	public void handleUpdateTime(Packet4UpdateTime packet4updatetime) {
		registerPacket(packet4updatetime);
	}

	public void handleSpawnPosition(Packet6SpawnPosition packet6spawnposition) {
		registerPacket(packet6spawnposition);
	}

	public void handleEntityVelocity(Packet28EntityVelocity packet28entityvelocity) {
		registerPacket(packet28entityvelocity);
	}

	public void handleEntityMetadata(Packet40EntityMetadata packet40entitymetadata) {
		registerPacket(packet40entitymetadata);
	}

	public void handleAttachEntity(Packet39AttachEntity packet39attachentity) {
		registerPacket(packet39attachentity);
	}

	public void handleUseEntity(Packet7UseEntity packet7useentity) {
		registerPacket(packet7useentity);
	}

	public void handleEntityStatus(Packet38EntityStatus packet38entitystatus) {
		registerPacket(packet38entitystatus);
	}

	public void handleHealth(Packet8UpdateHealth packet8updatehealth) {
		registerPacket(packet8updatehealth);
	}

	public void handleRespawn(Packet9Respawn packet9respawn) {
		registerPacket(packet9respawn);
	}

	public void handleExplosion(Packet60Explosion packet60explosion) {
		registerPacket(packet60explosion);
	}

	public void handleOpenWindow(Packet100OpenWindow packet100openwindow) {
		registerPacket(packet100openwindow);
	}

	public void handleCloseWindow(Packet101CloseWindow packet101closewindow) {
		registerPacket(packet101closewindow);
	}

	public void handleWindowClick(Packet102WindowClick packet102windowclick) {
		registerPacket(packet102windowclick);
	}

	public void handleSetSlot(Packet103SetSlot packet103setslot) {
		registerPacket(packet103setslot);
	}

	public void handleWindowItems(Packet104WindowItems packet104windowitems) {
		registerPacket(packet104windowitems);
	}

	public void handleUpdateSign(Packet130UpdateSign packet130updatesign) {
		registerPacket(packet130updatesign);
	}

	public void handleCraftingProgress(Packet105UpdateProgressbar packet105updateprogressbar) {
		registerPacket(packet105updateprogressbar);
	}

	public void handlePlayerInventory(Packet5PlayerInventory packet5playerinventory) {
		registerPacket(packet5playerinventory);
	}

	public void handleContainerTransaction(Packet106Transaction packet106transaction) {
		registerPacket(packet106transaction);
	}

	public void handleEntityPainting(Packet25EntityPainting packet25entitypainting) {
		registerPacket(packet25entitypainting);
	}

	public void handleNotePlay(Packet54PlayNoteBlock packet54playnoteblock) {
		registerPacket(packet54playnoteblock);
	}

	public void handleStatistic(Packet200Statistic packet200statistic) {
		registerPacket(packet200statistic);
	}

	public void handleSleep(Packet17Sleep packet17sleep) {
		registerPacket(packet17sleep);
	}

	public void handlePosition(Packet27Position packet27position) {
		registerPacket(packet27position);
	}

	public void handleBedUpdate(Packet70Bed packet70bed) {
		registerPacket(packet70bed);
	}

	public void handleWeather(Packet71Weather packet71weather) {
		registerPacket(packet71weather);
	}

	public void processItemData(Packet131MapData packet131mapdata) {
		registerPacket(packet131mapdata);
	}

	public void handleAuxSFX(Packet61DoorChange packet61doorchange) {
		registerPacket(packet61doorchange);
	}

	public void handleServerPing(Packet254ServerPing packet254serverping) {
		registerPacket(packet254serverping);
	}

	public void handleEntityEffect(Packet41EntityEffect packet41entityeffect) {
		registerPacket(packet41entityeffect);
	}

	public void handleRemoveEntityEffect(Packet42RemoveEntityEffect packet42removeentityeffect) {
		registerPacket(packet42removeentityeffect);
	}

	public void handlePlayerInfo(Packet201PlayerInfo packet201playerinfo) {
		registerPacket(packet201playerinfo);
	}

	public void handleKeepAlive(Packet0KeepAlive packet0keepalive) {
		registerPacket(packet0keepalive);
	}

	public void handleExperience(Packet43Experience packet43experience) {
		registerPacket(packet43experience);
	}

	public void handleCreativeSetSlot(Packet107CreativeSetSlot packet107creativesetslot) {
		registerPacket(packet107creativesetslot);
	}

	public void handleXPOrb(Packet26EntityExpOrb packet26entityexporb) {
		registerPacket(packet26entityexporb);
	}

	public void func_40599_a(Packet108EnchantItem packet108enchantitem) {
	}
}
