package com.countgandi.com.engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class CanvasTextField extends CanvasComponent {

	public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
	private String text = "";
	protected int fontSize = 12;
	private int textWidth, timer, graphicsWidth, graphicsHeight;
	protected boolean shift = false, showingCaret = true, extendedState = false;
	public int caretSpeed = 30;
	protected Color caretColor = Color.WHITE, textColor = Color.WHITE, BackgroundColor = Color.BLACK, ForegroundColor = Color.WHITE;

	public CanvasTextField(int x, int y, int width, Canvas canvas) {
		super(canvas);
		setBounds(new Rectangle(x, y, width, fontSize));
	}

	public CanvasTextField(int x, int y, int width, Canvas canvas, int graphicsWidth, int graphicsHeight) {
		super(canvas);
		this.graphicsHeight = graphicsHeight;
		this.graphicsWidth = graphicsWidth;
		setBounds(new Rectangle(x, y, width, fontSize));
		extendedState = true;

	}

	@Override
	public void render(Graphics g) {
		g.setFont(new Font("arial", 0, fontSize));
		g.setColor(BackgroundColor);
		g.fillRect(bounds.x - 4, bounds.y, bounds.width + 8, bounds.height);
		g.setColor(ForegroundColor);
		g.drawRect(bounds.x - 4, bounds.y, bounds.width + 8, bounds.height);
		g.setColor(textColor);
		g.drawString(getText(), bounds.x + 1, bounds.y + bounds.height - fontSize / 8);
		textWidth = g.getFontMetrics().stringWidth(text);
		if (focused && showingCaret) {
			g.drawRect(bounds.x + 1 + textWidth, bounds.y + 3, 2, bounds.height - 6);
		}
	}

	@Override
	public void tick() {
		timer++;
		if (timer > caretSpeed) {
			showingCaret = !showingCaret;
			timer = 0;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_SHIFT) {
			shift = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_SHIFT) {
			shift = false;
		}
		if (focused && key == KeyEvent.VK_BACK_SPACE) {
			if (text.length() > 0) {
				text = text.substring(0, text.length() - 1);
			}
		} else if (focused) {
			if (text.length() > 0) {
				if (!(textWidth < bounds.getWidth() - textWidth / text.length())) {
					return;
				}
			}
			if (key == KeyEvent.VK_SPACE) {
				text += " ";
			}
			// Letters
			for (int i = KeyEvent.VK_A; i <= KeyEvent.VK_Z; i++) {
				if (key == i) {
					if (shift) {
						text += alphabet.toUpperCase().charAt(i - KeyEvent.VK_A);
					} else {
						text += alphabet.charAt(i - KeyEvent.VK_A);
					}
				}
			}
			// Numbers
			for (int i = KeyEvent.VK_0; i <= KeyEvent.VK_9; i++) {
				if (!shift && key == i) {
					text += i - KeyEvent.VK_0;
				}
			}
			// Other characters
			switch (e.getKeyCode()) {
			case KeyEvent.VK_EXCLAMATION_MARK:
				text += "!";
				break;
			case KeyEvent.VK_COLON:
				text += ":";
				break;
			case KeyEvent.VK_COMMA:
				text += ",";
				break;
			case KeyEvent.VK_PLUS:
				text += "+";
				break;
			case KeyEvent.VK_MINUS:
				text += "-";
				break;
			case KeyEvent.VK_UNDERSCORE:
				text += "_";
				break;
			case KeyEvent.VK_PERIOD:
				text += ".";
				break;
			case KeyEvent.VK_SEMICOLON:
				text += ";";
				break;
			case KeyEvent.VK_QUOTE:
				text += "\"";
				break;
			case KeyEvent.VK_ASTERISK:
				text += "*";
				break;
			case KeyEvent.VK_AMPERSAND:
				text += "&";
				break;
			case KeyEvent.VK_CIRCUMFLEX:
				text += "^";
				break;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (extendedState) {
			Point m = new Point((int) (e.getX() / Toolkit.getDefaultToolkit().getScreenSize().getWidth() * graphicsWidth), (int) (e.getY() / Toolkit.getDefaultToolkit().getScreenSize().getHeight() * graphicsHeight));
			if (bounds.contains(m)) {
				focused = true;
			} else {
				focused = false;
			}
		} else {
			if (bounds.contains(e.getPoint())) {
				focused = true;
			} else {
				focused = false;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

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

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
		setBounds(new Rectangle(bounds.x, bounds.y, bounds.width, fontSize + 2));
	}

	public int getTextWidth() {
		return textWidth;
	}

	public void setTextWidth(int textWidth) {
		this.textWidth = textWidth;
	}

	public Color getCaretColor() {
		return caretColor;
	}

	public void setCaretColor(Color caretColor) {
		this.caretColor = caretColor;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public Color getBackgroundColor() {
		return BackgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		BackgroundColor = backgroundColor;
	}

	public Color getForegroundColor() {
		return ForegroundColor;
	}

	public void setForegroundColor(Color foregroundColor) {
		ForegroundColor = foregroundColor;
	}

}
