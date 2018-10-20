package com.countgandi.com.game.items.armor;

import com.countgandi.com.game.Handler;

public abstract class ItemTrinket extends ItemArmor {
	
	protected boolean inUse = false;

	public ItemTrinket(Handler handler) {
		super(ArmorType.Trinket, handler);
	}
	
	@Override
	public final void tick() {
		if(inUse) {
			tick(inUse);
		}
	}
	
	public abstract void tick(boolean inUse);

}
