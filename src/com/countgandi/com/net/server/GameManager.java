package com.countgandi.com.net.server;

import com.countgandi.com.game.dimensions.Dimension;
import com.countgandi.com.game.entities.MPlayer;

public class GameManager {
	
	private Server server;
	
	public GameManager(Server server) {
		this.server = server;
		
		new Thread() {
			@Override
			public void run() {
				long startTime = System.currentTimeMillis();
				long lastSecond = 0;
				while (Server.serverRunning) {
					long elapsedTime = System.currentTimeMillis() - startTime;
					if (elapsedTime - 1000.0F >= lastSecond) {
						handleGame();
						lastSecond = elapsedTime;
					}
				}
			}
		}.start();
	}
	
	public void handleGame() {
		String playerData = "Server@PLAYERDATA$";
		for(int i = 0; i < server.clients.size(); i++) {
			Dimension dimension = server.getHandler().dimensionHandler.dimensions.get(server.clients.get(i).getPlayer().dimension);
			String s = "Server@ENTITYDATA$";
			for(int j = 0; j < dimension.entities.size(); j++) {
				s += dimension.entities.get(j).toString();
			}
			server.clients.get(i).sendUdpData(s);
			MPlayer player = server.clients.get(i).getPlayer();
			
			playerData += player.username + ",";
			playerData += player.getX() + ",";
			playerData += player.getY() + ",";
			playerData += player.getDirection() + "#";
		}
		for(int j = 0; j < server.clients.size(); j++) {
			server.clients.get(j).sendUdpData(playerData);
		}
	}

	public void sendUdpToAllClients(String data) {
		for(int i = 0; i < server.clients.size(); i++) {
			server.clients.get(i).sendUdpData(data);
		}
	}
	
	public void sendTcpToAllClients(String data) {
		for(int i = 0; i < server.clients.size(); i++) {
			server.clients.get(i).sendTcpData(data);
		}
	}

}
