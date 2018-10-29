package com.countgandi.com.game.items.armor;

import com.countgandi.com.Assets;
import com.countgandi.com.net.Handler;

public abstract class ItemArmorHeadpiece extends ItemArmor {

	public ItemArmorHeadpiece(String mat, Handler handler) {
		super(ArmorType.Head, mat, handler);
		magical = 0;
		physical = 10;
		imgs = Assets.Items.MetalArmor;
	}

}
