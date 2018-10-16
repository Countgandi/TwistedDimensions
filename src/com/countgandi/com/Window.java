package com.countgandi.com;

import javax.swing.JFrame;

public class Window {

	public JFrame frame;

	public Window(int width, int height, int screen, String title, Game game) {
		frame = new JFrame("Test");
		
		frame.add(game);
		frame.setIconImage(Assets.icon);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);

	}

}
