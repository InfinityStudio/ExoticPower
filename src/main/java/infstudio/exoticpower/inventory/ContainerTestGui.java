package infstudio.exoticpower.inventory;

import java.util.Iterator;

import infstudio.exoticpower.tileentity.TileEntityTestGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerTestGui extends Container{

	private int lastMaxBurnTime;
	private int lastTableBurnTime;
	private TileEntityTestGui tile;
	
	public ContainerTestGui(InventoryPlayer par1InventoryPlayer, TileEntityTestGui tileEntity) {
		this.tile = tileEntity;
		this.addSlotToContainer(new Slot(tileEntity, 0, 49, 19));
        this.addSlotToContainer(new Slot(tileEntity, 1, 112, 19));
        this.addSlotToContainer(new Slot(tileEntity, 2, 80, 54));
        int var3;
        for (var3 = 0; var3 < 3; ++var3)
        {
            for (int var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, var3, 8 + var3 * 18, 142));
        }
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	@Override
    public void addCraftingToCrafters(ICrafting par1iCrafting) {
            super.addCraftingToCrafters(par1iCrafting);
            par1iCrafting.sendProgressBarUpdate(this, 0, this.tile.tableBurnTime);
            par1iCrafting.sendProgressBarUpdate(this, 1, this.tile.furnaceCookTime);
    }
	
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
    	if (par1 == 0)
    	{
    		this.tile.tableBurnTime = par2;
    	}
    	if (par1 == 1)
    	{
    		this.tile.furnaceCookTime = par2;
    	}
    }
    @Override
    public void detectAndSendChanges()
    {
    	// TODO Auto-generated method stub
    	super.detectAndSendChanges();
    	Iterator var1 = this.crafters.iterator();
    	while (var1.hasNext())
    	{
    		ICrafting var2 = (ICrafting)var1.next();
    		if (this.lastTableBurnTime != this.tile.tableBurnTime)
    		{
    			var2.sendProgressBarUpdate(this, 0, this.tile.tableBurnTime);
    		}
    		if (this.lastMaxBurnTime  != this.tile.tableBurnTime)
    		{
    			var2.sendProgressBarUpdate(this, 1, this.tile.tableBurnTime);
    		}
    	}
    	this.lastTableBurnTime = this.tile.tableBurnTime;
    	this.lastMaxBurnTime = this.tile.furnaceCookTime;
    }
	
    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        return null;
    }
    
}
