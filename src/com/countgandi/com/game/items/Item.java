package com.countgandi.com.game.items;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;
import com.countgandi.com.guis.ItemStatGui;

public abstract class Item {
	
	protected boolean facingl = true;
	protected Handler handler;
	protected String name = "Item";
	protected BufferedImage[] imgs;
	
	public Item(Handler handler) {
		this.handler = handler;
		imgs = Assets.Items.StoneSword;
	}
	/**
	 * Used only when holding item
	 */
	public void tick() {
		if (handler.getPlayer().getVelX() < 0) {
			facingl = true;
		} else if (handler.getPlayer().getVelX() > 0) {
			facingl = false;
		}
	}
	/**
	 * Used only when holding item
	 * @param g 
	 */
	public void render(Graphics g) {
		if (facingl) {
			g.drawImage(imgs[0], (int) handler.getPlayer().getX() - 30, (int) handler.getPlayer().getY(), 32, 32, null);
		} else {
			g.drawImage(imgs[1], (int) handler.getPlayer().getX() + 34, (int) handler.getPlayer().getY(), 32, 32, null);
		}
	}
	
	public abstract void onUse();	
	public abstract BufferedImage getImage();
	public abstract ItemStatGui createGuiStats(int x, int y);

}