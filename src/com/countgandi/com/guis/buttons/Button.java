package com.countgandi.com.guis.buttons;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import com.countgandi.com.net.client.ClientSideHandler;

public abstract class Button {
	
	protected ClientSideHandler handler;
	protected Rectangle rectangle;
	
	public Button(int x, int y, int width, int height, ClientSideHandler handler) {
		this.handler = handler;
		this.rectangle = new Rectangle(x, y, width, height);
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);

	public abstract void mouseMoved(MouseEvent e);
	public abstract void mouseReleased(MouseEvent e);
	public abstract void mousePressed(MouseEvent e);

}
