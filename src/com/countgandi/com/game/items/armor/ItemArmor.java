package com.countgandi.com.game.items.armor;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.items.Item;
import com.countgandi.com.guis.ItemStatGui;

public abstract class ItemArmor extends Item {

	protected int magical, physical = 2;
	protected float slowness = 0;
	protected ArmorType type;

	public ItemArmor(ArmorType type, Handler handler) {
		super(handler);
		imgs = Assets.Items.LeatherArmor;
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
	}

	@Override
	public boolean onUse() {
		return false;
	}

	@Override
	public BufferedImage getImage() {
		switch (type) {
		case Head:
			return imgs[0];
		case Chest:
			return imgs[1];
		case Legs:
			return imgs[2];
		case Boots:
			return imgs[3];
		case Trinket:
			return imgs[0];
		default:
			return imgs[0];
		}
	}

	@Override
	public ItemStatGui createGuiStats(int x, int y) {
		return null;
	}

	protected enum ArmorType {
		Head, Chest, Legs, Boots, Trinket;
	}

	public int getArmorPhysical() {
		return physical;
	}

	public int getArmorMagical() {
		return magical;
	}

	public ArmorType getArmorType() {
		return type;
	}

}
