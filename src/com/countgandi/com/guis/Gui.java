package com.countgandi.com.guis;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import com.countgandi.com.net.client.ClientSideHandler;

public abstract class Gui {

	protected ClientSideHandler handler;
	protected int x, y;

	public Gui(ClientSideHandler handler) {
		this.handler = handler;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}
	
	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		
	}
	
	public void keyPressed(KeyEvent e) {
		
	}
	
	public void keyReleased(KeyEvent e) {
		
	}

}
