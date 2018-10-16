package com.countgandi.com.game.objects;

import java.awt.Graphics;

import com.countgandi.com.Assets;
import com.countgandi.com.game.ChestTable;
import com.countgandi.com.game.Handler;
import com.countgandi.com.guis.InventoryGui;

public class ObjectMysteryBox extends GameObject {
	
	private ChestTable table;

	public ObjectMysteryBox(int x, int y, Handler handler) {
		this(x, y, handler.getDimensionHandler().currentDimension.getChestTable(), handler);
	}
	
	public ObjectMysteryBox(int x, int y, ChestTable table, Handler handler) {
		super(x, y, handler);
		width = 23 * 6;
		height = 12 * 6;
		this.table = table;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.Objects.Chest, (int) x, (int) y, width, height, null);
	}

	@Override
	public void onHit() {
		InventoryGui.items.add(table.getItem());
		if(handler.dungeon == null) {
			handler.getDimensionHandler().currentDimension.objects.remove(this);
		} else {
			handler.dungeon.getObjects().remove(this);
		}
	}

}