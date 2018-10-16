package com.countgandi.com.game.dimensions;

import java.util.ArrayList;

import com.countgandi.com.game.ChestTable;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.underworld.EntityBat;
import com.countgandi.com.game.entities.underworld.EntitySpider;
import com.countgandi.com.game.items.Item;
import com.countgandi.com.game.items.swords.ItemAmethystSword;

public class Underworld extends Dimension {
	
	public Underworld(String title, Handler handler) {
		super(title, handler);
		this.id = 2;
	}

	@Override
	public int getGroundTexture() {
		return 3;
	}

	@Override
	protected ArrayList<Class<? extends Entity>> getEntitiesSpawnable(ArrayList<Class<? extends Entity>> array) {
		array.add(EntitySpider.class);
		array.add(EntityBat.class);
		return array;
	}

	@Override
	public ChestTable getChestTable() {
		ArrayList<Class<? extends Item>> items = new ArrayList<Class<? extends Item>>();
		items.add(ItemAmethystSword.class);
		return new ChestTable(items, new int[] {1}, handler);
	}

}
