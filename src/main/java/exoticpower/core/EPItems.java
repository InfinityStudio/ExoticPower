package exoticpower.core;

import net.minecraft.item.Item;

public class EPItems {
	public static Item copper,tin,nether_ingot,end_ingot,agate,firestone,chrysocolla_ingot,sapphire;
    public static void init(){
    	copper = new EPItem("copper");
    	tin = new EPItem("tin");
    	nether_ingot = new EPItem("nether_ingot");
    	end_ingot = new EPItem("end_ingot");
    	agate = new EPItem("agate");
    	firestone = new EPItem("firestone");
    	chrysocolla_ingot = new EPItem("chrysocolla_ingot");
    	sapphire = new EPItem("sapphire");
    }
}
