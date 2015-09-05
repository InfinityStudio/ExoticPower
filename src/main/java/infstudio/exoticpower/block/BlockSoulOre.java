package infstudio.exoticpower.block;

import infstudio.exoticpower.ExoticPower;
import infstudio.exoticpower.item.ItemSoulFire;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockSoulOre extends Block {

    protected BlockSoulOre() {
        super(Material.rock);
        this.setUnlocalizedName("soulore");
        this.setCreativeTab(ExoticPower.ept);
        this.setStepSound(soundTypeStone);
        this.setHardness(3.0F);
        this.setResistance(15.0F);
        this.setLightLevel(7.0F);
        this.setHarvestLevel("pickaxe", 2);
        EPBlocks.blockList.add(this);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return new ItemSoulFire();
    }

}
