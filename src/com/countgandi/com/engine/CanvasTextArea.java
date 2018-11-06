package com.countgandi.com.engine;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class CanvasTextArea extends CanvasTextField {

	private ByteArrayOutputStream bos = new ByteArrayOutputStream();
	private ByteArrayOutputStream bosErr = new ByteArrayOutputStream();
	private boolean editable = false, newData = false, newDataErr = false;
	public int x = bounds.x, y = bounds.x;
	private ArrayList<String> text = new ArrayList<String>();
	public boolean autoscroll;

	public CanvasTextArea(Rectangle bounds, Canvas canvas) {
		super(0, 0, 0, canvas);
		setBounds(bounds);
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setFont(new Font("arial", 0, fontSize));
		g.setColor(BackgroundColor);
		g.fillRect(bounds.x - 4, bounds.y, bounds.width + 8, bounds.height);
		g.setColor(ForegroundColor);
		g.drawRect(bounds.x - 4, bounds.y, bounds.width + 8, bounds.height);
		g.setColor(caretColor);

		g2d.setClip(bounds);
		g.setColor(textColor);
		
		if(autoscroll && y + text.size() * g.getFontMetrics().getHeight() > bounds.getHeight()) {
			y -= g.getFontMetrics().getHeight();
		}
		for (int i = 0; i < text.size(); i++) {
			g.drawString(text.get(i), x + bounds.x, y + bounds.y + (i + 1) * g.getFontMetrics().getHeight());
		}
		g2d.setClip(0, 0, canvas.getWidth(), canvas.getHeight());

		if (focused && showingCaret && editable) {
			g.drawRect(bounds.x + 1 + g.getFontMetrics().stringWidth(text.get(y)), bounds.y + 3 + g.getFontMetrics().getHeight() + 1 * x, 2, bounds.height - 6);
		}
	}

	@Override
	public void tick() {
		if (editable) {
			super.tick();
		}

		String[] s = new String(bos.toByteArray()).split("\n");
		for (int i = 0; i < s.length; i++) {
			if (s[i].length() > 1) {
				text.add("[Info] " + s[i]);
				if(System.console() != null) {
					System.console().writer().println("[Info] " + s[i]);
				}
				newData = true;
			}
		}
		if (newData) {
			bos.reset();
		}
		s = new String(bosErr.toByteArray()).split("\n");
		for (int i = 0; i < s.length; i++) {
			if (s[i].length() > 1) {
				text.add("[Error] " + s[i]);
				if(System.console() != null) {
					System.console().writer().println("[Error] " + s[i]);
				}
				newDataErr = true;
			}
		}
		if (newDataErr) {
			bosErr.reset();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (editable) {
			super.keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (editable) {
			super.keyReleased(e);
		}
	}

	public void write(byte[] bytes) {
		text.add(new String(bytes));
	}

	public void append(byte[] bytes) {
		text.set(text.size() - 1, text.get(text.size() - 1) + new String(bytes));
	}

	public void write(String string) {
		this.text.add(string);
	}

	public void append(String string) {
		text.set(text.size() - 1, text.get(text.size() - 1) + string);
	}

	public OutputStream getOutputStream() {
		return bos;
	}

	public OutputStream getOutputErrStream() {
		return bosErr;
	}

}
