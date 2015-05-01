package com.mycompanioncube.cushions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Custom entity that the player "rides" in order to trigger the sitting
 * "animation". This entity does not have a rendering component as the default
 * white block is hidden inside the cushion.
 * 
 * @author Serial Coder Lain (serialcoderlain@gmail.com)
 *
 */
public class EntityCushion extends EntityLivingBase {
	public EntityCushion(World worldIn) {
		super(worldIn);
		setSize(0.5f, 0.01f);
	}

	@Override	
	public void onEntityUpdate() {		
		super.onEntityUpdate();
		
		// Automatically die if there is no use for it
		if (riddenByEntity == null)
			setDead();
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	public ItemStack getHeldItem() {
		return null;
	}

	@Override
	public ItemStack getEquipmentInSlot(int slotIn) {
		return null;
	}

	@Override
	public ItemStack getCurrentArmor(int slotIn) {
		return null;
	}

	@Override
	public void setCurrentItemOrArmor(int slotIn, ItemStack stack) {
	}
	

	@Override
	public ItemStack[] getInventory() {
		return new ItemStack[0];
	}
}
