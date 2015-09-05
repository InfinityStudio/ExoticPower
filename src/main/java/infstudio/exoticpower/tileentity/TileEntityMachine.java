package infstudio.exoticpower.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IChatComponent;

public class TileEntityMachine extends TileEntity implements IUpdatePlayerListBox, IInventory {

    public ItemStack tstack[];
    public String name;

    public TileEntityMachine(String name, int size) {
        this.name = name;
        tstack = new ItemStack[size];
    }

    public static int getItemBurnTime(ItemStack itemStack) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getName() {
        return this.name;
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
        if (this.tstack[index] != null) {
            ItemStack itemstack;

            if (this.tstack[index].stackSize <= count) {
                itemstack = this.tstack[index];
                this.tstack[index] = null;
                return itemstack;
            } else {
                itemstack = this.tstack[index].splitStack(count);

                if (this.tstack[index].stackSize == 0) {
                    this.tstack[index] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        if (this.tstack[index] != null) {
            ItemStack itemstack = this.tstack[index];
            this.tstack[index] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.tstack[index] = stack;
        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
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
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        NBTTagCompound tag = compound.getCompoundTag("inventory");
        for (int i = 0; i < tstack.length; ++i) {
            String name = "" + i;
            if (tag.hasKey(name)) {
                tstack[i] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag(name));
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        NBTTagCompound tag = new NBTTagCompound();
        for (int i = 0; i < tstack.length; ++i) {
            if (tstack[i] != null) {
                NBTTagCompound tag2 = new NBTTagCompound();
                tstack[i].writeToNBT(tag2);
                tag.setTag("" + i, tag2);
            }
        }

        compound.setTag("inventory", tag);
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

}
