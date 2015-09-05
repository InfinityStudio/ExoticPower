package infstudio.exoticpower.util;

import infstudio.exoticpower.GuiID;
import infstudio.exoticpower.gui.inventory.GuiElementGenerator;
import infstudio.exoticpower.gui.inventory.GuiPuncher;
import infstudio.exoticpower.gui.inventory.GuiTestGui;
import infstudio.exoticpower.inventory.ContainerElementGenerator;
import infstudio.exoticpower.inventory.ContainerPuncher;
import infstudio.exoticpower.inventory.ContainerTestGui;
import infstudio.exoticpower.tileentity.TileEntityElementGenerator;
import infstudio.exoticpower.tileentity.TileEntityPuncher;
import infstudio.exoticpower.tileentity.TileEntityTestGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos bp = new BlockPos(x, y, z);
        switch (ID) {
            case GuiID.GUI_TG:
                return new ContainerTestGui(player.inventory, (TileEntityTestGui) world.getTileEntity(bp));
            case GuiID.GUI_EG:
                return new ContainerElementGenerator(player.inventory, (TileEntityElementGenerator) world.getTileEntity(bp));
            case GuiID.GUI_PC:
                return new ContainerPuncher(player.inventory, (TileEntityPuncher) world.getTileEntity(bp));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos bp = new BlockPos(x, y, z);
        switch (ID) {
            case GuiID.GUI_TG:
                return new GuiTestGui(player.inventory, (TileEntityTestGui) world.getTileEntity(bp));
            case GuiID.GUI_EG:
                return new GuiElementGenerator(player.inventory, (TileEntityElementGenerator) world.getTileEntity(bp));
            case GuiID.GUI_PC:
                return new GuiPuncher(player.inventory, (TileEntityPuncher) world.getTileEntity(bp));
        }
        return null;
    }

}
