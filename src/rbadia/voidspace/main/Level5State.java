package rbadia.voidspace.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import rbadia.voidspace.graphics.GraphicsManager;
import rbadia.voidspace.model.Boss;
import rbadia.voidspace.model.Bullet;
import rbadia.voidspace.model.MegaMan;
import rbadia.voidspace.sounds.SoundManager;

public class Level5State extends Level4State{

	private static final long serialVersionUID = -2094575762243216079L;

	private Boss boss;
	private int bossLives = 5;
	protected List<Bullet> bossBullets;
	private boolean isBossMoveUp = true;
	private long lastBossBulletTime;
	private static final int NEW_BOSSBULLET_DELAY = 150;
	
	public Level5State(int level, MainFrame frame, GameStatus status, LevelLogic gameLogic, InputHandler inputHandler,
			GraphicsManager graphicsMan, SoundManager soundMan) {
		super(level, frame, status, gameLogic, inputHandler, graphicsMan, soundMan);
		// TODO Auto-generated constructor stub
	}
	
	public Boss getBoss() 							{ return boss; 				}
	public List<Bullet> getBossBullets() 			{ return bossBullets; 		}
	
	@Override
	public void doStart() {	
		bossBullets = new ArrayList<Bullet>();
		lastBossBulletTime = -NEW_BOSSBULLET_DELAY;
		super.doStart();
		setStartState(GETTING_READY);
		setCurrentState(getStartState());
		newBoss();
	}
	
	@Override
	public void updateScreen() {
		super.updateScreen();
		drawBossLives();
		drawBoss();
		bossShoot();
		drawBossBullets();
		checkBossBulletMegaManCollisions();
		checkBulletBossCollisions();
		checkMegaManBossCollisions();
	}
	
	public void drawBoss() {
		Graphics2D g2d = getGraphics2D();
		getGraphicsManager().drawBoss(boss, g2d, this);
		moveBoss(this.getBoss());
	}
	
	public Boss newBoss() {
		this.boss = new Boss(SCREEN_WIDTH - Boss.WIDTH + 70, (SCREEN_HEIGHT - Boss.HEIGHT - Boss.Y_OFFSET) / 2);
		return boss;
	}
	
	public void moveBoss(Boss boss) {
		int yPos = (int) boss.getY();
		
		if (yPos <= 50)	{
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
	
	public boolean bossShoot() {
		if ((this.boss.getY() > this.megaMan.getY() - this.megaMan.getHeight()/2) && (this.boss.getY() < this.megaMan.getMinY() + this.megaMan.getHeight()/2)) {
			fireBossBullet();
			return true;
		}
		else 
			return false;
	}
	
	public boolean bossFire(){
		MegaMan boss = this.getBoss();
		List<Bullet> bullets = this.getBossBullets();
		for(int i=0; i<bullets.size(); i++){
			Bullet bullet = bullets.get(i);
			if(((bullet.getX() > boss.getX() + boss.getWidth()) && 
					(bullet.getX() <= boss.getX() + boss.getWidth() + 60)) || 
						((bullet.getX() < boss.getX()) && (bullet.getX() >= boss.getX() - 60))){
				return true;
			}
		}
		return false;
	}
	
	public void fireBossBullet(){
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastBossBulletTime > NEW_BOSSBULLET_DELAY) {
			Bullet bullet = new Bullet(boss.x - Bullet.WIDTH/2,
					boss.y + boss.width/2 - Bullet.HEIGHT +2);
			bossBullets.add(bullet);
			lastBossBulletTime = currentTime;
			this.getSoundManager().playBulletSound();
		}
	}
	
	public boolean moveBossBullet(Bullet bullet){
		if(((bullet.getX() - bullet.getSpeed() >= 0) && (bullet.getX() + bullet.getSpeed() <= SCREEN_WIDTH)) || (bossBullets.size() < 7)) {
			bullet.translate(-bullet.getSpeed(), 0);
			return false;
		}
		else {
			return true;
		}
	}
	
	public void drawBossBullets() {
		Graphics2D g2d = getGraphics2D();
		for(int i=0; i<this.bossBullets.size(); i++){
			Bullet bullet = bossBullets.get(i);
			getGraphicsManager().drawBossBullet(bullet, g2d, this);

			boolean remove = this.moveBossBullet(bullet);
			if(remove){
				bossBullets.remove(i);
				i--;
			}
		}
	}
	
	public void checkBossBulletMegaManCollisions() {
		GameStatus status = getGameStatus();
		for(int i=0; i<bossBullets.size(); i++){
			Bullet bullet = bossBullets.get(i);
			if(megaMan.intersects(bullet)){
				status.setLivesLeft(status.getLivesLeft() - 1);
				bossBullets.remove(i);
				break;
			}
		}
	}
	
	public void checkMegaManBossCollisions() {
		GameStatus status = getGameStatus();
		if(boss.intersects(megaMan)){
			status.setLivesLeft(status.getLivesLeft() - 1);
//			removeAsteroid(asteroid);
		}
	}
	
	public void checkBulletBossCollisions() {
		for(int i=0; i<bullets.size(); i++){
			Bullet bullet = bullets.get(i);
			if(boss.intersects(bullet)){
				bossLives--;
				bullets.remove(i);
				break;
			}
		}
	}
	
	public void drawBossLives() {
		Graphics2D g2d = getGraphics2D();
		String bossLivesString = "Boss Lives Left: " + bossLives;
		getGraphicsManager().drawBossLivesDisplay(bossLivesString, g2d);
	}
	
	protected void clearScreen() {
		// clear screen
		Graphics2D g2d = getGraphics2D();
		g2d.setPaint(Color.BLACK);
		g2d.fillRect(0, 0, getSize().width, getSize().height);
		getGraphicsManager().drawBackground5(g2d, this);
	}
	
	@Override
	public boolean isLevelWon() {
		return (levelAsteroidsDestroyed >= 3 || bossLives == 0);
	}
	
	@Override
	protected void drawAsteroid() {
		// empty so that there are no asteroids on this level
	}
	
}