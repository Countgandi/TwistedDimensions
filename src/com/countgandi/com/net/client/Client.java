package com.countgandi.com.net.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.menus.ErrorOccurredScreen;
import com.countgandi.com.net.NetConstants;

public class Client {

	public static ArrayList<Entity> currentEntities;
	public static int dimension = 0;
	public static boolean running = false;
	public static String username = "YoHallo", ipAddress;
	private static Socket tcpSocket;
	private static DatagramSocket udpSocket;
	private ClientSideHandler handler;

	public Client(String ipAddress, String username, ClientSideHandler handler) {
		this.handler = handler;
		Client.username = username;
		Client.ipAddress = ipAddress;
		currentEntities = new ArrayList<Entity>();
	}

	public boolean connect() {
		try {
			tcpSocket = new Socket(ipAddress, NetConstants.Port);
			udpSocket = new DatagramSocket();
			udpSocket.connect(tcpSocket.getInetAddress(), NetConstants.Port);
			running = true;
			handler.multiplayer = true;
			sendTcp(""); // sends username
			byte[] bytes = new byte[256];
			tcpSocket.getInputStream().read(bytes);
			String s = new String(bytes).substring(7).trim();
			if (s.contains("dimension:")) {
				dimension = Integer.parseInt(s.substring("dimension:".length()));
			}
		} catch (Exception e) {
			handler.multiplayer = false;
			running = false;
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
					try {
						udpRecieve();
					} catch (IOException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException | ClassNotFoundException e) {
						System.err.println("Could not recieve udp data");
						handler.setMenu(new ErrorOccurredScreen(ErrorOccurredScreen.CONNECTION_ERROR2, handler));
						running = false;
					}
				}
			}
		}.start();
		return true;
	}

	private void tcpRecieve() {

	}

	private void udpRecieve() throws IOException, NumberFormatException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, ClassNotFoundException {
		DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
		udpSocket.receive(packet);
		System.out.println("Hi");
		String string = new String(packet.getData()).trim();

		if (string.startsWith("Server@ENTITYDATA$")) {
			ArrayList<Entity> entities = new ArrayList<Entity>();
			String[] strings = string.split("$")[1].split("#");
			for (int i = 0; i < strings.length; i++) {
				String[] ss = strings[i].split(",");

				@SuppressWarnings("unchecked")
				Entity entity = (Entity) ((Class<? extends Entity>) Class.forName(ss[0].trim())).getConstructors()[0].newInstance(Float.parseFloat(ss[2]), Float.parseFloat(ss[3]), handler);
				entity.setHealth(Integer.parseInt(ss[1]));
				entities.add(entity);

			}
			currentEntities = entities;
			return;
		}
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
			udpSocket.send(new DatagramPacket(data.getBytes(), data.length(), tcpSocket.getInetAddress(), NetConstants.Port));
		} catch (IOException e) {
			System.err.println("Could not send data to server through udp...");
			handler.setMenu(new ErrorOccurredScreen(ErrorOccurredScreen.CONNECTION_ERROR1, handler));
		}
	}

}
