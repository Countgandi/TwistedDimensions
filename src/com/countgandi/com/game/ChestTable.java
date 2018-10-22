package com.countgandi.com.game;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

import com.countgandi.com.game.items.Item;

public class ChestTable {
	
	private Item item;
	
	public ChestTable(ArrayList<Class<? extends Item>> items, int[] amounts, Handler handler) {
		try {
			ArrayList<Class<? extends Item>> newItems = new ArrayList<Class<? extends Item>>();
			for(int i = 0; i < items.size(); i++) {
				for(int j = 0; j < amounts[i]; j++) {
					newItems.add(items.get(i));
				}
			}
			item = (Item) newItems.get(new Random().nextInt(newItems.size())).getConstructors()[0].newInstance(handler);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public ChestTable(ArrayList<Class<? extends Item>> items, Handler handler) {
		try {
			ArrayList<Class<? extends Item>> newItems = new ArrayList<Class<? extends Item>>();
			for(int i = 0; i < items.size(); i++) {
				newItems.add(items.get(i));
			}
			item = (Item) newItems.get(new Random().nextInt(newItems.size())).getConstructors()[0].newInstance(handler);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	public Item getItem() {
		return item;
	}
	
}
