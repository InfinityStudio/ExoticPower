package infstudio.exoticpower.proxy;

import infstudio.exoticpower.block.EPBlocks;
import infstudio.exoticpower.item.EPItems;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {

    }

    public void init(FMLInitializationEvent event) {
        super.init(event);
        this.registerBlockModels();
        this.registerItemModels();
    }

    public void postInit(FMLPostInitializationEvent event) {

    }

    public void registerBlockModels() {
        for (Block block : EPBlocks.blockList) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block),
                    0, new ModelResourceLocation("ExoticPower:" + block.getUnlocalizedName(), "inventory"));
        }
    }

    public void registerItemModels() {
        for (Item item : EPItems.itemList) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item,
                    0, new ModelResourceLocation("ExoticPower:" + item.getUnlocalizedName(), "inventory"));
            ModelBakery.addVariantName(item, "ExoticPower:" + item.getUnlocalizedName());
        }
    }
}
