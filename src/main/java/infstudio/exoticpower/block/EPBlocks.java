package infstudio.exoticpower.block;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class EPBlocks{
	
	public static final ArrayList<Block> blockList = new ArrayList<Block>();
	public static Block soulore, assemblymachine, elementgenerator, nodedetector, puncher,
		teleporter, diamondpipe,testgui;
	
	public static void init() {
		soulore = new BlockSoulOre();
		assemblymachine = new MachineAssembly();
		elementgenerator = new MachineElementGenerator();
		nodedetector = new MachineNodeDetector();
		puncher = new MachinePuncher();
		teleporter = new MachineTeleporter();
		diamondpipe = new PipeDiamond();
		testgui = new BlockTestGui();
		for (Block block : blockList) {
			GameRegistry.registerBlock(block, block.getUnlocalizedName());
		}
	}
}