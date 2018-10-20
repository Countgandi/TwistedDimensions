package com.countgandi.com.game.animations;

import java.awt.Graphics;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;

public class SwordSlashAnimation extends Animation {

	public SwordSlashAnimation(int x, int y, Handler handler) {
		super(x, y, handler, 10);
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.Animations.swordSlash, x, y, 32, 8, null);
	}

}
