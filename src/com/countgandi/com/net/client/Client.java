package com.countgandi.com.net.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.menus.ErrorOccurredScreen;
import com.countgandi.com.net.NetConstants;

public class Client {

	public static ArrayList<Entity> currentEntities;
	public static boolean running = false;
	public static String username = "YoHallo", ipAddress;
	private static Socket tcpSocket;
	private static DatagramSocket udpSocket;
	private static DatagramPacket packet;
	private ClientSideHandler handler;

	public Client(String ipAddress, String username, ClientSideHandler handler) {
		this.handler = handler;
		Client.username = username;
		Client.ipAddress = ipAddress;
		currentEntities = new ArrayList<Entity>();
	}
	
	public boolean connect() {
		try {
			tcpSocket = new Socket(ipAddress, NetConstants.TcpPort);
			udpSocket = new DatagramSocket(tcpSocket.getLocalSocketAddress());
			packet = new DatagramPacket(new byte[256], 256);
			running = true;
			handler.multiplayer = true;
			sendTcp("");
		} catch (Exception e) {
			return false;
		}
		new Thread() {
			@Override
			public void run() {
				while (running) {
					tcpRecieve();
				}
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				while (running) {
					udpRecieve();
				}
			}
		}.start();
		return true;
	}

	private void tcpRecieve() {
		
	}

	private void udpRecieve() {
		
	}

	public void sendTcp(String data) {
		try {
			tcpSocket.getOutputStream().write(("Client@" + username + data).getBytes());
		} catch (IOException e) {
			System.err.println("Could not send data to server through tcp...");
			handler.setMenu(new ErrorOccurredScreen(ErrorOccurredScreen.CONNECTION_ERROR1, handler));
		}	
	}

	public void sendUdp(String data) {
		try {
			data = "Client@" + username + data;
			packet.setData(data.getBytes());
			packet.setLength(data.length());
			udpSocket.send(packet);
		} catch (IOException e) {
			System.err.println("Could not send data to server through udp...");
			handler.setMenu(new ErrorOccurredScreen(ErrorOccurredScreen.CONNECTION_ERROR1, handler));
		}
	}

}
