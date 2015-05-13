package exoticpower.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import exoticpower.core.EPBlocks;
import exoticpower.core.EPItems;
import exoticpower.core.ExoticPowerRecipe;
import exoticpower.util.EPWorldGenerator;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
		
	}
	
	public void init(FMLInitializationEvent event) {
		EPItems.init();
		EPBlocks.init();
		elsething();
		ExoticPowerRecipe.init();
	}
	
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	public void elsething(){
		GameRegistry.registerWorldGenerator(new EPWorldGenerator(), 8);
	}
}
