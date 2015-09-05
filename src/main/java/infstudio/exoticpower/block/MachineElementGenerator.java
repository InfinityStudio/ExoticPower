package infstudio.exoticpower.block;

import infstudio.exoticpower.GuiID;
import infstudio.exoticpower.tileentity.TileEntityElementGenerator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class MachineElementGenerator extends BlockMachine {

    public MachineElementGenerator() {
        this.setUnlocalizedName("elementgenerator");
        this.Guiid = GuiID.GUI_EG;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityElementGenerator();
    }

}
