package com.countgandi.com.game.items.armor.metal;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.items.armor.ItemArmorHeadpiece;

public class ItemMetalArmorHeadpiece extends ItemArmorHeadpiece {

	public ItemMetalArmorHeadpiece(Handler handler) {
		super("Metal", handler);
		imgs = Assets.Items.MetalArmor;
		physical = 8;
		magical = 0;
	}

}
