package com.mycompanioncube.cushions;

import net.minecraft.block.Block;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Custom item for cushions. Allows for storing the meta data (colour) for the block in its meta data field 
 * 
 * @author Serial Coder Lain (serialcoderlain@gmail.com)
 */
public class ItemCushions extends ItemBlock {
	public ItemCushions(Block block) {
		super(block);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int metadata) {
		return metadata;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + EnumDyeColor.byMetadata(stack.getMetadata()).toString();
	}
}