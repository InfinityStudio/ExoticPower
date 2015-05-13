package exoticpower.core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import exoticpower.proxy.CommonProxy;



@Mod(modid="ExoticPower", name="ExoticPower", version="1.1.0")

public class ExoticPowerCore{
	public static final ExoticPowerTab FcTab = new ExoticPowerTab();

	@SidedProxy(clientSide = "exoticpower.proxy.ClientProxy",
		    serverSide = "exoticpower.proxy.CommonProxy")
		public static CommonProxy proxy;
		
		@Instance("ExoticPower")
	    public static ExoticPowerCore instance;

		@EventHandler
		public void preInit(FMLPreInitializationEvent event) {
			proxy.preInit(event);
		}
		
		@EventHandler
		public void init(FMLInitializationEvent event) {
			proxy.init(event);
		}
		
		@EventHandler
		public void postInit(FMLPostInitializationEvent event) {
			proxy.postInit(event);
		}

}
