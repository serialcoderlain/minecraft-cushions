package com.mycompanioncube.cushions.client;

import com.mycompanioncube.cushions.CommonProxy;
import com.mycompanioncube.cushions.BlockCushion;
import com.mycompanioncube.cushions.Cushions;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		
		// Find the cushions item
		Item itemCushions = GameRegistry.findItem(Cushions.MODID, BlockCushion.name);
		
		// Register all 16 types of subtypes
		ModelBakery.addVariantName(itemCushions, Cushions.MODID + ":" + "blockCushion_white", Cushions.MODID + ":" + "blockCushion_orange", Cushions.MODID
				+ ":" + "blockCushion_magenta", Cushions.MODID + ":" + "blockCushion_lightBlue", Cushions.MODID + ":" + "blockCushion_yellow", Cushions.MODID
				+ ":" + "blockCushion_lime", Cushions.MODID + ":" + "blockCushion_pink", Cushions.MODID + ":" + "blockCushion_gray", Cushions.MODID + ":"
				+ "blockCushion_silver", Cushions.MODID + ":" + "blockCushion_cyan", Cushions.MODID + ":" + "blockCushion_purple", Cushions.MODID + ":"
				+ "blockCushion_blue", Cushions.MODID + ":" + "blockCushion_brown", Cushions.MODID + ":" + "blockCushion_green", Cushions.MODID + ":"
				+ "blockCushion_red", Cushions.MODID + ":" + "blockCushion_black");

		// Set mesh information for all 16 subtypes
		for (int u = 0; u < 16; u++) {
			ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(Cushions.MODID + ":" + "blockCushion_"
					+ EnumDyeColor.byMetadata(u).getUnlocalizedName(), "inventory");

			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemCushions, EnumDyeColor.values()[u].getMetadata(), itemModelResourceLocation);
		}
		
	}

}