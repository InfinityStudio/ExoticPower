package infstudio.exoticpower.block;

import infstudio.exoticpower.ExoticPower;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockPipe extends Block {

    protected BlockPipe() {
        super(Material.glass);
        this.setCreativeTab(ExoticPower.ept);
        this.setHardness(0.5f);
        this.setStepSound(soundTypeGlass);
        EPBlocks.blockList.add(this);
    }

}
