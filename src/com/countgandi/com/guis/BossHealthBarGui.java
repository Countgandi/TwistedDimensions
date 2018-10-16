package com.countgandi.com.guis;

import java.awt.Color;
import java.awt.Graphics;

import com.countgandi.com.game.Handler;
import com.countgandi.com.game.entities.Entity;

public class BossHealthBarGui extends Gui {
	
	private int width = 200, height = 32, maxHealth;
	private Entity e;

	public BossHealthBarGui(Entity e, Handler handler) {
		super(handler);
		this.e = e;
		x = 300;
		y = 15;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect((int)x, (int) y, width, height);
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int) y, (int)(((float)e.getHealth() / (float)maxHealth) * width), height);
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect((int)x, (int) y, width, height);
	}

}
