package com.countgandi.com.game.objects;

import java.awt.Graphics;

import com.countgandi.com.Assets;
import com.countgandi.com.game.ChestTable;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.Player;
import com.countgandi.com.guis.InventoryGui;
import com.countgandi.com.net.Handler;

public class ObjectMysteryBox extends GameObject {
	
	private ChestTable table;
	
	public ObjectMysteryBox(int x, int y, ChestTable table, Handler handler) {
		super(x, y, handler);
		width = 23 * 3;
		height = 12 * 3;
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
	public void onHit(Entity e) {
		if(!(e instanceof Player)) {
			return;
		}
		InventoryGui.addItem(table.getItem());
		handler.removeObject(this);
	}

}
