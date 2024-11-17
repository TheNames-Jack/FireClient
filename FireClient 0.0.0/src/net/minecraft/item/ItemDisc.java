// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockJukeBox;
import net.minecraft.entity.EntityPlayer;
import net.minecraft.util.EnumRarity;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            Item, World, Block, BlockJukeBox, 
//            ItemStack, EnumRarity, EntityPlayer

public class ItemDisc extends Item {

	public final String recordName;

	protected ItemDisc(int i, String s) {
		super(i);
		recordName = s;
		maxStackSize = 1;
	}

	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l) {
		if(world.getBlockId(i, j, k) == Block.jukebox.blockID && world.getBlockMetadata(i, j, k) == 0) {
			if(world.multiplayerWorld) {
				return true;
			}else {
				((BlockJukeBox) Block.jukebox).ejectRecord(world, i, j, k, id);
				world.playAuxSFXAtEntity(null, 1005, i, j, k, id);
				itemstack.stackSize--;
				return true;
			}
		}else {
			return false;
		}
	}
	
	@Override
	public void addInformation(ItemStack itemstack, List list) {
		list.add((new StringBuilder()).append("C418 - ").append(recordName).toString());
	}

	public EnumRarity getRarity(ItemStack itemstack) {
		return EnumRarity.rare;
	}
}
