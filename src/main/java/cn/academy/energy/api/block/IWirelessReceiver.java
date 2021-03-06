/**
 * Copyright (c) Lambda Innovation, 2013-2015
 * 本作品版权由Lambda Innovation所有。
 * http://www.li-dev.cn/
 * <p/>
 * This project is open-source, and it is distributed under
 * the terms of GNU General Public License. You can modify
 * and distribute freely as long as you follow the license.
 * 本项目是一个开源项目，且遵循GNU通用公共授权协议。
 * 在遵照该协议的情况下，您可以自由传播和修改。
 * http://www.gnu.org/licenses/gpl.html
 */
package cn.academy.energy.api.block;

/**
 * @author WeathFolD
 *
 */
public interface IWirelessReceiver extends IWirelessUser {

    double getRequiredEnergy();

    /**
     * Inject some amount of energy into the machine. ALWAYS positive
     * @return energy not injected
     */
    double injectEnergy(double amt);

    /**
     * Pull some energy out of the machine. ALWAYS positive
     * @param amt The amount
     * @return energy really pulled out.
     */
    double pullEnergy(double amt);

    /**
     * @return How much energy this receiver can retrieve each tick.
     */
    double getBandwidth();

}
