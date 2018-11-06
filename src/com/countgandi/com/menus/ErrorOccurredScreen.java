package com.countgandi.com.menus;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.countgandi.com.engine.CanvasButton;
import com.countgandi.com.engine.CanvasLabel;
import com.countgandi.com.net.client.ClientSideHandler;

public class ErrorOccurredScreen extends Menu {
	
	public static final String CONNECTION_ERROR2 = "Server Disconnected...";
	public static final String CONNECTION_ERROR1 = "Could not connect to server...";
	public static final String DEFAULT_ERROR = "An Error has occurred...";
	private CanvasButton returnButton;
	private CanvasLabel label;

	public ErrorOccurredScreen(String text, ClientSideHandler handler) {
		super(handler);
		label = new CanvasLabel(text, 100, 100, 24, handler.getGame());
		returnButton = new CanvasButton(new Rectangle(100, 200, 100, 30), 20, "Return", handler.getGame());
		returnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(text.equals(DEFAULT_ERROR)) {
					handler.setMenu(new MenuScreen(handler));
				} else if(text.equals(CONNECTION_ERROR1)) {
					handler.setMenu(new MultiplayerLoginScreen(handler));
				}
			}
		});
	}

	@Override
	public void tick() {
		returnButton.tick();
		label.tick();
	}

	@Override
	public void render(Graphics g) {
		returnButton.render(g);
		label.render(g);
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
		returnButton.dispose();
		label.dispose();
	}

}
