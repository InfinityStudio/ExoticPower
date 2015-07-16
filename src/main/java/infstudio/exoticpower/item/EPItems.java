package infstudio.exoticpower.item;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class EPItems {

	public static final ArrayList<Item> itemList = new ArrayList<Item>();
	public static Item Wrench = new ItemWrench();
	
	public static void init() {
		GameRegistry.registerItem(Wrench, "wrench");
	}
	
}
