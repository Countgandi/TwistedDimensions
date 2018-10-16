package com.countgandi.com.game.items.bows;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.entities.DamageType;

public class ItemWoodBow extends ItemBow {

	public ItemWoodBow(Handler handler) {
		super(handler);
		name = "Wooden Bow";
		imgs = Assets.Items.WoodBow;
		dmg = 10;
		type = DamageType.Attack;
	}

}
