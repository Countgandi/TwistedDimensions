package com.countgandi.com.game.objects;

import java.awt.Graphics;

import com.countgandi.com.Assets;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.Player;
import com.countgandi.com.game.entities.other.EntityArrow;
import com.countgandi.com.net.Handler;
import com.countgandi.com.net.client.ClientSideHandler;

public class ObjectDimensionPortal extends GameObject {

	public ObjectDimensionPortal(int x, int y, Handler handler) {
		super(x, y, handler);
		width = Assets.Objects.DimensionPortal.getWidth() * 3;
		height = Assets.Objects.DimensionPortal.getHeight() * 3;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.Objects.DimensionPortal, (int) x, (int) y, width, height, null);
	}

	@Override
	public void tick() {
		
	}
	
	@Override
	public void onHit(Entity e) {
		if (e instanceof EntityArrow)
			return;
		if(handler instanceof ClientSideHandler && e instanceof Player) {
			((ClientSideHandler)handler).dungeon.exitDungeon(null, (Player) e);
			handler.getDimensionHandler().loadDimension(handler.getDimensionHandler().dimension + 1, (Player)e);
		}
	}

}
