package com.countgandi.com.game.entities.other;

import java.awt.Graphics;

import com.countgandi.com.Assets;
import com.countgandi.com.game.entities.DamageType;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.net.Handler;
import com.countgandi.com.net.client.ClientSideHandler;

public class EntityDimensionPortal extends Entity {

	public EntityDimensionPortal(float x, float y, Handler handler) {
		super(x, y, handler);
		width = Assets.Objects.DimensionPortal.getWidth() * 3;
		height = Assets.Objects.DimensionPortal.getHeight() * 3;
	}

	@Override
	public void takeDamage(float damage, Entity e, DamageType type) {
		if (e instanceof EntityArrow)
			return;
		if(handler instanceof ClientSideHandler) {
			((ClientSideHandler)handler).dungeon.exitDungeon(null);
		}
		handler.getDimensionHandler().loadDimension(handler.getDimensionHandler().dimension + 1);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.Objects.DimensionPortal, (int) x, (int) y, width, height, null);
	}

}
