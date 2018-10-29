package com.countgandi.com.game.entities.activities;

import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.net.Handler;

public class ActivityWander extends ActivityBasic {

	private int timer;
	private Entity e;

	public ActivityWander(Entity e, Handler handler) {
		super(handler);
		this.e = e;
	}
	
	@Override
	public void tick() {
		timer++;
		if (timer > ran.nextInt(120) + 120) {
			if (ran.nextInt(10) > 8) {
				double angle = ran.nextFloat() * Math.PI * 2;
				e.setVelX((float) (Math.cos(angle) * e.getSpeed()));
				e.setVelY((float) (Math.sin(angle) * e.getSpeed()));
				timer = 0;
			} else {
				e.setVelX(0);
				e.setVelY(0);
			}
		}
	}

}
