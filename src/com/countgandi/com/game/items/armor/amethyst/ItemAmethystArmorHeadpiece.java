package com.countgandi.com.game.items.armor.amethyst;

import com.countgandi.com.Assets;
import com.countgandi.com.game.items.armor.ItemArmorHeadpiece;
import com.countgandi.com.net.Handler;

public class ItemAmethystArmorHeadpiece extends ItemArmorHeadpiece {

	public ItemAmethystArmorHeadpiece(Handler handler) {
		super("Amethyst", handler);
		imgs = Assets.Items.AmethystArmor;
		physical = 18;
		magical = 4;
	}

}
