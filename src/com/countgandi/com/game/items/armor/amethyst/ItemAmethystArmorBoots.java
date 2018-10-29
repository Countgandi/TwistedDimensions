package com.countgandi.com.game.items.armor.amethyst;

import com.countgandi.com.Assets;
import com.countgandi.com.game.items.armor.ItemArmorBoots;
import com.countgandi.com.net.Handler;

public class ItemAmethystArmorBoots extends ItemArmorBoots {

	public ItemAmethystArmorBoots(Handler handler) {
		super("Amethyst", handler);
		imgs = Assets.Items.AmethystArmor;
		physical = 18;
		magical = 4;
	}

}
