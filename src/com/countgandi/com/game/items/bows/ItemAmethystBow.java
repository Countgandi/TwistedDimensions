package com.countgandi.com.game.items.bows;

import com.countgandi.com.Assets;
import com.countgandi.com.game.entities.DamageType;
import com.countgandi.com.net.Handler;

public class ItemAmethystBow extends ItemBow {

	public ItemAmethystBow(Handler handler) {
		super(handler);
		name = "Amethyst Bow";
		imgs = Assets.Items.AmethystBow;
		dmg = 20;
		type = DamageType.Attack;
		multiplySpeed = 2;
	}

}
