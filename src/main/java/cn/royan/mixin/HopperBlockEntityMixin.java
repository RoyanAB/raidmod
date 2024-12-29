package cn.royan.mixin;

import cn.royan.network.Channels;
import cn.royan.network.NetWorkPacket;
import cn.royan.render.HopperLogger;
import cn.royan.render.HopperRender;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.client.RunArgs;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

import static cn.royan.render.HopperLogger.getPlayersNearBy;

@Mixin(HopperBlockEntity.class)
public abstract class HopperBlockEntityMixin {

    @Shadow private int transferCooldown;
    @Inject(method = "serverTick", at = @At(value = "HEAD"))
    private static void serverTick(World world, BlockPos pos, BlockState state, HopperBlockEntity blockEntity, CallbackInfo ci) {
        for (ServerPlayerEntity player : getPlayersNearBy(pos,32)){
            NetWorkPacket.serverSend(player,Channels.hopper,NetWorkPacket.createStringPacket(pos.toShortString() + ", " + blockEntity.transferCooldown));
        }
        // HopperLogger.addServerLogger(pos,blockEntity);

    }
}
