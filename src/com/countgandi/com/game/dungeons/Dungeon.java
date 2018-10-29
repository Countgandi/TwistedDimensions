package com.countgandi.com.game.dungeons;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.objects.GameObject;
import com.countgandi.com.net.Handler;
import com.countgandi.com.net.client.ClientSideHandler;

/*
 * List of dungeons
 * when walks into one, activate dungeon enter method
 */

public abstract class Dungeon {

	protected Handler handler;
	protected ArrayList<Entity> entities;
	protected ArrayList<GameObject> objects;
	protected float x, y;
	protected int width, height;
	protected int worldBorderWidth, worldBorderHeight;
	//private String path;
	public int id = -1;

	public Dungeon(String dungeonFileName, float x, float y, int width, int height, Handler handler) {
		//this.path = dungeonFileName;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.handler = handler;
	}

	/**
	 * 
	 * @param dungeon:
	 *            The dungeon to be exited to (null if exitting to normal world)
	 */

	public void exitDungeon(Dungeon dungeon) {
		exitDungeon();
		if (handler instanceof ClientSideHandler) {
			if (dungeon != null) {
				dungeon.dungeonEnter();
				((ClientSideHandler)handler).dungeon = dungeon;
			} else {
				((ClientSideHandler)handler).dungeon = null;
				handler.getDimensionHandler().currentDimension.loadDimension(handler.getDimensionHandler().currentDimension);
			}
		}
	}

	public void dungeonEnter() {
		entities = new ArrayList<Entity>();
		objects = new ArrayList<GameObject>();
		((ClientSideHandler)handler).dungeon = this;

		entities.add(handler.getPlayer());

		enterDungeon();
	}

	protected abstract void enterDungeon();

	protected abstract void exitDungeon();

	public void tick() {
		if (((ClientSideHandler)handler).dungeon == null && handler.getPlayer().getRectangle().intersects(getEnterBounds())) {
			dungeonEnter();
		}
	}

	public void renderFloor(Graphics g) {
		
	}

	public abstract void renderEntity(Graphics g);

	public abstract Rectangle getEnterBounds();

	public Rectangle getRectangle() {
		return new Rectangle((int) x, (int) y, width, height);
	}

	private String[] ReadFile(String string) {
		try {
			Scanner scanner = new Scanner(this.getClass().getResourceAsStream(string));
			List<String> lines = new ArrayList<String>();
			while (scanner.hasNextLine()) {
				lines.add(scanner.nextLine());
			}
			scanner.close();
			String[] newLines = new String[lines.size()];
			for (int i = 0; i < lines.size(); i++) {
				newLines[i] = lines.get(i);
			}
			return newLines;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int getWorldBorerWidth() {
		return worldBorderWidth;
	}

	public int getWorldBorderHeight() {
		return worldBorderHeight;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public ArrayList<GameObject> getObjects() {
		return objects;
	}

}