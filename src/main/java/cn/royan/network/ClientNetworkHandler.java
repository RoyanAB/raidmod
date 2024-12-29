package cn.royan.network;

import cn.royan.render.HopperRender;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import static cn.royan.render.HopperRender.coolTimeMaps;

public class ClientNetworkHandler {
    public static void loadServer(){
        ClientNetworkHandlerRegister();
    }

    private static void ClientNetworkHandlerRegister(){
        ClientPlayNetworking.registerGlobalReceiver(Channels.hopper, ((client, handler, buf, responseSender) -> {
//            coolTimeMaps.clear();
            HopperRender.getServerLogger(buf);
//            coolTimeMap.clear();
        }));
        ClientPlayNetworking.registerGlobalReceiver(Channels.clear,((client, handler, buf, responseSender) -> HopperRender.clear()));
    }
}
