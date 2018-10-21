package com.countgandi.com.game.items.armor.metal;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.items.armor.ItemArmorLeggings;

public class ItemMetalArmorLeggings extends ItemArmorLeggings {

	public ItemMetalArmorLeggings(Handler handler) {
		super("Metal", handler);
		imgs = Assets.Items.MetalArmor;
		physical = 12;
		magical = 0;
	}
	
}
