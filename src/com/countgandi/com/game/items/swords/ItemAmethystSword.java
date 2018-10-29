package com.countgandi.com.game.items.swords;

import com.countgandi.com.Assets;
import com.countgandi.com.net.Handler;

public class ItemAmethystSword extends ItemSword {

	public ItemAmethystSword(Handler handler) {
		super(handler);
		name = "Amethyst Sword";
		weapDamage = 20;
		imgs = Assets.Items.AmethystSword;
	}

}
