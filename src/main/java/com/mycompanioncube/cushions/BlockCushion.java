package com.mycompanioncube.cushions;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Block that renders as a tiny "cushion" 
 * 
 * @author Serial Coder Lain (serialcoderlain@gmail.com)
 */
public class BlockCushion extends Block {
	/** Name of the block */
	public static final String name = "blockCushion";
	
	/** Colour property for this block */
	public static final PropertyEnum COLOR = PropertyEnum.create("color", EnumDyeColor.class);

	protected BlockCushion() {
		super(Material.carpet);
		setHardness(0.5F);
		setStepSound(Block.soundTypeCloth);
		setUnlocalizedName(Cushions.MODID + "." + name);
		setCreativeTab(CreativeTabs.tabDecorations);
		setTickRandomly(false);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.13F, 1.0F);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	public String getName() {
		return name;
	}

	public boolean isFullCube() {
		return false;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {		
		if (!worldIn.isRemote) {
			// Creates a dummy entity the player can ride in order to show the player as sitting
			if (playerIn.ridingEntity == null && worldIn.getBlockState(pos.add(0, 1, 0)).getBlock() == Blocks.air) {
				
				EntityCushion entity = new EntityCushion(worldIn);
				entity.setPosition(pos.getX() + 0.5, pos.getY()+0.2, pos.getZ() + 0.5);
				worldIn.spawnEntityInWorld(entity);
				playerIn.mountEntity(entity);
			} else {
				playerIn.mountEntity(null);
			}
			return true;
		}
		return true;
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {	
		return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return side == EnumFacing.UP ? true : super.shouldSideBeRendered(worldIn, pos, side);
	}

	public int damageDropped(IBlockState state) {
		return ((EnumDyeColor) state.getValue(COLOR)).getMetadata();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
		// Register all 16 subtypes
		for (int i = 0; i < 16; ++i) {
			list.add(new ItemStack(itemIn, 1, i));
		}
	}

	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(meta));
	}

	public int getMetaFromState(IBlockState state) {
		return ((EnumDyeColor) state.getValue(COLOR)).getMetadata();
	}

	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { COLOR });
	}
}