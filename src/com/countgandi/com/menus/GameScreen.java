package com.countgandi.com.menus;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.countgandi.com.game.Handler;
import com.countgandi.com.game.objects.GameObject;
import com.countgandi.com.guis.HotbarGui;
import com.countgandi.com.guis.HudGui;

public class GameScreen extends Menu {

	private boolean inventoryOpen = false;

	public GameScreen(Handler handler) {
		super(handler);
		this.handler.addGui(new HotbarGui(handler));
		this.handler.addGui(new HudGui(handler));
		try {
			this.handler.loadFiles();
		} catch(Exception e) {
			this.handler.getDimensionHandler().loadDimension(0);
			System.out.println("No world could be detected, creating new world...");
			e.printStackTrace();
		}
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
			handler.up = true;
		}
		if (key == KeyEvent.VK_S) {
			handler.down = true;
		}
		if (key == KeyEvent.VK_A) {
			handler.left = true;
		}
		if (key == KeyEvent.VK_D) {
			handler.right = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W) {
			handler.up = false;
		}
		if (key == KeyEvent.VK_S) {
			handler.down = false;
		}
		if (key == KeyEvent.VK_A) {
			handler.left = false;
		}
		if (key == KeyEvent.VK_D) {
			handler.right = false;
		}
		if (key == KeyEvent.VK_SPACE) {
			if(handler.dungeon == null) {
				for(int i = 0; i < handler.getDimensionHandler().currentDimension.objects.size(); i++) {
					GameObject ob = handler.getDimensionHandler().currentDimension.objects.get(i);
					if(ob.getRectangle().intersects(handler.getPlayer().getAttackBounds())) {
						ob.onHit();
					}
				}
			} else {
				for(int i = 0; i < handler.dungeon.getObjects().size(); i++) {
					GameObject ob = handler.dungeon.getObjects().get(i);
					if(ob.getRectangle().intersects(handler.getPlayer().getAttackBounds())) {
						ob.onHit();
					}
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

}
