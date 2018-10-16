package com.countgandi.com.game.map.tiles;

public class AnimatedTile extends Tile {
	
	private int timer = 0;
	private AnimationId animationId;
	private int currentAnimation = 0;

	public AnimatedTile(int x, int y, AnimationId id) {
		super(x, y, id.animation[0]);
		this.animationId = id;
	}
	
	public void tick() {
		timer ++;
		if(timer >= animationId.speed) {
			if(currentAnimation < animationId.animation.length - 1) {
				currentAnimation ++;
				id = animationId.animation[currentAnimation];
			} else {
				currentAnimation = 0;
				id = animationId.animation[currentAnimation];
			}
		}
	}
	
	public enum AnimationId {
		
		Water(10, new int[] {29, 30, 31, 32});
		
		public int[] animation;
		public int speed;
		private AnimationId(int speed, int[] animation) {
			this.animation = animation;
			this.speed = speed;
		}
	}

}
