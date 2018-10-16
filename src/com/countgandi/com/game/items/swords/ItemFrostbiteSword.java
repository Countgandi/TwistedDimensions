package com.countgandi.com.game.items.swords;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;

public class ItemFrostbiteSword extends ItemSword {

	public ItemFrostbiteSword(Handler handler) {
		super(handler);
		name = "Frostbite Sword";
		weapDamage = 30;
		imgs = Assets.Items.FrostbiteSword;
	}

}
