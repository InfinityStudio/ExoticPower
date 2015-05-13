package exoticpower.core.block.ore;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;
import exoticpower.core.ExoticPowerCore;

public class FPOre extends BlockOre{

	public FPOre(String name) {
		super();
    	this.setCreativeTab(ExoticPowerCore.FcTab);
    	this.setBlockTextureName("exoticpower:" + name);
    	this.setBlockName(name);
    	GameRegistry.registerBlock(this, name);
	}
  
	
}
