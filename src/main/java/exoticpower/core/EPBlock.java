package exoticpower.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class EPBlock extends Block{

	public EPBlock(String name, Material m) {
		super(m);
    	this.setCreativeTab(ExoticPowerCore.FcTab);
    	this.setBlockTextureName("exoticpower:" + name);
    	this.setBlockName(name);
    	GameRegistry.registerBlock(this, name);
	}
  
	public EPBlock(String name) {
		super(Material.rock);
    	this.setCreativeTab(ExoticPowerCore.FcTab);
    	this.setBlockTextureName("exoticpower:" + name);
    	this.setBlockName(name);
    	GameRegistry.registerBlock(this, name);
	}
	
}
