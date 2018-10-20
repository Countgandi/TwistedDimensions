package com.countgandi.com.guis;

import java.awt.Color;
import java.awt.Font;
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
import com.countgandi.com.game.items.ItemStackable;
import com.countgandi.com.game.items.bows.ItemWoodBow;
import com.countgandi.com.game.items.foods.ItemDuckFood;
import com.countgandi.com.game.items.swords.ItemWoodSword;

public class InventoryGui extends Gui {

	public static ArrayList<Item> items = new ArrayList<Item>(50);
	public static int RowAmount = 10, ColumnAmount = 5;
	private Rectangle[] slots = new Rectangle[RowAmount * ColumnAmount];
	private Rectangle currentSlot;
	private Item item;
	private ItemStatGui itemGui;
	private static final int xPos = 191, yPos = 40;

	public InventoryGui(Handler handler) {
		super(handler);
		items.add(new ItemWoodSword(handler));
		items.add(new ItemWoodBow(handler));
		items.add(new ItemStackable(new ItemDuckFood(handler), 8));
		for (int y = 0; y < ColumnAmount; y++) {
			for (int x = 0; x < RowAmount; x++) {
				if (y == 0) {
					slots[x + y * RowAmount] = new Rectangle(xPos + 4 + x * 26, yPos + 225, 16, 16);
				} else {
					slots[x + y * RowAmount] = new Rectangle(xPos + 4 + x * 26, yPos + 136 + (y - 1) * 22, 16, 16);
				}
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
		g.drawImage(Assets.Guis.inventory, xPos, yPos, null);
		int i = 0;
		for (int y = 0; y < ColumnAmount; y++) {
			for (int x = 0; x < RowAmount; x++) {
				if (i < items.size() && i > -1 && items.size() > 0) {
					if (y == 0) {
						g.drawImage(items.get(i).getImage(), xPos + 4 + x * 26, yPos + 226, 16, 16, null);
						if (items.get(i) instanceof ItemStackable) {
							if (((ItemStackable) items.get(i)).stacked > 1) {
								g.setColor(Color.WHITE);
								g.setFont(new Font("arial", 2, 6));
								g.drawString("" + ((ItemStackable) items.get(i)).stacked, xPos + 4 + x * 26, yPos + 241);
							}
						}
					} else {
						g.drawImage(items.get(i).getImage(), xPos + 4 + x * 26, yPos + 137 + y * 22, 16, 16, null);
						if (items.get(i) instanceof ItemStackable) {
							if (((ItemStackable) items.get(i)).stacked > 1) {
								g.setColor(Color.WHITE);
								g.setFont(new Font("arial", 2, 6));
								g.drawString("" + ((ItemStackable) items.get(i)).stacked, xPos + 4 + x * 26, yPos + 152 + (y - 1) * 22);
							}
						}
					}
				}
				i++;
			}
		}
		if (currentSlot != null) {
			g.drawImage(Assets.Guis.inventorySelected, currentSlot.x - 2, currentSlot.y - 1, null);
		}
		if (itemGui != null) {
			itemGui.render(g);
		}
		
		for(int j = 0; j < slots.length; j++) {
			//g.drawRect(slots[j].x, slots[j].y, slots[j].width, slots[j].height);
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
							if (y == 0) {
								itemGui = item.createGuiStats(xPos + 4 + x * 26, yPos + 226);
							} else {
								itemGui = item.createGuiStats(xPos + 4 + x * 26, yPos + 137 + y * 22);
							}
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

	/**
	 * Adds an item to an itemstack (if stackable) and adds that to the item
	 * storage
	 * 
	 * @param item
	 *            - the item to be added
	 */
	public static void addItem(Item item) {
		if (item.stackable) {
			for (int i = 0; i < InventoryGui.items.size(); i++) {
				if (InventoryGui.items.get(i) instanceof ItemStackable) {
					ItemStackable stackableItem = (ItemStackable) InventoryGui.items.get(i);
					if (stackableItem.item.getClass().equals(item.getClass()) && stackableItem.stacked < ItemStackable.MAX_STACKS) {
						stackableItem.stacked++;
						return;
					} else if (stackableItem.stacked >= ItemStackable.MAX_STACKS && i >= InventoryGui.items.size() - 1) {
						InventoryGui.items.add(new ItemStackable(item, 1));
						return;
					}
				} else if (i >= InventoryGui.items.size() - 1) {
					InventoryGui.items.add(new ItemStackable(item, 1));
					return;
				}
			}
		} else {
			InventoryGui.items.add(item);
		}
	}

	public static void removeItem(Item item) {
		if (item.stackable) {
			for (int i = 0; i < InventoryGui.items.size(); i++) {
				if (InventoryGui.items.get(i) instanceof ItemStackable) {
					ItemStackable stackableItem = (ItemStackable) InventoryGui.items.get(i);
					if (stackableItem.item.getClass().equals(item.getClass()) && stackableItem.stacked - 1 > 0) {
						stackableItem.stacked--;
						return;
					} else if (stackableItem.stacked - 1 <= 0) {
						InventoryGui.items.remove(item);
						return;
					}
				} else if (i >= InventoryGui.items.size() - 1) {
					InventoryGui.items.remove(item);
				}
			}
		} else {
			InventoryGui.items.remove(item);
		}
	}

}
