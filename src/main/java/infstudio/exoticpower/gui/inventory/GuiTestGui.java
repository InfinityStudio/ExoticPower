package infstudio.exoticpower.gui.inventory;

import infstudio.exoticpower.inventory.ContainerTestGui;
import infstudio.exoticpower.tileentity.TileEntityTestGui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiTestGui extends GuiContainer {

	private TileEntityTestGui tile;
	
	public GuiTestGui(InventoryPlayer playerInv, TileEntityTestGui tileEntity) {
		super(new ContainerTestGui(playerInv, tileEntity));
		// TODO Auto-generated constructor stub
		this.tile = tileEntity;
        this.doesGuiPauseGame();
	}

	@Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String s = StatCollector.translateToLocal("TitleTestGui");
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
	    this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }
	
	@Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		// TODO Auto-generated method stub
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("exoticpower", "textures/gui/container/testgui.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int b = tile.tableBurnTime; // 取得Tile内的燃料燃烧时间
        float maxBurnTime = tile.furnaceCookTime*1.0F;// 取得最大燃料燃烧时间，用float，不用的话得不出百分比
        if (b > 0 && maxBurnTime > 0) // 确定描绘的时机
        {
                // 描绘火焰图像
            this.drawTexturedModalRect(this.guiLeft + 81, this.guiTop + 37 + (int)(14 - 14 * ((float)b / maxBurnTime)), 176, (int)(14 - 14 * ((float)b / maxBurnTime)), 14, (int)(14 * ((float)b / maxBurnTime)));
        }
    }
	
}
