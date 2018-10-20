package com.countgandi.com.guis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.countgandi.com.game.Handler;

public class ItemStatGui extends Gui {
	
	private String[] text;
	private int x, y, width;

	/**
	 * @param x
	 * @param y
	 * @param text
	 * @param handler
	 */
	public ItemStatGui(int x, int y, String[] text, Handler handler) {
		super(handler);
		this.text = text;
		this.x = x + 16;
		this.y = y + 16;
		for(int i = 0; i < text.length; i++) {
			String[] strs = text[i].split("/#");
			if(strs[0].length() > this.width) {
				this.width = strs[i].length();
			}
		}
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x, y, width * 4, text.length * 6 + 11);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width * 4, text.length * 6 + 11);
		
		g.setFont(new Font("arial", 0, 6));
		for(int i = 0; i < text.length; i++) {
			String[] strs = text[i].split("/#");
			if(strs.length > 1) {
				g.setColor(Color.decode("#" + strs[1]));
			} else {
				g.setColor(Color.WHITE);
			}
			g.drawString(strs[0], x + 1, y + 6 * (i + 1));
		}
	}

}
