package com.countgandi.com.game.items.armor;

import com.countgandi.com.net.Handler;

public abstract class ItemArmorChestpiece extends ItemArmor {

	public ItemArmorChestpiece(String mat, Handler handler) {
		super(ArmorType.Chest, mat, handler);
	}

}
