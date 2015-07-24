package infstudio.exoticpower.tileentity;

import infstudio.exoticpower.api.energy.IEnergyProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;

public class TileEntityMachine extends TileEntity implements IUpdatePlayerListBox, IInventory, IEnergyProvider {
	
	public ItemStack tstack[];
	public int furnaceBurnTime;
	public int currentItemBurnTime;
    public int cookTime;
    public int totalCookTime;
    
    public int energy;
    public int capacity;
	public int maxExtract;
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IChatComponent getDisplayName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSizeInventory() {
		return tstack.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return tstack[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (this.tstack[index] != null)
        {
            ItemStack itemstack;

            if (this.tstack[index].stackSize <= count)
            {
                itemstack = this.tstack[index];
                this.tstack[index] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.tstack[index].splitStack(count);

                if (this.tstack[index].stackSize == 0)
                {
                    this.tstack[index] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		if (this.tstack[index] != null)
        {
            ItemStack itemstack = this.tstack[index];
            this.tstack[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.tstack[index] = stack;
        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getField(int id) {
		switch (id)
        {
            case 0:
                return this.furnaceBurnTime;
            case 1:
                return this.currentItemBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.totalCookTime;
            case 4:
            	return this.energy;
            default:
                return 0;
        }
	}

	@Override
	public void setField(int id, int value) {
		switch (id)
        {
            case 0:
                this.furnaceBurnTime = value;
                break;
            case 1:
                this.currentItemBurnTime = value;
                break;
            case 2:
                this.cookTime = value;
                break;
            case 3:
                this.totalCookTime = value;
            case 4:
            	this.energy = value;
        }
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
    {
		super.readFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.tstack = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.tstack.length)
            {
                this.tstack[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.furnaceBurnTime = compound.getShort("BurnTime");
        this.cookTime = compound.getShort("CookTime");
        this.totalCookTime = compound.getShort("CookTimeTotal");
        this.currentItemBurnTime = getItemBurnTime(this.tstack[1]);
        this.energy = compound.getInteger("Energy");
    }

	public static int getItemBurnTime(ItemStack itemStack) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
    {
		super.writeToNBT(compound);
        compound.setShort("BurnTime", (short)this.furnaceBurnTime);
        compound.setShort("CookTime", (short)this.cookTime);
        compound.setShort("CookTimeTotal", (short)this.totalCookTime);
        compound.setInteger("Energy", this.energy);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.tstack.length; ++i)
        {
            if (this.tstack[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.tstack[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        compound.setTag("Items", nbttaglist);
    }

	public boolean isBurning()
    {
        return this.furnaceBurnTime > 0;
    }

	@Override
	public boolean canConnectEnergy(EnumFacing from) {
		return true;
	}

	@Override
	public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
		int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
		if (!simulate) {
			energy -= energyExtracted;
		}
		return energyExtracted;
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
