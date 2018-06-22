package com.mycompanioncube.cushions;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

/**
 * Custom entity that the player "rides" in order to trigger the sitting
 * "animation". This entity does not have a rendering component as the default
 * white block is hidden inside the cushion.
 * 
 * @author Serial Coder Lain (serialcoderlain@gmail.com)
 *
 */
public class EntityCushion extends Entity {
	protected double mountedYOffset = 0;

	public EntityCushion(World worldIn, double mountHeight) {
		super(worldIn);
		mountedYOffset = mountHeight;
		setSize(0.01f, 0.01f);
	}

	public EntityCushion(World worldIn) {
		this(worldIn, 0);
	}

	public void setMountedYOffset(double mountedYOffset) {
		this.mountedYOffset = mountedYOffset;
	}

	@Override
	protected boolean canBeRidden(Entity entityIn) {
		return true;
	}

	@Override
	protected boolean canFitPassenger(Entity passenger) {
		return true;
	}

	@Override
	public double getMountedYOffset() {
		return mountedYOffset;
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
	}

	@Override
	protected void removePassenger(Entity passenger) {
		super.removePassenger(passenger);
		setDead();
	}
	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected void entityInit() {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tagCompund) {
		mountedYOffset = tagCompund.getDouble("height");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tagCompound) {
		tagCompound.setDouble("height", mountedYOffset);
	}

}
