package com.countgandi.com.game.entities.other;

import java.awt.Graphics;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.entities.DamageType;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.Player;
import com.countgandi.com.game.entities.activities.ActivityAttackTarget;
import com.countgandi.com.game.entities.activities.ActivityChaseTarget;
import com.countgandi.com.game.entities.activities.ActivityWander;
import com.countgandi.com.game.entities.overworld.EntityDuck;

public class EntityCoyote extends Entity {

	private int id;

	public EntityCoyote(float x, float y, Handler handler) {
		super(x, y, handler);
		attackRange = 400;
		width = 11 * 3;
		height = 7 * 3;
		health = 10;
		id = handler.getDimensionHandler().dimensions.indexOf(handler.getDimensionHandler().currentDimension) * 4;
		
		activities.add(new ActivityWander(this, handler));
		activities.add(new ActivityChaseTarget(this, Player.class, 1.0f, handler));
		activities.add(new ActivityAttackTarget(this, Player.class, 8 + id, 12 + id, 60, DamageType.Attack, handler));
		activities.add(new ActivityChaseTarget(this, EntityDuck.class, 1.0f, handler));
		activities.add(new ActivityAttackTarget(this, EntityDuck.class, 8 + id, 12 + id, 60, DamageType.Attack, handler));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.Entities.dog[id + direction], (int)x, (int)y, width, height, null);
	}

}
