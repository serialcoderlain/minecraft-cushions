package com.mycompanioncube.cushions;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockWall;
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
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
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
public class BlockCushion extends Block {
	/** Name of the block */
	public static final String name = "blockCushion";

	/** Colour property for this block */
	public static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.<EnumDyeColor>create("color",
			EnumDyeColor.class);

	protected BlockCushion() {
		super(Material.CARPET);
		setHardness(0.5F);
		setSoundType(SoundType.CLOTH);
		setUnlocalizedName(Cushions.MODID + "." + name);
		setCreativeTab(CreativeTabs.DECORATIONS);
		setTickRandomly(false);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public String getName() {
		return name;
	}

	public boolean isFullCube() {
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
				playerIn.startRiding(null);
			}
			return true;
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		return side == EnumFacing.UP ? true : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}

	public int damageDropped(IBlockState state) {
		return ((EnumDyeColor) state.getValue(COLOR)).getMetadata();
	}

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

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { COLOR });
	}
}