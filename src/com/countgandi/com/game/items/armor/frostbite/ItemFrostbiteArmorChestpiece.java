package com.countgandi.com.game.items.armor.frostbite;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.items.armor.ItemArmorChestpiece;

public class ItemFrostbiteArmorChestpiece extends ItemArmorChestpiece {

	public ItemFrostbiteArmorChestpiece(Handler handler) {
		super("Frostbite", handler);
		imgs = Assets.Items.FrostbiteArmor;
		physical = 36;
		magical = 14;
	}

}
