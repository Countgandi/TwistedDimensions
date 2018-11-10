package com.countgandi.com.menus;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.countgandi.com.game.entities.Player;
import com.countgandi.com.game.objects.GameObject;
import com.countgandi.com.guis.HotbarGui;
import com.countgandi.com.guis.HudGui;
import com.countgandi.com.guis.LoadingScreenGui;
import com.countgandi.com.net.client.Client;
import com.countgandi.com.net.client.ClientSideHandler;

public class GameScreen extends Menu {

	@SuppressWarnings("unused")
	private Client client;
	private boolean inventoryOpen = false;

	public GameScreen(ClientSideHandler handler) {
		super(handler);

		this.handler.addGui(new HotbarGui(handler));
		this.handler.addGui(new HudGui(handler));
		try {
			// if(!this.handler.loadFiles()) {
			this.handler.getDimensionHandler().loadDimension(0, handler.getPlayer());
			// System.out.println("No world could be detected, creating new world...");
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public GameScreen(Client client, ClientSideHandler handler) {
		super(handler);

		this.handler.addGui(new HotbarGui(handler));
		this.handler.addGui(new HudGui(handler));
		this.handler.addGui(new LoadingScreenGui(handler));
		this.client = client;
		handler.dimensionHandler.loadDimension(Client.dimension, handler.getPlayer());
	}

	@Override
	public void tick() {
		handler.tick();
	}

	@Override
	public void render(Graphics g) {
		handler.render(g);
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_I) {
			if (inventoryOpen) {
				handler.removeGui(handler.getInventory());
				inventoryOpen = false;
			} else {
				handler.addGui(handler.getInventory());
				inventoryOpen = true;
			}
		}
		if (key == KeyEvent.VK_W) {
			Player.up = true;
		}
		if (key == KeyEvent.VK_S) {
			Player.down = true;
		}
		if (key == KeyEvent.VK_A) {
			Player.left = true;
		}
		if (key == KeyEvent.VK_D) {
			Player.right = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W) {
			Player.up = false;
		}
		if (key == KeyEvent.VK_S) {
			Player.down = false;
		}
		if (key == KeyEvent.VK_A) {
			Player.left = false;
		}
		if (key == KeyEvent.VK_D) {
			Player.right = false;
		}
		if (key == KeyEvent.VK_SPACE) {
			if (handler.dungeon == null) {
				for (int i = 0; i < handler.getDimensionHandler().currentDimension.objects.size(); i++) {
					GameObject ob = handler.getDimensionHandler().currentDimension.objects.get(i);
					if (ob.getRectangle().intersects(handler.getPlayer().getAttackBounds())) {
						ob.onHit(handler.getPlayer());
					}
				}
			} else {
				try {
					for (int i = 0; i < handler.dungeon.getObjects().size(); i++) {
						GameObject ob = handler.dungeon.getObjects().get(i);
						if (ob.getRectangle().intersects(handler.getPlayer().getAttackBounds())) {
							ob.onHit(handler.getPlayer());
						}
					}
				} catch (NullPointerException ne) {

				}
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	public void closeMenu() {

	}

}
