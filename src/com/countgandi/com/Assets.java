package com.countgandi.com;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Assets {

	public static BufferedImage[] tileSet = loadImageSheet(16, 16, "/pics/tileset.png");
	public static BufferedImage[] player = loadImageSheet(7, 12, "/pics/Player.png");
	public static BufferedImage icon = loadImage("/pics/icon.png");

	public static final class Guis {
		public static BufferedImage inventory = loadImage("/pics/guis/Inventory.png");
		public static BufferedImage hotbar = loadImage("/pics/guis/hotbar.png");
		public static BufferedImage inventorySelected = loadImage("/pics/guis/inventorySelected.png");
	}

	public static final class Animations {
		public static BufferedImage swordSlash = loadImage("/pics/animations/SwordSlash.png");
	}

	public static final class Entities {
		public static BufferedImage[] giraffe = loadImageSheet(59, 38, "/pics/mobs/giraffe.png");
		public static BufferedImage[] lion = loadImageSheet(18, 9, "/pics/mobs/Lion.png");
		public static BufferedImage[] bat = loadImageSheet(16, 13, "/pics/mobs/Bat.png");
		public static BufferedImage[] dragons = loadImageSheet(54, 64, "/pics/dragon/Dragons.png");
		public static BufferedImage[] duck = loadImageSheet(16, 14, "/pics/mobs/Duck.png");
		public static BufferedImage[] dog = loadImageSheet(11, 7, "/pics/mobs/dog.png");
		public static BufferedImage[] arrow = loadImageSheet(15, 5, "/pics/items/Arrow.png");
		public static BufferedImage blackWidow = loadImage("/pics/mobs/Black_Widow.png");
		public static BufferedImage fireBall = loadImage("/pics/Dimensionbattle/MFire.png");
	}

	public static final class Items {
		// Swords
		public static BufferedImage[] WoodSword = loadImageSheet(16, 16, "/pics/items/WoodSword.png");
		public static BufferedImage[] StoneSword = loadImageSheet(16, 16, "/pics/items/StoneSword.png");
		public static BufferedImage[] AmethystSword = loadImageSheet(16, 16, "/pics/items/AmethystSword.png");
		public static BufferedImage[] FrostbiteSword = loadImageSheet(16, 16, "/pics/items/FrostbiteSword.png");
		public static BufferedImage[] BeachSword = loadImageSheet(16, 16, "/pics/items/BeachSword.png");

		// Armor
		private static BufferedImage[] armor = loadImageSheet(16, 64, "/pics/items/armor.png");
		public static BufferedImage[] LeatherArmor = loadImageSheet(16, 16, armor[0]);
		public static BufferedImage[] MetalArmor = loadImageSheet(16, 16, armor[1]);
		public static BufferedImage[] AmethystArmor = loadImageSheet(16, 16, armor[2]);
		public static BufferedImage[] FrostbiteArmor = loadImageSheet(16, 16, armor[3]);

		// Trinkets
		public static BufferedImage[] WoodenRing = loadImageSheet(16, 16, "/pics/items/trinkets.png");

		// Bows
		public static BufferedImage[] WoodBow = loadImageSheet(20, 16, "/pics/items/Bow.png");
		public static BufferedImage[] AmethystBow = loadImageSheet(20, 16, replaceImageColor(loadImage("/pics/items/Bow.png"), 0xFF7F3300, 0xFF9000D3));
		public static BufferedImage[] FrostbiteBow = loadImageSheet(20, 16, replaceImageColor(loadImage("/pics/items/Bow.png"), 0xFF7F3300, 0xFF00FFFF));

		// Foods
		public static BufferedImage[] ChickenWings = loadImageSheet(16, 16, "/pics/items/ChickenWing.png");

		// Other
	}

	public static final class Objects {
		public static BufferedImage BossDungeon = loadImage("/pics/objects/BossDungeon.png");
		public static BufferedImage Chest = loadImage("/pics/objects/chest.png");
		public static BufferedImage OverworldTree = loadImage("/pics/objects/tree.png");
		public static BufferedImage SavannahTree = loadImage("/pics/objects/SavannahTree.png");
		public static BufferedImage OverworldBush = loadImage("/pics/objects/bush.png");
		public static BufferedImage DimensionPortal = loadImage("/pics/spriteSheets/portals.png");
	}

	public static final class Menu {
		public static BufferedImage splash1 = loadImage("/pics/MenuSplash.png");
		public static BufferedImage splash2 = loadImage("/pics/MenuSplash2.png");
		public static BufferedImage splash3 = loadImage("/pics/MenuSplash3.png");
	}

	public static BufferedImage loadImage(String src) {
		try {
			return ImageIO.read(Assets.class.getClassLoader().getResourceAsStream(src.substring(1)));
		} catch (Exception e) {
			System.err.println("Could not load image with src: " + src);
			e.printStackTrace();
		}
		return null;
	}

	public static BufferedImage[] loadImageSheet(int tileWidth, int tileHeight, BufferedImage img) {
		BufferedImage[] imgs = new BufferedImage[(img.getWidth() / tileWidth) * (img.getHeight() / tileHeight)];
		int id = 0;
		for (int y = 0; y < img.getHeight(); y += tileHeight) {
			for (int x = 0; x < img.getWidth(); x += tileWidth) {
				imgs[id] = img.getSubimage(x, y, tileWidth, tileHeight);
				id++;
			}
		}
		return imgs;
	}

	public static BufferedImage[] loadImageSheet(int tileWidth, int tileHeight, String src) {
		return loadImageSheet(tileWidth, tileHeight, loadImage(src));
	}

	public static BufferedImage replaceImageColor(BufferedImage img, int colorToBeReplaced, int colorReplacement) {
		int[] pixels = new int[img.getWidth() * img.getHeight()];
		img.getRGB(0, 0, img.getWidth(), img.getHeight(), pixels, 0, img.getWidth());
		for (int i = 0; i < pixels.length; i++) {
			if (pixels[i] == colorToBeReplaced) {
				pixels[i] = colorReplacement;
			}
		}
		img.setRGB(0, 0, img.getWidth(), img.getHeight(), pixels, 0, img.getWidth());
		return img;
	}

}
