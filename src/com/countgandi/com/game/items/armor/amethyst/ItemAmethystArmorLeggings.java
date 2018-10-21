package com.countgandi.com.game.items.armor.amethyst;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.items.armor.ItemArmorLeggings;

public class ItemAmethystArmorLeggings extends ItemArmorLeggings {

	public ItemAmethystArmorLeggings(Handler handler) {
		super("Amethyst", handler);
		imgs = Assets.Items.AmethystArmor;
		physical = 24;
		magical = 8;
	}

}
