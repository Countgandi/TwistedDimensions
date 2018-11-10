package com.countgandi.com.game.dimensions;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.countgandi.com.Assets;
import com.countgandi.com.game.ChestTable;
import com.countgandi.com.game.dungeons.BossDungeon;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.overworld.EntityDuck;
import com.countgandi.com.game.entities.underworld.EntitySpider;
import com.countgandi.com.game.items.Item;
import com.countgandi.com.game.items.armor.frostbite.ItemFrostbiteArmorBoots;
import com.countgandi.com.game.items.armor.frostbite.ItemFrostbiteArmorChestpiece;
import com.countgandi.com.game.items.armor.frostbite.ItemFrostbiteArmorHeadpiece;
import com.countgandi.com.game.items.armor.frostbite.ItemFrostbiteArmorLeggings;
import com.countgandi.com.game.items.swords.ItemFrostbiteSword;
import com.countgandi.com.game.objects.ObjectBush;
import com.countgandi.com.game.objects.ObjectMysteryBox;
import com.countgandi.com.game.objects.ObjectTree;
import com.countgandi.com.net.Handler;

public class Arctic extends Dimension {
	
	public Arctic(String title, Handler handler) {
		super(title, handler);
		this.id = 3;
	}

	@Override
	public int getGroundTexture() {
		return 4;
	}

	@Override
	protected ArrayList<Class<? extends Entity>> getEntitiesSpawnable(ArrayList<Class<? extends Entity>> array) {
		array.add(EntitySpider.class);
		array.add(EntityDuck.class);
		return array;
	}

	@Override
	public ChestTable getChestTable() {
		ArrayList<Class<? extends Item>> items = new ArrayList<Class<? extends Item>>();
		items.add(ItemFrostbiteSword.class);
		items.add(ItemFrostbiteArmorHeadpiece.class);
		items.add(ItemFrostbiteArmorChestpiece.class);
		items.add(ItemFrostbiteArmorLeggings.class);
		items.add(ItemFrostbiteArmorBoots.class);
		return new ChestTable(items, handler);
	}

	@Override
	protected void loadObjects() {
		BufferedImage img = Assets.loadImage("/pics/Dimensions/" + title.replaceAll(" ", "") + "ob.png");
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				int color = img.getRGB(x, y);
				if (color == 0xFF007F0E) {
					objects.add(new ObjectTree(x * 32, y * 32, id, handler));
				} else if (color == 0xFF00FF21) {
					objects.add(new ObjectBush(x * 32, y * 32, handler));
				} else if (color == 0xFFFF0000) {
					dungeons.add(new BossDungeon(x * 32, y * 32, handler));
				} else if (color == 0xFF7F3300) {
					objects.add(new ObjectMysteryBox(x * 32, y * 32, getChestTable(), handler));
				}
			}
		}
	}

}
