package com.countgandi.com.game.entities.other;

import java.awt.Graphics;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.entities.DamageType;
import com.countgandi.com.game.entities.Entity;

public class EntityFireball extends Entity {

	private int timer = 0;
	private DragonBoss b;

	public EntityFireball(float x, float y, DragonBoss b, Handler handler) {
		super(x, y, handler);
		this.b = b;
		width = 20 * 4;
		height = 17 * 4;
		health = 5;
		speed = 5;
		double angle = Math.atan2(handler.getPlayer().getY() - y, handler.getPlayer().getX() - x);
		velX = (float) (Math.cos(angle) * speed);
		velY = (float) (Math.sin(angle) * speed);
	}

	@Override
	public void tick() {
		if (getRectangle().intersects(handler.getPlayer().getRectangle())) {
			handler.getPlayer().takeDamage(50 + handler.getDimensionHandler().dimension * 25, b, DamageType.Magic);
			handler.dungeon.getEntities().remove(this);
		}

		timer++;
		if (timer > 180) {
			handler.dungeon.getEntities().remove(this);
		}
	}
	
	public void btick() {
		x += velX;
		y += velY;

		if (health < 0) {
			if (width >= 2) {
				width--;
			}
			if (height >= 2) {
				height--;
			}
			if (width == 1 && height == 1) {
				dead = true;
				handler.dungeon.getEntities().remove(this);
			}
		}
		tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.Entities.fireBall, (int) x, (int) y, width, height, null);
	}

}
