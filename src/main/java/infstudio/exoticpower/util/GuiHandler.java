package infstudio.exoticpower.util;

import infstudio.exoticpower.gui.inventory.*;
import infstudio.exoticpower.inventory.*;
import infstudio.exoticpower.tileentity.*;
import infstudio.exoticpower.GuiID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos bp = new BlockPos(x, y, z);
		switch(ID)
        {
        	case GuiID.GUI_TG:
        		return new ContainerTestGui(player.inventory, (TileEntityTestGui)world.getTileEntity(bp));
        	case GuiID.GUI_EG:
        		return new ContainerElementGenerator(player.inventory, (TileEntityElementGenerator)world.getTileEntity(bp));
        	case GuiID.GUI_PC:
        		return new ContainerPuncher(player.inventory, (TileEntityPuncher)world.getTileEntity(bp));
        }
        return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos bp = new BlockPos(x, y, z);
		switch(ID)
		{
			case GuiID.GUI_TG:
				return new GuiTestGui(player.inventory, (TileEntityTestGui) world.getTileEntity(bp));
			case GuiID.GUI_EG:
				return new GuiElementGenerator(player.inventory, (TileEntityElementGenerator)world.getTileEntity(bp));
			case GuiID.GUI_PC:
				return new GuiPuncher(player.inventory, (TileEntityPuncher) world.getTileEntity(bp));
		}
		return null;
	}

}
