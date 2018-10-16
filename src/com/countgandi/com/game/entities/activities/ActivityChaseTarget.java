package com.countgandi.com.game.entities.activities;

import com.countgandi.com.game.Handler;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.Player;

public class ActivityChaseTarget extends ActivityBasic {

	private Entity e, found;
	private float chaseSpeed;
	private Class<? extends Entity> target;

	public ActivityChaseTarget(Entity e, Class<? extends Entity> target, float chaseSpeed, Handler handler) {
		super(handler);
		this.e = e;
		this.target = target;
		this.chaseSpeed = chaseSpeed;
	}

	@Override
	public void tick() {
		if (found != null) {
			if (e.getAttackBounds().intersects(found.getRectangle())) {
				double direction = Math.atan2((found.getY() + found.getHeight() / 2) - (e.getY() + e.getHeight() / 2), (found.getX() + found.getWidth() / 2) - (e.getX() + e.getWidth()));
				e.setVelX((float) (Math.cos(direction) * chaseSpeed));
				e.setVelY((float) (Math.sin(direction) * chaseSpeed));
				if(found.isDead()) {
					found = null;
				}
			} else {
				found = null;
			}
		} else {
				if (target.equals(Player.class)) {
					found = handler.getPlayer();
				} else if (handler.dungeon == null) {
					for (int i = 0; i < handler.getDimensionHandler().currentDimension.entities.size(); i++) {
						Entity entity = handler.getDimensionHandler().currentDimension.entities.get(i);
						if (target.equals(entity.getClass()) && e.getAttackBounds().intersects(entity.getRectangle())) {
							found = entity;
						}
					}
				} else {
					for (int i = 0; i < handler.dungeon.getEntities().size(); i++) {
						Entity entity = handler.dungeon.getEntities().get(i);
						if (target.equals(entity.getClass()) && e.getAttackBounds().intersects(entity.getRectangle())) {
							found = entity;
						}

					}
				}
			
		}
	}
	
	
	
}
