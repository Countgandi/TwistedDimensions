package com.countgandi.com.game.items.swords;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;

public class ItemWoodSword extends ItemSword {

	public ItemWoodSword(Handler handler) {
		super(handler);
		name = "Wooden Sword";
		weapDamage = 2;
		imgs = Assets.Items.WoodSword;
	}

}
