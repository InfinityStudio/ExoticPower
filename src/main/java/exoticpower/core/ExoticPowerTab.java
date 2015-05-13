package exoticpower.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;


public class ExoticPowerTab extends CreativeTabs{
	
	public ExoticPowerTab() {
		super("ExoticPower");
	}

@Override
	public Item getTabIconItem() {
		return Items.apple;
	}
	}
