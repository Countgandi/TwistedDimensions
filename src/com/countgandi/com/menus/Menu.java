package com.countgandi.com.menus;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.countgandi.com.net.client.ClientSideHandler;

public abstract class Menu {

	protected ClientSideHandler handler;

	public Menu(ClientSideHandler handler) {
		this.handler = handler;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract void mousePressed(MouseEvent e);

	public abstract void mouseReleased(MouseEvent e);

	public abstract void keyPressed(KeyEvent e);

	public abstract void keyReleased(KeyEvent e);

	public abstract void mouseDragged(MouseEvent e);

	public abstract void mouseMoved(MouseEvent e);

	public abstract void closeMenu();

}
