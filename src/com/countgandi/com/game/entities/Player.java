package com.countgandi.com.game.entities;

import java.awt.Graphics;

import com.countgandi.com.Assets;
import com.countgandi.com.game.SkillHandler;
import com.countgandi.com.game.dimensions.Dimension;
import com.countgandi.com.guis.HotbarGui;
import com.countgandi.com.guis.InventoryGui;
import com.countgandi.com.net.Handler;

public class Player extends Entity {

	public static boolean up, down, left, right, holdingSpace;
	public int maxHealth = 100, maxEnergy = 100, maxExperience = 100, level = 1;
	public int[] baseDamage = new int[3];
	public boolean sprinting = false;
	private float energy = 100;
	/**
	 * Headpiece MR, Chestpiece MR, Leggings MR, Boots MR, Headpiece AR,
	 * Chestpiece AR, Leggings AR, Boots AR
	 */
	public int[] armorStats = { 0, 0, 0, 0, 0, 0, 0, 0 };

	public Player(float x, float y, Handler handler) {
		super(x, y, handler);
		width = 7 * 3;
		height = 12 * 3;
		exp = 90;
		health = maxHealth;
		SkillHandler.init();
	}

	@Override
	public void tick() {
		if (up) {
			velY = -speed;
			direction = 2;
		} else if (!down) {
			velY = 0;
		}
		if (down) {
			velY = speed;
			direction = 0;
		} else if (!up) {
			velY = 0;
		}
		if (left) {
			velX = -speed;
			direction = 1;
		} else if (!right) {
			velX = 0;
		}
		if (right) {
			velX = speed;
			direction = 3;
		} else if (!left) {
			velX = 0;
		}
		if(InventoryGui.headpiece != null) {
			armorStats[0] = InventoryGui.headpiece.getArmorMagical();
			armorStats[4] = InventoryGui.headpiece.getArmorPhysical();
		}
		if(InventoryGui.chestpiece != null) {
			armorStats[1] = InventoryGui.chestpiece.getArmorMagical();
			armorStats[5] = InventoryGui.chestpiece.getArmorPhysical();
		}
		if(InventoryGui.leggings != null) {
			armorStats[2] = InventoryGui.leggings.getArmorMagical();
			armorStats[6] = InventoryGui.leggings.getArmorPhysical();
		}
		if(InventoryGui.boots != null) {
			armorStats[3] = InventoryGui.boots.getArmorMagical();
			armorStats[7] = InventoryGui.boots.getArmorPhysical();
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
			speed = 3;
		} else {
			if (energy < maxEnergy && velX == 0 && velY == 0) {
				energy++;
			}
			speed = 2;
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
		return baseDamage[1];
	}
	public int getBaseSwordDamage() {
		return baseDamage[0];
	}

}
