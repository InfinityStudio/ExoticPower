package infstudio.exoticpower.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IChatComponent;

public class TileEntityTestGui extends TileEntity implements IUpdatePlayerListBox, IInventory{

	private ItemStack tstack[] = new ItemStack[3];
	public int tableBurnTime = 0;
	public int furnaceCookTime = 0;
	
	public void update() {
		if(tableBurnTime > 0)
        {
			// ȡ���޸�����Ʒ
			ItemStack repairItem = getStackInSlot(0);
			// ȡ���޸��õ���Ʒ
			ItemStack outputItem = getStackInSlot(1);
			// ȷ����ʼ�޸�������֮һ���޸���Ʒ�۲�Ϊ�գ����޸���Ʒ��Ϊ��
			if(repairItem != null && outputItem == null)
			{
				// �жϱ��޸�����Ʒ�Ƿ�Ϊ���߻�����
				if(repairItem.getItem() instanceof ItemTool || repairItem.getItem() instanceof ItemArmor) 
				{
					// �ж���Ʒ�Ƿ�Ҫ����
					if(repairItem.getItemDamage() > 0)
					{
						// �޸���Ʒ
						repairItem.setItemDamage(repairItem.getItemDamage() - 1);
					}
				}
			}
			// ����ȼ��ʱ��
			tableBurnTime -= 1;
        }
        else // û��ȼ�ϵ������
        {
        	// ����б��޸�����Ʒ
        	if(getStackInSlot(0) != null)
        	{
        		// ȡ��ȼ�ϲ۵���Ʒ
        		ItemStack burnItem = getStackInSlot(2);
        		// ȡ����Ʒ��ȼ��ֵ
        		int getBurnTime = getItemBurnTime(burnItem);
        		// �ж���Ʒ�Ƿ���ȼ��
        		if(getBurnTime > 0)
                {
        			furnaceCookTime = getBurnTime;
        			tableBurnTime = getBurnTime;
        			// ������Ʒ�ͼ���
        			if(burnItem.stackSize - 1 > 0)
        			{
        				burnItem.stackSize--;
        				setInventorySlotContents(2, burnItem);
        			}
        			else
        			{
        				setInventorySlotContents(2, null);
        			}
        		}
        	}
        }
    }

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
		// TODO Auto-generated method stub
		return tstack.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		this.tstack[index] = stack;
        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		// TODO Auto-generated method stub
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
        compound.setTag("ItemsPDG", var2);
    }

    public static int getItemBurnTime(ItemStack p_145952_0_)
    {
        if (p_145952_0_ == null)
        {
            return 0;
        }
        else
        {
            Item item = p_145952_0_.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
            {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.wooden_slab)
                {
                    return 150;
                }

                if (block.getMaterial() == Material.wood)
                {
                    return 300;
                }

                if (block == Blocks.coal_block)
                {
                    return 16000;
                }
            }

            if (item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemHoe && ((ItemHoe)item).getMaterialName().equals("WOOD")) return 200;
            if (item == Items.stick) return 100;
            if (item == Items.coal) return 1600;
            if (item == Items.lava_bucket) return 20000;
            if (item == Item.getItemFromBlock(Blocks.sapling)) return 100;
            if (item == Items.blaze_rod) return 2400;
            return net.minecraftforge.fml.common.registry.GameRegistry.getFuelValue(p_145952_0_);
        }
    }
    
}
