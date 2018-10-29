package com.countgandi.com.guis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.countgandi.com.Game;
import com.countgandi.com.net.client.ClientSideHandler;

public class SettingsGui extends Gui {

	public SettingsGui(ClientSideHandler handler) {
		super(handler);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", 2, 50));
		g.drawString("Settings", 25, 75);
	}

}
