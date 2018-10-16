package com.countgandi.com.game.dungeons;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.map.tiles.Tile;
import com.countgandi.com.game.objects.GameObject;

/*
 * List of dungeons
 * when walks into one, activate dungeon enter method
 */

public abstract class Dungeon {

	protected Handler handler;
	protected ArrayList<Tile> tiles = new ArrayList<Tile>();
	protected ArrayList<Entity> entities;
	protected ArrayList<GameObject> objects;
	protected float x, y;
	protected int width, height;
	protected int worldBorderWidth, worldBorderHeight;
	private String path;
	public int id = -1;

	public Dungeon(String dungeonFileName, float x, float y, int width, int height, Handler handler) {
		this.path = dungeonFileName;
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
		tiles.clear();
		exitDungeon();

		if (dungeon != null) {
			dungeon.dungeonEnter();
			handler.dungeon = dungeon;
		} else {
			handler.dungeon = null;
			handler.getDimensionHandler().currentDimension.loadDimension(handler.getDimensionHandler().currentDimension);
		}
	}

	public void dungeonEnter() {
		entities = new ArrayList<Entity>();
		objects = new ArrayList<GameObject>();
		handler.dungeon = this;

		entities.add(handler.getPlayer());

		String[] strings = ReadFile("/files/dungeons/" + path + ".dungeon");
		for (int i = 0; i < strings.length; i++) {
			String[] line = strings[i].split(",");
			int x = Integer.parseInt(line[1]), y = Integer.parseInt(line[2]), tex = Integer.parseInt(line[0]);
			tiles.add(new Tile(x, y, tex));
		}
		enterDungeon();
	}

	protected abstract void enterDungeon();

	protected abstract void exitDungeon();

	public void tick() {
		if (handler.dungeon == null && handler.getPlayer().getRectangle().intersects(getEnterBounds())) {
			dungeonEnter();
		}
	}

	public void renderFloor(Graphics g) {
		for (int i = 0; i < tiles.size(); i++) {
			Tile tile = tiles.get(i);
			g.drawImage(Assets.tileSet[tile.id], tile.x, tile.y, 64, 64, null);
		}
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