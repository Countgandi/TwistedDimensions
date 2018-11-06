package com.countgandi.com.game.entities;

import com.countgandi.com.game.dungeons.Dungeon;
import com.countgandi.com.net.Handler;

public class MPlayer extends Player {
	
	public String username;
	public Dungeon dungeon;

	public MPlayer(float x, float y, Handler handler) {
		super(x, y, handler);
	}
	
	public MPlayer(String username, Handler handler) {
		super(-1000, -1000, handler);
		this.username = username;
	}

}
