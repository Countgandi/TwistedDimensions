package com.countgandi.com.game.items.armor.metal;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.items.armor.ItemArmorChestpiece;

public class ItemMetalArmorChestpiece extends ItemArmorChestpiece {
	public ItemMetalArmorChestpiece(Handler handler) {
		super("Metal", handler);
		imgs = Assets.Items.MetalArmor;
		physical = 12;
		magical = 0;
	}
}
