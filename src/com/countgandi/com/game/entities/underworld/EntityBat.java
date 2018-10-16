package com.countgandi.com.game.entities.underworld;

import java.awt.Graphics;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.dimensions.Dimension;
import com.countgandi.com.game.entities.DamageType;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.Player;
import com.countgandi.com.game.entities.activities.ActivityAttackTarget;
import com.countgandi.com.game.entities.activities.ActivityChaseTarget;
import com.countgandi.com.game.entities.activities.ActivityWander;

public class EntityBat extends Entity {
	
	private int timer = 0;
	
	public EntityBat(float x, float y, Handler handler) {
		super(x, y, handler);
		attackRange = 400;
		width = 16 * 4;
		height = 13 * 4;
		health = 60;
		activities.add(new ActivityWander(this, handler));
		activities.add(new ActivityChaseTarget(this, Player.class, 2.0f, handler));
		activities.add(new ActivityAttackTarget(this, Player.class, 8, 12, 60, DamageType.Attack, handler));
	}
	
	public void btick() {
		x += velX;
		y += velY;
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

		if (health < 0) {
			if (width >= 1) {
				width--;
			}
			if (height >= 1) {
				height--;
			}
			if (width <= 1 && height <= 1) {
				handler.removeEntity(this);
			}
		}
		tick();
		handleActivities();
	}
	
	@Override
	public void tick() {
		timer ++;
		if(timer >= 20) {
			if(direction == 0) {
				direction = 1;
			} else {
				direction = 0;
			}
			timer = 0;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.Entities.bat[direction], (int)x, (int)y, width, height, null);
	}

}
