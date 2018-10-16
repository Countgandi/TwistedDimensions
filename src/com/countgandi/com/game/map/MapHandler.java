package com.countgandi.com.game.map;

import java.awt.Graphics;

import com.countgandi.com.Assets;
import com.countgandi.com.Game;
import com.countgandi.com.game.Camera;
import com.countgandi.com.game.Handler;

public class MapHandler {

	private Handler handler;
	public Map mapLoaded;

	public MapHandler(Handler handler) {
		this.handler = handler;
	}

	public void loadMap(String src) {
		mapLoaded = new Map(src, handler);
	}

	public void tick() {

	}

	public void render(Graphics g) {
		int yIncrement = 96, xIncrement = 96;
		if (handler.dungeon != null) {
			for (int y = (int) (Camera.y - yIncrement - (Camera.y % yIncrement)); y < Camera.y + Game.HEIGHT + yIncrement; y += yIncrement) {
				for (int x = (int) (Camera.x - xIncrement - (Camera.x % xIncrement)); x < Camera.x + Game.WIDTH + xIncrement; x += xIncrement) {
					g.drawImage(Assets.tileSet[handler.dungeon.id], x, y, 96, 96, null);
				}
			}
		} else {
			for (int y = (int) (Camera.y - yIncrement - (Camera.y % yIncrement)); y < Camera.y + Game.HEIGHT + yIncrement; y += yIncrement) {
				for (int x = (int) (Camera.x - xIncrement - (Camera.x % xIncrement)); x < Camera.x + Game.WIDTH + xIncrement; x += xIncrement) {
					g.drawImage(Assets.tileSet[handler.getDimensionHandler().currentDimension.getGroundTexture()], x, y, 96, 96, null);
				}
			}
		}

	}

}