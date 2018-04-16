package rbadia.voidspace.main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import rbadia.voidspace.graphics.GraphicsManager;
import rbadia.voidspace.model.Asteroid;
import rbadia.voidspace.model.Platform;
import rbadia.voidspace.sounds.SoundManager;

/**
 * Level very similar to LevelState1.  
 * Platforms arranged in triangular form. 
 * Asteroids travel at 225 degree angle
 */
public class Level3State extends Level1State {

	private static final long serialVersionUID = -2094575762243216079L;

	private Asteroid asteroid2;
	private long lastAsteroid2Time;
	
	private Rectangle asteroidExplosion2;

	
	// Constructors
	public Level3State(int level, MainFrame frame, GameStatus status, 
			LevelLogic gameLogic, InputHandler inputHandler,
			GraphicsManager graphicsMan, SoundManager soundMan) {
		super(level, frame, status, gameLogic, inputHandler, graphicsMan, soundMan);
	}
	
	
	@Override
	public void doStart() {
		this.numPlatforms = 4;
		lastAsteroid2Time = -NEW_ASTEROID_DELAY;
		super.doStart();
		newAsteroid2(this);
		setStartState(GETTING_READY);
		setCurrentState(getStartState());
	}
	
	@Override
	public void updateScreen() {
		super.updateScreen();
		movePlatform(platforms);
		drawAsteroid2();
	}

	/**
	 * Creates platforms at different random heights
	 */
	@Override
	public Platform[] newPlatforms(int n){
		platforms = new Platform[n];
		Random rand = new Random();
		for(int i=0; i<n; i++){
			int yPos;
			do {
				yPos = rand.nextInt(300);
			}
			while (yPos < 100);
			this.platforms[i] = new Platform(54*i, yPos);
		}
		return platforms;
	}
	
	@Override
	protected void drawPlatforms() {
		//draw platforms
		Graphics2D g2d = getGraphics2D();
		for(int i=0; i<getNumPlatforms(); i++){
			getGraphicsManager().drawPlatform(platforms[i], g2d, this, i);
		}
	}
	
	/**
	 * Makes platforms move vertically
	 * @param platformsArray all the platforms to be drawn in the level
	 */
	public void movePlatform(Platform[] platformsArray) {
		for(int i=0; i<getNumPlatforms(); i++){
			int yPos = (int) platformsArray[i].getY();
			
			if (yPos <= 100)	{
				platformsArray[i].setIsPlatformMoveUp(false);
			}
			if (yPos >= SCREEN_HEIGHT/2 + 160) {
				platformsArray[i].setIsPlatformMoveUp(true);
			}
			if (platformsArray[i].getIsPlatformMoveUp()) {
				platformsArray[i].translate(0, -platformsArray[i].getDefaultSpeed());
			}
			else {
				platformsArray[i].translate(0, platformsArray[i].getDefaultSpeed());
			}
		}
	}
	
	/**
	 * Creates a second asteroid on the level with a random constant speed
	 * @param screen
	 * @return asteroid2 the new asteroid
	 */
	public Asteroid newAsteroid2(Level1State screen){
		int xPos = (int) (SCREEN_WIDTH - Asteroid.WIDTH);
		int yPos;
		do{
			yPos = rand.nextInt((int)(SCREEN_HEIGHT - Asteroid.HEIGHT- 32));
			asteroid2 = new Asteroid(xPos, yPos);
		} while (asteroid2.intersects(asteroid));
		asteroid2.setRandomSpeed();
		return asteroid2;
	}
	
	/**
	 * Draws a the second asteroid translated or creates a new asteroid2 if one is not on the screen
	 */
	protected void drawAsteroid2() {
		Graphics2D g2d = getGraphics2D();
		GameStatus status = getGameStatus();
		if((asteroid2.getX() + asteroid2.getWidth() > 0)){
			asteroid2.translate(-asteroid2.getSpeed(), 0);
			getGraphicsManager().drawAsteroid(asteroid2, g2d, this);	
		}
		else {
			long currentTime = System.currentTimeMillis();
			if((currentTime - lastAsteroid2Time) > NEW_ASTEROID_DELAY){
				// draw a new asteroid
				lastAsteroid2Time = currentTime;
				status.setNewAsteroid(false);
				asteroid2.setLocation((int) (SCREEN_WIDTH - asteroid2.getPixelsWide()),
						(rand.nextInt((int) (SCREEN_HEIGHT - asteroid2.getPixelsTall() - 32))));
				newAsteroid2(this);
			}

			else{
				// draw explosion
				asteroidExplosion2 = new Rectangle(
						asteroid2.x,
						asteroid2.y,
						asteroid2.getPixelsWide(),
						asteroid2.getPixelsTall());
				getGraphicsManager().drawAsteroidExplosion(asteroidExplosion2, g2d, this);
			}
		}
	}
	
	/**
	 * Checks whether MegaMan intersects with either of the asteroids on the screen
	 */
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
	
}
