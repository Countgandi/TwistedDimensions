package com.countgandi.com.game.items.armor.trinkets;

import com.countgandi.com.game.Handler;
import com.countgandi.com.game.items.armor.ItemTrinket;

public class ItemWoodenRingTrinket extends ItemTrinket {

	public ItemWoodenRingTrinket(Handler handler) {
		super(handler);
	}

	@Override
	public void tick(boolean inUse) {
		handler.getPlayer().maxEnergy = 200;
	}

}
