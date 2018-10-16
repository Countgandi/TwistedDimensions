package com.countgandi.com.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.countgandi.com.Game;
import com.countgandi.com.game.animations.Animation;
import com.countgandi.com.game.dimensions.Dimension;
import com.countgandi.com.game.dungeons.Dungeon;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.Player;
import com.countgandi.com.game.map.MapHandler;
import com.countgandi.com.guis.Gui;
import com.countgandi.com.guis.InventoryGui;

public class Handler {

	public ArrayList<Gui> guis = new ArrayList<Gui>();
	public ArrayList<Animation> animations = new ArrayList<Animation>();
	public boolean up, down, left, right, holdingSpace;
	public Dungeon dungeon;
	private Game game;
	private MapHandler mapHandler;
	private DimensionHandler dimensionHandler;
	private Player player;
	private Camera camera;
	private InventoryGui inventoryGui;

	public Handler(Game game) {
		this.game = game;
		player = new Player(Dimension.WorldBounds / 2, Dimension.WorldBounds / 2, this);
		dimensionHandler = new DimensionHandler(this);
		mapHandler = new MapHandler(this);
		camera = new Camera(this);
		inventoryGui = new InventoryGui(this);
	}

	public void tick() {
		dimensionHandler.tick();
		for (int i = 0; i < animations.size(); i++) {
			animations.get(i).tick();
		}
		for (int i = 0; i < guis.size(); i++) {
			guis.get(i).tick();
		}
		camera.tick();
	}

	public void tickGuis() {
		for (int i = 0; i < guis.size(); i++) {
			guis.get(i).tick();
		}
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(-Camera.x, -Camera.y);
		mapHandler.render(g);
		dimensionHandler.renderDungeonEntity(g);
		dimensionHandler.renderEntities(g);
		for (int i = 0; i < animations.size(); i++) {
			animations.get(i).render(g);
		}
		g2d.translate(Camera.x, Camera.y);
		for (int i = 0; i < guis.size(); i++) {
			guis.get(i).render(g);
		}
	}

	public void renderGuis(Graphics g) {
		for (int i = 0; i < guis.size(); i++) {
			guis.get(i).render(g);
		}
	}

	public void addEntity(Entity e) {
		if (dungeon == null) {
			dimensionHandler.currentDimension.entities.add(e);
		} else {
			dungeon.getEntities().add(e);
		}
	}

	public void removeEntity(Entity e) {
		if (dungeon == null) {
			dimensionHandler.currentDimension.entities.remove(e);
		} else {
			dungeon.getEntities().remove(e);
		}
	}

	public void addGui(Gui gui) {
		guis.add(gui);
	}

	public void removeGui(Gui gui) {
		guis.remove(gui);
	}

	public void removeAnimation(Animation animation) {
		animations.remove(animation);
	}

	public void addAnimation(Animation animation) {
		animations.add(animation);
	}

	public void mousePressed(MouseEvent e) {
		for (int i = 0; i < guis.size(); i++) {
			guis.get(i).mousePressed(e);
		}
	}

	public void mouseReleased(MouseEvent e) {
		for (int i = 0; i < guis.size(); i++) {
			guis.get(i).mouseReleased(e);
		}
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		for (int i = 0; i < guis.size(); i++) {
			guis.get(i).mouseWheelMoved(e);
		}
	}

	public void mouseMoved(MouseEvent e) {
		for (int i = 0; i < guis.size(); i++) {
			guis.get(i).mouseMoved(e);
		}
	}

	public Game getGame() {
		return game;
	}

	public MapHandler getMapHandler() {
		return mapHandler;
	}

	public DimensionHandler getDimensionHandler() {
		return dimensionHandler;
	}

	public Player getPlayer() {
		return player;
	}

	public InventoryGui getInventory() {
		return inventoryGui;
	}

	public boolean saveFiles() {
		try {
			return FileHandler.save(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.err.println("Could not save files...");
		return false;
	}

	/**
	 * @return if files load successfully
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public boolean loadFiles() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException {
		return FileHandler.load(this);
	}

}
