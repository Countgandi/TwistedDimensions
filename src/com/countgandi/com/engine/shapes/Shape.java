package com.countgandi.com.engine.shapes;

public abstract class Shape {

	protected float x, y, width, height, rotation;

	public Shape(float x, float y, float width, float height, float rotation) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rotation = rotation;
	}

	public Shape(float x, float y, float width, float height) {
		this(x, y, width, height, 0);
	}

	public void rotate(float rotation) {
		this.rotation = rotation;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getRotation() {
		return rotation;
	}

}
