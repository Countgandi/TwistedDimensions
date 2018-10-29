package com.countgandi.com.engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import com.countgandi.com.Game;

public class CanvasButton extends CanvasComponent {

	private Color BackgroundColor = Color.GRAY, ForegroundColor = Color.LIGHT_GRAY, textColor = Color.WHITE;
	private Font font;
	private String text = "Button";
	private ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();

	public CanvasButton(Rectangle bounds, int fontSize, Canvas canvas) {
		super(canvas);
		this.bounds = bounds;
		font = new Font("arial", 0, fontSize);
	}

	public CanvasButton(Rectangle bounds, int fontSize, String text, Canvas canvas) {
		this(bounds, fontSize, canvas);
		this.text = text;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(BackgroundColor);
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
		g.setColor(ForegroundColor);
		g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);

		g.setColor(textColor);
		g.setFont(font);
		g.drawString(text, bounds.x + 2, bounds.y + font.getSize());
	}

	@Override
	public void tick() {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point m = new Point((int) (e.getX() / Toolkit.getDefaultToolkit().getScreenSize().getWidth() * Game.WIDTH), (int) (e.getY() / Toolkit.getDefaultToolkit().getScreenSize().getHeight() * Game.HEIGHT));
		if (bounds.contains(m)) {
			BackgroundColor = Color.DARK_GRAY;
			ForegroundColor = Color.GRAY;
			textColor = Color.LIGHT_GRAY;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Point m = new Point((int) (e.getX() / Toolkit.getDefaultToolkit().getScreenSize().getWidth() * Game.WIDTH), (int) (e.getY() / Toolkit.getDefaultToolkit().getScreenSize().getHeight() * Game.HEIGHT));
		if (bounds.contains(m)) {
			for (int i = 0; i < listeners.size(); i++) {
				listeners.get(i).actionPerformed(new ActionEvent(this, i, "Released"));
			}
			
			BackgroundColor = Color.GRAY;
			ForegroundColor = Color.LIGHT_GRAY;
			textColor = Color.WHITE;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point m = new Point((int) (e.getX() / Toolkit.getDefaultToolkit().getScreenSize().getWidth() * Game.WIDTH), (int) (e.getY() / Toolkit.getDefaultToolkit().getScreenSize().getHeight() * Game.HEIGHT));
		if (bounds.contains(m)) {
			BackgroundColor = Color.GRAY;
			ForegroundColor = Color.LIGHT_GRAY;
			textColor = Color.LIGHT_GRAY;
		} else {
			BackgroundColor = Color.GRAY;
			ForegroundColor = Color.LIGHT_GRAY;
			textColor = Color.WHITE;
		}
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
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

	}

	public void addActionListener(ActionListener action) {
		listeners.add(action);
	}
	
	public void dispose() {
		super.dispose();
		listeners.clear();
	}

}
