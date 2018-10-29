package com.countgandi.com.game.items.bows;

import com.countgandi.com.Assets;
import com.countgandi.com.game.entities.DamageType;
import com.countgandi.com.net.Handler;

public class ItemFrostbiteBow extends ItemBow {

	public ItemFrostbiteBow(Handler handler) {
		super(handler);
		name = "Frostbite Bow";
		imgs = Assets.Items.FrostbiteBow;
		dmg = 30;
		type = DamageType.Magic;
		multiplySpeed = 2;
	}

}
