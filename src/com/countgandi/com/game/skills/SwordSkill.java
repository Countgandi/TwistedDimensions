package com.countgandi.com.game.skills;

import com.countgandi.com.net.Handler;

public class SwordSkill implements Skill {

	@Override
	public String getName() {
		return "Sword";
	}

	@Override
	public int getPointDrain() {
		return 1;
	}

	@Override
	public void updateStats(Handler handler) {
		handler.getPlayer().baseDamage[0] += 1;
	}

}
