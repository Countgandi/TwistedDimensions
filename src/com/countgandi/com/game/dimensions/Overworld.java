package com.countgandi.com.game.dimensions;

import java.util.ArrayList;

import com.countgandi.com.game.ChestTable;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.other.EntityCoyote;
import com.countgandi.com.game.entities.overworld.EntityDuck;
import com.countgandi.com.game.items.Item;
import com.countgandi.com.game.items.swords.ItemWoodSword;

public class Overworld extends Dimension {

	public Overworld(String title, Handler handler) {
		super(title, handler);
		this.id = 0;
	}

	@Override
	public int getGroundTexture() {
		return 1;
	}

	@Override
	protected ArrayList<Class<? extends Entity>> getEntitiesSpawnable(ArrayList<Class<? extends Entity>> array) {
		array.add(EntityDuck.class);
		array.add(EntityCoyote.class);
		return array;
	}

	@Override
	public ChestTable getChestTable() {
		ArrayList<Class<? extends Item>> items = new ArrayList<Class<? extends Item>>();
		items.add(ItemWoodSword.class);
		return new ChestTable(items, new int[] {1}, handler);
	}

}
