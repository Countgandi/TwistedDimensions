package com.countgandi.com.net.server;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.countgandi.com.Game;
import com.countgandi.com.engine.CanvasLabel;
import com.countgandi.com.engine.CanvasLabelList;
import com.countgandi.com.engine.CanvasPanel;
import com.countgandi.com.engine.CanvasTextArea;
import com.countgandi.com.engine.CanvasTextField;
import com.countgandi.com.net.NetConstants;

public class Server {

	public static CanvasPanel panel;
	public static CanvasTextArea console;
	public static CanvasLabelList players;
	private Canvas canvas;
	private static CanvasTextField commands;
	public static boolean serverRunning = false;
	public static ServerSocket tcpSocket;
	public static DatagramSocket udpSocket;
	public ArrayList<ClientConnection> clients;
	private ServerSideHandler handler;
	private GameManager gameManager;
	private PlayerDatabase playerDatabase;
	private int maxClients = 4;

	public Server() throws IOException, NoSuchMethodException, SecurityException {
		serverRunning = true;
		createWindow();
		System.setOut(new PrintStream(console.getOutputStream()));
		System.setErr(new PrintStream(console.getOutputErrStream()));
		System.out.println("Starting Server on port: " + NetConstants.Port);
		handler = new ServerSideHandler();
		playerDatabase = new PlayerDatabase(handler);
		clients = new ArrayList<ClientConnection>();
		System.out.println("Starting connections to the internet...");
		tcpSocket = new ServerSocket(NetConstants.Port);
		udpSocket = new DatagramSocket(NetConstants.Port);
		new ClientManager(this);
		gameManager = new GameManager(this);
		System.out.println("Server has started successfully...");
		startThreads();
	}

	private void createWindow() {
		JFrame frame = new JFrame(Game.TITLE + " Server");
		Dimension size = new Dimension(1024, 720);
		canvas = new Canvas();
		panel = new CanvasPanel(canvas);
		console = new CanvasTextArea(new Rectangle(410, 5, 600, 640), canvas);
		console.autoscroll = true;
		commands = new CanvasTextField(410, 655, 600, canvas);
		players = new CanvasLabelList(new Rectangle(5, 300, 390, 380), canvas);
		commands.setFontSize(24);

		panel.add(players);
		panel.add(console);
		panel.add(commands);
		panel.add(new CanvasLabel("Players Connected: ", 20, 290, 12, canvas));

		frame.setPreferredSize(size);
		frame.setMinimumSize(size);
		frame.setMaximumSize(size);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(canvas);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		canvas.createBufferStrategy(3);
		new Thread() {
			@Override
			public void run() {
				long startTime = System.currentTimeMillis();
				long lastSecond = 0;
				while (serverRunning) {
					long elapsedTime = System.currentTimeMillis() - startTime;
					if (elapsedTime - 1000.0F / 60.0F >= lastSecond) {
						Graphics g = canvas.getBufferStrategy().getDrawGraphics();

						g.setColor(Color.BLACK);
						g.fillRect(0, 0, (int) size.getWidth(), (int) size.getHeight());

						
						panel.render(g);
						panel.tick();
						if (clients != null) {
							((CanvasLabel) panel.get(3)).setText("Players Connected: " + clients.size() + "/" + maxClients);
						}

						g.dispose();
						canvas.getBufferStrategy().show();
						lastSecond = elapsedTime;
					}
				}
			}
		}.start();
	}

	public void waitForClients() {
		while (clients.size() <= maxClients) {
			try {
				clients.add(new ClientConnection(tcpSocket.accept(), this));
			} catch (IOException e) {
				System.err.println("Client could not join the server...");
				e.printStackTrace();
			}
		}
	}

	public void waitForUdpInput() throws IOException {
		DatagramPacket packet = new DatagramPacket(new byte[256], 256);
		udpSocket.receive(packet);
		String data = new String(packet.getData());
		for (int i = 0; i < clients.size(); i++) {
			if (data.startsWith("Client@user:" + clients.get(i).getPlayer().username))
				clients.get(i).recieveUdp(data);
		}
	}

	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	private void startThreads() {
		new Thread() {
			@Override
			public void run() {
				while (serverRunning) {
					waitForClients();
				}
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				while (serverRunning) {
					try {
						waitForUdpInput();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	public ServerSideHandler getHandler() {
		return handler;
	}

	public GameManager getGameManager() {
		return gameManager;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public PlayerDatabase getPlayerDatabase() {
		return playerDatabase;
	}

}
