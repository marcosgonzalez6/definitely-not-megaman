package rbadia.voidspace.model;

import java.util.Random;

//import rbadia.voidspace.main.speed;

public class Asteroid extends GameObject {
	private static final long serialVersionUID = 1L;
	
	private int DEFAULT_SPEED = 4;
	
	public static final int WIDTH = 32;
	public static final int HEIGHT = 32;
	
	public Asteroid(int xPos, int yPos) {
		super(xPos, yPos, Asteroid.WIDTH, Asteroid.HEIGHT);
		this.setSpeed(DEFAULT_SPEED);
	}

//	public int getDefaultSpeed(){
//		return DEFAULT_SPEED;
//	}
	
	public void setDefaultSpeed(int speed){
		this.DEFAULT_SPEED = speed;
	}
	
	public void setRandomSpeed() {
		int speed;
		Random rand = new Random();
		do {
			speed = rand.nextInt(DEFAULT_SPEED*2);
		} while (speed == 0);
		this.setSpeed(speed);
	}
}
