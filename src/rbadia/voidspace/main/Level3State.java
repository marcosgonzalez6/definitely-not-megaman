package rbadia.voidspace.main;

import java.awt.Graphics2D;
import java.util.Random;

import rbadia.voidspace.graphics.GraphicsManager;
import rbadia.voidspace.model.Platform;
import rbadia.voidspace.sounds.SoundManager;

/**
 * Level very similar to LevelState1.  
 * Platforms arranged in triangular form. 
 * Asteroids travel at 225 degree angle
 */
public class Level3State extends Level2State {

	private static final long serialVersionUID = -2094575762243216079L;
//	private boolean isPlatformMoveUp = true;

	// Constructors
	public Level3State(int level, MainFrame frame, GameStatus status, 
			LevelLogic gameLogic, InputHandler inputHandler,
			GraphicsManager graphicsMan, SoundManager soundMan) {
		super(level, frame, status, gameLogic, inputHandler, graphicsMan, soundMan);
	}
	
	
	@Override
	public void doStart() {
		this.numPlatforms = 4;
		super.doStart();
		setStartState(GETTING_READY);
		setCurrentState(getStartState());
	}
	
//	@Override
//	public Asteroid newAsteroid(Level1State screen){
//		Random rand = new Random();
//		int xPos = 250;
//		int yPos = 0;
//		switch (rand.nextInt(3)) {
//		case 0: 
//			//xPos = 0;
//			yPos = rand.nextInt((int)(SCREEN_HEIGHT - Asteroid.HEIGHT- 32));
//			break;
//		case 1:
//			xPos = (int) (SCREEN_WIDTH - Asteroid.WIDTH);
//			yPos = rand.nextInt((int)(SCREEN_HEIGHT - Asteroid.HEIGHT- 32));
//			break;
//		case 2:
//			xPos = rand.nextInt((int)(SCREEN_WIDTH - Asteroid.WIDTH));  // -32 ?
//			break;
//		}
//
//		asteroid = new Asteroid(xPos, yPos);
//		return asteroid;
//	}

//	@Override
//	protected void drawAsteroid() {
//		Graphics2D g2d = getGraphics2D();
//		if((asteroid.getX() + asteroid.getPixelsWide() > 0)) {
//			asteroid.translate(-asteroid.getSpeed(), 0);
//			getGraphicsManager().drawAsteroid(asteroid, g2d, this);	
//		}
//		if((asteroid.getX() - asteroid.getPixelsWide() < 500)) {
//			asteroid.translate(asteroid.getSpeed(), 0);
//			getGraphicsManager().drawAsteroid(asteroid, g2d, this);	
//		}
//		else {
//			long currentTime = System.currentTimeMillis();
//			if((currentTime - lastAsteroidTime) > NEW_ASTEROID_DELAY){
//
//				asteroid.setLocation(SCREEN_WIDTH - asteroid.getPixelsWide(),
//						rand.nextInt(SCREEN_HEIGHT - asteroid.getPixelsTall() - 32));
//			}
//			else {
//				// draw explosion
//				getGraphicsManager().drawAsteroidExplosion(asteroidExplosion, g2d, this);
//			}
//		}	
//	}
	
	@Override
	public void updateScreen() {
		super.updateScreen();
		movePlatform(platforms);
	}

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
//			movePlatform(platforms);
		}
//		movePlatform(platforms);

	}
	
	public void movePlatform(Platform[] platformsArray) {
//		double xPos = platform.getX();
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
	
}
