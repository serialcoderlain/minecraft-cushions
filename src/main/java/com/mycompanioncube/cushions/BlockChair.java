package com.mycompanioncube.cushions;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Block that renders as a tiny "cushion"
 * 
 * @author Serial Coder Lain (serialcoderlain@gmail.com)
 */
public class BlockChair extends Block {
	/** Name of the block */
	public static final String name = "blockChair";
	protected double mountedYOffset = 0;

	/** Colour property for this block */
	public static final PropertyDirection FACING = BlockHorizontal.FACING;

	private String type;

	protected BlockChair(String type) {
		this(type, 0);
	}

	protected BlockChair(String type, double mountHeight) {
		super(Material.CARPET);
		this.mountedYOffset = mountHeight;
		this.type = type;
		setHardness(0.5F);
		setSoundType(SoundType.CLOTH);
		setRegistryName(name + "_" + type);
		setUnlocalizedName(name + "_" + type);
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		setCreativeTab(CreativeTabs.DECORATIONS);
		setTickRandomly(false);
		setHarvestLevel("axe", 0);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public String getUnlocalizedName() {
		return name + "_" + type;
	}

	public String getName() {
		return name + "_" + type;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			// Creates a dummy entity the player can ride in order to show the
			// player as sitting
			if (playerIn.getRidingEntity() == null
					&& worldIn.getBlockState(pos.add(0, 1, 0)).getBlock() == Blocks.AIR) {
				EntityCushion entity = new EntityCushion(worldIn, mountedYOffset);
				entity.setPosition(pos.getX() + 0.5, pos.getY() + entity.getMountedYOffset() + 0.25, pos.getZ() + 0.5);
				worldIn.spawnEntity(entity);
				playerIn.startRiding(entity, true);
			} else {
				if (playerIn != null)
					playerIn.dismountRidingEntity();
			}
			return true;
		}
		return true;
	}
	

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@SideOnly(Side.CLIENT)
	public IBlockState getStateForEntityRender(IBlockState state) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		return side == EnumFacing.UP ? true : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}

	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);

		if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
			enumfacing = EnumFacing.NORTH;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	public String getTypeName() {
		return name + "_" + type;
	}

}