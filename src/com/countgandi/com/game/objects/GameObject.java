package com.countgandi.com.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.countgandi.com.game.Handler;

public abstract class GameObject {

	protected int x, y, width = 32, height = 32;
	protected Handler handler;

	public GameObject(int x, int y, Handler handler) {
		this.x = x;
		this.y = y;
		this.handler = handler;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void onHit() {
		
	}

}
