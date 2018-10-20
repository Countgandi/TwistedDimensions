package com.countgandi.com.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.countgandi.com.Assets;
import com.countgandi.com.Game;
import com.countgandi.com.game.Handler;

public class MenuScreen extends Menu {

	private Rectangle playRectangle = new Rectangle(25, 150, 100, 25);

	private int mx, my, timer;

	public MenuScreen(Handler handler) {
		super(handler);
	}

	@Override
	public void tick() {
		timer += 4;
		if(timer > Game.WIDTH) {
			timer = -Game.WIDTH;
		}
		handler.tickGuis();
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.drawImage(Assets.Menu.splash2, timer, 100, Game.WIDTH, Game.HEIGHT, null);
		g.drawImage(Assets.Menu.splash1, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		
		g.drawImage(Assets.Menu.splash3, 60, 60, (int)(333 * 1.5f), (int)(22.5F * 1.5f), null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", 2, 50));

		g2d.draw(playRectangle);
		
		handler.renderGuis(g);
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (playRectangle.contains(mx, my)) {
			handler.getGame().menu = new GameScreen(handler);
		}
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
		mx = (int) (e.getX() / Toolkit.getDefaultToolkit().getScreenSize().getWidth() * Game.WIDTH);
		my = (int) (e.getY() / Toolkit.getDefaultToolkit().getScreenSize().getHeight() * Game.HEIGHT);
	}

}
