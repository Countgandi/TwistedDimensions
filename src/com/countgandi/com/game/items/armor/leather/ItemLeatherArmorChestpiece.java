package com.countgandi.com.game.items.armor.leather;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.items.armor.ItemArmorChestpiece;

public class ItemLeatherArmorChestpiece extends ItemArmorChestpiece {

	public ItemLeatherArmorChestpiece(Handler handler) {
		super("Leather", handler);
		physical = 4;
		magical = 0;
		slowness = 0;
		imgs = Assets.Items.LeatherArmor;
	}

}
