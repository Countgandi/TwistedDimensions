package com.countgandi.com.engine;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

public class CanvasPanel extends CanvasComponent {
	
	private ArrayList<CanvasComponent> components = new ArrayList<CanvasComponent>();

	public CanvasPanel(Canvas canvas) {
		super(canvas);
	}
	
	@Override
	public void render(Graphics g) {
		for(CanvasComponent c:components) {
			c.render(g);
		}
	}

	@Override
	public void tick() {
		for(CanvasComponent c:components) {
			c.tick();
		}
	}
	
	public void add(CanvasComponent c) {
		components.add(c);
	}
	
	public CanvasComponent get(int i) {
		return components.get(i);
	}
	
	public int indexOf(CanvasComponent c) {
		return components.indexOf(c);
	}
	
	public boolean contains(CanvasComponent c) {
		return components.contains(c);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
	}
	
	public void dispose() {
		super.dispose();
		for(CanvasComponent c:components) {
			c.dispose();
		}
	}

}
