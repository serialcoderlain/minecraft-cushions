package com.mycompanioncube.cushions.client;

import java.util.ArrayList;
import java.util.List;

import com.mycompanioncube.cushions.BlockChair;
import com.mycompanioncube.cushions.BlockSofa;
import com.mycompanioncube.cushions.CommonProxy;
import com.mycompanioncube.cushions.BlockCushion;
import com.mycompanioncube.cushions.Cushions;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {

		if (Cushions.allowCushions) {
			// Find the cushions item
			Item itemCushions = Item.REGISTRY.getObject(new ResourceLocation(Cushions.MODID, BlockCushion.name));

			ResourceLocation[] resourceVariants = new ResourceLocation[] {
					new ResourceLocation(Cushions.MODID + ":" + "blockCushion_white"),
					new ResourceLocation(Cushions.MODID + ":" + "blockCushion_orange"),
					new ResourceLocation(Cushions.MODID + ":" + "blockCushion_magenta"),
					new ResourceLocation(Cushions.MODID + ":" + "blockCushion_lightBlue"),
					new ResourceLocation(Cushions.MODID + ":" + "blockCushion_yellow"),
					new ResourceLocation(Cushions.MODID + ":" + "blockCushion_lime"),
					new ResourceLocation(Cushions.MODID + ":" + "blockCushion_pink"),
					new ResourceLocation(Cushions.MODID + ":" + "blockCushion_gray"),
					new ResourceLocation(Cushions.MODID + ":" + "blockCushion_silver"),
					new ResourceLocation(Cushions.MODID + ":" + "blockCushion_cyan"),
					new ResourceLocation(Cushions.MODID + ":" + "blockCushion_purple"),
					new ResourceLocation(Cushions.MODID + ":" + "blockCushion_blue"),
					new ResourceLocation(Cushions.MODID + ":" + "blockCushion_brown"),
					new ResourceLocation(Cushions.MODID + ":" + "blockCushion_green"),
					new ResourceLocation(Cushions.MODID + ":" + "blockCushion_red"),
					new ResourceLocation(Cushions.MODID + ":" + "blockCushion_black") };

			ModelBakery.registerItemVariants(itemCushions, resourceVariants);

			// Set mesh information for all 16 subtypes
			for (int u = 0; u < 16; u++) {
				ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(
						Cushions.MODID + ":" + "blockCushion_" + EnumDyeColor.byMetadata(u).getUnlocalizedName(),
						"inventory");

				Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemCushions,
						EnumDyeColor.values()[u].getMetadata(), itemModelResourceLocation);
			}
		}
		List<Block> blocks = new ArrayList();
		blocks.add(Cushions.instance.cushionChair_oak);

		blocks.add(Cushions.instance.cushionChair);
		blocks.add(Cushions.instance.cushionChair_birch);
		blocks.add(Cushions.instance.cushionChair_patio);
		blocks.add(Cushions.instance.cushionSofa);
		blocks.add(Cushions.instance.cushionSofaLeather);
		blocks.add(Cushions.instance.cushionChair_stool);
		blocks.add(Cushions.instance.cushionChair_high);

		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

		for (Block b : blocks) {
			Item item = Item.REGISTRY.getObject(new ResourceLocation(Cushions.MODID, b.getUnlocalizedName()));

			renderItem.getItemModelMesher().register(item, 0,
					new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}

		{
			Item item = Item.REGISTRY.getObject(
					new ResourceLocation(Cushions.MODID, ((BlockChair) Cushions.instance.cushionChair).getName()));
			renderItem.getItemModelMesher().register(item, 0,
					new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
		{
			Item item = Item.REGISTRY.getObject(
					new ResourceLocation(Cushions.MODID, ((BlockChair) Cushions.instance.cushionChair_oak).getName()));
			renderItem.getItemModelMesher().register(item, 0,
					new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}

		{
			Item item = Item.REGISTRY.getObject(new ResourceLocation(Cushions.MODID,
					((BlockChair) Cushions.instance.cushionChair_birch).getName()));
			renderItem.getItemModelMesher().register(item, 0,
					new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}

		{
			Item item = Item.REGISTRY.getObject(new ResourceLocation(Cushions.MODID,
					((BlockChair) Cushions.instance.cushionChair_patio).getName()));
			renderItem.getItemModelMesher().register(item, 0,
					new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}

		{
			Item item = Item.REGISTRY.getObject(
					new ResourceLocation(Cushions.MODID, ((BlockSofa) Cushions.instance.cushionSofa).getName()));
			renderItem.getItemModelMesher().register(item, 0,
					new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}

		{
			Item item = Item.REGISTRY.getObject(
					new ResourceLocation(Cushions.MODID, ((BlockSofa) Cushions.instance.cushionSofaLeather).getName()));
			renderItem.getItemModelMesher().register(item, 0,
					new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}

		{
			Item item = Item.REGISTRY.getObject(new ResourceLocation(Cushions.MODID,
					((BlockChair) Cushions.instance.cushionChair_stool).getName()));
			renderItem.getItemModelMesher().register(item, 0,
					new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
		{
			Item item = Item.REGISTRY.getObject(
					new ResourceLocation(Cushions.MODID, ((BlockChair) Cushions.instance.cushionChair_high).getName()));
			renderItem.getItemModelMesher().register(item, 0,
					new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}

	}

}