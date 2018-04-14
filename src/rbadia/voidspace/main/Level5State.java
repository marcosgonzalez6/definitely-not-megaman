package rbadia.voidspace.main;

import java.awt.Graphics2D;
import java.util.List;


import rbadia.voidspace.graphics.GraphicsManager;
import rbadia.voidspace.model.Boss;
import rbadia.voidspace.model.Bullet;
import rbadia.voidspace.model.Floor;
import rbadia.voidspace.model.MegaMan;
import rbadia.voidspace.model.Platform;
import rbadia.voidspace.sounds.SoundManager;

public class Level5State extends Level4State{

	private static final long serialVersionUID = -2094575762243216079L;

	private Boss boss;
	protected List<Bullet> bossBullets;
	
	private boolean isBossMoveUp = true;
	
	public Level5State(int level, MainFrame frame, GameStatus status, LevelLogic gameLogic, InputHandler inputHandler,
			GraphicsManager graphicsMan, SoundManager soundMan) {
		super(level, frame, status, gameLogic, inputHandler, graphicsMan, soundMan);
		// TODO Auto-generated constructor stub
	}
	
	public Boss getBoss() 							{ return boss; 				}
	public List<Bullet> getBossBullets() 			{ return bossBullets; 		}
	
	@Override
	public void doStart() {	
		super.doStart();
		setStartState(GETTING_READY);
		setCurrentState(getStartState());
		newBoss();
//		drawBossBullets();
	}
	
	@Override
	public void updateScreen() {
		super.updateScreen();
		drawBoss();
	}
	
	public void drawBoss() {
//		bossGravity();
		Graphics2D g2d = getGraphics2D();
		getGraphicsManager().drawBoss(boss, g2d, this);
		moveBoss(this.getBoss());
	}
	
	public Boss newBoss(){
		this.boss = new Boss((SCREEN_WIDTH - Boss.WIDTH) / 2 + 200, (SCREEN_HEIGHT - Boss.HEIGHT - Boss.Y_OFFSET) / 2);
		return boss;
	}
	
	public void moveBoss(Boss boss) {
//		double xPos = platform.getX();
		int yPos = (int) boss.getY();
		
		if (yPos <= 100)	{
			isBossMoveUp = false;
		}
		if (yPos >= 360) {
			isBossMoveUp = true;
		}
		if (isBossMoveUp) {
			boss.translate(0, -boss.getDefaultSpeed());
		}
		else {
			boss.translate(0, boss.getDefaultSpeed());
		}
	}
	
	public boolean bossFall(){
		MegaMan boss = this.getBoss(); 
		Platform[] platforms = this.getPlatforms();
		for(int i=0; i<getNumPlatforms(); i++){
			if((((platforms[i].getX() < boss.getX()) && (boss.getX()< platforms[i].getX() + platforms[i].getWidth()))
					|| ((platforms[i].getX() < boss.getX() + boss.getWidth()) 
							&& (boss.getX() + boss.getWidth()< platforms[i].getX() + platforms[i].getWidth())))
					&& boss.getY() + boss.getHeight() == platforms[i].getY()
					){
				return false;
			}
		}
		return true;
	}
	
	
	
	protected void bossGravity(){
		MegaMan boss = this.getBoss();
		Floor[] floor = this.getFloor();

		for(int i=0; i<9; i++){
			if((boss.getY() + boss.getHeight() -17 < SCREEN_HEIGHT - floor[i].getHeight()/2) 
					&& bossFall() == true){

				boss.translate(0 , 2);
			}
		}
	}
	

	
//	public boolean bossShoot() {
//		if ((this.boss.getY() > this.megaMan.getY() - this.megaMan.getHeight()) && (this.boss.getY() < this.megaMan.getY() + 2*this.megaMan.getHeight())) {
//			fireBossBullet();
//			return true;
//		}
//		else 
//			return false;
//	}
//	
//	public void fireBossBullet() {
//		megaMan = getBoss();
//		Bullet bullet = new Bullet(megaMan.x + Bullet.WIDTH/2,
//				megaMan.y + megaMan.width/2 - Bullet.HEIGHT +2);	
//		bossBullets.add(bullet);	
//	}
	
//	public void fireBullet(){
//		if (!super.getIsMoveLeft()) {
//			Bullet bullet = new Bullet(megaMan.x + megaMan.width - Bullet.WIDTH/2,
//					megaMan.y + megaMan.width/2 - Bullet.HEIGHT +2);
//			bullets.add(bullet);
//		}
//		// TODO add if super.getIsMoveLeft to make new bullet Bullet(megaMan.x without width, ...)?
//		else {
//			Bullet bullet = new Bullet(megaMan.x + Bullet.WIDTH/2,
//					megaMan.y + megaMan.width/2 - Bullet.HEIGHT +2);	
//			bullets.add(bullet);
//		}
//		this.getSoundManager().playBulletSound();
//	}
	
//	protected void drawBossBullets() {
//		Graphics2D g2d = getGraphics2D();
//		for(int i=0; i<this.bossBullets.size(); i++){
//			Bullet bullet = bossBullets.get(i);
//			getGraphicsManager().drawBullet(bullet, g2d, this);
//
//			boolean remove = this.moveBullet(bullet);
//			if(remove){
//				bossBullets.remove(i);
//				i--;
//			}
//		}
//	}
	
}