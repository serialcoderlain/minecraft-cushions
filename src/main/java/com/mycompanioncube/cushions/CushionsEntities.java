package com.mycompanioncube.cushions;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Registers entities
 * 
 * @author Serial Coder Lain (serialcoderlain@gmail.com)
 */
public class CushionsEntities {
  
	public static void register() {
		ResourceLocation resourceLocation = new ResourceLocation(Cushions.MODID, "dummyentity");
		EntityRegistry.registerModEntity(resourceLocation, EntityCushion.class, "Softbottoms", 1, Cushions.instance, 61, 10, false);
	}

}