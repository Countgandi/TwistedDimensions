package com.countgandi.com.game.items.bows;

import com.countgandi.com.Assets;
import com.countgandi.com.game.entities.DamageType;
import com.countgandi.com.net.Handler;

public class ItemWoodBow extends ItemBow {

	public ItemWoodBow(Handler handler) {
		super(handler);
		name = "Wooden Bow";
		imgs = Assets.Items.WoodBow;
		dmg = 10;
		type = DamageType.Attack;
	}

}
