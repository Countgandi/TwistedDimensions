package com.countgandi.com.game.items.armor.leather;

import com.countgandi.com.Assets;
import com.countgandi.com.game.items.armor.ItemArmorLeggings;
import com.countgandi.com.net.Handler;

public class ItemLeatherArmorLeggings extends ItemArmorLeggings {

	public ItemLeatherArmorLeggings(Handler handler) {
		super("Leather", handler);
		physical = 4;
		magical = 0;
		slowness = 0;
		imgs = Assets.Items.LeatherArmor;
	}

}
