package com.countgandi.com.guis;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.countgandi.com.Assets;
import com.countgandi.com.Game;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.items.Item;
import com.countgandi.com.game.items.foods.ItemDuckFood;
import com.countgandi.com.game.items.swords.ItemWoodSword;

public class InventoryGui extends Gui {

	public static ArrayList<Item> items = new ArrayList<Item>(210);
	public static int RowAmount = 21, ColumnAmount = 10;
	private Rectangle[] slots = new Rectangle[RowAmount * ColumnAmount];
	private Rectangle currentSlot;
	private Item item;
	private ItemStatGui itemGui;

	public InventoryGui(Handler handler) {
		super(handler);
		items.add(new ItemWoodSword(handler));
		items.add(new ItemDuckFood(handler));
		items.add(new ItemDuckFood(handler));
		items.add(new ItemDuckFood(handler));
		items.add(new ItemDuckFood(handler));
		items.add(new ItemDuckFood(handler));
		items.add(new ItemDuckFood(handler));
		items.add(new ItemDuckFood(handler));
		items.add(new ItemDuckFood(handler));
		items.add(new ItemDuckFood(handler));
		items.add(new ItemDuckFood(handler));
		for (int y = 0; y < ColumnAmount; y++) {
			for (int x = 0; x < RowAmount; x++) {
				slots[x + y * RowAmount] = new Rectangle(142 + x * 48, 128 + y * 48, 32, 32);
			}
		}
	}

	@Override
	public void tick() {
		if (itemGui != null) {
			itemGui.tick();
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.Guis.inventory, 0, 0, null);
		int i = 0;
		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 21; x++) {
				if (i < items.size() && i > -1 && items.size() > 0) {
					g.drawImage(items.get(i).getImage(), 144 + x * 48, 130 + y * 48, 32, 32, null);
				}
				i++;
			}
		}
		if (currentSlot != null) {
			g.drawImage(Assets.Guis.inventorySelected, currentSlot.x, currentSlot.y, null);
		}
		if (itemGui != null) {
			itemGui.render(g);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point m = new Point((int) (e.getX() / Toolkit.getDefaultToolkit().getScreenSize().getWidth() * Game.WIDTH), (int) (e.getY() / Toolkit.getDefaultToolkit().getScreenSize().getHeight() * Game.HEIGHT));
		if (e.getButton() == MouseEvent.BUTTON1) {
			for (int y = 0; y < ColumnAmount; y++) {
				for (int x = 0; x < RowAmount; x++) {
					if (slots[x + y * RowAmount].contains(m)) {
						currentSlot = slots[x + y * RowAmount];
						if (items.size() > x + y * RowAmount) {
							item = items.get(x + y * RowAmount);
							itemGui = item.createGuiStats(144 + x * 48, 130 + y * 48);
						}
					} else if (currentSlot == slots[x + y * RowAmount]) {
						currentSlot = null;
						itemGui = null;
						item = null;
					}
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

}
