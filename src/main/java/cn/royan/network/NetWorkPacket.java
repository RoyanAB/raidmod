package cn.royan.network;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class NetWorkPacket {
    public static void serverSend(ServerPlayerEntity player, Identifier identifier, PacketByteBuf buf){
        ServerPlayNetworking.send(player,identifier,buf);
    }

    public static PacketByteBuf createStringPacket(String str) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(str);
        return buf;
    }

    public static String readStringPacket(PacketByteBuf buf) {
        return buf.readString(32767);
    }
}
