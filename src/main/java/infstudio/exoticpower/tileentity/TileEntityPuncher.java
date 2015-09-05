package infstudio.exoticpower.tileentity;

import cn.academy.energy.api.block.IWirelessReceiver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;

public class TileEntityPuncher extends TileEntityMachine implements IUpdatePlayerListBox, IInventory, IWirelessReceiver{

	public TileEntityPuncher() {
		this.tstack = new ItemStack[3];
		this.capacity = 100;
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public double getRequiredEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double injectEnergy(double amt) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double pullEnergy(double amt) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBandwidth() {
		// TODO Auto-generated method stub
		return 0;
	}

}
