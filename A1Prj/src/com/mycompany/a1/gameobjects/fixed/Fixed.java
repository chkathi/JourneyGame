package com.mycompany.a1.gameobjects.fixed;

import com.mycompany.a1.gameobjects.GameObject;
import java.util.Random; 

public abstract class Fixed extends GameObject {
	public Fixed(int size, int color) {
		// Super should be first line of code in constructor
		// color and size are passed up the gameObject constructor
		super(size, color);
	}
	
	// Fixed objects should not have the ability to set their own location
	public void setLocation() {}
}
