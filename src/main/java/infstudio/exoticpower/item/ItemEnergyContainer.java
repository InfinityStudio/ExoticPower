package infstudio.exoticpower.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import infstudio.exoticpower.ExoticPower;
import infstudio.exoticpower.api.energy.IEnergyContainerItem;

public class ItemEnergyContainer extends Item implements IEnergyContainerItem{

	public int energy;
	public int capacity;
	public int maxReceive;
	public int maxExtract;
	
	public ItemEnergyContainer() {
		this.setCreativeTab(ExoticPower.ept);
		EPItems.itemList.add(this);
	}
	
	@Override
	public int receiveEnergy(ItemStack container, int maxReceive,
			boolean simulate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract,
			boolean simulate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		return this.getDamage(container);
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		return this.getMaxDamage(container);
	}

}
