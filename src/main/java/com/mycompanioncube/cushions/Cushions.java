package com.mycompanioncube.cushions;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Mod that implements a cushion that lets players sit down.
 * 
 * @author Serial Coder Lain (serialcoderlain@gmail.com)
 */
@Mod(modid = Cushions.MODID, name = Cushions.NAME, version = Cushions.VERSION)
public class Cushions {

	// Mod information
	public static final String MODID = "cushions";
	public static final String VERSION = "0.9.0";
	public static final String NAME = "Cushions";

	@Instance(value = "cushions")
	public static Cushions instance;

	// Load "server" and client side proxies
	@SidedProxy(clientSide = "com.mycompanioncube.cushions.client.ClientProxy", serverSide = "com.mycompanioncube.cushions.CommonProxy")
	public static CommonProxy proxy;

	/** Cushion block */
	public final static Block cushionBlock = new BlockCushion();
	public final static Block cushionChair = new BlockChair("basic");
	public final static Block cushionChair_oak = new BlockChair("oak");
	public final static Block cushionChair_birch = new BlockChair("birch");
	public final static Block cushionChair_patio = new BlockChair("patio");
	public final static Block cushionChair_stool = new BlockChair("stool", 0.5);
	public final static Block cushionChair_high = new BlockChair("high");
	public final static Block cushionSofa = new BlockSofa("chesterfield");
	public final static Block cushionSofaLeather = new BlockSofa("leather");

	// Unsupported in this version
	public final static boolean allowCushions = false;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		if (allowCushions) {
			ForgeRegistries.ITEMS.register(new ItemCushions(cushionBlock).setRegistryName(cushionBlock.setRegistryName(MODID, "blockcushion").getRegistryName()));
		}
		
		ForgeRegistries.BLOCKS.register(cushionChair_oak);

		ForgeRegistries.ITEMS.register(new ItemBlock(cushionChair_oak).setRegistryName(cushionChair_oak.getRegistryName()));

		ForgeRegistries.BLOCKS.register(cushionChair_birch);
		ForgeRegistries.ITEMS.register(new ItemBlock(cushionChair_birch).setRegistryName(cushionChair_birch.getRegistryName()));

		ForgeRegistries.BLOCKS.register(cushionChair_patio);
		ForgeRegistries.ITEMS.register(new ItemBlock(cushionChair_patio).setRegistryName(cushionChair_patio.getRegistryName()));

		ForgeRegistries.BLOCKS.register(cushionChair);
		ForgeRegistries.ITEMS.register(new ItemBlock(cushionChair).setRegistryName(cushionChair.getRegistryName()));

		ForgeRegistries.BLOCKS.register(cushionChair_stool);
		ForgeRegistries.ITEMS.register(new ItemBlock(cushionChair_stool).setRegistryName(cushionChair_stool.getRegistryName()));

		ForgeRegistries.BLOCKS.register(cushionChair_high);
		ForgeRegistries.ITEMS.register(new ItemBlock(cushionChair_high).setRegistryName(cushionChair_high.getRegistryName()));

		ForgeRegistries.BLOCKS.register(cushionSofa);
		ForgeRegistries.ITEMS.register(new ItemBlock(cushionSofa).setRegistryName(cushionSofa.getRegistryName()));

		ForgeRegistries.BLOCKS.register(cushionSofaLeather);
		ForgeRegistries.ITEMS.register(new ItemBlock(cushionSofaLeather).setRegistryName(cushionSofaLeather.getRegistryName()));

		// Register the entity that players "ride" to sit
		CushionsEntities.register();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		// Register proxy methods
		proxy.registerRenderers();
	}

}