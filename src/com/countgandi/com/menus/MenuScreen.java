package com.countgandi.com.menus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.countgandi.com.Assets;
import com.countgandi.com.Game;
import com.countgandi.com.engine.CanvasButton;
import com.countgandi.com.engine.CanvasPanel;
import com.countgandi.com.net.client.ClientSideHandler;

public class MenuScreen extends Menu {

	private CanvasPanel panel;

	private int timer;

	public MenuScreen(ClientSideHandler handler) {
		super(handler);
		panel = new CanvasPanel(handler.getGame());
		
		CanvasButton playButton = new CanvasButton(new Rectangle(Game.WIDTH / 2 - 58, 150, 115, 25), 20, "Singleplayer", handler.getGame());
		CanvasButton multiplayerButton = new CanvasButton(new Rectangle(Game.WIDTH / 2 - 50, 200, 100, 25), 20, "Multiplayer", handler.getGame());
		CanvasButton exitButton = new CanvasButton(new Rectangle(Game.WIDTH / 2 - 20, 250, 40, 25), 20, "Exit", handler.getGame());
		
		panel.add(playButton);
		panel.add(multiplayerButton);
		panel.add(exitButton);
		
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handler.setMenu(new GameScreen(handler));
			}
		});
		multiplayerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handler.setMenu(new MultiplayerLoginScreen(handler));
			}
		});
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
	}

	@Override
	public void tick() {
		timer += 4;
		if(timer > Game.WIDTH) {
			timer = -Game.WIDTH;
		}
		handler.tickGuis();
		panel.tick();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.drawImage(Assets.Menu.splash2, timer, 100, Game.WIDTH, Game.HEIGHT, null);
		g.drawImage(Assets.Menu.splash1, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		
		g.drawImage(Assets.Menu.splash3, 60, 60, (int)(333 * 1.5f), (int)(22.5F * 1.5f), null);
		
		panel.render(g);
		
		handler.renderGuis(g);
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	
	@Override
	public void closeMenu() {
		panel.dispose();
	}

}
