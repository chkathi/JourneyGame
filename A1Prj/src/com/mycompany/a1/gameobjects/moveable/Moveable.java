package com.mycompany.a1.gameobjects.moveable;

import com.mycompany.a1.gameobjects.GameObject;
import java.lang.Math;

public abstract class Moveable extends GameObject {
	private int heading;

	// set to reasonable initial reasonable positive value
	private int speed;
	private int foodLevel;

	public Moveable(int size, int color) {
		// size and color are passed to GameObject
		super(size, color);
		
		// Speed and food are initally set
		this.speed = 10;
		this.foodLevel = 15; // set to non zero positive value
	}

	// returns the food level
	public int getFoodLevel() {
		return this.foodLevel;
	}

	// returns the heading 
	// 0 is north and 90 is east
	public int getHeading() {
		return this.heading;
	}

	// returns the speed
	public int getSpeed() {
		return this.speed;
	}
	
	// Updates the position of the moveable using the speed and heading
	// and the current location of the moveable
	public void move() {
		float x = this.getLocationX();
		float y = this.getLocationY();
		double heading = (this.heading);
		
		// Calculate the deltaX and deltaY
		// speed = distance / 1 tick  ... same as speed = distance
		
		// deltaX = speed * Cos(heading)
		float deltaX = (float) ((float) speed * Math.cos(heading));
		
		// deltaY = speed * Sin(heading)
		float deltaY = (float) ((float) speed * Math.sin(heading));

		// Update the position 
		x += deltaX;
		y += deltaY;
		
		// set the position
		this.setLocation(x, y);
	};

	// set foodLevel
	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}

	// set the heading
	public void setHeading(int degree) {
		// if heading is negative then change to equivalent positve heading
		if (degree < 0) {
			this.heading = 360 + (degree % 360);
		}

		// heading remains between 0 and 359
		this.heading = degree % 360;
	}

	// sets the speed of the moveable
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	// takes the parent's description and adds a little more to the the description
	// adds heading and speed
	@Override
	public String toString() {
		String parentDesc = super.toString();
		String headingStr = "heading=" + this.getHeading();
		String speedStr = "speed=" + this.getSpeed();

		return parentDesc + " " + headingStr + " " + speedStr;
	}

}
