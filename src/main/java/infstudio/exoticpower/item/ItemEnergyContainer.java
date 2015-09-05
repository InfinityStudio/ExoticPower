package infstudio.exoticpower.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import infstudio.exoticpower.ExoticPower;

public class ItemEnergyContainer extends Item{
	
	public ItemEnergyContainer() {
		this.setCreativeTab(ExoticPower.ept);
		EPItems.itemList.add(this);
	}

}
