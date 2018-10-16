package com.countgandi.com.game.entities.savannah;

import java.awt.Graphics;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.entities.DamageType;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.Player;
import com.countgandi.com.game.entities.activities.ActivityAttackTarget;
import com.countgandi.com.game.entities.activities.ActivityChaseTarget;
import com.countgandi.com.game.entities.activities.ActivityWander;

public class EntityLion extends Entity {

	public EntityLion(float x, float y, Handler handler) {
		super(x, y, handler);
		attackRange = 400;
		width = 19 * 6;
		height = 9 * 6;
		health = 60;
		activities.add(new ActivityWander(this, handler));
		activities.add(new ActivityChaseTarget(this, Player.class, 2.0f, handler));
		activities.add(new ActivityAttackTarget(this, Player.class, 8, 12, 60, DamageType.Attack, handler));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.Entities.lion[direction], (int)x, (int)y, width, height, null);
	}

}
