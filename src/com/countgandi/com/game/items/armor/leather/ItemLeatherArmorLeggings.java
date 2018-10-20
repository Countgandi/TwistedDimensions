package com.countgandi.com.game.items.armor.leather;

import com.countgandi.com.game.Handler;
import com.countgandi.com.game.items.armor.ItemArmorLeggings;

public class ItemLeatherArmorLeggings extends ItemArmorLeggings {

	public ItemLeatherArmorLeggings(Handler handler) {
		super("Leather", handler);
		physical = 4;
		magical = 0;
		slowness = 0;
	}

}
