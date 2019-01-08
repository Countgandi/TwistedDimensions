package com.countgandi.com.engine.shapes;

public class Rectangle extends Shape {

	public Rectangle(float x, float y, float width, float height) {
		super(x, y, width, height);
	}
	
	public boolean intersects(Circle circle) {
		
		return false;
	}
	
	public boolean intersects(Rectangle rectangle) {
		
		return false;
	}
	
}
