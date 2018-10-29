package com.countgandi.com.game.skills;

import com.countgandi.com.net.Handler;

public class HealthSkill implements Skill {

	@Override
	public String getName() {
		return "Health";
	}

	@Override
	public int getPointDrain() {
		return 2;
	}

	@Override
	public void updateStats(Handler handler) {
		handler.getPlayer().maxHealth += 25;
	}

}
