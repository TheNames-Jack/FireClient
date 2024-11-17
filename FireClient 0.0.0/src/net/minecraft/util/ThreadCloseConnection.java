package net.minecraft.util;

import net.minecraft.client.network.NetworkManager;

public class ThreadCloseConnection extends Thread {
	final NetworkManager networkManager; /* synthetic field */
	public ThreadCloseConnection(NetworkManager networkmanager) {
		networkManager = networkmanager;
	}

	public void run() {
		try {
			Thread.sleep(2000L);
			if(NetworkManager.isRunning(networkManager)) {
				NetworkManager.getWriteThread(networkManager).interrupt();
				networkManager.networkShutdown("disconnect.closed", new Object[0]);
			}
		}catch(Exception exception) {
			exception.printStackTrace();
		}
	}
}
