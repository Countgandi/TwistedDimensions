package com.countgandi.com.guis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.countgandi.com.net.client.ClientSideHandler;

public class ItemStatGui extends Gui {
	
	private String[] text;
	private static Font font = new Font("arial", 0, 6);
	private int x, y, width;

	/**
	 * @param x
	 * @param y
	 * @param text
	 * @param handler
	 */
	public ItemStatGui(int x, int y, String[] text, ClientSideHandler handler) {
		super(handler);
		this.text = text;
		this.x = x + 16;
		this.y = y + 16;
		int largest = 0;
		handler.getGraphics().setFont(font);
		for(int i = 0; i < text.length; i++) {
			String[] strs = text[i].split("/#");
			if(strs[0].length() > text[largest].split("/#")[0].length()) {
				largest = i;
			}
		}
		this.width = handler.getGraphics().getFontMetrics().stringWidth(text[largest].split("/#")[0]);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x, y, width + 2, g.getFontMetrics().getHeight() * text.length + 2);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width + 2, g.getFontMetrics().getHeight() * text.length + 2);
		
		g.setFont(font);
		for(int i = 0; i < text.length; i++) {
			String[] strs = text[i].split("/#");
			if(strs.length > 1) {
				g.setColor(Color.decode("#" + strs[1]));
			} else {
				g.setColor(Color.WHITE);
			}
			g.drawString(strs[0], x + 1, y + g.getFontMetrics().getHeight() * (i + 1) - 1);
		}
	}

}
