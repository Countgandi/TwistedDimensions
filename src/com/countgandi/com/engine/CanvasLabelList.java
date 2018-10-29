package com.countgandi.com.engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

public class CanvasLabelList extends CanvasComponent {
	
	private ArrayList<CanvasLabel> list = new ArrayList<CanvasLabel>();
	private int fontSize = 12;

	public CanvasLabelList(Rectangle bounds, Canvas canvas) {
		super(canvas);
		setBounds(bounds);
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
		g.setColor(Color.WHITE);
		g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
		
		g.setClip(bounds);
		g.setFont(new Font("arial", 0, fontSize));
		for(int i = 0; i < list.size(); i++) {
			g.drawString(list.get(i).getText(), bounds.x + 1, bounds.y + (i + 1) * g.getFontMetrics().getHeight());
		}
		g.setClip(new Rectangle(0, 0, canvas.getWidth(), canvas.getHeight()));
	}

	@Override
	public void tick() {
		
	}
	
	public void add(CanvasLabel label) {
		list.add(label);
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
	
	@Override
	public void dispose() {
		super.dispose();
		for(CanvasComponent c:list) {
			c.dispose();
		}
	}

	public void remove(CanvasLabel label) {
		list.remove(label);
	}

}
