package infstudio.exoticpower.proxy;

import infstudio.exoticpower.EPGenerator;
import infstudio.exoticpower.ExoticPower;
import infstudio.exoticpower.block.EPBlocks;
import infstudio.exoticpower.item.EPItems;
import infstudio.exoticpower.tileentity.TileEntityTestGui;
import infstudio.exoticpower.util.GuiHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
	    
	}
	 
	public void init(FMLInitializationEvent event) {
    	EPBlocks.init();
		EPItems.init(); 
		GameRegistry.registerTileEntity(TileEntityTestGui.class, "TileEntityTestGui");
		//GameRegistry.registerWorldGenerator(new EPGenerator(), 0);
		NetworkRegistry.INSTANCE.registerGuiHandler(ExoticPower.instance, new GuiHandler());
    }
	 
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
}
