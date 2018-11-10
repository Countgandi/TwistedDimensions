package com.countgandi.com.game.dimensions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

import com.countgandi.com.game.ChestTable;
import com.countgandi.com.game.dungeons.Dungeon;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.MPlayer;
import com.countgandi.com.game.entities.Player;
import com.countgandi.com.game.objects.GameObject;
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

		loadObjects();
	}

	protected abstract void loadObjects();

	public void loadDimension(Dimension previous, Player player) {
		if (handler instanceof ClientSideHandler) {
			((ClientSideHandler) handler).addGui(new LoadingScreenGui((ClientSideHandler) handler));
		}
		if (previous != null) {
			if (previous.entities.contains(player)) {
				previous.entities.remove(player);
			}
		}
		if (!entities.contains(player)) {
			entities.add(player);
		}

		while (entities.size() < numberOfEntities) {
			entities.add(getRandomEntity());
		}

		player.setX(WorldBounds / 2);
		player.setY(WorldBounds / 2);

		LoadingScreenGui.isLoading = false;
		if (player instanceof MPlayer) {
			System.out.println("Dimension loaded for player: " + ((MPlayer) player).username);
		} else {
			System.out.println("Dimension loaded for player");
		}
	}

	public void tick() {
		if (Client.running && Client.currentEntities != null) {
			if (!entities.equals(Client.currentEntities)) {
				// entities = Client.currentEntities;
			}
		} else if(handler instanceof ClientSideHandler && !Client.running){
			while (entities.size() < numberOfEntities) {
				entities.add(getRandomEntity());
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
	 * @param classes - add all the classes of the entities you want to spawn in
	 * @return - return classes
	 */
	protected abstract ArrayList<Class<? extends Entity>> getEntitiesSpawnable(
			ArrayList<Class<? extends Entity>> classes);

	/**
	 * 
	 * @return - a random entity from the class list to be spawned into the map
	 */
	protected Entity getRandomEntity() {
		ArrayList<Class<? extends Entity>> classes = getEntitiesSpawnable(new ArrayList<Class<? extends Entity>>());
		int i = ran.nextInt(classes.size());
		try {
			return (Entity) classes.get(i).getConstructors()[0].newInstance(ran.nextInt(WorldBounds),
					ran.nextInt(WorldBounds), handler);
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
