package com.countgandi.com.net;

import java.util.ArrayList;

import com.countgandi.com.game.DimensionHandler;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.Player;
import com.countgandi.com.game.objects.GameObject;

public abstract class Handler {
	
	public ArrayList<Player> players = new ArrayList<Player>(1);
	public DimensionHandler dimensionHandler;

	public abstract void removeEntity(Entity entity);
	public abstract void addEntity(Entity entity);
	public abstract void tick();
	public DimensionHandler getDimensionHandler() {
		return dimensionHandler;
	}
	public ArrayList<Player> getPlayers() {
		return players;
	}
	public Player getPlayer() {
		return players.get(0);
	}
	public abstract void removeObject(GameObject obj);
	public abstract void addObject(GameObject obj);

}
