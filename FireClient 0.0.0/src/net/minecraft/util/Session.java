package net.minecraft.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;

@SuppressWarnings("unchecked")
public class Session {
	public static List registeredBlocksList;
	public String username;
	public String token;
	public String mpPassParameter;
	public String uuid;
	public String playerSkinURL;
	public String playerCapeURL;
	public EnumSkinType skinType;
	public boolean validPlayer = false;
	public Session(String username, String token) {
		this.username = username;
		this.token = token;
		this.uuid = PlayerInfo.GetUUID(this.username);
		this.playerSkinURL = PlayerInfo.GetSkinURL(this.uuid);
		this.playerCapeURL = PlayerInfo.GetCapeURL(this.uuid);
		this.skinType = PlayerInfo.GetPlayerSkinType(this.uuid);
		
		System.out.println("USERNAME: " + this.username);
		System.out.println("TOKEN: " + this.token);
		System.out.println("UUID: " + this.uuid);
		System.out.println("PLAYER SKIN URL: " + this.playerSkinURL);
		System.out.println("PLAYER CAPE URL: " + this.playerCapeURL);
		System.out.println("SKIN TYPE: " + this.skinType);
		
		if(username.length() > 0 && token != "-" && this.uuid.length() > 0) {
			this.validPlayer = true;
		}
	}

	static {
		registeredBlocksList = new ArrayList();
		registeredBlocksList.add(Block.stone);
		registeredBlocksList.add(Block.cobblestone);
		registeredBlocksList.add(Block.brick);
		registeredBlocksList.add(Block.dirt);
		registeredBlocksList.add(Block.planks);
		registeredBlocksList.add(Block.wood);
		registeredBlocksList.add(Block.leaves);
		registeredBlocksList.add(Block.torchWood);
		registeredBlocksList.add(Block.stairSingle);
		registeredBlocksList.add(Block.glass);
		registeredBlocksList.add(Block.cobblestoneMossy);
		registeredBlocksList.add(Block.sapling);
		registeredBlocksList.add(Block.plantYellow);
		registeredBlocksList.add(Block.plantRed);
		registeredBlocksList.add(Block.mushroomBrown);
		registeredBlocksList.add(Block.mushroomRed);
		registeredBlocksList.add(Block.sand);
		registeredBlocksList.add(Block.gravel);
		registeredBlocksList.add(Block.sponge);
		registeredBlocksList.add(Block.cloth);
		registeredBlocksList.add(Block.oreCoal);
		registeredBlocksList.add(Block.oreIron);
		registeredBlocksList.add(Block.oreGold);
		registeredBlocksList.add(Block.blockSteel);
		registeredBlocksList.add(Block.blockGold);
		registeredBlocksList.add(Block.bookShelf);
		registeredBlocksList.add(Block.tnt);
		registeredBlocksList.add(Block.obsidian);
	}
}
