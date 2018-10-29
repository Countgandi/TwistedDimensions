package com.countgandi.com.game.dungeons;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.countgandi.com.Assets;
import com.countgandi.com.Game;
import com.countgandi.com.game.Camera;
import com.countgandi.com.game.dimensions.Dimension;
import com.countgandi.com.game.entities.Player;
import com.countgandi.com.game.entities.other.DragonBoss;
import com.countgandi.com.net.Handler;
import com.countgandi.com.net.client.ClientSideHandler;

public class BossDungeon extends Dungeon {

	public BossDungeon(float x, float y, Handler handler) {
		super("BossDungeon", x, y, 18 * 3, 18 * 3, handler);
		this.id = 0;
	}

	@Override
	public void renderEntity(Graphics g) {
		if (((ClientSideHandler) handler).dungeon == null) {
			g.drawImage(Assets.Objects.BossDungeon, (int) x, (int) y, width, height, null);
		}
	}

	@Override
	public Rectangle getEnterBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}

	@Override
	public void tick() {
		super.tick();
		if (handler instanceof ClientSideHandler) {
			if (((ClientSideHandler)handler).dungeon == this) {
				Camera.x = 0;
				Camera.y = 0;
				handler.getPlayer().setX(Game.clamp(handler.getPlayer().getX(), 0, Game.WIDTH - handler.getPlayer().getWidth()));
				handler.getPlayer().setY(Game.clamp(handler.getPlayer().getY(), 0, Game.HEIGHT - handler.getPlayer().getHeight()));
			}
		}
	}

	@Override
	protected void enterDungeon() {
		handler.addEntity(new DragonBoss(Game.WIDTH / 2, Game.HEIGHT / 2, handler));
		handler.getPlayer().setX(50);
		handler.getPlayer().setY(Game.HEIGHT / 2);
	}

	@Override
	protected void exitDungeon() {
		Player player = handler.getPlayer();
		player.setX(Dimension.WorldBounds / 2 - player.getWidth() / 2);
		player.setY(Dimension.WorldBounds / 2 - player.getHeight() / 2);
	}
}
