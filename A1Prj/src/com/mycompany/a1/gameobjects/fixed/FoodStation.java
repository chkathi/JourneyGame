package com.mycompany.a1.gameobjects.fixed;

import com.codename1.charts.util.ColorUtil;
import java.util.Random;

public class FoodStation extends Fixed {
	private int capacity;

	public FoodStation(int size) {
		// all FoodStations are preset to green color
		// size is determined in game world
		super(size, ColorUtil.GREEN);

		// make the size proportional
		this.setCapacity(size);
	}

	// capacity is half the size of the food station 
	public void setCapacity(int size) {
		this.capacity = size / 2;
	}

	// returns the food capactity
	public int getFoodCapacity() {
		return this.capacity;
	}
	
	// get's the gameObject.toString() and adds a little more description as well
	@Override
	public String toString() {
		String parentDesc = super.toString();

		String capacityStr = "capacity=" + this.getFoodCapacity();

		return "FoodStation:  " + parentDesc + " " + capacityStr;
	}

}