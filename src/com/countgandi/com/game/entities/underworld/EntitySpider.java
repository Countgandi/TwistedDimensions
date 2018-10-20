package com.countgandi.com.game.entities.underworld;

import java.awt.Graphics;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.entities.DamageType;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.Player;
import com.countgandi.com.game.entities.activities.ActivityAttackTarget;
import com.countgandi.com.game.entities.activities.ActivityJumpToTarget;
import com.countgandi.com.game.entities.other.EntityCoyote;

public class EntitySpider extends Entity {
	
	public EntitySpider(float x, float y, Handler handler) {
		super(x, y, handler);
		attackRange = 200;
		width = 19 * 3;
		height = 10 * 3;
		health = 10;
		activities.add(new ActivityJumpToTarget(this, Player.class, 120, handler));
		activities.add(new ActivityAttackTarget(this, Player.class, 8, 12, 60, DamageType.Attack, handler));
		activities.add(new ActivityJumpToTarget(this, EntityCoyote.class, 120, handler));
		activities.add(new ActivityAttackTarget(this, EntityCoyote.class, 8, 12, 60, DamageType.Attack, handler));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.Entities.blackWidow, (int) x, (int) y, width, height, null);
	}

}
