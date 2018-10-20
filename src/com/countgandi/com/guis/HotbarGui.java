package com.countgandi.com.guis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

import com.countgandi.com.Assets;
import com.countgandi.com.Game;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.items.Item;
import com.countgandi.com.game.items.ItemStackable;

public class HotbarGui extends Gui {

	public List<Item> items = new ArrayList<Item>(10);
	public static Item currentItem;

	public HotbarGui(Handler handler) {
		super(handler);
	}

	@Override
	public void tick() {
		items.clear();
		if (InventoryGui.items.size() > 10) {
			for (int i = 0; i < 10; i++) {
				items.add(InventoryGui.items.get(i));
			}
		} else if (InventoryGui.items.size() > 0) {
			for (int i = 0; i < InventoryGui.items.size(); i++) {
				items.add(InventoryGui.items.get(i));
			}
		}
		if (!(selected > items.size() - 1)) {
			currentItem = items.get(selected);
		} else {
			currentItem = null;
		}
		if (currentItem != null) {
			currentItem.tick();
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.Guis.hotbar, 191, Game.HEIGHT - 28, null);
		for (int x = 0; x < 10; x++) {
			if (x < items.size() && x > -1 && items.size() > 0) {
				g.drawImage(items.get(x).getImage(), 195 + x * 26, Game.HEIGHT - 24, 16, 16, null);
				if (items.get(x) instanceof ItemStackable) {
					if (((ItemStackable) items.get(x)).stacked > 1) {
						g.setColor(Color.WHITE);
						g.setFont(new Font("arial", 2, 6));
						g.drawString("" + ((ItemStackable) items.get(x)).stacked, 195 + x * 26, Game.HEIGHT - 9);
					}
				}
			}
		}
		g.drawImage(Assets.Guis.inventorySelected, 193 + selected * 26, Game.HEIGHT - 26, null);
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	int selected = 0;

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		selected += e.getWheelRotation();
		if (selected > InventoryGui.RowAmount - 1) {
			selected = 0;
		}
		if (selected < 0) {
			selected = InventoryGui.RowAmount - 1;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		for(int i = KeyEvent.VK_1; i < KeyEvent.VK_9 + 1; i++) {
			if(e.getKeyCode() == i) {
				selected = i - KeyEvent.VK_0 - 1;
			} else if(e.getKeyCode() == KeyEvent.VK_0) {
				selected = 9;
			}
		}
	}

}
