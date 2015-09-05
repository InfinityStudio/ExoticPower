/**
 * Copyright (c) Lambda Innovation, 2013-2015
 * 鏈綔鍝佺増鏉冪敱Lambda Innovation鎵�鏈夈��
 * http://www.li-dev.cn/
 *
 * This project is open-source, and it is distributed under 
 * the terms of GNU General Public License. You can modify
 * and distribute freely as long as you follow the license.
 * 鏈」鐩槸涓�涓紑婧愰」鐩紝涓旈伒寰狦NU閫氱敤鍏叡鎺堟潈鍗忚銆�
 * 鍦ㄩ伒鐓ц鍗忚鐨勬儏鍐典笅锛屾偍鍙互鑷敱浼犳挱鍜屼慨鏀广��
 * http://www.gnu.org/licenses/gpl.html
 */
package cn.academy.energy.api.event;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;
import cn.academy.energy.api.block.IWirelessTile;

/**
 * Base class of any wireless event.
 * All WirelessEvent should only be executed in SERVER side.
 * @author WeathFolD
 */
public class WirelessEvent extends Event {
    
    public final IWirelessTile tile;

    public WirelessEvent(IWirelessTile _tile) {
        tile = _tile;
    }
    
    public TileEntity getTileEntity() {
        return (TileEntity) tile;
    }
    
    public World getWorld() {
        return getTileEntity().getWorld();
    }

}
