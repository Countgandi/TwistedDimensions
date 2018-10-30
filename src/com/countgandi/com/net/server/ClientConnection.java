package com.countgandi.com.net.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Socket;

import com.countgandi.com.engine.CanvasLabel;
import com.countgandi.com.game.entities.MPlayer;
import com.countgandi.com.net.NetConstants;

public class ClientConnection {
	
	private DatagramPacket packet;
	private MPlayer player;
	private Socket socket;
	private Server server;
	private CanvasLabel label;
	
	public ClientConnection(Socket socket, Server server) {
		this.server = server;
		this.socket = socket;
		packet = new DatagramPacket("Error".getBytes(), 5, socket.getInetAddress(), NetConstants.UdpPort);
		try {
			byte[] bytes = new byte[256];
			socket.getInputStream().read(bytes);
			String s = new String(bytes);
			s = s.substring(7).trim();
			player = new MPlayer(s, server.getHandler());
			label = new CanvasLabel(player.username, 0, 0, 0, server.getCanvas());
			Server.players.add(label);
			System.out.println(player.username + " has joined the game.");
		} catch (IOException e) {
			System.err.println("Client could not join, an error has occured when getting the clients usernamme...");
			disconnect();
			e.printStackTrace();
		}
		new Thread() {
			@Override
			public void run() {
				while(Server.serverRunning) {
					recieveTcp();
				}
			}
		}.start();
	}
	
	private void disconnect() {
		Server.players.remove(label);
		server.clients.remove(this);
		System.out.println(player.username + " has left the game.");
	}

	public void recieveUdp(String data) {
		data = data.trim().split("@")[1];
		if(!data.startsWith(player.username)) {
			return;
		}
		String[] strings = data.split("$");
		String sendData = "Server@" + player.username;
		for(int i = 0; i < strings.length; i++) {
			String[] s = strings[i].split(":");
			sendData += "$" + strings[i];
			if(s[0].startsWith("health")) {
				player.setHealth(Integer.parseInt(s[1]));
			} else if(s[0].startsWith("maxhealth")) {
				player.maxHealth = Integer.parseInt(s[1]);
			} else if(s[0].startsWith("maxEnergy")) {
				player.maxEnergy = Integer.parseInt(s[1]);
			} else if(s[0].startsWith("level")) {
				player.level = Integer.parseInt(s[1]);
			} else if(s[0].startsWith("x")) {
				player.setX(Float.parseFloat(s[1]));
			} else if(s[0].startsWith("y")) {
				player.setX(Float.parseFloat(s[1]));
			} else if(s[0].startsWith("dimension")) {
				player.setHealth(Integer.parseInt(s[1]));
			} else if(s[0].startsWith("armorStats")) {
				String[] ns = s[1].split("&");
				int[] armorStats = new int[ns.length];
				for(int k = 0; k < armorStats.length; k++) {
					armorStats[k] = Integer.parseInt(ns[k]);
				}
				player.armorStats = armorStats;
			} else if(s[0].startsWith("baseDamage")) {
				String[] ns = s[1].split("&");
				int[] baseDamage = new int[ns.length];
				for(int k = 0; k < baseDamage.length; k++) {
					baseDamage[k] = Integer.parseInt(ns[k]);
				}
				player.baseDamage = baseDamage;
			}
		}
		server.getGameManager().sendUdpToAllClients(sendData);
	}
	
	private void recieveTcp() {
		
	}
	
	public void sendUdpData(String data) {
		try {
			packet.setLength(data.length());
			packet.setData(data.getBytes());
			Server.udpSocket.send(packet);
		} catch (IOException e) {
			System.err.println("Could not write to socket with data: " + data);
			disconnect();
			e.printStackTrace();
		}
	}
	
	public void sendTcpData(String data) {
		try {
			socket.getOutputStream().write(data.getBytes());
		} catch (IOException e) {
			System.err.println("Could not write to socket with data: " + data);
			disconnect();
			e.printStackTrace();
		}
	}

	public MPlayer getPlayer() {
		return player;
	}

}
