package cn.royan;

import cn.royan.network.Channels;
import cn.royan.network.NetWorkPacket;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static cn.royan.render.HopperLogger.sendHopperCoolTime;


public class RaidInformation implements ModInitializer {
	public static final String MOD_ID = "raidinformation";
	public static MinecraftServer server = null;

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ServerTickEvents.START_WORLD_TICK.register(this::serverTick);
		ServerLifecycleEvents.SERVER_STARTED.register(s -> server = s);
		LOGGER.info("Hello Fabric world!");
	}

	private void serverTick(ServerWorld serverWorld) {
		sendHopperCoolTime();
	}

}