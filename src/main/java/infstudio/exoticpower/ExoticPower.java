package infstudio.exoticpower;

import infstudio.exoticpower.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "ExoticPower", name="ExoticPower" ,version= "1.0.0")
public class ExoticPower {
	
	public static final ExoticPowerTab ept = new ExoticPowerTab();

    @SidedProxy(clientSide = "infstudio.exoticpower.proxy.ClientProxy",
    	    serverSide = "infstudio.exoticpower.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Instance("ExoticPower")
    public static ExoticPower instance;
	
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
