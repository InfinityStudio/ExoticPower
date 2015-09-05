package infstudio.exoticpower.EPCreativeTabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;


public class MageExoticPowerTab extends CreativeTabs
{
    public MageExoticPowerTab() {
        super("ExoticPower[Mage]");
    }

    @Override
    public Item getTabIconItem() {
        return Items.golden_apple;
    }
}
