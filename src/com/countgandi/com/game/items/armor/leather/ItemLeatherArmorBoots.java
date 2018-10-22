package com.countgandi.com.game.items.armor.leather;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.items.armor.ItemArmorBoots;

public class ItemLeatherArmorBoots extends ItemArmorBoots {

	public ItemLeatherArmorBoots(Handler handler) {
		super("Leather", handler);
		physical = 2;
		magical = 0;
		slowness = 0;
		imgs = Assets.Items.LeatherArmor;
	}

}
