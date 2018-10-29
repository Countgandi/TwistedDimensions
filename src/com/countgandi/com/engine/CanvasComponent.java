package com.countgandi.com.engine;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

public abstract class CanvasComponent implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	
	protected boolean focused = false;
	protected Rectangle bounds;
	protected Canvas canvas;
	
	public CanvasComponent(Canvas canvas) {
		this.canvas = canvas;
		canvas.addKeyListener(this);
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		canvas.addMouseWheelListener(this);
	}
	
	public abstract void render(Graphics g);
	public abstract void tick();
	
	public boolean getFocused() {
		return focused;
	}
	
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	public void dispose() {
		canvas.removeKeyListener(this);
		canvas.removeMouseListener(this);
		canvas.removeMouseMotionListener(this);
		canvas.removeMouseWheelListener(this);
	}

}
