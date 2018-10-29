package com.countgandi.com.game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import com.countgandi.com.game.EntityDrop;
import com.countgandi.com.game.dimensions.Dimension;
import com.countgandi.com.game.entities.activities.ActivityBasic;
import com.countgandi.com.game.entities.other.EntityArrow;
import com.countgandi.com.guis.InventoryGui;
import com.countgandi.com.net.Handler;

public abstract class Entity {

	protected static Random ran = new Random();
	protected float x, y, velX, velY, speed = 1;
	protected boolean dead = false;
	protected int exp = 10, health = 5, width = 32, height = 32, direction, attackRange;
	protected Handler handler;
	protected EntityDrop lootDrop;
	protected ArrayList<ActivityBasic> activities = new ArrayList<ActivityBasic>(2);

	public Entity(float x, float y, Handler handler) {
		this.x = x;
		this.y = y;
		this.handler = handler;
	}

	protected void tick() {

	}

	public abstract void render(Graphics g);

	public void btick() {
		x += velX;
		y += velY;
		if (velX > 0) {
			direction = 1;
		} else if (velX < 0) {
			direction = 0;
		}
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

		if (health <= 0) {
			if (width >= 1) {
				width--;
			}
			if (height >= 1) {
				height--;
			}
			if (width <= 1 && height <= 1) {
				handler.removeEntity(this);
			}
		}
		tick();
		handleActivities();
	}

	public void onKill() {
		if (lootDrop != null) {
			for (int i = 0; i < lootDrop.getItems().size(); i++) {
				InventoryGui.addItem(lootDrop.getItems().get(i));
			}
		}
		handler.getPlayer().setExp(handler.getPlayer().getExp() + exp);
	}

	public void takeDamage(float damage, Entity entity, DamageType type) {
		if (dead)
			return;
		health -= damage;
		if ((entity == null || entity instanceof EntityArrow) && health <= 0) {
			dead = true;
			onKill();
		}
	}

	protected void handleActivities() {
		if (health >= 1) {
			for (int i = 0; i < activities.size(); i++) {
				activities.get(i).tick();
			}
		}
	}

	public Rectangle getRectangle() {
		return new Rectangle((int) x, (int) y, width, height);
	}

	public Rectangle getAttackBounds() {
		return new Rectangle((int) (x - attackRange), (int) (y - attackRange), attackRange * 2 + width, attackRange * 2 + height);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isDead() {
		return dead;
	}

}
