package infstudio.exoticpower.block;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

public class EPBlocks {

    public static final ArrayList<Block> blockList = new ArrayList<Block>();
    public static Block soulore, assemblymachine, elementgenerator, nodedetector, puncher,
            teleporter, diamondpipe, testgui;

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