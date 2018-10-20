package com.countgandi.com.game.items.swords;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.countgandi.com.game.Handler;
import com.countgandi.com.game.animations.SwordSlashAnimation;
import com.countgandi.com.game.entities.DamageType;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.Player;
import com.countgandi.com.game.items.Item;
import com.countgandi.com.guis.ItemStatGui;

public abstract class ItemSword extends Item {

	protected float weapDamage = 10;

	public ItemSword(Handler handler) {
		super(handler);
		name = "Sword";
		stackable = false;
	}

	@Override
	public boolean onUse() {
		int x, y = (int) (handler.getPlayer().getY() + 12), width = 32, height = 8;
		if (facingl) {
			x = (int) handler.getPlayer().getX() - 19;
			handler.addAnimation(new SwordSlashAnimation((int) handler.getPlayer().getX() - 19, (int) handler.getPlayer().getY() + 12, handler));
		} else {
			x = (int) handler.getPlayer().getX() + 2;
			handler.addAnimation(new SwordSlashAnimation((int) handler.getPlayer().getX() + 2, (int) handler.getPlayer().getY() + 12, handler));
		}
		if (handler.dungeon != null) {
			try {
				for (int i = 0; i < handler.dungeon.getEntities().size(); i++) {
					Entity e = handler.dungeon.getEntities().get(i);
					if (e.getRectangle().intersects(new Rectangle(x, y, width, height)) && !e.getClass().equals(Player.class)) {
						e.takeDamage(weapDamage + handler.getPlayer().getBaseSwordDamage(), null, DamageType.Attack);
					}
				}
			} catch (NullPointerException e) {
				// Do nothing
			}
		} else {
			if (handler.getDimensionHandler().currentDimension.entities != null) {
				for (int i = 0; i < handler.getDimensionHandler().currentDimension.entities.size(); i++) {
					Entity e = handler.getDimensionHandler().currentDimension.entities.get(i);
					if (e.getRectangle().intersects(new Rectangle(x, y, width, height)) && !e.getClass().equals(Player.class)) {
						e.takeDamage(weapDamage + handler.getPlayer().getBaseSwordDamage(), null, DamageType.Attack);
					}
				}
			}
		}
		return false;
	}

	@Override
	public BufferedImage getImage() {
		return imgs[0];
	}

	@Override
	public ItemStatGui createGuiStats(int x, int y) {
		return new ItemStatGui(x, y, new String[] { name + "/#00FFFF", weapDamage + " " + DamageType.Attack.toString() + " Dmg" + "/#FF0000" }, handler);
	}

}
