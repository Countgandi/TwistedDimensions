package com.countgandi.com.game.items.armor.amethyst;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.items.armor.ItemArmorBoots;

public class ItemAmethystArmorBoots extends ItemArmorBoots {

	public ItemAmethystArmorBoots(Handler handler) {
		super("Amethyst", handler);
		imgs = Assets.Items.AmethystArmor;
		physical = 18;
		magical = 4;
	}

}
