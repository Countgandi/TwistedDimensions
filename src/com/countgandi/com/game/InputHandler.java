package com.countgandi.com.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.countgandi.com.game.entities.Player;
import com.countgandi.com.guis.HotbarGui;
import com.countgandi.com.net.client.ClientSideHandler;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

	private ClientSideHandler handler;

	public InputHandler(ClientSideHandler handler) {
		this.handler = handler;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		handler.getGame().menu.mousePressed(e);
		handler.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		handler.getGame().menu.mouseReleased(e);
		handler.mouseReleased(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_ESCAPE) {
			// handler.saveFiles();
			// System.exit(0);
		}
		if (key == KeyEvent.VK_SHIFT) {
			handler.getPlayer().beginSprint();
		}
		if(key == KeyEvent.VK_SPACE) {
			Player.holdingSpace = true;
		}

		handler.getGame().menu.keyPressed(e);
		handler.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_UP) {
			handler.getDimensionHandler().loadDimension(1);
		}
		if (key == KeyEvent.VK_DOWN) {
			handler.getDimensionHandler().loadDimension(0);
		}
		if (key == KeyEvent.VK_SHIFT) {
			handler.getPlayer().stopSprint();
		}
		if(key == KeyEvent.VK_SPACE) {
			Player.holdingSpace = false;
		}

		if (key == KeyEvent.VK_SPACE) {
			if (HotbarGui.currentItem != null) {
				HotbarGui.currentItem.onUse();
			}
		}

		handler.getGame().menu.keyReleased(e);
		handler.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		handler.getGame().menu.mouseDragged(e);
		handler.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		handler.getGame().menu.mouseMoved(e);
		handler.mouseMoved(e);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		handler.mouseWheelMoved(e);
	}

}
