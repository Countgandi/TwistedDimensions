package com.countgandi.com.guis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import com.countgandi.com.Assets;
import com.countgandi.com.Game;
import com.countgandi.com.game.SkillHandler;
import com.countgandi.com.game.items.Item;
import com.countgandi.com.game.items.ItemStackable;
import com.countgandi.com.game.items.armor.ItemArmorBoots;
import com.countgandi.com.game.items.armor.ItemArmorChestpiece;
import com.countgandi.com.game.items.armor.ItemArmorHeadpiece;
import com.countgandi.com.game.items.armor.ItemArmorLeggings;
import com.countgandi.com.game.items.armor.trinkets.ItemTrinket;
import com.countgandi.com.guis.buttons.AddButton;
import com.countgandi.com.net.client.ClientSideHandler;

public class InventoryGui extends Gui {

	public static Item[] items = new Item[50];
	public static ItemArmorHeadpiece headpiece;
	public static ItemArmorChestpiece chestpiece;
	public static ItemArmorLeggings leggings;
	public static ItemArmorBoots boots;
	public static ItemTrinket trinket1, trinket2;
	public static int RowAmount = 10, ColumnAmount = 5;
	private Rectangle[] slots = new Rectangle[RowAmount * ColumnAmount + 6];
	private AddButton[] buttons = new AddButton[getStatText().length - 1];
	private Rectangle currentSlot;
	private Item item;
	private ItemStatGui itemGui;
	private static final int xPos = 191, yPos = 40;
	private int mx, my;

	public InventoryGui(ClientSideHandler handler) {
		super(handler);

		for (int y = 0; y < ColumnAmount; y++) {
			for (int x = 0; x < RowAmount; x++) {
				if (y == 0) {
					slots[x + y * RowAmount] = new Rectangle(xPos + 4 + x * 26, yPos + 226, 16, 16);
				} else {
					slots[x + y * RowAmount] = new Rectangle(xPos + 4 + x * 26, yPos + 137 + (y - 1) * 22, 16, 16);
				}
			}
		}
		for (int i = 0; i < 6; i++) {
			slots[slots.length - i - 1] = new Rectangle(xPos + 238, yPos + 4 + i * 22, 16, 16);
		}
		String[] strings = getStatText();
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new AddButton(xPos + 4 + strings[0].length() * 4, yPos + 4 + (i + 1) * 8, strings[i], handler);
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
				if (items[i] != null) {
					if (i < items.length && i > -1 && items.length > 0) {
						if (y == 0) {
							g.drawImage(items[i].getImage(), xPos + 4 + x * 26, yPos + 226, 16, 16, null);
							if (items[i] instanceof ItemStackable) {
								if (((ItemStackable) items[i]).stacked > 1) {
									g.setColor(Color.WHITE);
									g.setFont(new Font("arial", 2, 6));
									g.drawString("" + ((ItemStackable) items[i]).stacked, xPos + 4 + x * 26, yPos + 241);
								}
							}
						} else {
							g.drawImage(items[i].getImage(), xPos + 4 + x * 26, yPos + 137 + (y - 1) * 22, 16, 16, null);
							if (items[i] instanceof ItemStackable) {
								if (((ItemStackable) items[i]).stacked > 1) {
									g.setColor(Color.WHITE);
									g.setFont(new Font("arial", 2, 6));
									g.drawString("" + ((ItemStackable) items[i]).stacked, xPos + 4 + x * 26, yPos + 152 + (y - 1) * 22);
								}
							}
						}
					}
				}
				i++;
			}
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", 2, 6));
		String[] strings = getStatText();
		for (int k = 0; k < strings.length; k++) {
			g.drawString(strings[k], xPos + 4, yPos + 10 + k * 8);
			if (k < buttons.length) {
				buttons[k].render(g);
			}
		}
		g.drawString("Skill points: " + SkillHandler.available, xPos + 4, yPos + 10 + strings.length * 8);

