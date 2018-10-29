package com.countgandi.com.game.items.bows;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.countgandi.com.Assets;
import com.countgandi.com.game.entities.DamageType;
import com.countgandi.com.game.entities.Player;
import com.countgandi.com.game.entities.other.EntityArrow;
import com.countgandi.com.game.items.Item;
import com.countgandi.com.guis.ItemStatGui;
import com.countgandi.com.net.Handler;
import com.countgandi.com.net.client.ClientSideHandler;

public abstract class ItemBow extends Item {

	protected DamageType type;
	protected int dmg, tex, timer;
	protected float multiplySpeed = 1;

	public ItemBow(Handler handler) {
		super(handler);
		stackable = false;
		type = DamageType.Attack;
		dmg = 10;
		imgs = Assets.Items.WoodBow;
	}

	@Override
	public void tick() {
		super.tick();
		if (Player.holdingSpace) {
			if (timer > 60) {
				tex = 3;
			} else if (timer > 40) {
				tex = 2;
			} else if (timer > 20) {
				tex = 1;
			}
			timer++;
		} else {
			timer = 0;
			tex = 0;
		}
	}

	@Override
	public void render(Graphics g) {
		if (facingl) {
			g.drawImage(imgs[tex], (int) handler.getPlayer().getX() - 7, (int) handler.getPlayer().getY() + 2, 30, 24, null);
		} else {
			g.drawImage(imgs[4 + tex], (int) handler.getPlayer().getX() - 2, (int) handler.getPlayer().getY() + 2, 30, 24, null);
		}
	}

	@Override
	public boolean onUse() {
		if (timer > 60) {
			if (facingl) {
				handler.addEntity(new EntityArrow((int) handler.getPlayer().getX() - 7, (int) handler.getPlayer().getY() + 10, (int) (tex * multiplySpeed), facingl, type, dmg + handler.getPlayer().getBaseBowDamage(), handler));
			} else {
				handler.addEntity(new EntityArrow((int) handler.getPlayer().getX() - 2, (int) handler.getPlayer().getY() + 10, (int) (tex * multiplySpeed), facingl, type, dmg + handler.getPlayer().getBaseBowDamage(), handler));
			}

		}
		timer = 0;
		return false;
	}

	@Override
	public BufferedImage getImage() {
		return imgs[0];
	}

	@Override
	public ItemStatGui createGuiStats(int x, int y) {
		return new ItemStatGui(x, y, new String[] { name + "/#00FFFF", dmg + " " + type.toString() + " Dmg/#00FF00" }, (ClientSideHandler)handler);
	}

}
