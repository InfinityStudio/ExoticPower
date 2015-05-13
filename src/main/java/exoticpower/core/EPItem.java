package exoticpower.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class EPItem extends Item{
    public EPItem(String name){
    	this.setCreativeTab(ExoticPowerCore.FcTab);
    	this.setTextureName("exoticpower:" + name);
    	this.setUnlocalizedName(name);
    	GameRegistry.registerItem(this, name);
    }
}
