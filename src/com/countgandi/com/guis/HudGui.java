package com.countgandi.com.guis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.countgandi.com.game.Handler;

public class HudGui extends Gui {
	
	private static int healthWidth = 200;

	public HudGui(Handler handler) {
		super(handler);
	}

	@Override
	public void tick() {
		if(handler.getPlayer().getExp() >= handler.getPlayer().maxExperience) {
			handler.getPlayer().level ++;
			handler.getPlayer().setExp(0);
			handler.getPlayer().maxExperience += 20;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(15, 15, healthWidth, 16);
		g.fillRect(15, 31, healthWidth, 8);
		g.fillRect(15, 39, healthWidth, 8);
		g.setColor(Color.GREEN);
		g.fillRect(15, 15, (int)(((float)handler.getPlayer().getHealth() / (float)handler.getPlayer().maxHealth) * healthWidth), 16);
		g.setColor(Color.YELLOW);
		g.fillRect(15, 31, (int)(((float)handler.getPlayer().getEnergy() / (float)handler.getPlayer().maxEnergy) * healthWidth), 8);
		g.setColor(Color.CYAN);
		g.fillRect(15, 39, (int)(((float)handler.getPlayer().getExp() / (float)handler.getPlayer().maxExperience) * healthWidth), 8);
		g.setColor(Color.WHITE);
		g.drawRect(15, 15, healthWidth, 16);
		g.drawRect(15, 31, healthWidth, 8);
		g.drawRect(15, 39, healthWidth, 8);
		g.setFont(new Font("arial", 0, 15));
		g.drawString(handler.getPlayer().getHealth() + "/" + handler.getPlayer().maxHealth, 30, 28);
		g.setFont(new Font("arial", 0, 7));
		g.drawString(handler.getPlayer().getEnergy() + "/" + handler.getPlayer().maxEnergy, 30, 37);
		g.setFont(new Font("arial", 0, 7));
		g.drawString(handler.getPlayer().getExp() + "/" + handler.getPlayer().maxExperience, 30, 46);
	}

}
