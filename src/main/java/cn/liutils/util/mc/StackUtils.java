/**
 * Copyright (c) Lambda Innovation, 2013-2015
 * 本作品版权由Lambda Innovation所有。
 * http://www.li-dev.cn/
 *
 * This project is open-source, and it is distributed under 
 * the terms of GNU General Public License. You can modify
 * and distribute freely as long as you follow the license.
 * 本项目是一个开源项目，且遵循GNU通用公共授权协议。
 * 在遵照该协议的情况下，您可以自由传播和修改。
 * http://www.gnu.org/licenses/gpl.html
 */
package cn.liutils.util.mc;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class StackUtils {

	public static void dropItems(World world, int x, int y, int z, IInventory inv) {
		Random rand = new Random();

		for (int i = 0; i < inv.getSizeInventory(); ++i) {
			ItemStack stack = inv.getStackInSlot(i);
			if (stack != null && stack.stackSize > 0) {
				float rx = rand.nextFloat() * 0.8F + 0.1F;
				float ry = rand.nextFloat() * 0.8F + 0.1F;
				float rz = rand.nextFloat() * 0.8F + 0.1F;

				EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, stack.copy());

				if (stack.hasTagCompound()) {
					entityItem.getEntityItem().setTagCompound((NBTTagCompound) stack.getTagCompound().copy());
				}

				float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
				stack.stackSize = 0;
			}
		}
	}

	/**
	 * Return whether two stack's item instance and data are equal.
	 */
	public static boolean isStackDataEqual(ItemStack s1, ItemStack s2) {
		if(s1.getItem() != s1.getItem())
			return false;
		NBTTagCompound tag1 = s1.getTagCompound(), tag2 = s2.getTagCompound();
		if(tag1 == null || tag2 == null) {
			return tag1 == null && tag2 == null;
		}
		
		return tag1.equals(tag2);
	}
	
	public static NBTTagCompound loadTag(ItemStack stack) {
		NBTTagCompound ret = stack.getTagCompound();
		if(ret == null)
			stack.setTagCompound(new NBTTagCompound());
			ret = stack.getTagCompound();
		return ret;
	}
	
}
