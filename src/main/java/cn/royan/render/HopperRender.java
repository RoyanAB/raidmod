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
    public static final List<HopperEntityObject> coolTimeMaps = new CopyOnWriteArrayList<>();

    public static void render(MatrixStack matrices) {
        BlockPos pos;
        for (HopperEntityObject hopperEntityObject: coolTimeMaps) {
            pos = hopperEntityObject.getPos();
            if (hopperEntityObject.getCoolDown() > 0) {
                InfoRender.drawString(matrices,pos,hopperEntityObject.getCoolDown()+"", Formatting.RED.getColorValue(),0.03F);
            }else if(hopperEntityObject.getCoolDown() == 0){
                InfoRender.drawString(matrices,pos,"0", Formatting.GREEN.getColorValue(),0.03F);
            }else coolTimeMaps.remove(pos);
        }
        coolTimeMaps.clear();
    }

    public static void getServerLogger(PacketByteBuf buf) {
        List<HopperEntityObject> temp = buf.readCollection(ArrayList::new,packetByteBuf -> new HopperEntityObject(
                packetByteBuf.readBlockPos(),
                packetByteBuf.readInt()
        ));
        coolTimeMaps.addAll(temp);

    }
}
