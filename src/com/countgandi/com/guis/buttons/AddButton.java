package com.countgandi.com.guis.buttons;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import com.countgandi.com.Assets;
import com.countgandi.com.Game;
import com.countgandi.com.game.SkillHandler;
import com.countgandi.com.net.client.ClientSideHandler;

public class AddButton extends Button {

	private boolean over = false, down = false;
	private String string;

	public AddButton(int x, int y, String s, ClientSideHandler handler) {
		super(x, y, 7, 7, handler);
		this.string = s;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		if (over && !down) {
			g.drawImage(Assets.Buttons.addButtonLit, rectangle.x, rectangle.y, null);
		} else if(!over && !down){
			g.drawImage(Assets.Buttons.addButton, rectangle.x, rectangle.y, null);
		} else {
			g.drawImage(Assets.Buttons.addButtonPressed, rectangle.x, rectangle.y, null);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point m = new Point((int) (e.getX() / Toolkit.getDefaultToolkit().getScreenSize().getWidth() * Game.WIDTH),
				(int) (e.getY() / Toolkit.getDefaultToolkit().getScreenSize().getHeight() * Game.HEIGHT));
		if (rectangle.contains(m)) {
			over = true;
		} else {
			over = false;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point m = new Point((int) (e.getX() / Toolkit.getDefaultToolkit().getScreenSize().getWidth() * Game.WIDTH),
				(int) (e.getY() / Toolkit.getDefaultToolkit().getScreenSize().getHeight() * Game.HEIGHT));
		if (rectangle.contains(m)) {
			down = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Point m = new Point((int) (e.getX() / Toolkit.getDefaultToolkit().getScreenSize().getWidth() * Game.WIDTH),
				(int) (e.getY() / Toolkit.getDefaultToolkit().getScreenSize().getHeight() * Game.HEIGHT));
		if (rectangle.contains(m)) {
			SkillHandler.upskill(string, handler);
		}
		down = false;
	}

}
