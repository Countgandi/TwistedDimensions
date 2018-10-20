package com.countgandi.com.game.items.armor;

import java.awt.image.BufferedImage;

import com.countgandi.com.game.Handler;
import com.countgandi.com.game.items.Item;
import com.countgandi.com.guis.ItemStatGui;

public abstract class ItemArmor extends Item {

	public ItemArmor(ArmorType type, Handler handler) {
		super(handler);
	}

	public boolean onUse() {
		return false;
	}
	
	public BufferedImage getImage() {
		return imgs[0];
	}
	
	public ItemStatGui createGuiStats(int x, int y) {
		return null;
	}
	
	protected enum ArmorType {
		Head, Chest, Legs, Boots, Trinket;
	}

}
