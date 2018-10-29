package com.countgandi.com.game.dimensions;

import java.util.ArrayList;

import com.countgandi.com.game.ChestTable;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.other.EntityCoyote;
import com.countgandi.com.game.entities.savannah.EntityGiraffe;
import com.countgandi.com.game.items.Item;
import com.countgandi.com.game.items.armor.metal.ItemMetalArmorBoots;
import com.countgandi.com.game.items.armor.metal.ItemMetalArmorChestpiece;
import com.countgandi.com.game.items.armor.metal.ItemMetalArmorHeadpiece;
import com.countgandi.com.game.items.armor.metal.ItemMetalArmorLeggings;
import com.countgandi.com.game.items.swords.ItemStoneSword;
import com.countgandi.com.net.Handler;

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
		items.add(ItemMetalArmorHeadpiece.class);
		items.add(ItemMetalArmorChestpiece.class);
		items.add(ItemMetalArmorLeggings.class);
		items.add(ItemMetalArmorBoots.class);
		return new ChestTable(items, handler);
	}

}
