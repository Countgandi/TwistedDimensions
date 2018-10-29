package com.countgandi.com.game.skills;

import com.countgandi.com.net.Handler;

public interface Skill {
	
	/**
	 * @return the skill name
	 */
	public abstract String getName();
	public abstract int getPointDrain();
	public abstract void updateStats(Handler handler);

}