		if (headpiece != null) {
			g.drawImage(headpiece.getImage(), xPos + 238, yPos + 4, null);
		}
		if (chestpiece != null) {
			g.drawImage(chestpiece.getImage(), xPos + 238, yPos + 4 + 22, null);
		}
		if (leggings != null) {
			g.drawImage(leggings.getImage(), xPos + 238, yPos + 4 + 44, null);
		}
		if (boots != null) {
			g.drawImage(boots.getImage(), xPos + 238, yPos + 4 + 66, null);
		}
		if (trinket1 != null) {
			g.drawImage(trinket1.getImage(), xPos + 238, yPos + 4 + 88, null);
		}
		if (trinket2 != null) {
			g.drawImage(trinket2.getImage(), xPos + 238, yPos + 4 + 110, null);
		}
		if (currentSlot != null) {
			g.drawImage(Assets.Guis.inventorySelected, currentSlot.x - 2, currentSlot.y - 2, null);
		}
		if (itemGui != null) {
			itemGui.render(g);
		}
		if (dragging) {
			if (item != null) {
				g.drawImage(item.getImage(), mx, my, 16, 16, null);
			}
		}

	}

	public String[] getStatText() {
		return new String[] { "Player Stats - level " + handler.getPlayer().level, "Maximum Energy: " + handler.getPlayer().maxEnergy, "Maximum Health: " + handler.getPlayer().maxHealth, "Base Bow Damage: " + handler.getPlayer().getBaseBowDamage(),
				"Base Sword Damage: " + handler.getPlayer().getBaseSwordDamage() };
	}

	private boolean dragging = false;

	@Override
	public void mousePressed(MouseEvent e) {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].mousePressed(e);
		}
		Point m = new Point((int) (e.getX() / Toolkit.getDefaultToolkit().getScreenSize().getWidth() * Game.WIDTH), (int) (e.getY() / Toolkit.getDefaultToolkit().getScreenSize().getHeight() * Game.HEIGHT));
		if (e.getButton() == MouseEvent.BUTTON1) {
			for (int y = 0; y < ColumnAmount; y++) {
				for (int x = 0; x < RowAmount; x++) {
					if (slots[x + y * RowAmount].contains(m)) {
						currentSlot = slots[x + y * RowAmount];
						if (items.length > x + y * RowAmount) {
							item = items[x + y * RowAmount];
							if (item != null) {
								if (y == 0) {
									itemGui = item.createGuiStats(xPos + 4 + x * 26, yPos + 226);
								} else {
									itemGui = item.createGuiStats(xPos + 4 + x * 26, yPos + 137 + y * 22);
								}
							}
						}
					} else if (currentSlot == slots[x + y * RowAmount]) {
						currentSlot = null;
						itemGui = null;
						item = null;
					}
				}
			}
			for (int i = 0; i < 6; i++) {
				if (slots[slots.length - 1 - i].contains(m)) {
					currentSlot = slots[slots.length - 1 - i];
					switch (i) {
					case 0:
						item = headpiece;
						break;
					case 1:
						item = chestpiece;
						break;
					case 2:
						item = leggings;
						break;
					case 3:
						item = boots;
						break;
					case 4:
						item = trinket1;
						break;
					case 5:
						item = trinket2;
						break;
					}
					if (item != null) {
						itemGui = item.createGuiStats(xPos + 4 + x * 26, yPos + 226);
					}
				} else if (currentSlot == slots[slots.length - 1 - i]) {
					currentSlot = null;
					itemGui = null;
					item = null;
				}
			}
		}
		dragging = true;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point m = new Point((int) (e.getX() / Toolkit.getDefaultToolkit().getScreenSize().getWidth() * Game.WIDTH), (int) (e.getY() / Toolkit.getDefaultToolkit().getScreenSize().getHeight() * Game.HEIGHT));
		// move items
		itemGui = null;
		mx = (int) m.getX();
		my = (int) m.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].mouseMoved(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].mouseReleased(e);
		}
		if (dragging) {
			int j = 0;
			for (int i = 0; i < slots.length; i++) {
				if (currentSlot != null) {
					if (slots[i].intersects(currentSlot)) {
						j = i;
					}
				}
			}
			for (int i = 0; i < slots.length; i++) {
				if (slots[i].contains(mx, my)) {
					if (j > slots.length - 7 && i < slots.length - 7) {
						if (j == slots.length - 1) {
							headpiece = null;
						} else if (j == slots.length - 2) {
							chestpiece = null;
						} else if (j == slots.length - 3) {
							leggings = null;
						} else if (j == slots.length - 4) {
							boots = null;
						} else if (j == slots.length - 5) {
							trinket1 = null;
						} else if (j == slots.length - 6) {
							trinket2 = null;
						}
						moveItem(item, j, InventoryGui.findFirstInventoryFreeSpace());
					} else if (i > slots.length - 7) {
						boolean madeit = true;
						if (i == slots.length - 1 && item instanceof ItemArmorHeadpiece) {
							headpiece = (ItemArmorHeadpiece) item;
						} else if (i == slots.length - 2 && item instanceof ItemArmorChestpiece) {
							chestpiece = (ItemArmorChestpiece) item;
						} else if (i == slots.length - 3 && item instanceof ItemArmorLeggings) {
							leggings = (ItemArmorLeggings) item;
						} else if (i == slots.length - 4 && item instanceof ItemArmorBoots) {
							boots = (ItemArmorBoots) item;
						} else if (i == slots.length - 5 && item instanceof ItemTrinket) {
							trinket1 = (ItemTrinket) item;
						} else if (i == slots.length - 6 && item instanceof ItemTrinket) {
							trinket2 = (ItemTrinket) item;
						} else {
							madeit = false;
						}
						if (madeit) {
							items[j] = null;
						}
					} else {
						moveItem(item, j, i);
					}
				}
			}
		}
		mx = 10000;
		my = 10000;
		item = null;
		dragging = false;
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
			for (int i = 0; i < InventoryGui.items.length; i++) {
				if (InventoryGui.items[i] instanceof ItemStackable) {
					ItemStackable stackableItem = (ItemStackable) InventoryGui.items[i];
					if (stackableItem.item.getClass().equals(item.getClass()) && stackableItem.stacked < ItemStackable.MAX_STACKS) {
						stackableItem.stacked++;
						return;
					} else if (stackableItem.stacked >= ItemStackable.MAX_STACKS && i >= InventoryGui.items.length - 1) {
						InventoryGui.items[findFirstInventoryFreeSpace()] = new ItemStackable(item, 1);
						return;
					}
				} else if (i >= InventoryGui.items.length - 1) {
					InventoryGui.items[findFirstInventoryFreeSpace()] = new ItemStackable(item, 1);
					return;
				}
			}
		} else {
			InventoryGui.items[findFirstInventoryFreeSpace()] = item;
		}
	}

	private static int findFirstInventoryFreeSpace() {
		for (int i = 0; i < items.length; i++) {
			if (items[i] == null) {
				return i;
			}
		}
		System.err.println("There is no free space in INVENTORY....");
		return items.length;
	}

	public static void removeItem(Item item) {
		if (item.stackable) {
			for (int i = 0; i < InventoryGui.items.length; i++) {
				if (InventoryGui.items[i] instanceof ItemStackable) {
					ItemStackable stackableItem = (ItemStackable) InventoryGui.items[i];
					if (stackableItem.item.getClass().equals(item.getClass()) && stackableItem.stacked - 1 > 0) {
						stackableItem.stacked--;
						break;
					} else if (stackableItem.stacked - 1 <= 0) {
						InventoryGui.items[i] = null;
						break;
					}
				} else if (i >= InventoryGui.items.length - 1) {
					InventoryGui.items[i] = null;
					break;
				}
			}
		} else {
			for (int k = 0; k < items.length; k++) {
				if (items[k] != null) {
					if (items[k].equals(item)) {
						InventoryGui.items[k] = null;
						break;
					}
				}
			}
			return;
		}
	}

	/**
	 * Moves an item to a different location in the inventory
	 * 
	 * @param item
	 *            - the item being moved
	 * @param current
	 *            - the current position of the item
	 * @param next
	 *            - the position to move to
	 * @return if the item was moved successfully
	 */
	public static boolean moveItem(Item item, int current, int next) {
		if (items[next] == null) {
			items[next] = item;
			if (current < items.length) {
				items[current] = null;
			}
			return true;
		} else if (items[next] != null) {
			Item nitem = items[next];
			items[next] = item;
			items[current] = nitem;
			return true;
		}
		System.err.println("Could not move item: " + item.getClass().getName() + " to position " + next + " from position " + current);
		return false;
	}

}
