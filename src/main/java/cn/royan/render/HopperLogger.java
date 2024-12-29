package cn.royan.render;

import cn.royan.network.Channels;
import cn.royan.network.NetWorkPacket;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static cn.royan.RaidInformation.server;

public class HopperLogger {
//    private static final List<HopperBlockEntityObject> hopperLoggers = new ArrayList<>();
//    public static void addServerLogger(BlockPos pos, HopperBlockEntity blockEntity){
//        HopperBlockEntityObject hopperBlockEntityObject = new HopperBlockEntityObject(pos,blockEntity.transferCooldown);
//        hopperLoggers.add(hopperBlockEntityObject);
//    }
//
//    public static void sendHopperCoolTime() {
//        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
//            NetWorkPacket.serverSend(player,Channels.clear,PacketByteBufs.empty());
//        }
//        PacketByteBuf buf = PacketByteBufs.create();
//        buf.writeCollection(hopperLoggers,(packetByteBuf,HopperBlockEntityObject) -> {
//            packetByteBuf.writeBlockPos(HopperBlockEntityObject.getPos());
//            packetByteBuf.writeInt(HopperBlockEntityObject.getCooldown());
//        });
//        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
//            NetWorkPacket.serverSend(player,Channels.hopper,buf);
//        }
//        hopperLoggers.clear();
//    }

//    private static final Map<BlockPos,Integer> hopperLoggers = new ConcurrentHashMap<>();
//    public static void addServerLogger(BlockPos pos, HopperBlockEntity blockEntity){
//        hopperLoggers.put(pos,blockEntity.transferCooldown);
//    }
//
//    public static void sendHopperCoolTime() {
//        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()){
//            NetWorkPacket.serverSend(player,Channels.clear,NetWorkPacket.createStringPacket(""));
//        }
//        for(Map.Entry<BlockPos,Integer> hopperLogger : hopperLoggers.entrySet()){
//            for (ServerPlayerEntity player : getPlayersNearBy(hopperLogger.getKey(),32)){
//                NetWorkPacket.serverSend(player,Channels.hopper,NetWorkPacket.createStringPacket(hopperLogger.getKey().toShortString() + ", " + hopperLogger.getValue()));
//            }
//        }
//        hopperLoggers.clear();
//    }

    public static List<ServerPlayerEntity> getPlayersNearBy(BlockPos blockPos,float distance){
        List<ServerPlayerEntity> pList = new ArrayList<>();
        for(ServerPlayerEntity player : server.getPlayerManager().getPlayerList()){
            double playerX = player.getX();
            double playerY = player.getY();
            double playerZ = player.getZ();

            double blockX = blockPos.getX() + 0.5;
            double blockY = blockPos.getY() + 0.5;
            double blockZ = blockPos.getZ() + 0.5;

            double distanceSquared = Math.pow(blockX - playerX, 2)
                    + Math.pow(blockY - playerY, 2)
                    + Math.pow(blockZ - playerZ, 2);

            if(distanceSquared < distance*distance){
                pList.add(player);
            }
        }
        return pList;
    }


}
