package infstudio.exoticpower.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
	    //PreInit方法 主要进行Config读取 设置等
	}
	 
	public void init(FMLInitializationEvent event) {
	    //Init方法 主要进行设置方块 物品等
    }
	 
	public void postInit(FMLPostInitializationEvent event) {
	    //PostInit方法
	}
	
}
