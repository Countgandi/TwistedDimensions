package com.countgandi.com.game.map;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.countgandi.com.Assets;
import com.countgandi.com.game.Handler;
import com.countgandi.com.game.map.tiles.AnimatedTile;
import com.countgandi.com.game.map.tiles.Tile;
import com.countgandi.com.game.map.tiles.AnimatedTile.AnimationId;

public class Map {

	public ArrayList<MapSection> sections;
	//private Handler handler;

	public Map(String folderLocation, Handler handler) {
		//this.handler = handler;
		this.sections = loadTilesFromFile(folderLocation);
	}

	private ArrayList<MapSection> loadTilesFromFile(String folderLocation) {
		int width = 16;
		BufferedImage[] imgs = Assets.loadImageSheet(16, 16, "/tex/terrain/maps/map0/tiles.png");
		ArrayList<MapSection> sections = new ArrayList<MapSection>();
		for (int y1 = 0; y1 < width; y1++) {
			for (int x1 = 0; x1 < width; x1++) {

				BufferedImage img = imgs[x1 + y1 * width];
				ArrayList<Tile> tiles = new ArrayList<Tile>();
				int x2 = x1 * 16;
				int y2 = y1 * 16;
				for (int y = 0; y < img.getHeight(); y++) {
					for (int x = 0; x < img.getWidth(); x++) {

						if (img.getRGB(x, y) == 0xFF0000FF) {
							AnimatedTile tile = new AnimatedTile(x2 + x, y2 + y, AnimationId.Water);
							tiles.add(tile);
						}
						if (img.getRGB(x, y) == 0xFF8E4A1C) {
							tiles.add(new Tile(x2 + x, y2 + y, 13));
						}
						if (img.getRGB(x, y) == 0xFFFFFF00) {
							tiles.add(new Tile(x2 + x, y2 + y, 10));
						}
					}
				}
				if (tiles.size() > 1) {
					sections.add(new MapSection(tiles));
				}
			}
		}
		return sections;
	}

	public class MapSection {
		public ArrayList<Tile> tiles;
		public Rectangle rectangle;

		public MapSection(ArrayList<Tile> tiles) {
			this.tiles = tiles;
			rectangle = new Rectangle(tiles.get(0).x, tiles.get(0).y, 16 * Tile.width, 16 * Tile.width);
		}
	}

}
