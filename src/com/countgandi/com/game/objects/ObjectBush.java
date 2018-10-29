package com.countgandi.com.game.objects;

import java.awt.Graphics;

import com.countgandi.com.Assets;
import com.countgandi.com.net.Handler;

public class ObjectBush extends GameObject {

	public ObjectBush(int x, int y, Handler handler) {
		super(x, y, handler);
		width = 23 * 3;
		height = 15 * 3;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.Objects.OverworldBush, (int)x, (int)y, width, height, null);
	}

}
