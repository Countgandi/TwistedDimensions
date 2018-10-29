package com.countgandi.com;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import com.countgandi.com.game.FileHandler;
import com.countgandi.com.game.InputHandler;
import com.countgandi.com.menus.Menu;
import com.countgandi.com.menus.MenuScreen;
import com.countgandi.com.net.client.ClientSideHandler;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private static Thread thread;
	private static boolean running = false;
	public static int WIDTH = 640, HEIGHT = (int) ((float) WIDTH * 9.0F / 16.0F);
	public static String TITLE = "Twisted Dimensions";
	public static Window window;
	public static boolean render = true;

	public Menu menu;
	private ClientSideHandler handler;
	private InputHandler inputs;

	public Game() {
		handler = new ClientSideHandler(this);
		new FileHandler("world");
		inputs = new InputHandler(handler);
		menu = new MenuScreen(handler);

		this.addKeyListener(inputs);
		this.addMouseListener(inputs);
		this.addMouseMotionListener(inputs);
		this.addMouseWheelListener(inputs);
		window = new Window(WIDTH, HEIGHT, 0, TITLE, this);

		start();
	}

	public void tick() {
		menu.tick();
	}

	public void render() {
		if (render) {
			if (this.getBufferStrategy() == null) {
				this.createBufferStrategy(3);
			}
			Graphics g = this.getBufferStrategy().getDrawGraphics();
			Graphics2D g2d = (Graphics2D) g;
			g2d.scale(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / WIDTH, Toolkit.getDefaultToolkit().getScreenSize().getHeight() / HEIGHT);

			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH, HEIGHT);

			menu.render(g);

			g.dispose();
			this.getBufferStrategy().show();
		}
	}

	public static void main(String[] args) {
		new Game();
	}

	public synchronized void start() {
		if (running) {
			return;
		}
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}

		}
	}

	public static int clamp(double x, int min, int max) {
		if (x >= max) {
			return (int) (x = max);
		} else if (x <= min) {
			return (int) (x = min);
		} else {
			return (int) x;
		}
	}

}
