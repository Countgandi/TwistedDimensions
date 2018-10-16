package com.countgandi.com.game;

import java.awt.Rectangle;

import com.countgandi.com.Game;
import com.countgandi.com.game.dimensions.Dimension;

public class Camera {

	public static int x, y;
	private Handler handler;

	public Camera(Handler handler) {
		this.handler = handler;
	}

	public void tick() {
		x = (int) (handler.getPlayer().getX() + handler.getPlayer().getWidth() / 2 - Game.WIDTH / 2);
		y = (int) (handler.getPlayer().getY() + handler.getPlayer().getHeight() / 2 - Game.HEIGHT / 2);
		
		if(x < 0) {
			x = 0;
		}
		if(x > Dimension.WorldBounds - Game.WIDTH) {
			x = Dimension.WorldBounds - Game.WIDTH;
		}
		if(y < 0) {
			y = 0;
		}
		if(y > Dimension.WorldBounds - Game.HEIGHT) {
			y = Dimension.WorldBounds - Game.HEIGHT;
		}
	}

	public static Rectangle getCamera() {
		return new Rectangle(x, y, Game.WIDTH, Game.HEIGHT);
	}

}
