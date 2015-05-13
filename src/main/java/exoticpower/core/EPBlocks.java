package exoticpower.core;

import exoticpower.core.block.ore.DropGemOre;
import exoticpower.core.block.ore.FPOre;
import net.minecraft.block.Block;



public class EPBlocks {
	public static Block copper_ore,tin_ore,nether_ore,end_ore,agate_ore,fire_ore,chrysocolla_ore
	,sapphire_ore;
    public static void init(){
    	copper_ore = new EPBlock("copper_ore").setHardness(5F);
    	tin_ore = new FPOre("tin_ore").setHardness(5F);
    	nether_ore = new EPBlock("nether_ore").setHardness(5F);
    	end_ore = new FPOre("end_ore").setHardness(5F);
    	agate_ore = new DropGemOre("agate_ore",EPItems.agate).setLightLevel(0.4F).setHardness(5F);
    	fire_ore = new DropGemOre("fire_ore",EPItems.firestone).setLightLevel(0.5F).setHardness(5F);
    	chrysocolla_ore = new FPOre("chrysocolla_ore").setLightLevel(0.3F).setHardness(5F);
    	sapphire_ore = new DropGemOre("sapphire_ore",EPItems.sapphire).setLightLevel(0.4F).setHardness(5F);
    }
}
