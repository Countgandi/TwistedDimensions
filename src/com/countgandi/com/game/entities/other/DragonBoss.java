package com.countgandi.com.game.entities.other;

import java.awt.Graphics;
import java.util.ArrayList;

import com.countgandi.com.Assets;
import com.countgandi.com.Game;
import com.countgandi.com.game.ChestTable;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.dimensions.Dimension;
import com.countgandi.com.game.entities.DamageType;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.items.Item;
import com.countgandi.com.game.items.bows.ItemAmethystBow;
import com.countgandi.com.game.items.bows.ItemFrostbiteBow;
import com.countgandi.com.game.items.bows.ItemWoodBow;
import com.countgandi.com.game.objects.ObjectMysteryBox;

public class DragonBoss extends Entity {

	private int id, timer;

	public DragonBoss(float x, float y, Handler handler) {
		super(x, y, handler);
		width = 54 * 3;
		height = 64 * 3;
		id = handler.getDimensionHandler().dimension;
		health = 25 + 25 * id;
		exp = health * 4;
	}

	@Override
	public void tick() {
		timer++;
		if (timer > 60 && !(health <= 0)) {
			handler.dungeon.getEntities().add(new EntityFireball(x - 30, y + 30, this, handler));
			timer = 0;
		}
	}

	@Override
	public void takeDamage(float damage, Entity e, DamageType type) {
		health -= damage;
		x = ran.nextInt(Game.WIDTH - width);
		y = ran.nextInt(Game.HEIGHT - height);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.Entities.dragons[id], (int) x, (int) y, width, height, null);
	}

	public void btick() {
		x += velX;
		y += velY;
		if (velX > 0) {
			direction = 1;
		} else if (velX < 0) {
			direction = 0;
		}
		if (x < 0) {
			x = 0;
		}
		if (x > Dimension.WorldBounds - width) {
			x = Dimension.WorldBounds - width;
		}
		if (y < 0) {
			y = 0;
		}
		if (y > Dimension.WorldBounds - height) {
			y = Dimension.WorldBounds - height;
		}

		if (health <= 0) {
			if (width >= 2) {
				width -= 4;
			}
			if (height >= 2) {
				height -= 4;
			}
			if (width <= 1 && height <= 0) {
				dead = true;
				onKill();
				handler.dungeon.getObjects().add(new ObjectMysteryBox(200, 200, getTable(), handler));
				handler.dungeon.getEntities().add(new EntityDimensionPortal(Game.WIDTH - 200, Game.HEIGHT / 2, handler));
				handler.dungeon.getEntities().remove(this);
			}
		}
		tick();
		handleActivities();
	}

	private ChestTable getTable() {
		int[] ints = new int[] { 1 };
		ArrayList<Class<? extends Item>> items = new ArrayList<Class<? extends Item>>();
		switch (id) {
		case 0:
			items.add(ItemWoodBow.class);
			break;
		case 1:
			items.add(ItemAmethystBow.class);
			break;
		case 2:
			items.add(ItemFrostbiteBow.class);
			break;
		default:
			break;
		}
		return new ChestTable(items, ints, handler);
	}

}
