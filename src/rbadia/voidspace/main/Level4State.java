package rbadia.voidspace.main;



import java.awt.Graphics2D;

import rbadia.voidspace.graphics.GraphicsManager;
import rbadia.voidspace.model.Asteroid;
import rbadia.voidspace.sounds.SoundManager;

public class Level4State extends Level2State{   //Change the extension to Level3State
	private boolean moreThanOnce = true;
	private boolean once = true;
	private int counter = 0;
	private Asteroid asteroid2;

	private static final long serialVersionUID = 1L;

	public Level4State(int level, MainFrame frame, GameStatus status, LevelLogic gameLogic, InputHandler inputHandler,
			GraphicsManager graphicsMan, SoundManager soundMan) {
		super(level, frame, status, gameLogic, inputHandler, graphicsMan, soundMan);
		
	}      
	
	@Override
	public void doStart() {	
		super.doStart();
		newAsteroid2(this);
		setStartState(GETTING_READY);
		setCurrentState(getStartState());
	}
	
	@Override
	public void updateScreen() {
		super.updateScreen();
		newPowerUp(this);
		
		if(moreThanOnce) {
			drawPowerUp();
		}
		if (checkPowerUpMegaManCollisions()) {
			powerUp.setLocation(-10, 0);
		}
		
		checkPowerUpMegaManCollisions();
		drawAsteroid2();
	}
	
	public Asteroid newAsteroid2(Level1State screen){
		int xPos = (int) (SCREEN_WIDTH - Asteroid.WIDTH);
		int yPos;
		do{
			yPos = rand.nextInt((int)(SCREEN_HEIGHT - Asteroid.HEIGHT- 32));
			asteroid2 = new Asteroid(xPos, yPos);
		} while (asteroid2.intersects(asteroid));
		
		return asteroid2;
	}
	
	protected void drawAsteroid2() {
		Graphics2D g2d = getGraphics2D();
//		GameStatus status = getGameStatus();
		if((asteroid2.getX() + asteroid2.getWidth() > 0)){
			asteroid2.translate(-asteroid2.getSpeed(), 0);
			getGraphicsManager().drawAsteroid(asteroid2, g2d, this);	
		}
//		else {
//			long currentTime = System.currentTimeMillis();
//			if((currentTime - lastAsteroidTime) > NEW_ASTEROID_DELAY){
//				// draw a new asteroid
//				lastAsteroidTime = currentTime;
//				status.setNewAsteroid(false);
//				asteroid.setLocation((int) (SCREEN_WIDTH - asteroid.getPixelsWide()),
//						(rand.nextInt((int) (SCREEN_HEIGHT - asteroid.getPixelsTall() - 32))));
//			}
//
//			else{
//				// draw explosion
//				getGraphicsManager().drawAsteroidExplosion(asteroidExplosion, g2d, this);
//			}
//		}
	}
	
	@Override
	protected void checkMegaManAsteroidCollisions() {
		GameStatus status = getGameStatus();
		if(asteroid.intersects(megaMan)){
			status.setLivesLeft(status.getLivesLeft() - 1);
			removeAsteroid(asteroid);
		}
		else if(asteroid2.intersects(megaMan)){
			status.setLivesLeft(status.getLivesLeft() - 1);
			// TODO explode when second asteroid collides with MegaMan
			removeAsteroid(asteroid2);
		}
	}
	
	protected boolean checkPowerUpMegaManCollisions() {
		GameStatus status = getGameStatus();
		if(powerUp.intersects(megaMan)){
			if (once) {
				status.setLivesLeft(status.getLivesLeft() + 5);
				once = false;
			}
			super.removePowerUp(powerUp);
			moreThanOnce = false;
			return true;
		}	
		else {
			return false;
		}
	}
	
	@Override
	protected void drawAsteroid() {
		Graphics2D g2d = getGraphics2D();
		if((asteroid.getX() + asteroid.getPixelsWide() > 0)) {
			if (counter < 20) { 
				asteroid.translate(-asteroid.getSpeed(), asteroid.getSpeed()/2);
			}
			else if (counter >= 20) {
				asteroid.translate(-asteroid.getSpeed(), -asteroid.getSpeed()/2);
			}
			counter++;
				if (counter == 40) 				
					counter = 0;	
			getGraphicsManager().drawAsteroid(asteroid, g2d, this);	
		}
		else {
			long currentTime = System.currentTimeMillis();
			if((currentTime - lastAsteroidTime) > NEW_ASTEROID_DELAY){

				asteroid.setLocation(SCREEN_WIDTH - asteroid.getPixelsWide(),
						rand.nextInt(SCREEN_HEIGHT - asteroid.getPixelsTall() - 32));
				newAsteroid2(this);
			}
			else {
				// draw explosion
				getGraphicsManager().drawAsteroidExplosion(asteroidExplosion, g2d, this);
			}
		}	
	}
	
}
