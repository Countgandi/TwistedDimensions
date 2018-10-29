package com.countgandi.com.engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class CanvasLabel extends CanvasComponent {
	
	private String text = "";
	private String[] texts;
	private int x, y, fontSize = 12;
	
	public CanvasLabel(String text, int x, int y, int fontSize, Canvas canvas) {
		super(canvas);
		this.text = text;
		this.x = x;
		this.y = y;
		this.fontSize = fontSize;
		if(text.contains("\n")) {
			texts = text.split("\n");
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", 0, fontSize));
		if(texts == null) {
			g.drawString(text, x, y);
		} else {
			for(int i = 0; i < texts.length; i++) {
				g.drawString(texts[i], x, y + i * g.getFontMetrics().getHeight());
			}
		}
	}

	@Override
	public void tick() {
		
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

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
