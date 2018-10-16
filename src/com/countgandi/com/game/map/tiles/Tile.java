package com.countgandi.com.game.map.tiles;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.countgandi.com.Assets;

public class Tile {
	
	public int x, y;
	public static int width = 96, height = 96;
	public int id;
	
	public Tile(int x, int y, int id) {
		this.x = x * width;
		this.y = y * height;
		this.id = id;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(Assets.tileSet[id], x, y, width, height, null);
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}

}
