package com.countgandi.com.game.dimensions;

import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

import com.countgandi.com.Assets;
import com.countgandi.com.game.ChestTable;
import com.countgandi.com.game.dungeons.BossDungeon;
import com.countgandi.com.game.dungeons.Dungeon;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.objects.GameObject;
import com.countgandi.com.game.objects.ObjectBush;
import com.countgandi.com.game.objects.ObjectMysteryBox;
import com.countgandi.com.game.objects.ObjectTree;
import com.countgandi.com.guis.LoadingScreenGui;
import com.countgandi.com.net.Handler;
import com.countgandi.com.net.client.Client;
import com.countgandi.com.net.client.ClientSideHandler;

public abstract class Dimension {

	public static final int WorldBounds = 8192;
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public ArrayList<Dungeon> dungeons = new ArrayList<Dungeon>();
	public ArrayList<GameObject> objects = new ArrayList<GameObject>(0);
	protected static Random ran = new Random();
	protected Handler handler;
	protected int id = -1;
	protected int numberOfEntities = 150;
	protected String title;

	public Dimension(String title, Handler handler) {
		this.handler = handler;
		this.title = title;
	}

	public void loadDimension(Dimension previous) {
		if (handler instanceof ClientSideHandler) {
			((ClientSideHandler) handler).addGui(new LoadingScreenGui((ClientSideHandler) handler));
		}
		if (previous != null) {
			if (previous.entities.contains(handler.getPlayer())) {
				previous.entities.remove(handler.getPlayer());
			}
		}
		if (!entities.contains(handler.getPlayer())) {
			entities.add(handler.getPlayer());
		}

		if (objects.size() < 1) {
			BufferedImage img = Assets.loadImage("/pics/Dimensions/" + title.replaceAll(" ", "") + "ob.png");
			for (int y = 0; y < img.getHeight(); y++) {
				for (int x = 0; x < img.getWidth(); x++) {
					int color = img.getRGB(x, y);
					if (color == 0xFF007F0E) {
						objects.add(new ObjectTree(x * 32, y * 32, handler));
					} else if (color == 0xFF00FF21) {
						objects.add(new ObjectBush(x * 32, y * 32, handler));
					} else if (color == 0xFFFF0000) {
						dungeons.add(new BossDungeon(x * 32, y * 32, handler));
					} else if (color == 0xFF7F3300) {
						objects.add(new ObjectMysteryBox(x * 32, y * 32, handler));
					}
				}
			}
		}

		handler.getPlayer().setX(WorldBounds / 2);
		handler.getPlayer().setY(WorldBounds / 2);

	}

	/**
	 * For spawning in entities
	 */
	private int tickOnEntities = 0;

	public void tick() {
		if (handler instanceof ClientSideHandler) {
			if (((ClientSideHandler) handler).multiplayer && LoadingScreenGui.isLoading) {
				if (entities.size() < Client.currentEntities.size()) {
					entities.add(Client.currentEntities.get(tickOnEntities));
					tickOnEntities++;
				} else {
					tickOnEntities = 0;
					LoadingScreenGui.isLoading = false;
				}
			} else if (!((ClientSideHandler) handler).multiplayer && LoadingScreenGui.isLoading) {
				if (entities.size() < numberOfEntities) {
					entities.add(getRandomEntity());
				} else {
					tickOnEntities = 0;
					LoadingScreenGui.isLoading = false;
				}
			}
		}
	}

	public int getId() {
		return id;
	}

	/**
	 * @return - the texture to be rendered as ground
	 */
	public abstract int getGroundTexture();

	/**
	 * 
	 * @param classes
	 *            - add all the classes of the entities you want to spawn in
	 * @return - return classes
	 */
	protected abstract ArrayList<Class<? extends Entity>> getEntitiesSpawnable(ArrayList<Class<? extends Entity>> classes);

	/**
	 * 
	 * @return - a random entity from the class list to be spawned into the map
	 */
	protected Entity getRandomEntity() {
		ArrayList<Class<? extends Entity>> classes = getEntitiesSpawnable(new ArrayList<Class<? extends Entity>>());
		int i = ran.nextInt(classes.size());
		try {
			return (Entity) classes.get(i).getConstructors()[0].newInstance(ran.nextInt(WorldBounds), ran.nextInt(WorldBounds), handler);
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
		System.out.println("Could not use random entity: " + i);
		return null;
	}

	public abstract ChestTable getChestTable();

	@Override
	public String toString() {
		return title;
	}

}
