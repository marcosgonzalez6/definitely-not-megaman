package rbadia.voidspace.main;



import java.awt.Color;
import java.awt.Graphics2D;

import rbadia.voidspace.graphics.GraphicsManager;
import rbadia.voidspace.sounds.SoundManager;

public class Level4State extends Level2State{   //Change the extension to Level3State
	private boolean moreThanOnce = true;
	private boolean once = true;
	private int counter = 0;

	private static final long serialVersionUID = 1L;

	public Level4State(int level, MainFrame frame, GameStatus status, LevelLogic gameLogic, InputHandler inputHandler,
			GraphicsManager graphicsMan, SoundManager soundMan) {
		super(level, frame, status, gameLogic, inputHandler, graphicsMan, soundMan);
		
	}      
	
	protected void clearScreen() {
		// clear screen
		Graphics2D g2d = getGraphics2D();
		g2d.setPaint(Color.BLACK);
		g2d.fillRect(0, 0, getSize().width, getSize().height);
		getGraphicsManager().drawBackground4(g2d, this);
	}
	
	@Override
	public void doStart() {	
		super.doStart();
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
			}
			else {
				// draw explosion
				getGraphicsManager().drawAsteroidExplosion(asteroidExplosion, g2d, this);
			}
		}	
	}
	
}
