package com.countgandi.com.game.dimensions;

import java.util.ArrayList;

import com.countgandi.com.game.ChestTable;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.overworld.EntityDuck;
import com.countgandi.com.game.entities.underworld.EntitySpider;
import com.countgandi.com.game.items.Item;
import com.countgandi.com.game.items.swords.ItemFrostbiteSword;

public class Arctic extends Dimension {
	
	public Arctic(String title, Handler handler) {
		super(title, handler);
		this.id = 3;
	}

	@Override
	public int getGroundTexture() {
		return 4;
	}

	@Override
	protected ArrayList<Class<? extends Entity>> getEntitiesSpawnable(ArrayList<Class<? extends Entity>> array) {
		array.add(EntitySpider.class);
		array.add(EntityDuck.class);
		return array;
	}

	@Override
	public ChestTable getChestTable() {
		ArrayList<Class<? extends Item>> items = new ArrayList<Class<? extends Item>>();
		items.add(ItemFrostbiteSword.class);
		return new ChestTable(items, new int[] {1}, handler);
	}

}
