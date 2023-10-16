package com.mycompany.a1.gameobjects;

import java.util.Random;
import java.lang.Math;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject {
	private int size;
	private Point location;
	private int color;
	private Random random = new Random();

	public GameObject(int size, int color) {
		// the object should have an x and y that falls between 0 and 1000
		float min = 0;
		float max = 1000;

		// sets the color and the size of the gameObject
		this.size = size;
		this.color = color;
		
		// Set random location within the length and width of map
		float x = min + this.random.nextFloat() * (max - min);
		float y = min + this.random.nextFloat() * (max - min);

		// set this location
		this.location = new Point();
		this.setLocation(x, y);
	}

	// setter returns color as an int
	public int getColor() {
		return color;
	}

	// Get the X value of location
	public float getLocationX() {
		return this.location.getX();
	}

	// Get the Y value of location
	public float getLocationY() {
		return this.location.getY();
	}

	// setter and getter of size (data type int)
	public int getSize() {
		return this.size;
	}

	// set the color of the game object 
	public void setColor(int color) {
		this.color = color;
	};

	// Takes the X and Y to set location
	public void setLocation(float x, float y) {
		this.location.setX(x);
		this.location.setY(y);
	}
	
	// toString provides a description to the user of the gameObject's
	// location, color and size
	@Override
	public String toString() {
		double x = (double) this.getLocationX();
		double y = (double) this.getLocationY();

		// Rounding and formatting the coordinates
		double rX = Math.round(x * 10.0) / 10.0;
		double rY = Math.round(y * 10.0) / 10.0;
		String locationStr = "loc=" + rX + "," + rY;

		int color = this.getColor();
		String colorStr = "color=[" + ColorUtil.red(color) + "," + ColorUtil.green(color) + "," + ColorUtil.blue(color)
				+ "]";

		String sizeStr = "size=" + this.getSize();

		return locationStr + " " + colorStr + " " + sizeStr;
	}
}
