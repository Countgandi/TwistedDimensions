package com.countgandi.com.game.entities.activities;

import java.util.Random;

import com.countgandi.com.net.Handler;

public abstract class ActivityBasic {
	
	protected static Random ran = new Random();
	protected Handler handler;
	
	public ActivityBasic (Handler handler) {
		this.handler = handler;
	}
	
	public abstract void tick();

}
