package com.countgandi.com.game.items.armor.frostbite;

import com.countgandi.com.Assets;
import com.countgandi.com.game.items.armor.ItemArmorBoots;
import com.countgandi.com.net.Handler;

public class ItemFrostbiteArmorBoots extends ItemArmorBoots {

	public ItemFrostbiteArmorBoots(Handler handler) {
		super("Frostbite", handler);
		imgs = Assets.Items.FrostbiteArmor;
		physical = 30;
		magical = 10;
	}

}
