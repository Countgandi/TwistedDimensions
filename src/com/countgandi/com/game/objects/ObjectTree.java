package com.countgandi.com.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;

public class ObjectTree extends GameObject {

	private BufferedImage img;

	public ObjectTree(int x, int y, Handler handler) {
		super(x, y, handler);
		int id = handler.getDimensionHandler().dimensions.indexOf(handler.getDimensionHandler().currentDimension);
		if (id == 0) {
			img = Assets.Objects.OverworldTree;
		} else if (id == 1) {
			img = Assets.Objects.SavannahTree;
		}
		width = img.getWidth() * 6;
		height = img.getHeight() * 6;

	}

	@Override
	public void tick() {
		
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
