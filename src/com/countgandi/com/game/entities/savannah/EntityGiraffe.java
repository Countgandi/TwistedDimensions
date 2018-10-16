package com.countgandi.com.game.entities.savannah;

import java.awt.Graphics;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.activities.ActivityWander;

public class EntityGiraffe extends Entity {

	public EntityGiraffe(float x, float y, Handler handler) {
		super(x, y, handler);
		width = 59 * 5;
		height = 38 * 5;
		health = 100;
		activities.add(new ActivityWander(this, handler));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.Entities.giraffe[direction], (int) x, (int) y, width, height, null);
	}

}