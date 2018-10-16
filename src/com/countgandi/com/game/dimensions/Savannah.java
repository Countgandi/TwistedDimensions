package com.countgandi.com.game.dimensions;

import java.util.ArrayList;

import com.countgandi.com.game.ChestTable;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.other.EntityCoyote;
import com.countgandi.com.game.entities.savannah.EntityGiraffe;
import com.countgandi.com.game.items.Item;
import com.countgandi.com.game.items.swords.ItemStoneSword;

public class Savannah extends Dimension {

	public Savannah(String title, Handler handler) {
		super(title, handler);
		id = 1;
	}

	@Override
	public int getGroundTexture() {
		return 2;
	}

	@Override
	protected ArrayList<Class<? extends Entity>> getEntitiesSpawnable(ArrayList<Class<? extends Entity>> array) {
		array.add(EntityGiraffe.class);
		array.add(EntityCoyote.class);
		return array;
	}
	
	@Override
	public ChestTable getChestTable() {
		ArrayList<Class<? extends Item>> items = new ArrayList<Class<? extends Item>>();
		items.add(ItemStoneSword.class);
		return new ChestTable(items, new int[] {1}, handler);
	}

}
