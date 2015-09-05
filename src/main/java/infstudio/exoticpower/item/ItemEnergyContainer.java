package infstudio.exoticpower.item;

import infstudio.exoticpower.ExoticPower;
import net.minecraft.item.Item;

public class ItemEnergyContainer extends Item {

    public ItemEnergyContainer() {
        this.setCreativeTab(ExoticPower.ept);
        EPItems.itemList.add(this);
    }

}
