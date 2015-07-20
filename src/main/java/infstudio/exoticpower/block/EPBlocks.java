package infstudio.exoticpower.block;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class EPBlocks {

	public static Block testgui = new BlockTestGui();
	public static Block pipediamond = new PipeDiamond();
	
	public static final ArrayList<Block> blockList = new ArrayList<Block>();
	
	public static void init() {
		GameRegistry.registerBlock(testgui, "testgui");
		GameRegistry.registerBlock(pipediamond, "pipediamond");
	}
}