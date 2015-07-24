package infstudio.exoticpower.gui.inventory;

import infstudio.exoticpower.inventory.ContainerElementGenerator;
import infstudio.exoticpower.tileentity.TileEntityElementGenerator;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiElementGenerator extends GuiMachine {

	private TileEntityElementGenerator tile;
	
	public GuiElementGenerator(InventoryPlayer playerInv, TileEntityElementGenerator tileEntity) {
		super(new ContainerElementGenerator(playerInv, tileEntity));
		this.tile = tileEntity;
		this.name = "Element Generator";
        this.doesGuiPauseGame();
	}

	@Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("exoticpower", "textures/gui/container/elementgenerator.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;
        if (TileEntityFurnace.isBurning(this.tile))
        {
            i1 = this.func_175382_i(13);
            this.drawTexturedModalRect(k + 61, l + 38 + 12 - i1, 176, 12 - i1, 18, i1 + 1);
        }
        i1 = this.tile.energy * 53 / this.tile.capacity;
        this.drawTexturedModalRect(k + 97, (int)l + 18 + 53 - i1, 176, (int)13 + 53 - i1, 18, (int)i1 + 1);
    }
	
	private int func_175382_i(int p_175382_1_)
    {
        int j = this.tile.getField(1);

        if (j == 0)
        {
            j = 200;
        }

        return this.tile.getField(0) * p_175382_1_ / j;
    }
	
}
