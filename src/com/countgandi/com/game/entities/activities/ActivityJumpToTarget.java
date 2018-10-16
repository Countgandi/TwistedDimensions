package com.countgandi.com.game.entities.activities;

import com.countgandi.com.game.Handler;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.Player;

public class ActivityJumpToTarget extends ActivityBasic {

	private Entity e;
	private Class<? extends Entity> target;
	private int jumpTimer, jumpFrequency;

	public ActivityJumpToTarget(Entity current, Class<? extends Entity> target, int jumpFrequency, Handler handler) {
		super(handler);
		this.e = current;
		this.target = target;
		this.jumpFrequency = jumpFrequency;
	}

	@Override
	public void tick() {
		if (target.equals(Player.class)) {
			if (e.getAttackBounds().intersects(handler.getPlayer().getRectangle())) {
				if (jumpTimer > jumpFrequency) {
					e.setX(handler.getPlayer().getX());
					e.setY(handler.getPlayer().getY() + handler.getPlayer().getHeight() / 2);
					jumpTimer = 0;
				}
				jumpTimer++;
			}
		} else if (handler.dungeon == null) {
			for (int i = 0; i < handler.getDimensionHandler().currentDimension.entities.size(); i++) {
				Entity entity = handler.getDimensionHandler().currentDimension.entities.get(i);
				if (target.equals(entity.getClass()) && e.getAttackBounds().intersects(entity.getRectangle())) {
					if (jumpTimer > jumpFrequency) {
						e.setX(entity.getX());
						e.setY(entity.getY() + entity.getHeight() / 2);
						jumpTimer = 0;
					}
					jumpTimer++;
				}
			}
		} else {
			for (int i = 0; i < handler.dungeon.getEntities().size(); i++) {
				Entity entity = handler.dungeon.getEntities().get(i);
				if (target.equals(entity.getClass()) && e.getAttackBounds().intersects(entity.getRectangle())) {
					if (jumpTimer > jumpFrequency) {
						e.setX(entity.getX());
						e.setY(entity.getY() + entity.getHeight() / 2);
						jumpTimer = 0;
					}
					jumpTimer++;
				}

			}

		}
	}

}
