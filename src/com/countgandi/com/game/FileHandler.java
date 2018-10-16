package com.countgandi.com.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.countgandi.com.game.dimensions.Dimension;
import com.countgandi.com.game.entities.Entity;
import com.countgandi.com.game.entities.Player;
import com.countgandi.com.guis.InventoryGui;

public class FileHandler {

	public static String path;
	private static ArrayList<File> files = new ArrayList<File>();

	public FileHandler(String worldName) {
		String os = System.getProperty("os.name");
		if (os.contains("Windows")) {
			path = System.getenv("AppData") + "\\TwistedDimensions";
			new File(path + "\\" + worldName).mkdirs();
		}
		System.out.println("Path:" + path);

		// the data for a single player
		addFile(new File(path + "\\" + worldName + "\\", "playerData.data"));
		// For all objects/entities
		addFile(new File(path + "\\" + worldName + "\\", "dimensionData.data"));
		// For all achievements and stuff that effects everything
		addFile(new File(path + "\\" + worldName + "\\", "worldData.data"));

	}

	public static boolean load(Handler handler) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException {
		loadPlayer(files.get(0), handler);
		System.out.println("Loading dimensions...");
		loadDimension(files.get(1), handler);

		return false;
	}

	private static void loadPlayer(File file, Handler handler) throws FileNotFoundException, IOException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		@SuppressWarnings("deprecation")
		String[] strings = in.readLine().split(";");
		for (int i = 0; i < strings.length; i++) {
			String[] s = strings[i].split(":");
			if (s[0].startsWith("level")) {
				handler.getPlayer().level = Integer.parseInt(s[1]);
			} else if (s[0].startsWith("dimension")) {
				handler.getDimensionHandler().loadDimension(Integer.parseInt(s[1]));
			} else if (s[0].startsWith("exp")) {
				handler.getPlayer().setExp(Integer.parseInt(s[1]));
			} else if (s[0].startsWith("maxExp")) {
				handler.getPlayer().maxExperience = Integer.parseInt(s[1]);
			} else if (s[0].startsWith("health")) {
				handler.getPlayer().setHealth(Integer.parseInt(s[1]));
			} else if (s[0].startsWith("maxHealth")) {
				handler.getPlayer().maxHealth = Integer.parseInt(s[1]);
			} else if (s[0].startsWith("maxEnergy")) {
				handler.getPlayer().maxEnergy = Integer.parseInt(s[1]);
			} else if (s[0].startsWith("x")) {
				handler.getPlayer().setX(Float.parseFloat(s[1]));
			} else if (s[0].startsWith("y")) {
				handler.getPlayer().setY(Float.parseFloat(s[1]));
			}
		}
		in.close();
	}

	private static void loadDimension(File file, Handler handler) throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		@SuppressWarnings("deprecation")
		String[] ogStrings = in.readLine().split("#");
		for(int j = 0; j < handler.getDimensionHandler().dimensions.size(); j++) {
			String[] strings = ogStrings[j].split(";");
			System.out.println("Loading dimension: " + j);
			for (int i = 0; i < strings.length; i++) {
				String[] s = strings[i].split(":");
				if (s[0].startsWith("entity")) {
					@SuppressWarnings("unchecked")
					Class<? extends Entity> cc = (Class<? extends Entity>) Class.forName(s[1].trim());
					if(!cc.equals(Player.class)) {
					float x = Float.parseFloat(s[2]);
					float y = Float.parseFloat(s[3]);
					Entity entity = (Entity) cc.getConstructors()[0].newInstance(x, y, handler);
					entity.setHealth(Integer.parseInt(s[4]));
					handler.getDimensionHandler().dimensions.get(j).entities.add(entity);
					}
				}
			}
		}
		in.close();
	}

	public static boolean save(Handler handler) throws IOException {
		savePlayer(files.get(0), handler);
		saveDimensions(files.get(1), handler);
		saveWorld(files.get(2), handler);
		return false;
	}

	private static void savePlayer(File file, Handler handler) throws FileNotFoundException, IOException {
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
		String text = "";

		text += "level:" + handler.getPlayer().level + ";";
		text += "dimension:" + handler.getDimensionHandler().dimension + ";";
		text += "exp:" + handler.getPlayer().getExp() + ";";
		text += "maxExp:" + handler.getPlayer().maxExperience + ";";
		text += "health:" + handler.getPlayer().getHealth() + ";";
		text += "maxHealth:" + handler.getPlayer().maxHealth + ";";
		text += "maxEnergy:" + handler.getPlayer().maxHealth + ";";
		text += "x:" + handler.getPlayer().getX() + ";";
		text += "y:" + handler.getPlayer().getY() + ";";
		for (int i = 0; i < InventoryGui.items.size(); i++) {
			text += "item:" + InventoryGui.items.get(i).getClass().getName() + ";";
		}

		os.write(text.getBytes());
		os.close();
	}

	private static void saveDimensions(File file, Handler handler) throws FileNotFoundException, IOException {
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
		String text = "";

		System.out.println("Saving dimensions...");
		for (int i = 0; i < handler.getDimensionHandler().dimensions.size(); i++) {
			Dimension dimension = handler.getDimensionHandler().dimensions.get(i);
			System.out.println("Saving dimension:" + i);
			for (int j = 0; j < dimension.entities.size(); j++) {
				Entity entity = dimension.entities.get(j);
				text += "entity: " + entity.getClass().getName() + ":" + entity.getX() + ":" + entity.getY() + ":" + entity.getHealth() + ";";
			}
			text += "#";
		}

		os.write(text.getBytes());
		os.close();
	}

	private static void saveWorld(File file, Handler handler) {

	}

	private static void addFile(File file) {
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		files.add(file);
	}

}
