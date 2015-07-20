package infstudio.exoticpower.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.util.StatCollector;

public class GuiMachine extends GuiContainer{

	public String name;
	
	public GuiMachine(Container p_i1072_1_) {
		super(p_i1072_1_);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks,
			int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		String s = StatCollector.translateToLocal(name);
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
	    this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
	}

}
