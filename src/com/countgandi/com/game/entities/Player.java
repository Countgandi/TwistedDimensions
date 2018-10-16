package com.countgandi.com.game.entities;

import java.awt.Graphics;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.dimensions.Dimension;
import com.countgandi.com.guis.HotbarGui;

public class Player extends Entity {

	public int maxHealth = 100, maxEnergy = 100, maxExperience = 100, level = 1;
	public boolean sprinting = false;
	private float energy = 100;
	/**
	 * Headpiece MR, Chestpiece MR, Leggings MR, Boots MR, Headpiece AR,
	 * Chestpiece AR, Leggings AR, Boots AR
	 */
	public int[] armorStats = { 0, 0, 0, 0, 0, 0, 0, 0 };

	public Player(float x, float y, Handler handler) {
		super(x, y, handler);
		width = 64;
		height = 64;
		exp = 0;
		health = maxHealth;
	}

	@Override
	public void tick() {
		if (handler.up) {
			velY = -speed;
			direction = 2;
		} else if (!handler.down) {
			velY = 0;
		}
		if (handler.down) {
			velY = speed;
			direction = 0;
		} else if (!handler.up) {
			velY = 0;
		}
		if (handler.left) {
			velX = -speed;
			direction = 1;
		} else if (!handler.right) {
			velX = 0;
		}
		if (handler.right) {
			velX = speed;
			direction = 3;
		} else if (!handler.left) {
			velX = 0;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player[direction], (int) x, (int) y, width, height, null);
		if (HotbarGui.currentItem != null) {
			HotbarGui.currentItem.render(g);
		}
	}

	@Override
	public void takeDamage(float damage, Entity e, DamageType type) {
		if (type == DamageType.Attack) {
			int totalarmor = armorStats[4] + armorStats[5] + armorStats[6] + armorStats[7];
			health -= (damage * ((float) damage / ((float) damage + totalarmor)));
		} else if (type == DamageType.Magic) {
			int totalarmor = armorStats[0] + armorStats[1] + armorStats[2] + armorStats[3];
			health -= (damage * ((float) damage / ((float) damage + totalarmor)));
		}
	}

	@Override
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

		if (sprinting) {
			if (energy > 0) {
				energy--;
			}
			if (energy <= 0) {
				sprinting = false;
			}
			speed = 4;
		} else {
			if (energy < 100 && velX == 0 && velY == 0) {
				energy++;
			}
			speed = 3;
		}

		tick();
		handleActivities();
	}

	public void beginSprint() {
		sprinting = true;
	}

	public void stopSprint() {
		sprinting = false;
	}

	public float getEnergy() {
		return energy;
	}

	public int getBaseBowDamage() {
		return level;
	}
	public int getBaseSwordDamage() {
		return level;
	}

}