package rbadia.voidspace.model;

import java.awt.Rectangle;

public class Platform extends Rectangle {
	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_SPEED = 2;
	private static final int WIDTH = 44;
	private static final int HEIGHT = 4;
	
	private boolean isPlatformMoveUp = true;

	public Platform(int xPos, int yPos) {
		super(xPos, yPos, WIDTH, HEIGHT);
	}
	
	public int getDefaultSpeed() {
		return DEFAULT_SPEED;
	}
	
	public boolean getIsPlatformMoveUp() {
		return isPlatformMoveUp;
	}
	
	public void setIsPlatformMoveUp(boolean movingUp) {
		this.isPlatformMoveUp = movingUp;
	}
}
