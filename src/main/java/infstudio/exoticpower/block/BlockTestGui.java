package infstudio.exoticpower.block;

import infstudio.exoticpower.ExoticPower;
import infstudio.exoticpower.GuiID;
import infstudio.exoticpower.tileentity.TileEntityTestGui;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockTestGui extends BlockContainer {

    protected BlockTestGui() {
        super(Material.rock);
        this.setCreativeTab(ExoticPower.ept);
        EPBlocks.blockList.add(this);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityTestGui();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        ItemStack stack = playerIn.inventory.mainInventory[playerIn.inventory.currentItem];
        playerIn.openGui(ExoticPower.instance, GuiID.GUI_TG, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

}
