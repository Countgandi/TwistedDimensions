package com.countgandi.com.game.dimensions;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.countgandi.com.Assets;
import com.countgandi.com.game.ChestTable;
import com.countgandi.com.game.dungeons.BossDungeon;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.other.EntityCoyote;
import com.countgandi.com.game.entities.savannah.EntityGiraffe;
import com.countgandi.com.game.items.Item;
import com.countgandi.com.game.items.armor.metal.ItemMetalArmorBoots;
import com.countgandi.com.game.items.armor.metal.ItemMetalArmorChestpiece;
import com.countgandi.com.game.items.armor.metal.ItemMetalArmorHeadpiece;
import com.countgandi.com.game.items.armor.metal.ItemMetalArmorLeggings;
import com.countgandi.com.game.items.swords.ItemStoneSword;
import com.countgandi.com.game.objects.ObjectBush;
import com.countgandi.com.game.objects.ObjectMysteryBox;
import com.countgandi.com.game.objects.ObjectSavannahShrub;
import com.countgandi.com.game.objects.ObjectTree;
import com.countgandi.com.net.Handler;

public class Savannah extends Dimension {

	public Savannah(String title, Handler handler) {
		super(title, handler);
		id = 1;
	}

	@Override
	public int getGroundTexture() {
		return 2;
	}

	@Override
	protected ArrayList<Class<? extends Entity>> getEntitiesSpawnable(ArrayList<Class<? extends Entity>> array) {
		array.add(EntityGiraffe.class);
		array.add(EntityCoyote.class);
		return array;
	}

	@Override
	public ChestTable getChestTable() {
		ArrayList<Class<? extends Item>> items = new ArrayList<Class<? extends Item>>();
		items.add(ItemStoneSword.class);
		items.add(ItemMetalArmorHeadpiece.class);
		items.add(ItemMetalArmorChestpiece.class);
		items.add(ItemMetalArmorLeggings.class);
		items.add(ItemMetalArmorBoots.class);
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
					objects.add(new ObjectSavannahShrub(x * 32, y * 32, handler));
				} else if (color == 0xFFFF0000) {
					dungeons.add(new BossDungeon(x * 32, y * 32, handler));
				} else if (color == 0xFF7F3300) {
					objects.add(new ObjectMysteryBox(x * 32, y * 32, getChestTable(), handler));
				}
			}
		}

	}

}
