package com.countgandi.com.game.animations;

import java.awt.Graphics;

import com.countgandi.com.Assets;
import com.countgandi.com.net.client.ClientSideHandler;

public class SwordSlashAnimation extends Animation {

	public SwordSlashAnimation(int x, int y, ClientSideHandler handler) {
		super(x, y, handler, 10);
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.Animations.swordSlash, x, y, 32, 8, null);
	}

}
