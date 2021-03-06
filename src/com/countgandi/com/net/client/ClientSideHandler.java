package com.countgandi.com.net.client;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.countgandi.com.Assets;
import com.countgandi.com.Game;
import com.countgandi.com.game.Camera;
import com.countgandi.com.game.DimensionHandler;
import com.countgandi.com.game.FileHandler;
import com.countgandi.com.game.animations.Animation;
import com.countgandi.com.game.dimensions.Dimension;
import com.countgandi.com.game.dungeons.Dungeon;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.Player;
import com.countgandi.com.game.objects.GameObject;
import com.countgandi.com.guis.Gui;
import com.countgandi.com.guis.InventoryGui;
import com.countgandi.com.menus.Menu;
import com.countgandi.com.net.Handler;

public class ClientSideHandler extends Handler {

	public ArrayList<Gui> guis = new ArrayList<Gui>();
	public ArrayList<Animation> animations = new ArrayList<Animation>();
	public boolean multiplayer = false;
	public Dungeon dungeon;
	private Game game;
	private Graphics g;
	
	private Camera camera;
	private InventoryGui inventoryGui;

	public ClientSideHandler(Game game) {
		this.game = game;
		players.add(new Player(Dimension.WorldBounds / 2, Dimension.WorldBounds / 2, this));
		dimensionHandler = new DimensionHandler(this);
		camera = new Camera(this);
		inventoryGui = new InventoryGui(this);
	}

	public void tick() {
		if (multiplayer) {
			dimensionHandler.tick();
			for (int i = 0; i < animations.size(); i++) {
				animations.get(i).tick();
			}
			for (int i = 0; i < guis.size(); i++) {
				guis.get(i).tick();
			}
			camera.tick();
		} else {
			dimensionHandler.tick();
			for (int i = 0; i < animations.size(); i++) {
				animations.get(i).tick();
			}
			for (int i = 0; i < guis.size(); i++) {
				guis.get(i).tick();
			}
			camera.tick();
		}
	}

	public void tickGuis() {
		for (int i = 0; i < guis.size(); i++) {
			guis.get(i).tick();
		}
	}

	public void render(Graphics g) {
		if(this.g == null) {
			this.g = g;
		}
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(-Camera.x, -Camera.y);
		if (dimensionHandler.currentDimension != null) {
			int yIncrement = 48, xIncrement = 48;
			if (dungeon != null) {
				int dungenID = dungeon.id;
				for (int y = (int) (Camera.y - yIncrement - (Camera.y % yIncrement)); y < Camera.y + Game.HEIGHT + yIncrement; y += yIncrement) {
					for (int x = (int) (Camera.x - xIncrement - (Camera.x % xIncrement)); x < Camera.x + Game.WIDTH + xIncrement; x += xIncrement) {
						g.drawImage(Assets.tileSet[dungenID], x, y, xIncrement, yIncrement, null);
					}
				}
			} else {
				for (int y = (int) (Camera.y - yIncrement - (Camera.y % yIncrement)); y < Camera.y + Game.HEIGHT + yIncrement; y += yIncrement) {
					for (int x = (int) (Camera.x - xIncrement - (Camera.x % xIncrement)); x < Camera.x + Game.WIDTH + xIncrement; x += xIncrement) {
						g.drawImage(Assets.tileSet[dimensionHandler.currentDimension.getGroundTexture()], x, y, xIncrement, yIncrement, null);
					}
				}
			}
		}
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
	
	public void renderGuis() {
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
	
	@Override
	public void removeObject(GameObject obj) {
		if (dungeon == null) {
			dimensionHandler.currentDimension.objects.remove(obj);
		} else {
			dungeon.getObjects().remove(obj);
		}
	}

	@Override
	public void addObject(GameObject obj) {
		if (dungeon == null) {
			dimensionHandler.currentDimension.objects.add(obj);
		} else {
			dungeon.getObjects().add(obj);
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
	
	public InventoryGui getInventory() {
		return inventoryGui;
	}

	public DimensionHandler getDimensionHandler() {
		return dimensionHandler;
	}
	
	public ArrayList<Player> getPlayers() {
		if(multiplayer) {
			return players;
		} else {
			System.err.println("You cannot get players without multiplayer...");
			return null;
		}
	}

	public Player getPlayer() {
		if(multiplayer) {
			return players.get(0);
		} else {
			return players.get(0);
		}
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

	public void keyPressed(KeyEvent e) {
		for (int i = 0; i < guis.size(); i++) {
			guis.get(i).keyPressed(e);
		}
	}

	public void keyReleased(KeyEvent e) {
		for (int i = 0; i < guis.size(); i++) {
			guis.get(i).keyReleased(e);
		}
	}

	public void mouseDragged(MouseEvent e) {
		for (int i = 0; i < guis.size(); i++) {
			guis.get(i).mouseDragged(e);
		}
	}
	
	public void setMenu(Menu menu) {
		game.menu.closeMenu();
		game.menu = menu;
	}
	
	public Graphics getGraphics() {
		return game.getGraphics();
	}
	
}
