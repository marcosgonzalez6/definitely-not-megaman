package rbadia.voidspace.main;

import java.awt.Graphics2D;

import rbadia.voidspace.graphics.GraphicsManager;
import rbadia.voidspace.sounds.SoundManager;

public class Level4State extends Level2State{   //Change the extension to Level3State

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Level4State(int level, MainFrame frame, GameStatus status, LevelLogic gameLogic, InputHandler inputHandler,
			GraphicsManager graphicsMan, SoundManager soundMan) {
		super(level, frame, status, gameLogic, inputHandler, graphicsMan, soundMan);
		
	}      

//	@Override
//	protected void drawPowerUp() {
//		super.drawPowerUp();
//		if (checkPowerUpMegaManCollisions()) {
//			powerUp.setLocation(0, 10);
//		}
//	}
	
	@Override
	public void doStart() {	
		super.doStart();
		//drawPowerUp();
		//newPowerUp(this);
		setStartState(GETTING_READY);
		setCurrentState(getStartState());
	}
	
	@Override
	public void updateScreen() {
		super.updateScreen();
		newPowerUp(this);
		if (checkPowerUpMegaManCollisions()) {
			powerUp.setLocation(0, 10);
		}
		drawPowerUp();
		checkPowerUpMegaManCollisions();
	}
	
	protected boolean checkPowerUpMegaManCollisions() {
		GameStatus status = getGameStatus();
		if(powerUp.intersects(megaMan)){
			status.setLivesLeft(status.getLivesLeft() + 5);
			//powerUp.setLocation(0, 10);
			//super.removePowerUp(powerUp);
			return true;
		}	
			else {
				return false;
			}
		
	}
	
}
