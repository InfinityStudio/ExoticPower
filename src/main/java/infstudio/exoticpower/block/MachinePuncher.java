package infstudio.exoticpower.block;

import infstudio.exoticpower.GuiID;
import infstudio.exoticpower.tileentity.TileEntityPuncher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class MachinePuncher extends BlockMachine {

    public MachinePuncher() {
        this.setUnlocalizedName("puncher");
        this.Guiid = GuiID.GUI_PC;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityPuncher();
    }

}
