package infstudio.exoticpower.item;

import infstudio.exoticpower.ExoticPower;
import net.minecraft.item.Item;

public class ItemSoulFire extends Item {

    public ItemSoulFire() {
        this.setUnlocalizedName("soulfire");
        this.setCreativeTab(ExoticPower.ept);
        EPItems.itemList.add(this);
    }

}
