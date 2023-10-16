package com.mycompany.a1.gameobjects.moveable;

import com.codename1.charts.util.ColorUtil;

public class Ant extends Moveable implements IFoodie {
	public int maxSpeed;
	public int currMaxSpeed;
	public int foodConsumptionRate;
	public int healthLevel;
	public int lastFlagReached;

	public Ant() {
		// Color and size of the ant is passed all the way up to GameObject
		super(40, ColorUtil.MAGENTA);
		
		// initial heading is 0
		this.setHeading(0);
		
		// ant starts at the first flag
		this.lastFlagReached = 1;
		
		// health is set to 10
		this.healthLevel = 10;
		
		// Max speed is 50 and the currMax is initial the same 
		this.maxSpeed = 50;
		this.currMaxSpeed = maxSpeed; // changes based on the health
		
		// user needs to set this using the 'c' command
		this.foodConsumptionRate = 0; 
	}
	
	// returns int of currMaxSpeed
	public int getCurrMaxSpeed() {
		return this.currMaxSpeed;
	}

	// return the foodConsumption rate
	public int getFoodConsumptionRate() {
		return this.foodConsumptionRate;
	}

	// get current health level 
	public int getHealthLevel() {
		return this.healthLevel;
	}

	// get the last flag reached 
	public int getLastFlagReached() {
		return this.lastFlagReached;
	}

	// gets the max speed 
	public int getMaxSpeed() {
		return this.maxSpeed;
	}

	// sets the foodConsumptionRate (overriding the IFoodie method)
	public void setFoodConsumptionRate(int rate) {
		this.foodConsumptionRate = rate;
	}

	// if the health is changed then also change the currMaxSpeed
	public void setHealthLevel(int health) {
		this.healthLevel = health;

		// Affecting the health directly affects the speed
		//  health / 10 = percentage of health
		// percentage of health * maxSpeed => currMaxSpeed
		
		// ant cannot go faster than this currMaxSpeed
		int currMaxSpeed = (int)(((float) (this.healthLevel) / 10) * (float)this.maxSpeed);
		this.setCurrMaxSpeed(currMaxSpeed);
	}

	// sets the last flag reached
	public void setLastLastFlagReached(int flag) {
		this.lastFlagReached = flag;
	}

	// sets the currMaxSpeed
	public void setCurrMaxSpeed(int currMaxSpeed) {
		this.currMaxSpeed = currMaxSpeed;
	}
	
	// Set the speed, but speed cannot exceed the currMaxSpeed
	public void setSpeed(int speed) {
		// User is told that the speed cannot exceed the currMaxSpeed
		if (speed > this.currMaxSpeed) {
			System.out.println("Cannot exceed current max speed of " + this.currMaxSpeed + '\n');
			super.setSpeed(this.currMaxSpeed);
			return;
		}
		
		// if the currMaxSpeed is not reached then, the speed is set
		super.setSpeed(speed);
	}

	// takes the parent's description and adds a little more to the the description
	// adds maxSpeed and consumptionRate
	@Override
	public String toString() {
		String parentDesc = super.toString();

		String maxSpeedStr = "maxSpeed=" + this.getMaxSpeed();
		String consumpRateStr = "foodConsumptionRate=" + this.getFoodConsumptionRate();

		return "Ant:  " + parentDesc + " " + maxSpeedStr + " " + consumpRateStr;
	}

}
