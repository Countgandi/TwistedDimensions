package com.countgandi.com.game.animations;

import java.awt.Graphics;

import com.countgandi.com.net.client.ClientSideHandler;

public abstract class Animation {
	
	protected ClientSideHandler handler;
	protected int timer, x, y;
	
	public Animation(int x, int y, ClientSideHandler handler, int timeAlive) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		timer = timeAlive;
	}
	
	public void tick() {
		timer --;
		if(timer <= 0) {
			handler.removeAnimation(this);
		}
	}

	public abstract void render(Graphics g);

}
