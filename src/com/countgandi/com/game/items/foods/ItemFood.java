package com.countgandi.com.game.items.foods;

import java.awt.image.BufferedImage;

import com.countgandi.com.game.items.Item;
import com.countgandi.com.guis.InventoryGui;
import com.countgandi.com.guis.ItemStatGui;
import com.countgandi.com.net.Handler;
import com.countgandi.com.net.client.ClientSideHandler;

public abstract class ItemFood extends Item {

	protected int health = 10;

	public ItemFood(Handler handler) {
		super(handler);
		name = "Food";
		stackable = true;
	}

	@Override
	public boolean onUse() {
		if (handler.getPlayer().getHealth() >= handler.getPlayer().maxHealth) {
			return false;
		}
		handler.getPlayer().setHealth(handler.getPlayer().getHealth() + health);
		if (handler.getPlayer().getHealth() > handler.getPlayer().maxHealth) {
			handler.getPlayer().setHealth(handler.getPlayer().maxHealth);
		}
		InventoryGui.removeItem(this);
		return true;
	}
	
	@Override
	public BufferedImage getImage() {
		return imgs[0];
	}

	@Override
	public ItemStatGui createGuiStats(int x, int y) {
		return new ItemStatGui(x, y, new String[] { name + "/#00FFFF", "Restore: " + health + "/#00FF00" }, (ClientSideHandler)handler);
	}

}
