package com.countgandi.com.game.entities.other;

import java.awt.Graphics;

import com.countgandi.com.Assets;
import com.countgandi.com.game.entities.DamageType;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.Player;
import com.countgandi.com.net.Handler;
import com.countgandi.com.net.client.ClientSideHandler;

public class EntityArrow extends Entity {

	private int timer, dmg;
	private DamageType type;

	public EntityArrow(float x, float y, int tex, boolean facingl, DamageType type, int dmg, Handler handler) {
		super(x, y, handler);
		this.type = type;
		this.dmg = dmg;
		speed = (float) tex * 1.5F;
		if (facingl) {
			velX = -speed;
		} else {
			velX = speed;
		}
		width = (int) (15 * 1.3333F);
		height = (int) (5 * 1.3333F);
	}

	@Override
	public void tick() {
		timer++;
		if (timer > 120) {
			handler.removeEntity(this);
		}

		if (handler instanceof ClientSideHandler) {
			if (((ClientSideHandler)handler).dungeon != null) {
				for (int i = 0; i < ((ClientSideHandler)handler).dungeon.getEntities().size(); i++) {
					Entity e = ((ClientSideHandler)handler).dungeon.getEntities().get(i);
					if (e.getRectangle().intersects(getRectangle()) && !(e.getClass().equals(Player.class) || e.getClass().equals(getClass()))) {
						e.takeDamage(dmg, this, type);
						handler.removeEntity(this);
					}
				}
				for (int i = 0; i < ((ClientSideHandler)handler).dungeon.getObjects().size(); i++) {
					if (((ClientSideHandler)handler).dungeon.getObjects().get(i).getRectangle().intersects(getRectangle())) {
						handler.removeEntity(this);
					}
				}
			} else {
				for (int i = 0; i < handler.getDimensionHandler().currentDimension.entities.size(); i++) {
					Entity e = handler.getDimensionHandler().currentDimension.entities.get(i);
					if (e.getRectangle().intersects(getRectangle()) && !(e.getClass().equals(Player.class) || e.getClass().equals(getClass()))) {
						e.takeDamage(dmg, this, type);
						handler.removeEntity(this);
					}
				}
				for (int i = 0; i < handler.getDimensionHandler().currentDimension.objects.size(); i++) {
					if (handler.getDimensionHandler().currentDimension.objects.get(i).getRectangle().intersects(getRectangle())) {
						handler.removeEntity(this);
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.Entities.arrow[direction], (int) x, (int) y, width, height, null);
	}

}
