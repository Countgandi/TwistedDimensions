package com.countgandi.com.menus;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.countgandi.com.engine.CanvasButton;
import com.countgandi.com.engine.CanvasLabel;
import com.countgandi.com.engine.CanvasPanel;
import com.countgandi.com.engine.CanvasTextField;
import com.countgandi.com.net.client.Client;
import com.countgandi.com.net.client.ClientSideHandler;

public class MultiplayerLoginScreen extends Menu {

	private CanvasPanel panel;
	private CanvasTextField username, ipAddress;
	private CanvasLabel ipLabel, usernameLabel, titleLabel;
	private CanvasButton connectButton, cancelButton;

	public MultiplayerLoginScreen(ClientSideHandler handler) {
		super(handler);

		panel = new CanvasPanel(handler.getGame());
		username = new CanvasTextField(240, 178, 250, handler.getGame());
		username.setFontSize(24);
		ipAddress = new CanvasTextField(240, 210, 250, handler.getGame());
		ipAddress.setFontSize(24);

		titleLabel = new CanvasLabel("Connect to a Server", 100, 100, 50, handler.getGame());
		usernameLabel = new CanvasLabel("Username: ", 100, 200, 24, handler.getGame());
		ipLabel = new CanvasLabel("Ip Address: ", 100, 232, 24, handler.getGame());

		connectButton = new CanvasButton(new Rectangle(150, 250, 120, 50), 24, "Connect", handler.getGame());
		cancelButton = new CanvasButton(new Rectangle(350, 250, 100, 50), 24, "Cancel", handler.getGame());

		panel.add(titleLabel);
		panel.add(usernameLabel);
		panel.add(username);
		panel.add(ipLabel);
		panel.add(ipAddress);
		panel.add(connectButton);
		panel.add(cancelButton);

		connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(username.getText().length() > 4 && (ipAddress.getText().equals("localhost") || ipAddress.getText().length() > 7)) {
					Client client = new Client(ipAddress.getText().trim(), username.getText().trim(), handler);
					if(client.connect()) {
						handler.setMenu(new GameScreen(client, handler));
					} else {
						handler.setMenu(new ErrorOccurredScreen(ErrorOccurredScreen.CONNECTION_ERROR1, handler));
					}
				}
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handler.setMenu(new MenuScreen(handler));
			}
		});

	}

	@Override
	public void tick() {
		panel.tick();
	}

	@Override
	public void render(Graphics g) {
		panel.render(g);
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
	
	public void closeMenu() {
		panel.dispose();
	}

}
