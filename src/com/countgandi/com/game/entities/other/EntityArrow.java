package com.countgandi.com.game.entities.other;

import java.awt.Graphics;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.entities.DamageType;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.Player;

public class EntityArrow extends Entity {
	
	private int timer, dmg;
	private DamageType type;

	public EntityArrow(float x, float y, int tex, boolean facingl, DamageType type, int dmg, Handler handler) {
		super(x, y, handler);
		this.type = type;
		this.dmg = dmg;
		speed = tex * 2;
		if(facingl) {
			velX = -speed;
		} else {
			velX = speed;
		}
		width = 15 * 2;
		height = 5 * 2;
	}
	
	@Override
	public void tick() {
		timer ++;
		if(timer > 120) {
			handler.removeEntity(this);
		}
		
		if(handler.dungeon != null) {
			for(int i = 0; i < handler.dungeon.getEntities().size(); i++) {
				Entity e = handler.dungeon.getEntities().get(i);
				if(e.getRectangle().intersects(getRectangle()) && !(e.getClass().equals(Player.class) || e.getClass().equals(getClass()))) {
					e.takeDamage(dmg, this, type);
					handler.removeEntity(this);
				}
			}
			for(int i = 0; i < handler.dungeon.getObjects().size(); i++) {
				if(handler.dungeon.getObjects().get(i).getRectangle().intersects(getRectangle())) {
					handler.removeEntity(this);
				}
			}
		} else {
			for(int i = 0; i < handler.getDimensionHandler().currentDimension.entities.size(); i++) {
				Entity e = handler.getDimensionHandler().currentDimension.entities.get(i);
				if(e.getRectangle().intersects(getRectangle()) && !(e.getClass().equals(Player.class) || e.getClass().equals(getClass()))) {
					e.takeDamage(dmg, this, type);
					handler.removeEntity(this);
				}
			}
			for(int i = 0; i < handler.getDimensionHandler().currentDimension.objects.size(); i++) {
				if(handler.getDimensionHandler().currentDimension.objects.get(i).getRectangle().intersects(getRectangle())) {
					handler.removeEntity(this);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.Entities.arrow[direction], (int)x, (int)y, width, height, null);
	}

}
