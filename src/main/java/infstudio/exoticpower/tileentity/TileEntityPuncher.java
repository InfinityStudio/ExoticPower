package infstudio.exoticpower.tileentity;

import infstudio.exoticpower.api.energy.IEnergyReceiver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;

public class TileEntityPuncher extends TileEntityMachine implements IUpdatePlayerListBox, IInventory, IEnergyReceiver{

	public TileEntityPuncher() {
		this.tstack = new ItemStack[3];
		this.capacity = 100;
	}
	
	@Override
	public void update() {
		
	}
	
	@Override
	public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
		int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));
		if (!simulate) {
			energy += energyReceived;
		}
		return energyReceived;
	}

	@Override
	public boolean canConnectEnergy(EnumFacing from) {
		return true;
	}

	@Override
	public int getEnergyStored(EnumFacing from) {
		return this.energy;
	}

	@Override
	public int getMaxEnergyStored(EnumFacing from) {
		return this.capacity;
	}

}
