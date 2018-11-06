package com.countgandi.com.net.server;

import java.util.ArrayList;

import com.countgandi.com.game.DimensionHandler;
import com.countgandi.com.game.dimensions.Dimension;
import com.countgandi.com.game.dungeons.Dungeon;
import com.countgandi.com.game.entities.Player;

public class ServerDimensionHandler extends DimensionHandler {

	public ArrayList<Dungeon> dungeonsLoaded = new ArrayList<Dungeon>();

	public ServerDimensionHandler(ServerSideHandler handler) {
		super(handler);
		dimension = -1;
	}

	public void tick() {
		for (Dungeon d : dungeonsLoaded) {
			d.tick();
		}
		for (Dimension d : dimensions) {
			d.tick();
		}
	}

	@Override
	public void loadDimension(int newDimension, Player player) {
		Dimension d = dimensions.get(player.dimension);
		dimensions.get(newDimension).loadDimension(d, player);
	}

}
