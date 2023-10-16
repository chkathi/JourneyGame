package com.mycompany.a1;

import java.util.ArrayList;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.mycompany.a1.gameobjects.GameObject;
import com.mycompany.a1.gameobjects.fixed.*;
import com.mycompany.a1.gameobjects.moveable.*;

public class GameWorld {
	private int screenWidth;
	private int screenLength;
	private int time;
	private int highestFlagReached;
	private int lives;
	private ArrayList<GameObject> objects;

	private char exitFlag;
	private Random r = new Random();

	public GameWorld() {
		// Set highest flag count so we know which flag collision will cause the use to win the game
		this.highestFlagReached = 4;
		this.lives = 3;
		this.screenLength = 1000; 
		this.screenWidth = 1000; 
		this.exitFlag = '-';
	}

	public void accelerate() {
		Ant ant;
		int curSpeed = 0;
		int currMaxSpeed = 0;
		
		// Get the ant and then increase the speed
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Ant) {
				ant = (Ant) objects.get(i);

				curSpeed = ant.getSpeed();
				currMaxSpeed = ant.getCurrMaxSpeed();

				// Ant speed cannot exceed the currMaxSpeed
				// currMaxSpeed is the maxSpeed based on the ant's health
				if ((curSpeed + 5) > currMaxSpeed) {
					// Cannot exceed currMaxSpeed
					curSpeed = currMaxSpeed;
					System.out.print("Max Speed of " + currMaxSpeed + " is reached!");
					System.out.print("To go faster recover health!" + '\n');
				} else {
					curSpeed += 5;
					System.out.print("Ant Speed increased by 5 to " + curSpeed + '\n');
				}

				// set speed and put ant back into the array
				ant.setSpeed(curSpeed);
				objects.set(i, ant);
				break;
			}
		}

		
	}

	public void brake() {
		// decrease ant speed by 5
		Ant ant;
		int curSpeed = 0;
	
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Ant) {
				ant = (Ant) objects.get(i);

				curSpeed = ant.getSpeed();

				if ((curSpeed - 5) <= 0) {
					// we cannot set the speed to negative so the speed remains at 0
					curSpeed = 0;
					System.out.print("Ant Speed is 0. Cannot reduce further!" + '\n');
				} else {
					curSpeed -= 5;
					System.out.print("Ant Speed decreased by 5 to " + curSpeed + '\n');
				}

				ant.setSpeed(curSpeed);
				objects.set(i, ant);
				break;
			}
		}
	}

	public void consumptionRate() {
		// set the foodConsumptionRate of the ant to 2
		// every tick the food will decrease by this amount
		Ant ant;
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Ant) {
				ant = (Ant) objects.get(i);
				ant.setFoodConsumptionRate(2);
				objects.set(i, ant);
				break;
			}
		}

		System.out.println("FoodConsumptionRate of Ant set to 2.");
		System.out.println("");
	}

	public void display() {
		// check display information
		// 1) number of lives
		// 2) current clock value (1 game clock == 1 tick)
		// 3) highest flag number the ant has reached sequentially
		// 4) the ant's current food level
		// 5) the ant's health level

		Ant ant = new Ant();
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Ant) {
				ant = (Ant) objects.get(i);
				break;
			}
		}

		System.out.println("Displaying game information: ");
		System.out.println("Time Passed: " + this.getTime());
		System.out.println("Number of Lives: " + this.getLives());
		System.out.println("Highest Flag Reached: " + ant.getLastFlagReached());
		System.out.println("Ant's Food Level: " + ant.getFoodLevel());
		System.out.println("Ant's Health Level: " + ant.getHealthLevel());
		System.out.println("");
	}

	public void error() {
		// if the user enter a different character than the character in the program
		// error message will show all possible commands use can type in the prompt
		System.out.println("Enter one of the following valid commands for the corresponding function:");

		System.out.println("'a' for accelerate(), 'b' for brake(), 'l' for turnLeft()");
		System.out
				.println("'r' for turnRight(), 'c' for consumptionRate(), 'a number between 1-9' for flagCollision()");
		System.out.println("'f' for foodCollision(), 'g' for spiderCollision(), 't' for tick()");
		System.out.println("'d' for display(), 'm' for map(), 'x' for exit()");

		System.out.println("");
	}

	public void exit() {
		// Presents a message that the user has exited the game and ends the program
		System.out.println("Thank you for playing! User has exited the game!" + '\n');
		System.exit(0);
	}

	public void flagCollision(int flagNo) {
		Ant ant;
		int value = 0;
		boolean isSeq = false;
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Ant) {
				ant = (Ant) objects.get(i);
				value = ant.getLastFlagReached();

				// if flag is the next highestFlag then the lastFlagReached is updated
				if (value + 1 == flagNo) {
					ant.setLastLastFlagReached(flagNo);
					objects.set(i, ant);
					isSeq = true;
				}
				break;
			}
		}

		if (isSeq) { // checks for whether or not flag collision happens in order
			if (flagNo == this.highestFlagReached) {
				// Last flag in the game... tells the user they won in the certain time and ends the program
				System.out.println("Last Flag Collision has occured...");
				System.out.println("Congrats! You won the game!");
				System.out.println("Time it took to complete the game was " + this.time + " ticks" + '\n');
				System.exit(0);
			}
			
			// flag collision is displayed
			System.out.println("Flag Collision has occured...");
			System.out.println("Ant's new highest flag is " + flagNo + '\n');
			return;
		}

		// isSeq == false and tells the user to collidein the right order
		System.out.println("Collide in the flags in the right order.." + '\n');
	}

	public void foodCollision() {
		// Max and min size of the foodStation 
		int min = 10;
		int max = 40;

		// Initialize a random size for our new FoodStation 
		int size = r.nextInt(max - min) + min;
		FoodStation newFoodStation = new FoodStation(size);
		
		// Place holder FoodStation and Ant
		FoodStation foodStation = null;
		Ant ant = null;
		int capacity = 0;
		
		
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof FoodStation) {
				foodStation = (FoodStation) objects.get(i);

				// if the foodStation is empty then we look for another
				if (foodStation.getFoodCapacity() == 0) {
					continue;
				}

				// save this capacity to add to the ant
				capacity = foodStation.getFoodCapacity();

				// Change the color to lighter green
				int lighterGreen = ColorUtil.rgb(0, 100, 0);
				foodStation.setColor(lighterGreen);

				// reduce the capacity to zero
				foodStation.setCapacity(0);
				objects.set(i, foodStation);
				break;
			}
		}

		// Add the new food Station
		objects.add(newFoodStation);

		// takes the saved capacity and increases the foodLevel of the ant
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Ant) {
				ant = (Ant) objects.get(i);
				capacity += ant.getFoodLevel();
				ant.setFoodLevel(capacity);

				objects.set(i, ant);
				break;
			}
		}

		// tells the user how much the collision occured and how much the ant has for food
		System.out.println("Food Collision has occured...");
		System.out.println("Ant Food Level increased to " + capacity);
		System.out.println("New Food Station added!!" + '\n');
	}
	
	// sets the ExitFlag for when user wants to exit the game
	public char getExitFlag() {
		return this.exitFlag;
	}

	// gets the number of lives for display
	public int getLives() {
		return this.lives;
	}
	
	// gets the game's screenLength
	public int getScreenLength() {
		return this.screenLength;
	}

	// gets the game's screenWidth
	public int getScreenWidth() {
		return this.screenWidth;
	}
	
	// gets time in the game to dispaly
	public int getTime() {
		return this.time;
	}
	
	
	// initalizes the gameworld with the gameobjects
	public void init() {
		// max and min size for the food station 
		int min = 10;
		int max = 40;
		
		// creates a new array for the game objects to be stored
		this.setObjects();

		// initail location of the ant and first flag
		float initialX = 0;
		float initialY = 0;

		// 4 flags in this program are added to the gameObjects
		for (int i = 0; i < this.highestFlagReached; i++) {
			this.objects.add(new Flag(i + 1));

			if (i == 0) {
				initialX = this.objects.get(i).getLocationX();
				initialY = this.objects.get(i).getLocationY();
			}
		}
		
		// one ant with the same location as the flag
		this.objects.add(new Ant());
		this.objects.get(4).setLocation(initialX, initialY);

		// greater than or equal to 2 Spiders
		for (int i = 0; i < 2; i++) {
			objects.add(new Spider());
		}

		// greater than or equal to 2 Food Stations
		for (int i = 0; i < 2; i++) {

			int size = r.nextInt(max - min) + min;

			new FoodStation(size);
			objects.add(new FoodStation(size));
		}
	}

	public void map() {
		// uses polymorphism to display all the description of the classes
		System.out.println("Map of Objects: ");
		for (int i = 0; i < objects.size(); i++) {
			String description = objects.get(i).toString();
			System.out.println(description);
		}

		System.out.println();
	}
	
	// sets the exit flag so user can exit
	public void setExitFlag(char exitFlag) {
		this.exitFlag = exitFlag;
	}

	// reduces lives by 1
	public void setLives() {
		int reduced = this.getLives() - 1;

		if (reduced <= 0) {
			// if all lives are lost then game is over
			System.out.println("You have lost all lives! Game Over!");
			System.exit(0);
		}
		
		this.lives = reduced;
	}
	
	// sets screenLength 
	public void setScreenLength(int screenLength) {
		this.screenLength = screenLength;
	}

	// sets screenWidth
	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}
	
	// increases the time by 1 for every tick
	public void setTime() {
		this.time++;
	}
	
	// creates a new array for when the game world is re initialized
	public void setObjects() {
		this.objects = new ArrayList<GameObject>();
	}
	
	// Spider PRETENDS to collide with the ant
	public void spiderCollision() {
		Ant ant = null; 
		int antIndex = 0; 
		
		// gets the ant and the index where it is stored
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Ant) {
				ant = (Ant) objects.get(i);
				antIndex = i;
				break;
			}
		}
		// gets the color and changes it to a lighter red
		int antRed = ColorUtil.red(ant.getColor());
		int lighterRed = ColorUtil.rgb(antRed - 15, 0, 0);
		ant.setColor(lighterRed);
		
		// tells the user the collision occured and the ant lost health
		System.out.println("Spider has collided with ant. Ant's health is decreased!" + '\n');
		int health = ant.getHealthLevel() - 1;
		ant.setHealthLevel(health); // health will affect the currMaxSpeed of the ant
		
		// if all health goes down then the gameworld is reset and lives goes down by 1 
		if (health <= 0) {
			System.out.println("Ant has lost all its health. GameWorld is reset!!");
			this.setLives();
			this.init();
			return;
		}
		
		// if ant does not have health of 0 then it will be updated in the array
		objects.set(antIndex, ant);
	}

	// Updates the gameworld and the objects within
	public void tick() {
		Ant ant = null;
		Spider spider = null;
		// set the time to time + 1 and then check specifications on what should happen on a
		// tick
		this.time++;
		
		// 1) Spider updates heading
		int heading = this.r.nextInt(10) - 5;
		
		// 2a) Spiders update their position after their heading has been updated
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Spider) {
				spider = (Spider) objects.get(i);	
				spider.setHeading(heading);
				spider.move();
				objects.set(i, spider);
			}
			break;
		}
		
		// 2b) Ant updates its location based on its current speed and heading 
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Ant) {
				ant = (Ant) objects.get(i);
				ant.move();
				objects.set(i, ant);
			}
		}
		
		// 3) the ant's food level is reduced to the amount indicated by its
		// foodConsumptionRate
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Ant) {
				ant = (Ant) objects.get(i);
						
				// decrease the foodLevel of the ant by the consumptionRate
				int currFoodLevel = ant.getFoodLevel() - ant.getFoodConsumptionRate();
				
				// if foodLevel == 0 then the ant can't move
				// if the ant can't move then it loses a life
				// losing a life means the world is reset
				if (currFoodLevel == 0) {
					this.setLives();
					this.init();
					return;
				}
				
				// food level is set and then the ant is updated in the array
				ant.setFoodLevel(currFoodLevel);
				objects.set(i, ant);
				break;
			}
		}
		
		// Tells the user the game world has been updated
		System.out.println("Time has ticked in the game world! ");
		System.out.println("Spiders have updated their heading. " );
		System.out.println("All Spiders and Ant have updated their location. ");
		System.out.println("Ant's food level went decreased by the set food consumptionRate. " + '\n');
	}

	public void turnLeft() {
		// change ant heading by 5 degrees to the left
		Ant ant;
		int heading = 0;
		
		// get and update ant's heading
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Ant) {
				ant = (Ant) objects.get(i);
				heading = ant.getHeading();
				ant.setHeading(heading - 5);
				objects.set(i, ant);
				break;
			}
		}

		// tell user heading is updated
		System.out.println("Heading of Ant moved 5 degrees to left." + '\n');
	}

	public void turnRight() {
		// change ant heading by 5 degrees to the right
		Ant ant;
		int heading = 0;
		
		// get and update ant's heading
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Ant) {
				ant = (Ant) objects.get(i);
				heading = ant.getHeading();
				ant.setHeading(heading + 5);
				objects.set(i, ant);
				break;
			}
		}

		// tell user heading is updated
		System.out.println("Heading of Ant moved 5 degrees to right." + '\n');
	}

}
