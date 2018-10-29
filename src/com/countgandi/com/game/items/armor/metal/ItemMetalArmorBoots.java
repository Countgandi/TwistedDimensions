package com.countgandi.com.game.items.armor.metal;

import com.countgandi.com.Assets;
import com.countgandi.com.game.items.armor.ItemArmorBoots;
import com.countgandi.com.net.Handler;

public class ItemMetalArmorBoots extends ItemArmorBoots {
	
	public ItemMetalArmorBoots(Handler handler) {
		super("Metal", handler);
		imgs = Assets.Items.MetalArmor;
		physical = 8;
		magical = 0;
	}

}
