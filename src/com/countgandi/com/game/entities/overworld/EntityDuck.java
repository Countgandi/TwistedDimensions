package com.countgandi.com.game.entities.overworld;

import java.awt.Graphics;
import java.util.ArrayList;

import com.countgandi.com.Assets;
import com.countgandi.com.game.EntityDrop;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.activities.ActivityWander;
import com.countgandi.com.game.items.Item;
import com.countgandi.com.game.items.foods.ItemDuckFood;

public class EntityDuck extends Entity {

	public EntityDuck(float x, float y, Handler handler) {
		super(x, y, handler);
		speed = 1;
		width = 16 * 3;
		height = 14 * 3;
		ArrayList<Class<? extends Item>> items = new ArrayList<Class<? extends Item>>();
		items.add(ItemDuckFood.class);
		lootDrop = new EntityDrop(items, new int[] {1}, new int[] {1}, handler);
		activities.add(new ActivityWander(this, handler));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.Entities.duck[0], (int)x, (int)y, width, height, null);
	}

}
