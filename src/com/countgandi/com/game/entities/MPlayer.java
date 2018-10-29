package com.countgandi.com.game.entities;

import com.countgandi.com.net.Handler;

public class MPlayer extends Player {
	
	public String username;
	public int dimension = 0;

	public MPlayer(String username, float x, float y, Handler handler) {
		super(x, y, handler);
		this.username = username;
	}
	
	public MPlayer(String username, Handler handler) {
		super(-1000, -1000, handler);
		this.username = username;
	}

}
