package rbadia.voidspace.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import rbadia.voidspace.model.Asteroid;
//import rbadia.voidspace.model.BigAsteroid;
import rbadia.voidspace.model.BigBullet;
import rbadia.voidspace.model.Boss;
//import rbadia.voidspace.model.Boss;
import rbadia.voidspace.model.Bullet;
import rbadia.voidspace.model.Floor;
//import rbadia.voidspace.model.BulletBoss;
//import rbadia.voidspace.model.BulletBoss2;
import rbadia.voidspace.model.MegaMan;
import rbadia.voidspace.model.Platform;

/**
 * Manages and draws game graphics and images.
 */
public class GraphicsManager {
	private BufferedImage megaManImg;
	private BufferedImage megaFallRImg;
	private BufferedImage megaFireRImg;
	private BufferedImage floorImg;
	private BufferedImage platformImg;
	private BufferedImage bulletImg;
	private BufferedImage bigBulletImg;
	private BufferedImage asteroidImg;
	private BufferedImage asteroidExplosionImg;
	private BufferedImage megaManExplosionImg;
	private BufferedImage bigAsteroidExplosionImg;
	private BufferedImage powerUpImg;
	private BufferedImage bossImg;

	/**
	 * Creates a new graphics manager and loads the game images.
	 */
	public GraphicsManager(){
		// load images
		try {
			this.megaManImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/megaMan3.png"));
			this.megaFallRImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/megaFallRight.png"));
			this.megaFireRImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/megaFireRight.png"));
			this.floorImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/megaFloor.png"));
			this.platformImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/platform3.png"));
			this.asteroidImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/asteroid.png"));
			this.asteroidExplosionImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/asteroidExplosion.png"));
			this.bulletImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/bullet.png"));
			this.bigBulletImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/bigBullet.png"));
			this.powerUpImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/PowerUp.png"));
			this.bossImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/ship.png"));
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "The graphic files are either corrupt or missing.",
					"MegaMan!!! - Fatal Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * Draws a MegaMan image to the specified graphics canvas.
	 * @param MegaMan the ship to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */

	public void drawMegaManR (MegaMan megaMan, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(megaManImg, megaMan.x, megaMan.y, observer);	
	}
	
	public void drawMegaManL (MegaMan megaMan, Graphics2D g2d, ImageObserver observer){
		int width = megaManImg.getWidth();
		int height = megaManImg.getHeight();
		BufferedImage megaManLImg = new BufferedImage(width, height, megaManImg.getType());
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				megaManLImg.setRGB((width - 1) - x, y, megaManImg.getRGB(x, y));
			}
		}
		g2d.drawImage(megaManLImg, megaMan.x, megaMan.y, observer);	
	}

	public void drawMegaFallR (MegaMan megaMan, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(megaFallRImg, megaMan.x, megaMan.y, observer);	
	}

	public void drawMegaFallL (MegaMan megaMan, Graphics2D g2d, ImageObserver observer){
		int width = megaFallRImg.getWidth();
		int height = megaFallRImg.getHeight();
		BufferedImage megaFallLImg = new BufferedImage(width, height, megaFallRImg.getType());
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				megaFallLImg.setRGB((width - 1) - x, y, megaFallRImg.getRGB(x, y));
			}
		}
		g2d.drawImage(megaFallLImg, megaMan.x, megaMan.y, observer);	
	}
	
	public void drawMegaFireR (MegaMan megaMan, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(megaFireRImg, megaMan.x, megaMan.y, observer);	
	}
	
	public void drawMegaFireL (MegaMan megaMan, Graphics2D g2d, ImageObserver observer){
		int width = megaFireRImg.getWidth();
		int height = megaFireRImg.getHeight();
		BufferedImage megaFireLImg = new BufferedImage(width, height, megaFireRImg.getType());
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				megaFireLImg.setRGB((width - 1) - x, y, megaFireRImg.getRGB(x, y));
			}
		}
		g2d.drawImage(megaFireLImg, megaMan.x, megaMan.y, observer);	
	}

	public void drawFloor (Floor floor, Graphics2D g2d, ImageObserver observer, int i){
		g2d.drawImage(floorImg, floor.x, floor.y, observer);				
	}
	
	public void drawPlatform (Platform platform, Graphics2D g2d, ImageObserver observer, int i){
		g2d.drawImage(platformImg, platform.x , platform.y, observer);	
	}
	
	// check if this method is necessary (seems to be exactly like drawPlatform)
	public void drawPlatform2 (Platform platform, Graphics2D g2d, ImageObserver observer, int i){
		g2d.drawImage(platformImg, platform.x , platform.y, observer);	
	}

	/**
	 * Draws a bullet image to the specified graphics canvas.
	 * @param bullet the bullet to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawBullet(Bullet bullet, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(bulletImg, bullet.x, bullet.y, observer);
	}

	/**
	 * Draws a big bullet image to the specified graphics canvas.
	 * @param bigBullet the bullet to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawBigBullet(BigBullet bigBullet, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(bigBulletImg, bigBullet.x, bigBullet.y, observer);
	}

	/**
	 * Draws an asteroid image to the specified graphics canvas.
	 * @param asteroid the asteroid to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawAsteroid(Asteroid asteroid, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(asteroidImg, asteroid.x, asteroid.y, observer);
	}

	// check if necessary
	/**
	 * Draws a MegaMan explosion image to the specified graphics canvas.
	 * @param megaManExplosion the bounding rectangle of the explosion
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawMegaManExplosion(Rectangle megaManExplosion, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(megaManExplosionImg, megaManExplosion.x, megaManExplosion.y, observer);
	}

	/**
	 * Draws an asteroid explosion image to the specified graphics canvas.
	 * @param asteroidExplosion the bounding rectangle of the explosion
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawAsteroidExplosion(Rectangle asteroidExplosion, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(asteroidExplosionImg, asteroidExplosion.x, asteroidExplosion.y, observer);
	}

	//check if necessary
	public void drawBigAsteroidExplosion(Rectangle bigAsteroidExplosion, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(bigAsteroidExplosionImg, bigAsteroidExplosion.x, bigAsteroidExplosion.y, observer);
	}

	public void drawPowerUp(Rectangle powerUp, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(powerUpImg, powerUp.x, powerUp.y, observer);
	}
	
	public void drawBoss(Boss boss, Graphics2D g2d, ImageObserver observer) {
//		int width = bossImg.getWidth();
//		int height = bossImg.getHeight();
//		BufferedImage bossFireLImg = new BufferedImage(width, height, bossImg.getType());
//		for (int x = 0; x < width; x++) {
//			for (int y = 0; y < height; y++) {
//				bossFireLImg.setRGB(x, (height - 1) + y, bossImg.getRGB(x, y));
//			}
//		}
//		g2d.rotate(Math.PI/2);
		g2d.drawImage(bossImg, boss.x, boss.y, observer);
	}
	
	public void drawBossLivesDisplay(String bossLivesString, Graphics2D g2d) {
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("SanSerif", Font.PLAIN, 12));
		g2d.drawString(bossLivesString, 380, 30);
	}

}
