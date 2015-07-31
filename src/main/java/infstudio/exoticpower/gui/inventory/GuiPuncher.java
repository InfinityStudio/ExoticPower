package infstudio.exoticpower.gui.inventory;

import infstudio.exoticpower.inventory.ContainerPuncher;
import infstudio.exoticpower.tileentity.TileEntityPuncher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiPuncher extends GuiMachine{

	private TileEntityPuncher tile;
	
	public GuiPuncher(InventoryPlayer playerInv, TileEntityPuncher tileEntity) {
		super(new ContainerPuncher(playerInv, tileEntity));
		this.tile = tileEntity;
		this.name = "Puncher";
        this.doesGuiPauseGame();
	}

	@Override
    protected void drawGuiContainerBackgroundLayer(float var1, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("exoticpower", "textures/gui/container/puncher.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
	
}
