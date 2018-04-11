package rbadia.voidspace.main;
import java.awt.Graphics2D;
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
public class Level3State extends Level2State {

	private static final long serialVersionUID = -2094575762243216079L;

	// Constructors
	public Level3State(int level, MainFrame frame, GameStatus status, 
			LevelLogic gameLogic, InputHandler inputHandler,
			GraphicsManager graphicsMan, SoundManager soundMan) {
		super(level, frame, status, gameLogic, inputHandler, graphicsMan, soundMan);
	}

	@Override
	public void doStart() {	
		super.doStart();
		setStartState(GETTING_READY);
		setCurrentState(getStartState());
	}
	
	@Override
	public Asteroid newAsteroid(Level1State screen){
		Random rand = new Random();
		int xPos = 250;
		int yPos = 0;
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

		asteroid = new Asteroid(xPos, yPos);
		return asteroid;
	}

	@Override
	protected void drawAsteroid() {
		Graphics2D g2d = getGraphics2D();
		if((asteroid.getX() + asteroid.getPixelsWide() > 0)) {
			asteroid.translate(-asteroid.getSpeed(), 0);
			getGraphicsManager().drawAsteroid(asteroid, g2d, this);	
		}
		if((asteroid.getX() - asteroid.getPixelsWide() < 500)) {
			asteroid.translate(asteroid.getSpeed(), 0);
			getGraphicsManager().drawAsteroid(asteroid, g2d, this);	
		}
		else {
			long currentTime = System.currentTimeMillis();
			if((currentTime - lastAsteroidTime) > NEW_ASTEROID_DELAY){

				asteroid.setLocation(SCREEN_WIDTH - asteroid.getPixelsWide(),
						rand.nextInt(SCREEN_HEIGHT - asteroid.getPixelsTall() - 32));
			}
			else {
				// draw explosion
				getGraphicsManager().drawAsteroidExplosion(asteroidExplosion, g2d, this);
			}
		}	
	}

//	@Override
//	public Platform[] newPlatforms(int n){
//		platforms = new Platform[n];
//		for(int i=0; i<n; i++){
//			this.platforms[i] = new Platform(0,0);
//			if(i<4)	platforms[i].setLocation(50+ i*50, SCREEN_HEIGHT/2 + 140 - i*40);
//			if(i==4) platforms[i].setLocation(50 +i*50, SCREEN_HEIGHT/2 + 140 - 3*40);
//			if(i>4){	
//				int k=4;
//				platforms[i].setLocation(50 + i*50, SCREEN_HEIGHT/2 + 20 + (i-k)*40 );
//				k=k+2;
//			}
//		}
//		return platforms;
//	}
}
