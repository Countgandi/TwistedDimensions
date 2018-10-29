package com.countgandi.com.game.items.armor.trinkets;

import com.countgandi.com.net.Handler;

public class ItemWoodenRingTrinket extends ItemTrinket {

	public ItemWoodenRingTrinket(Handler handler) {
		super("Wooden Ring", handler);
	}

	@Override
	public void tick() {
		handler.getPlayer().maxEnergy = 200;
	}

}
