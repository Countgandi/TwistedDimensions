package com.countgandi.com.game.items.swords;

import com.countgandi.com.Assets;
import com.countgandi.com.net.Handler;

public class ItemStoneSword extends ItemSword {

	public ItemStoneSword(Handler handler) {
		super(handler);
		name = "Stone Sword";
		weapDamage = 10;
		imgs = Assets.Items.StoneSword;
	}

}
