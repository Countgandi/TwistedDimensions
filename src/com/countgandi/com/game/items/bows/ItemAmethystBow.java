package com.countgandi.com.game.items.bows;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.entities.DamageType;

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
