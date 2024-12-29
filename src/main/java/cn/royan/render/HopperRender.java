package cn.royan.render;

import cn.royan.network.NetWorkPacket;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static cn.royan.RaidInformation.server;

public class HopperRender {
    public static final Map<BlockPos,Integer> coolTimeMaps = new ConcurrentHashMap<>();

    public static void render(MatrixStack matrices) {
        BlockPos pos;
        for (Map.Entry<BlockPos,Integer> coolTimeMap : coolTimeMaps.entrySet()) {
            pos = coolTimeMap.getKey();
            if (coolTimeMap.getValue() > 0) {
                InfoRender.drawString(matrices,pos,coolTimeMap.getValue()+"", Formatting.RED.getColorValue(),0.03F);
                coolTimeMaps.remove(coolTimeMap.getKey());
            }else if(coolTimeMap.getValue() == 0){
                InfoRender.drawString(matrices,pos,coolTimeMap.getValue()+"", Formatting.GREEN.getColorValue(),0.03F);
                coolTimeMaps.remove(coolTimeMap.getKey());
            }else coolTimeMaps.remove(pos);
        }

    }

    public static void getServerLogger(PacketByteBuf buf) {
//        coolTimeMaps.clear();
        String[] strings = NetWorkPacket.readStringPacket(buf).split(", ", 4);
        coolTimeMaps.put(new BlockPos(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2])), Integer.parseInt(strings[3]));
    }

    public static void clear(){
        coolTimeMaps.clear();
    }
}
