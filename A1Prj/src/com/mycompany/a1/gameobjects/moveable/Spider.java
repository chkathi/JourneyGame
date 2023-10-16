package com.mycompany.a1.gameobjects.moveable;

import java.util.Random;
import com.codename1.charts.util.ColorUtil;

public class Spider extends Moveable {
	private Random random;

	public Spider() {
		// size and color are sent up to game object
		super(15, ColorUtil.BLACK);

		// the heading should be random for the spider
		// heading should be between 0 and 359
		int min = 0;
		int max = 359;

		this.random = new Random();

		int heading = this.random.nextInt(max - min) + min;
		this.setHeading(heading);
	}

	// Spider overrides the super.move()
	public void move() {
		// All the movement and updating location is the same
		float x = this.getLocationX();
		float y = this.getLocationY();
		double heading = (double) (this.getHeading());
		float speed = (float) this.getSpeed();

		float deltaX = (float) (speed * Math.cos(heading));
		float deltaY = (float) (speed * Math.sin(heading));

		x += deltaX;
		y += deltaY;
		
		// However if..
		// Spider has collided with the boarder, so the heading is changed
		// Spider will move in the opposite direction
		if (x > 1000 || x < 0 || y > 1000 || y < 0) {	
			// heading is set to the opposite direction and the spider will move away from the boarder
			heading += 180;
			this.setHeading((int) heading);
			return;
		}

		// if location is within boundaries then update location
		this.setLocation(x, y);
	}

	// Just uses the parent's description, but adds the spider label
	@Override
	public String toString() {
		return "Spider:  " + super.toString();
	}
}
