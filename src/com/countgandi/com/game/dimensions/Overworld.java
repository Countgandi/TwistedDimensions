package com.countgandi.com.game.dimensions;

import java.util.ArrayList;

import com.countgandi.com.game.ChestTable;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.other.EntityCoyote;
import com.countgandi.com.game.entities.overworld.EntityDuck;
import com.countgandi.com.game.items.Item;
import com.countgandi.com.game.items.armor.leather.ItemLeatherArmorBoots;
import com.countgandi.com.game.items.armor.leather.ItemLeatherArmorChestpiece;
import com.countgandi.com.game.items.armor.leather.ItemLeatherArmorHeadpiece;
import com.countgandi.com.game.items.armor.leather.ItemLeatherArmorLeggings;
import com.countgandi.com.game.items.armor.trinkets.ItemWoodenRingTrinket;
import com.countgandi.com.game.items.swords.ItemWoodSword;
import com.countgandi.com.net.Handler;

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
		items.add(ItemWoodenRingTrinket.class);
		items.add(ItemLeatherArmorHeadpiece.class);
		items.add(ItemLeatherArmorChestpiece.class);
		items.add(ItemLeatherArmorLeggings.class);
		items.add(ItemLeatherArmorBoots.class);
		return new ChestTable(items, handler);
	}

}
