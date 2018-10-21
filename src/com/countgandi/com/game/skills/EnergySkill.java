package com.countgandi.com.game.skills;

import com.countgandi.com.game.Handler;

public class EnergySkill implements Skill {

	@Override
	public String getName() {
		return "Energy";
	}

	@Override
	public int getPointDrain() {
		return 1;
	}

	@Override
	public void updateStats(Handler handler) {
		handler.getPlayer().maxEnergy += 25;
	}

}
