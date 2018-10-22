package com.countgandi.com.guis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.countgandi.com.Game;
import com.countgandi.com.game.Handler;

public class LoadingScreenGui extends Gui {

	public static boolean isLoading = false;
	private int dotAmount = 0, timer;

	public LoadingScreenGui(Handler handler) {
		super(handler);
		isLoading = true;
	}

	@Override
	public void tick() {
		timer ++;
		if(timer > 60) {
			dotAmount++;
			timer = 0;
		}
		if(dotAmount > 3) {
			dotAmount = 0;
		}
		if(!isLoading) {
			handler.removeGui(this);
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", 2, 50));
		g.drawString("Loading", 100, 200);
		for(int i = 0; i < dotAmount; i++) {
			g.drawString(".", 280 + i * 15, 200);
		}
	}

}
