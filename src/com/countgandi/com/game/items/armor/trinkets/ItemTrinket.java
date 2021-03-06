package com.countgandi.com.game.items.armor.trinkets;

import com.countgandi.com.Assets;
import com.countgandi.com.game.items.armor.ItemArmor;
import com.countgandi.com.net.Handler;

public abstract class ItemTrinket extends ItemArmor {

	public ItemTrinket(String trinketName, Handler handler) {
		super(ArmorType.Trinket, trinketName, handler);
		imgs = Assets.Items.WoodenRing;
	}
	
	public abstract void tick();

}
