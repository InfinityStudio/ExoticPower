package exoticpower.core.block.ore;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import exoticpower.core.EPBlock;
import exoticpower.core.ExoticPowerCore;

public class DropGemOre extends BlockOre{

	private Item item;
	
	public DropGemOre(String name, Item item) {
		super();
		this.setCreativeTab(ExoticPowerCore.FcTab);
    	this.setBlockTextureName("exoticpower:" + name);
    	this.setBlockName(name);
    	GameRegistry.registerBlock(this, name);
		this.item = item;
	}
	@Override
    public Item getItemDropped(int i1, Random r1, int i2)
    {
        return item;
    }
}
