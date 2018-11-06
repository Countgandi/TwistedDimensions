package com.countgandi.com.net.server;

import java.io.IOException;
import java.net.DatagramPacket;

public class ClientManager {
	
	private Server server;
	
	public ClientManager(Server server) {
		this.server = server;
		
		new Thread() {
			@Override
			public void run() {
				while(Server.serverRunning) {
					try {
						recieveUdpPackets();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	public void recieveUdpPackets() throws IOException {
		DatagramPacket recievePacket = new DatagramPacket(new byte[1024], 1024);
		Server.udpSocket.receive(recievePacket);
		String s = new String(recievePacket.getData());
		for(int i = 0; i < server.clients.size(); i++) {
			ClientConnection client = server.clients.get(i);
			if(s.split("@")[0].equals(client.getPlayer().username)) {
				client.port = recievePacket.getPort();
				client.recieveUdp(s.substring(client.getPlayer().username.length() + 1));
				break;
			}
		}
	}

}
