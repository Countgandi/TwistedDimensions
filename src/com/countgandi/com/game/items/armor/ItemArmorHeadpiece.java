package com.countgandi.com.game.items.armor;

import com.countgandi.com.game.Handler;

public abstract class ItemArmorHeadpiece extends ItemArmor {

	public ItemArmorHeadpiece(String mat, Handler handler) {
		super(ArmorType.Head, mat, handler);
	}

}
