package com.countgandi.com.guis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.countgandi.com.game.SkillHandler;
import com.countgandi.com.net.client.ClientSideHandler;

public class HudGui extends Gui {
	
	private static int healthWidth = 125;

	public HudGui(ClientSideHandler handler) {
		super(handler);
	}

	@Override
	public void tick() {
		if(handler.getPlayer().getExp() >= handler.getPlayer().maxExperience) {
			handler.getPlayer().level ++;
			SkillHandler.available += 1;
			handler.getPlayer().setExp(0);
			handler.getPlayer().maxExperience += 20;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(7, 7, healthWidth, 12);
		g.fillRect(7, 19, healthWidth, 6);
		g.fillRect(7, 25, healthWidth, 6);
		g.setColor(Color.GREEN);
		g.fillRect(7, 7, (int)(((float)handler.getPlayer().getHealth() / (float)handler.getPlayer().maxHealth) * healthWidth), 12);
		g.setColor(Color.YELLOW);
		g.fillRect(7, 19, (int)(((float)handler.getPlayer().getEnergy() / (float)handler.getPlayer().maxEnergy) * healthWidth), 6);
		g.setColor(Color.CYAN);
		g.fillRect(7, 25, (int)(((float)handler.getPlayer().getExp() / (float)handler.getPlayer().maxExperience) * healthWidth), 6);
		g.setColor(Color.WHITE);
		g.drawRect(7, 7, healthWidth, 12);
		g.drawRect(7, 19, healthWidth, 6);
		g.drawRect(7, 25, healthWidth, 6);
		g.setFont(new Font("arial", 0, 7));
		g.drawString(handler.getPlayer().getHealth() + "/" + handler.getPlayer().maxHealth, 12, 16);
		g.setFont(new Font("arial", 0, 4));
		g.drawString(handler.getPlayer().getEnergy() + "/" + handler.getPlayer().maxEnergy, 12, 24);
		g.drawString(handler.getPlayer().getExp() + "/" + handler.getPlayer().maxExperience, 12, 30);
	}

}
