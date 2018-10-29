package com.countgandi.com.game.entities.activities;

import com.countgandi.com.game.entities.DamageType;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.Player;
import com.countgandi.com.net.Handler;
import com.countgandi.com.net.client.ClientSideHandler;

public class ActivityAttackTarget extends ActivityBasic {

	private Entity e;
	private Class<? extends Entity> target;
	private int attackTimer, min, max, attackFrequency;
	private DamageType dmg;

	/**
	 * 
	 * @param current
	 *            - the current Entity
	 * @param target
	 *            - the target class of the entity
	 * @param min
	 *            - the minimum amount of damage an attack can do
	 * @param max
	 *            - the maximum amount of damage an attack can do (must be
	 *            higher than min)
	 * @param attackFrequency
	 *            - how frequent an attack can be
	 * @param dmg
	 *            - the damage type of an attack
	 * @param handler
	 *            - self-explanitory
	 */
	public ActivityAttackTarget(Entity current, Class<? extends Entity> target, int min, int max, int attackFrequency, DamageType dmg, Handler handler) {
		super(handler);
		this.e = current;
		this.target = target;
		this.min = min;
		this.max = max;
		this.dmg = dmg;
		this.attackFrequency = attackFrequency;
	}

	@Override
	public void tick() {
		if (target.equals(Player.class)) {
			if (e.getRectangle().intersects(handler.getPlayer().getRectangle())) {
				if (attackTimer > attackFrequency) {
					handler.getPlayer().takeDamage(min + ran.nextInt(max - min), e, dmg);
					attackTimer = 0;
				}
				attackTimer++;
			}
		} else if (handler instanceof ClientSideHandler) {
			if (((ClientSideHandler) handler).dungeon == null) {
				for (int i = 0; i < handler.getDimensionHandler().currentDimension.entities.size(); i++) {
					Entity entity = handler.getDimensionHandler().currentDimension.entities.get(i);
					if (e.getRectangle().intersects(entity.getRectangle()) && target.equals(entity.getClass())) {
						if (attackTimer > attackFrequency) {
							entity.takeDamage(min + ran.nextInt(max - min), e, dmg);
							if (entity.isDead()) {
								e.onKill();
							}
							attackTimer = 0;
						}
						attackTimer++;
					}
				}
			} else {
				for (int i = 0; i < ((ClientSideHandler) handler).dungeon.getEntities().size(); i++) {
					Entity entity = ((ClientSideHandler) handler).dungeon.getEntities().get(i);
					if (e.getRectangle().intersects(entity.getRectangle()) && target.equals(entity.getClass())) {
						if (attackTimer > attackFrequency) {
							entity.takeDamage(min + ran.nextInt(max - min), e, dmg);
							if (entity.isDead()) {
								e.onKill();
							}
							attackTimer = 0;
						}
						attackTimer++;
					}
				}
			}
		}
	}

}
