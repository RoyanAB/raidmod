package cn.royan;

import net.fabricmc.api.ClientModInitializer;

import static cn.royan.network.ClientNetworkHandler.loadServer;

public class RaidInformationClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		loadServer();
		System.out.println("1");
	}
}