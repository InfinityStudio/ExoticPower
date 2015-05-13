package exoticpower.core;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ExoticPowerRecipe {
    public static void init(){
    	GameRegistry.addSmelting(EPBlocks.copper_ore, new ItemStack(EPItems.copper), 2F);
    	GameRegistry.addSmelting(EPBlocks.tin_ore, new ItemStack(EPItems.tin), 2F);
    	GameRegistry.addSmelting(EPBlocks.end_ore, new ItemStack(EPItems.end_ingot), 2F);
    	GameRegistry.addSmelting(EPBlocks.nether_ore, new ItemStack(EPItems.nether_ingot), 2F);
    	GameRegistry.addSmelting(EPBlocks.agate_ore, new ItemStack(EPItems.agate), 2F);
    	GameRegistry.addSmelting(EPBlocks.chrysocolla_ore, new ItemStack(EPItems.chrysocolla_ingot), 2F);
    }
}
