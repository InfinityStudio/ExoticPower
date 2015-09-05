package infstudio.exoticpower.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

public class EPItems {

    public static final ArrayList<Item> itemList = new ArrayList<Item>();
    public static Item soulfire, powergun, wrench, enrichmentcrystal;

    public static void init() {
        soulfire = new ItemSoulFire();
        powergun = new ItemPowerGun();
        wrench = new ItemWrench();
        enrichmentcrystal = new ItemEnrichmentCrystal();
        for (Item item : itemList) {
            GameRegistry.registerItem(item, item.getUnlocalizedName());
        }
    }

}
