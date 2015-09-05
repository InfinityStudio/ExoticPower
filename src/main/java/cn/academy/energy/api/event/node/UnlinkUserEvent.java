/**
 * Copyright (c) Lambda Innovation, 2013-2015
 * 鏈綔鍝佺増鏉冪敱Lambda Innovation鎵�鏈夈��
 * http://www.li-dev.cn/
 * <p/>
 * This project is open-source, and it is distributed under
 * the terms of GNU General Public License. You can modify
 * and distribute freely as long as you follow the license.
 * 鏈」鐩槸涓�涓紑婧愰」鐩紝涓旈伒寰狦NU閫氱敤鍏叡鎺堟潈鍗忚銆�
 * 鍦ㄩ伒鐓ц鍗忚鐨勬儏鍐典笅锛屾偍鍙互鑷敱浼犳挱鍜屼慨鏀广��
 * http://www.gnu.org/licenses/gpl.html
 */
package cn.academy.energy.api.event.node;

import cn.academy.energy.api.block.IWirelessTile;
import cn.academy.energy.api.event.WirelessUserEvent;

/**
 * Fired whenever a wireless user is to be destroyed.</br>
 * NOTE: When a user is no longer available, it will be automatically unlinked.
 * @author WeathFolD
 */
public class UnlinkUserEvent extends WirelessUserEvent {

    public UnlinkUserEvent(IWirelessTile _tile) {
        super(_tile);
    }

}
