package com.mycompanioncube.cushions;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Mod that implements a cushion that lets players sit down.
 * 
 * @author Serial Coder Lain (serialcoderlain@gmail.com)
 */
@Mod(modid = Cushions.MODID, name = Cushions.NAME, version = Cushions.VERSION)
public class Cushions {

	// Mod information
	public static final String MODID = "cushions";
	public static final String VERSION = "0.3.0";
	public static final String NAME = "Cushions";

	@Instance(value = "cushion")
	public static Cushions instance;

	// Load "server" and client side proxies
	@SidedProxy(clientSide = "com.mycompanioncube.cushions.client.ClientProxy", serverSide = "com.mycompanioncube.cushions.CommonProxy")
	public static CommonProxy proxy;

	/** Cushion block */
	public final static Block cushionBlock = new BlockCushion();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// Register the cushion block
		GameRegistry.registerBlock(cushionBlock, ItemCushions.class, BlockCushion.name);

		// Preferably harvested by axe due to its wooden base
		cushionBlock.setHarvestLevel("axe", 0);
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		// Register proxy methods
		proxy.registerRenderers();

		// Add recipes (wool on plank) for all 16 subtypes of cushions (16 colours)
		for (int u = 0; u < 16; u++)
			GameRegistry.addShapedRecipe(new ItemStack(cushionBlock, 1, u), new Object[] { "E", "W", 'E', new ItemStack(Blocks.wool, 1, u), 'W', Blocks.planks });

		// Register the entity that players "ride" to sit
		EntityRegistry.registerModEntity(EntityCushion.class, "Softbottoms", EntityRegistry.findGlobalUniqueEntityId(), this, 61, 10, false);
	}

}