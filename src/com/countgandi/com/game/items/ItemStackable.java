package com.countgandi.com.game.items;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.countgandi.com.guis.InventoryGui;
import com.countgandi.com.guis.ItemStatGui;

public class ItemStackable extends Item {

	public static final int MAX_STACKS = 16;
	public Item item;
	public int stacked;

	public ItemStackable(Item item, int stacked) {
		super(item.getHandler());
		this.item = item;
		if(stacked < 1) {
			stacked = 1;
		}
		this.stacked = stacked;
		imgs = item.getImgs();
	}

	/**
	 * Used only when holding item
	 */
	public void tick() {
		item.tick();
	}

	/**
	 * Used only when holding item
	 * 
	 * @param g
	 */
	public void render(Graphics g) {
		item.render(g);
	}

	public boolean onUse() {
		if (item.onUse()) {
			stacked--;
			if (stacked < 1) {
				InventoryGui.removeItem(this);
			}
			return true;
		}
		return false;
	}

	public BufferedImage getImage() {
		return item.getImage();
	}

	public ItemStatGui createGuiStats(int x, int y) {
		return item.createGuiStats(x, y);
	}

}
