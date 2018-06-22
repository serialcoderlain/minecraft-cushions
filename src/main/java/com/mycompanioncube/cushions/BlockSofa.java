package com.mycompanioncube.cushions;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
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
public class BlockSofa extends Block {
	/** Name of the block */
	public static final String name = "blockSofa";

	public static final PropertyDirection FACING = BlockHorizontal.FACING;

	public static final PropertyEnum<BlockSofa.EnumShape> SHAPE = PropertyEnum.<BlockSofa.EnumShape>create("shape",
			BlockSofa.EnumShape.class);

	private String type;

	protected BlockSofa(String type) {
		super(Material.CARPET);
		this.type = type;
		setHardness(0.5F);
		setSoundType(SoundType.CLOTH);
		setRegistryName(name + "_" + type);
		setUnlocalizedName(name + "_" + type);
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(SHAPE,
				EnumShape.STRAIGHT));
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
				EntityCushion entity = new EntityCushion(worldIn, 0);
				entity.setPosition(pos.getX() + 0.5, pos.getY() + 0.2, pos.getZ() + 0.5);
				worldIn.spawnEntity(entity);
				playerIn.startRiding(entity);
			} else {
				if (playerIn != null)
					playerIn.startRiding(null);
			}
			return true;
		}
		return true;
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite())
				.withProperty(SHAPE, EnumShape.STRAIGHT);
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		IBlockState blockStateRight = worldIn.getBlockState(pos.add(0, 0, -1));
		IBlockState blockStateLeft = worldIn.getBlockState(pos.add(0, 0, 1));

		IBlockState blockStateFront = worldIn.getBlockState(pos.add(-1, 0, 0));
		IBlockState blockStateBack = worldIn.getBlockState(pos.add(1, 0, 0));

		byte layout = 0;

		if (blockStateRight.getBlock().equals(this))
			layout += 64; // 0b01000000;

		if (blockStateLeft.getBlock().equals(this))
			layout += 2; // 0b00000010;

		if (blockStateFront.getBlock().equals(this))
			layout += 16; // 0b00010000;

		if (blockStateBack.getBlock().equals(this))
			layout += 8;// 0b00001000;

		// System.out.println("Layout: " + String.format("%8s", Integer.toBinaryString(layout)).replace(' ', '0'));

		if (layout == 80 /* 0b01010000 */)
			return state.withProperty(SHAPE, EnumShape.INNERCORNER).withProperty(FACING, EnumFacing.NORTH);

		if (layout == 18/* 0b00010010 */)
			return state.withProperty(SHAPE, EnumShape.INNERCORNER).withProperty(FACING, EnumFacing.WEST);

		if (layout == 10 /* 0b00001010 */)
			return state.withProperty(SHAPE, EnumShape.INNERCORNER).withProperty(FACING, EnumFacing.SOUTH);

		if (layout == 72 /* 0b01001000 */)
			return state.withProperty(SHAPE, EnumShape.INNERCORNER).withProperty(FACING, EnumFacing.EAST);

		if (layout == 66 /* 0b01000010 */)
			return state.withProperty(SHAPE, EnumShape.STRAIGHT);

		if (layout == 64 /* 0b01000000 */)
			return state.getValue(FACING).equals(EnumFacing.EAST) ? state.withProperty(SHAPE, EnumShape.LEFT)
					: state.withProperty(SHAPE, EnumShape.RIGHT);

		if (layout == 2 /* 0b00000010 */)
			return state.getValue(FACING).equals(EnumFacing.WEST) ? state.withProperty(SHAPE, EnumShape.LEFT)
					: state.withProperty(SHAPE, EnumShape.RIGHT);

		if (layout == 24 /* 0b00011000 */)
			return state.withProperty(SHAPE, EnumShape.STRAIGHT);

		if (layout == 16 /* 0b00010000 */)
			return state.getValue(FACING).equals(EnumFacing.NORTH) ? state.withProperty(SHAPE, EnumShape.LEFT)
					: state.withProperty(SHAPE, EnumShape.RIGHT);

		if (layout == 8 /* 0b00001000 */)
			return state.getValue(FACING).equals(EnumFacing.SOUTH) ? state.withProperty(SHAPE, EnumShape.LEFT)
					: state.withProperty(SHAPE, EnumShape.RIGHT);

		return state.withProperty(SHAPE, EnumShape.STRAIGHT);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);

		if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
			enumfacing = EnumFacing.NORTH;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(SHAPE, EnumShape.STRAIGHT);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING, SHAPE });
	}

	public String getTypeName() {
		return name + "_" + type;
	}

	public static enum EnumShape implements IStringSerializable {
		STRAIGHT("straight"), LEFT("left"), RIGHT("right"), INNERCORNER("innercorner");
		private final String name;

		private EnumShape(String name) {
			this.name = name;
		}

		public String toString() {
			return this.name;
		}

		public String getName() {
			return this.name;
		}
	}

}