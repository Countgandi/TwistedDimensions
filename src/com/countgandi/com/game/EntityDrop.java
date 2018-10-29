package com.countgandi.com.game;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

import com.countgandi.com.game.items.Item;
import com.countgandi.com.net.Handler;

public class EntityDrop {

	private ArrayList<Item> items = new ArrayList<Item>();

	public EntityDrop(ArrayList<Class<? extends Item>> items, int[] min, int[] max, Handler handler) {
		try {
			for (int i = 0; i < items.size(); i++) {
				int n = new Random().nextInt(max[i]) + min[i];
				for (int j = 0; j < n; j++) {
					this.items.add((Item) items.get(i).getConstructors()[0].newInstance(handler));
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.err.println("Item constructor arguments are different than specified in abstract class Item...");
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Item> getItems() {
		return items;
	}

}
