package infstudio.exoticpower.block;

import infstudio.exoticpower.ExoticPower;
import infstudio.exoticpower.api.energy.IEnergyConnection;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockPipe extends Block implements IEnergyConnection {
	
	protected BlockPipe() {
		super(Material.glass);
		this.setCreativeTab(ExoticPower.ept);
		this.setHardness(0.5f);
		this.setStepSound(soundTypeGlass);
		EPBlocks.blockList.add(this);
	}

	@Override
	public boolean canConnectEnergy(EnumFacing from) {
		return true;
	}
	
}
