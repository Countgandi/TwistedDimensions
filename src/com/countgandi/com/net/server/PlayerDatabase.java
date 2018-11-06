package com.countgandi.com.net.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.countgandi.com.game.entities.MPlayer;

public class PlayerDatabase {

	public ArrayList<MPlayer> playersInDatabase = new ArrayList<MPlayer>();

	public PlayerDatabase(ServerSideHandler handler) {
		/*try {
			File folder = new File(PlayerDatabase.class.getProtectionDomain().getCodeSource().getLocation().toURI());
			if (folder.exists()) {
				File[] files = folder.listFiles();

				for (int i = 0; i < files.length; i++) {
					String[] s = loadFile(files[i]);
					MPlayer player = new MPlayer(s[0].trim(), Float.parseFloat(s[3]), Float.parseFloat(s[4]), handler);
					player.dimension = Integer.parseInt(s[1]);
					player.level = Integer.parseInt(s[2]);
					String[] ss = s[5].split(",");
					for(int j = 0; j < ss.length; j ++) {
						player.armorStats[j] = Integer.parseInt(ss[j]);
					}
					ss = s[6].split(",");
					for(int j = 0; j < ss.length; j ++) {
						player.baseDamage[j] = Integer.parseInt(ss[j]);
					}
					player.maxEnergy = Integer.parseInt(s[7]);
					player.setExp(Integer.parseInt(s[8]));
					player.maxHealth = Integer.parseInt(s[9]);
					player.setHealth(Integer.parseInt(s[10]));
					playersInDatabase.add(player);
				}
			} else {
				folder.mkdirs();
			}

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}*/
	}
	
	public boolean addPlayerToDatabase(MPlayer player) {
		for(MPlayer p:playersInDatabase) {
			if(p.equals(player)) {
				return false;
			}
		}
		playersInDatabase.add(player);
		
		return true;
	}

	public String[] loadFile(File file) {
		String[] strings = null;
		try {
			ObjectInputStream os = new ObjectInputStream(new FileInputStream(file));
			byte[] bytes = new byte[1024];
			os.read(bytes);
			os.close();
			strings = new String(bytes).split("\n");
		} catch (IOException e) {
			System.err.println("Well this can't be good, file does not exist...");
			e.printStackTrace();
		}
		return strings;
	}
	
	public void writeToFile(String[] strings, File file) {
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
			int j = 0;
			for(int i = 0; i < strings.length; i++) {
				j += strings[i].length();
			}
			byte[] bytes = new byte[j];
			String s = "";
			for(int i = 0; i < strings.length; i++) {
				s += strings[i];
			}
			bytes = s.getBytes();
			os.write(bytes);
			os.close();
		} catch (IOException e) {
			System.err.println("Well this can't be good, file does not exist...");
			e.printStackTrace();
		}
	}

	public MPlayer getPlayer(MPlayer player) {
		for(int i = 0; i < playersInDatabase.size(); i++) {
			if(player.username.equals(playersInDatabase.get(i).username)) {
				return player;
			}
		}
		return null;
	}

}
