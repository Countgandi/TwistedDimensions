package com.countgandi.com.game.skills;

import com.countgandi.com.game.Handler;

public class BowSkill implements Skill {

	@Override
	public String getName() {
		return "Bow";
	}

	@Override
	public int getPointDrain() {
		return 1;
	}

	@Override
	public void updateStats(Handler handler) {
		handler.getPlayer().baseDamage[1] += 1;
	}

}
