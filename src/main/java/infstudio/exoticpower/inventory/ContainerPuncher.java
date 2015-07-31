package infstudio.exoticpower.inventory;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import infstudio.exoticpower.tileentity.TileEntityPuncher;

public class ContainerPuncher extends ContainerMachine{

	private TileEntityPuncher tile;
	private int field_178152_f;
    private int field_178153_g;
    private int field_178154_h;
    private int field_178155_i;
    private int field_178156_j;
	
	public ContainerPuncher(InventoryPlayer par1InventoryPlayer, TileEntityPuncher tileEntity){
		this.tile = tileEntity;
		this.addSlotToContainer(new Slot(tileEntity, 0, 62, 19));
        this.addSlotToContainer(new Slot(tileEntity, 1, 62, 54));
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
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {
		this.tile.setField(par1, par2);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.field_178152_f != this.tile.getField(2))
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tile.getField(2));
            }

            if (this.field_178154_h != this.tile.getField(0))
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tile.getField(0));
            }

            if (this.field_178155_i != this.tile.getField(1))
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tile.getField(1));
            }

            if (this.field_178153_g != this.tile.getField(3))
            {
                icrafting.sendProgressBarUpdate(this, 3, this.tile.getField(3));
            }
            
            if (this.field_178156_j != this.tile.getField(4))
            {
            	icrafting.sendProgressBarUpdate(this, 4, this.tile.getField(4));
            }
        }

        this.field_178152_f = this.tile.getField(2);
        this.field_178154_h = this.tile.getField(0);
        this.field_178155_i = this.tile.getField(1);
        this.field_178153_g = this.tile.getField(3);
        this.field_178156_j = this.tile.getField(4);
	}
}
