package com.countgandi.com.game;

import java.awt.Graphics;
import java.util.ArrayList;

import com.countgandi.com.game.dimensions.Arctic;
import com.countgandi.com.game.dimensions.Dimension;
import com.countgandi.com.game.dimensions.Overworld;
import com.countgandi.com.game.dimensions.Savannah;
import com.countgandi.com.game.dimensions.Underworld;
import com.countgandi.com.guis.LoadingScreenGui;

public class DimensionHandler {

	public ArrayList<Dimension> dimensions = new ArrayList<Dimension>();
	public Dimension currentDimension;
	public int dimension;
	private Handler handler;

	public DimensionHandler(Handler handler) {
		this.handler = handler;
		dimensions.add(new Overworld("Overworld", this.handler));
		dimensions.add(new Savannah("Savannah", this.handler));
		dimensions.add(new Underworld("Underworld", this.handler));
		dimensions.add(new Arctic("The Arctic", this.handler));
	}

	/**
	 * For spawning in entities and stuff
	 */
	public void tick() {
		if (handler.dungeon == null) {
			currentDimension.tick();
			if (!LoadingScreenGui.isLoading) {
				for (int i = 0; i < currentDimension.entities.size(); i++) {
					currentDimension.entities.get(i).btick();
				}
				for (int i = 0; i < currentDimension.objects.size(); i++) {
					currentDimension.objects.get(i).tick();
				}
				for (int i = 0; i < currentDimension.dungeons.size(); i++) {
					currentDimension.dungeons.get(i).tick();
				}
			}
		} else {
			for (int i = 0; i < handler.dungeon.getEntities().size(); i++) {
				handler.dungeon.getEntities().get(i).btick();
			}
			for (int i = 0; i < handler.dungeon.getObjects().size(); i++) {
				handler.dungeon.getObjects().get(i).tick();
			}
		}
	}

	public void renderEntities(Graphics g) {
		if (handler.dungeon == null) {
			for (int i = 0; i < currentDimension.entities.size(); i++) {
				currentDimension.entities.get(i).render(g);
			}
			for (int i = 0; i < currentDimension.objects.size(); i++) {
				currentDimension.objects.get(i).render(g);
			}
		} else {
			for (int i = 0; i < handler.dungeon.getEntities().size(); i++) {
				handler.dungeon.getEntities().get(i).render(g);
			}
			for (int i = 0; i < handler.dungeon.getObjects().size(); i++) {
				handler.dungeon.getObjects().get(i).render(g);
			}
		}
	}

	public void renderDungeonEntity(Graphics g) {
		for (int i = 0; i < currentDimension.dungeons.size(); i++) {
			currentDimension.dungeons.get(i).renderEntity(g);
		}
	}

	 public void loadDimension(int dimension) {
		this.dimension = dimension;
		Dimension dimensionC = currentDimension;
		currentDimension = dimensions.get(dimension);
		currentDimension.loadDimension(dimensionC);
	}

}