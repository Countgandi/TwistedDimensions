package com.countgandi.com.game.items.armor.leather;

import com.countgandi.com.Assets;
import com.countgandi.com.game.items.armor.ItemArmorHeadpiece;
import com.countgandi.com.net.Handler;

public class ItemLeatherArmorHeadpiece extends ItemArmorHeadpiece {

	public ItemLeatherArmorHeadpiece(Handler handler) {
		super("Leather", handler);
		physical = 2;
		magical = 0;
		slowness = 0;
		imgs = Assets.Items.LeatherArmor;
	}

}
