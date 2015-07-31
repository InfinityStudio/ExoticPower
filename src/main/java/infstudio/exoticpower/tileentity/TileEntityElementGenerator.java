package infstudio.exoticpower.tileentity;

import infstudio.exoticpower.api.energy.IEnergyProvider;
import infstudio.exoticpower.item.ItemEnergyContainer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumFacing;

public class TileEntityElementGenerator extends TileEntityMachine implements IEnergyProvider{
	
	public TileEntityElementGenerator() {
		this.tstack = new ItemStack[2];
		this.capacity = 500;
	}
	
	@Override
	public void update() {
		if (this.isBurning() && this.energy < this.capacity)
        {
            --this.furnaceBurnTime;
            ++this.energy;
        }
		else
		{
			if (this.energy < this.capacity)
			{
				ItemStack burnItem = this.getStackInSlot(1);
				int getBurnTime = this.getItemBurnTime(burnItem);
				if (getBurnTime > 0)
				{
					this.furnaceBurnTime = getBurnTime;
	    			this.currentItemBurnTime = getBurnTime;
	    			if(burnItem.stackSize - 1 > 0)
	    			{
	    				burnItem.stackSize--;
	    				setInventorySlotContents(1, burnItem);
	    			}
	    			else
	    			{
	    				setInventorySlotContents(1, null);
	    			}
				}
			}
		}
		
		ItemStack energyItem = this.getStackInSlot(0);
		if (energyItem != null) {
			if (energyItem.getItem() != null && energyItem.getItem() instanceof ItemEnergyContainer && this.energy > 0 && energyItem.getItemDamage() > 0) {
				--this.energy;
				energyItem.setItemDamage(energyItem.getItemDamage() - 1);
			}
		}
	}
	
	public static int getItemBurnTime(ItemStack p_145952_0_) {
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
