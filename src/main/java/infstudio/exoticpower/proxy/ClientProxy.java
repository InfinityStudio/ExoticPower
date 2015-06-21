package infstudio.exoticpower.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy {

	public void preInit(FMLPreInitializationEvent event) {
        //PreInit方法
    }
 
    public void init(FMLInitializationEvent event) {
    	//Init方法 进行注册物品 方块的模型 材质
    }
 
    public void postInit(FMLPostInitializationEvent event) {
        //PostInit方法
    }
	
}
