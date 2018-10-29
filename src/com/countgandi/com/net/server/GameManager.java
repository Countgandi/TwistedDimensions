package com.countgandi.com.net.server;

public class GameManager {
	
	private Server server;
	
	public GameManager(Server server) {
		this.server = server;
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
