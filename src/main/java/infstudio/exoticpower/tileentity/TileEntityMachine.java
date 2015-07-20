package infstudio.exoticpower.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IChatComponent;

public class TileEntityMachine extends TileEntity implements IUpdatePlayerListBox, IInventory{
	
	private ItemStack tstack[];
	public int tableBurnTime = 0;
	public int furnaceCookTime = 0;
	
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
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
        this.tableBurnTime = compound.getShort("tableBurnTime");
        this.furnaceCookTime = compound.getShort("furnaceCookTime");
    }
	
	public void writeToNBT(NBTTagCompound compound)
    {
    	super.writeToNBT(compound);
    	compound.setShort("tableBurnTime", (short)this.tableBurnTime);
    	compound.setShort("furnaceCookTime", (short)this.furnaceCookTime);
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < this.tstack.length; ++var3)
        {
            if (this.tstack[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.tstack[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }
        compound.setTag("Items", var2);
    }

}
