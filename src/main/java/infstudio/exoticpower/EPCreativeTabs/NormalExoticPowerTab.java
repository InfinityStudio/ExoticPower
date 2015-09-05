package infstudio.exoticpower.EPCreativeTabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class NormalExoticPowerTab extends CreativeTabs {

    public NormalExoticPowerTab() {
        super("ExoticPower[Normal]");
    }

    @Override
    public Item getTabIconItem() {
        return Items.apple;
    }

}
