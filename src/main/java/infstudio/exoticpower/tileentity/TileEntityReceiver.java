package infstudio.exoticpower.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import cn.academy.energy.api.block.IWirelessReceiver;
import cn.annoreg.core.RegNetworkCall;
import cn.annoreg.core.Registrant;
import cn.annoreg.core.StorageOption;
import cn.annoreg.core.StorageOption.Data;

@Registrant
public class TileEntityReceiver extends TileEntityMachine implements IWirelessReceiver {
	
	int UPDATE_WAIT = 20;
	int updateTicker = 0;
	
	final double maxEnergy;
	final double bandwidth;
	
	public double energy;

	public TileEntityReceiver(String name, int invSize, double max, double bwidth) {
		super(name, invSize);
		maxEnergy = max;
		bandwidth = bwidth;
	}
	
	@Override
	public void update() {
		if(!getWorld().isRemote) {
			if(++updateTicker == UPDATE_WAIT) {
				updateTicker = 0;
				syncEnergy(energy);
			}
		}
	}
	
	@Override
	public double getRequiredEnergy() {
		return maxEnergy - energy;
	}

	@Override
	public double injectEnergy(double amt) {
		double req = maxEnergy - energy;
		double give = Math.min(amt, req);
		energy += give;
		
		return amt - give;
	}
	
	public double getEnergy() {
		return energy;
	}
	
	public double getMaxEnergy() {
		return maxEnergy;
	}

	@Override
	public double getBandwidth() {
		return bandwidth;
	}
	
	@Override
    public void readFromNBT(NBTTagCompound tag) {
    	super.readFromNBT(tag);
    	energy = tag.getDouble("energy");
    }
    
	@Override
    public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setDouble("energy", energy);
    }
	
	@RegNetworkCall(side = Side.CLIENT, thisStorage = StorageOption.Option.INSTANCE)
	private void syncEnergy(@Data Double e) {
		energy = e;
	}

	@Override
	public double pullEnergy(double amt) {
		double a = Math.min(amt, energy);
		energy -= a;
		return a;
	}

}
