package com.countgandi.com.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.countgandi.com.Assets;
import com.countgandi.com.net.Handler;

public class ObjectTree extends GameObject {

	private BufferedImage img;

	public ObjectTree(int x, int y, int dimension, Handler handler) {
		super(x, y, handler);
		int id = dimension;
		if (id == 0) {
			img = Assets.Objects.OverworldTree;
		} else if (id == 1) {
			img = Assets.Objects.SavannahTree;
		} else {
			img = Assets.Objects.OverworldTree;
		}
		width = img.getWidth() * 3;
		height = img.getHeight() * 3;
	}

	@Override
	public void tick() {
		if(getRectangle().intersects(handler.getPlayer().getRectangle())) {
			if(handler.getPlayer().getVelX() != 0) {
				handler.getPlayer().setX(handler.getPlayer().getX() - handler.getPlayer().getVelY());
			}
			if(handler.getPlayer().getVelY() != 0) {
				handler.getPlayer().setY(handler.getPlayer().getY() - handler.getPlayer().getVelY());
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(img, (int) x, (int) y, width, height, null);
	}

	@Override
	public Rectangle getRectangle() {
		if (img == Assets.Objects.OverworldTree) {
			return new Rectangle((int) x + 20 * 6, (int) y + 44 * 6, 15 * 6, 5 * 6);
		} else if(img == Assets.Objects.SavannahTree) {
			return new Rectangle((int) x + 23 * 6, (int) y + 50 * 6, 15 * 6, 5 * 6);
		}
		return null;
	}

}
