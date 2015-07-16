package infstudio.exoticpower.item;

import infstudio.exoticpower.ExoticPower;
import infstudio.exoticpower.api.tools.IToolWrench;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockLever;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.util.EnumFacing;

public class ItemWrench extends Item implements IToolWrench{

	private final Set<Class<? extends Block>> shiftRotations = new HashSet<Class<? extends Block>>();

	public ItemWrench() {
		super();
		this.setCreativeTab(ExoticPower.ept);
		setFull3D();
		setMaxStackSize(1);
		shiftRotations.add(BlockLever.class);
		shiftRotations.add(BlockButton.class);
		shiftRotations.add(BlockChest.class);
		setHarvestLevel("wrench", 0);
	}

	private boolean isShiftRotation(Class<? extends Block> class1) {
		for (Class<? extends Block> shift : shiftRotations) {
			if (shift.isAssignableFrom(class1)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
		Block block = worldIn.getBlockState(pos).getBlock();

		if (block == null) {
			return false;
		}

		if (playerIn.isSneaking() != isShiftRotation(block.getClass())) {
			return false;
		}

		if (block.rotateBlock(worldIn, pos, side)) {
			playerIn.swingItem();
			return !worldIn.isRemote;
		}
		return false;
	}

	@Override
	public boolean canWrench(EntityPlayer player, BlockPos bp) {
		return true;
	}

	@Override
	public void wrenchUsed(EntityPlayer player, BlockPos bp) {
		player.swingItem();
	}

	@Override
	public boolean doesSneakBypassUse(World world, BlockPos bp, EntityPlayer player) {
		return true;
	}
	
}
