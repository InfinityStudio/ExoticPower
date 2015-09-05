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
package cn.academy.energy.internal;

import cn.academy.energy.api.block.IWirelessGenerator;
import cn.academy.energy.api.block.IWirelessReceiver;
import cn.academy.energy.api.event.WirelessUserEvent.UserType;
import cn.academy.energy.api.event.node.LinkUserEvent;
import cn.academy.energy.api.event.node.UnlinkUserEvent;
import cn.academy.energy.api.event.wen.*;
import cn.academy.energy.internal.VBlocks.VNGenerator;
import cn.academy.energy.internal.VBlocks.VNReceiver;
import cn.academy.energy.internal.VBlocks.VWNode;
import cn.annoreg.core.RegEventHandler;
import cn.annoreg.core.Registrant;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;

/**
 * @author WeAthFolD
 *
 */
@Registrant
public class WirelessSystem {

    @RegEventHandler
    public static WirelessSystem INSTANCE = new WirelessSystem();

    private WirelessSystem() {
        //AcademyCraft.log.info("AcademyCraft Wireless Energy System is loading...");
    }

    @SubscribeEvent
    public void onServerTick(ServerTickEvent event) {
        //System.out.println("---");
        for (WorldServer ws : MinecraftServer.getServer().worldServers) {
            if (ws.getClass() == WorldServer.class) //FIX: WorldServerMulti instance cause lookup loss.
                //TODO: Investigate why.
                WiWorldData.get(ws).tick();
        }
        //System.out.println("---");
    }

    @SubscribeEvent
    public void onCreateNet(CreateNetworkEvent event) {
        WiWorldData data = WiWorldData.get(event.getWorld());
        if (!data.createNetwork(event.mat, event.ssid, event.pwd)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onDestroyNet(DestroyNetworkEvent event) {
        WiWorldData data = WiWorldData.get(event.getWorld());
        WirelessNet net = data.getNetwork(event.mat);
        if (net != null) net.dispose();
    }

    @SubscribeEvent
    public void changePass(ChangePassEvent event) {
        WiWorldData data = WiWorldData.get(event.getWorld());
        WirelessNet net = data.getNetwork(event.mat);
        if (net == null || !net.resetPassword(event.oldpwd, event.pwd)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void linkNode(LinkNodeEvent event) {
        WiWorldData data = WiWorldData.get(event.getWorld());
        WirelessNet net = data.getNetwork(event.ssid);

        if (net == null || !net.addNode(new VWNode(event.node), event.pwd))
            event.setCanceled(true);
    }

    @SubscribeEvent
    public void unlinkNode(UnlinkNodeEvent event) {
        WiWorldData data = WiWorldData.get(event.getWorld());
        WirelessNet net = data.getNetwork(event.node);

        if (net != null)
            net.removeNode(new VWNode(event.node));
    }

    @SubscribeEvent
    public void linkUser(LinkUserEvent event) {
        WiWorldData data = WiWorldData.get(event.getWorld());
        NodeConn conn = data.getNodeConnection(event.node);

        if (event.type == UserType.GENERATOR) {
            if (!conn.addGenerator(new VNGenerator(event.getAsGenerator())))
                event.setCanceled(true);
        } else { //RECEIVER
            if (!conn.addReceiver(new VNReceiver(event.getAsReceiver())))
                event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void unlinkUser(UnlinkUserEvent event) {
        WiWorldData data = WiWorldData.get(event.getWorld());

        if (event.type == UserType.GENERATOR) {
            IWirelessGenerator gen = event.getAsGenerator();
            NodeConn conn = data.getNodeConnection(gen);
            conn.removeGenerator(new VNGenerator(gen));
        } else { //RECEIVER
            IWirelessReceiver rec = event.getAsReceiver();
            NodeConn conn = data.getNodeConnection(rec);
            conn.removeReceiver(new VNReceiver(rec));
        }
    }

    private void debug(Object msg) {
        //AcademyCraft.log.info("WirelessSys: " + msg);
    }

}
