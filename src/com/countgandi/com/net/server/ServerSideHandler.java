package com.countgandi.com.net.server;

import java.util.ArrayList;

import com.countgandi.com.game.dungeons.Dungeon;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.MPlayer;
import com.countgandi.com.game.entities.Player;
import com.countgandi.com.game.objects.GameObject;
import com.countgandi.com.net.Handler;

public class ServerSideHandler extends Handler {
	
	public ArrayList<Dungeon> dungeons = new ArrayList<Dungeon>();
	public ServerDimensionHandler dimensionHandler;

	public ServerSideHandler() {
		dimensionHandler = new ServerDimensionHandler(this);
	}

	@Override
	public void tick() {
		dimensionHandler.tick();
	}

	@Override
	public void addEntity(Entity e) {
		if(e instanceof Player || e instanceof MPlayer) {
			players.add((Player)e);
		}
		
	}

	@Override
	public void removeEntity(Entity e) {
		
	}

	@Override
	public void removeObject(GameObject obj) {
		
	}

	@Override
	public void addObject(GameObject obj) {
		
	}

}
