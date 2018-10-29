package com.countgandi.com.game.items.foods;

import com.countgandi.com.Assets;
import com.countgandi.com.net.Handler;

public class ItemDuckFood extends ItemFood {

	public ItemDuckFood(Handler handler) {
		super(handler);
		name = "Duck Wing";
		health = 10;
		imgs = Assets.Items.ChickenWings;
	}

}
