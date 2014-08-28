package pl.sphgames.rpg10;

import java.io.File;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;




public class Player {

	private Weapon weapon;
	private int level, health, experience;
	public static int x, y;
	private double playerSpeed;
	private BufferedImage playerImgN1, playerImgN2, playerImgE1, playerImgE2, playerImgW1, playerImgW2, playerImgS1, playerImgS2, displayedImage;
	private float stamina;
	public static Face face;
	private static int playerSize;
	private static int lastActionHelper;
	private CurrentTiles currentTiles_;
	private EventHandler eventHandler_;
	private Event event_;
	private boolean isMoving;
	private int changeFrame;
	private boolean faceChange;
	private Face lastFace;
	/** The time since the last frame change took place */
	private long lastFrameChange;
	/** The frame duration in milliseconds, i.e. how long any given frame of animation lasts */
	private long frameDuration = 10;
	private Random generator;

	public enum Face {
		NORTH,
		EAST,
		WEST,
		SOUTH
	};

	public Player() {
		createNewPlayer();
		loadImages();
	}


	public void createNewPlayer() {
		weapon = new Weapon();
		x = (Framework.frameWidth / 2) + 35;
		while (x % 64 != 0)
			x--;
		y = (Framework.frameHeight / 2) - 70;
		while (y % 64 != 0)
			y--;
		level = 1;
		health = 150;
		experience = 0;
		playerSize = 50;
		stamina = 150;
		playerSpeed = (level / 5 ) + 4;
		face = Face.EAST;
		generator = new Random();

	}

	public void loadImages() {
		try {
			playerImgW1 = ImageIO.read(new File("playerW1.png"));
			playerImgW2 = ImageIO.read(new File("playerW2.png"));
			playerImgN1 = ImageIO.read(new File("playerN1.png"));
			playerImgN2 = ImageIO.read(new File("playerN2.png"));
			playerImgE1 = ImageIO.read(new File("playerE1.png"));
			playerImgE2 = ImageIO.read(new File("playerE2.png"));
			playerImgS1 = ImageIO.read(new File("playerS1.png"));
			playerImgS2 = ImageIO.read(new File("playerS2.png"));
		}
		catch (IOException ex) {
			Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public boolean hasShot(long gameTime)
	{
		if(  (Canvas.keyboardKeyState(KeyEvent.VK_SPACE) || Canvas.mouseButtonState(MouseEvent.BUTTON1))    && 
				((gameTime - Bullet.timeOfLastCreatedBullet) >= Bullet.timeBetweenNewBullets) && 
				Weapon.currentAmmo > 0 ) 
		{
			return true;
		} else
			return false;
	}


	public void draw(Graphics2D g2d) {
		if(isMoving == true)
			System.out.println(("true"));
		switch (face) {

		case SOUTH:
			if(isMoving == true && ( displayedImage == playerImgS1 || displayedImage == playerImgS2 )){
				if(faceChange == true){
					int helper = generator.nextInt(2);
					if(helper == 1)
						displayedImage = playerImgS1;
					else
						displayedImage = playerImgS2;
					faceChange = false;
				}
				if(frameDuration < lastFrameChange){
					changeFrame = 1;
					if(changeFrame == 1 && displayedImage == playerImgS1){
						displayedImage = playerImgS2;
						changeFrame = 0;

					}
					if(changeFrame == 1 && displayedImage == playerImgS2){
						displayedImage = playerImgS1;
						changeFrame = 0;
					}
					lastFrameChange = 0;
				}
				lastFrameChange++;
			}
			else{
				displayedImage = playerImgS1;

			}
			break;


		case NORTH:

			if(isMoving == true && ( displayedImage == playerImgN1 || displayedImage == playerImgN2 )){
				if(faceChange == true){
					int helper = generator.nextInt(2);
					if(helper == 1)
						displayedImage = playerImgN1;
					else
						displayedImage = playerImgN2;
					faceChange = false;
				}
				if(frameDuration < lastFrameChange){
					changeFrame = 1;
					if(changeFrame == 1 && displayedImage == playerImgN2){
						displayedImage = playerImgN1;
						changeFrame = 0;

					}
					if(changeFrame == 1 && displayedImage == playerImgN1){
						displayedImage = playerImgN2;
						changeFrame = 0;
					}
					lastFrameChange = 0;
				}
				lastFrameChange++;
			}
			else{
				displayedImage = playerImgN2;

			}
			break;

		case EAST:
			if(isMoving == true && ( displayedImage == playerImgE1 || displayedImage == playerImgE2 )){
				if(faceChange == true){
					int helper = generator.nextInt(2);
					if(helper == 1)
						displayedImage = playerImgE1;
					else
						displayedImage = playerImgE2;
					faceChange = false;
				}
				if(frameDuration < lastFrameChange){
					changeFrame = 1;
					if(changeFrame == 1 && displayedImage == playerImgE1){
						displayedImage = playerImgE2;
						changeFrame = 0;

					}
					if(changeFrame == 1 && displayedImage == playerImgE2){
						displayedImage = playerImgE1;
						changeFrame = 0;
					}
					lastFrameChange = 0;
				}
				lastFrameChange++;
			}
			else{
				displayedImage = playerImgE1;

			}
			break;

		case WEST:
			if(isMoving == true && ( displayedImage == playerImgW1 || displayedImage == playerImgW2 )){
				if(faceChange == true){
					int helper = generator.nextInt(2);
					if(helper == 1)
						displayedImage = playerImgW1;
					else
						displayedImage = playerImgW2;
					faceChange = false;
				}
				if(frameDuration < lastFrameChange){
					changeFrame = 1;
					if(changeFrame == 1 && displayedImage == playerImgW1){
						displayedImage = playerImgW2;
						changeFrame = 0;

					}
					if(changeFrame == 1 && displayedImage == playerImgW2){
						displayedImage = playerImgW1;
						changeFrame = 0;
					}
					lastFrameChange = 0;
				}
				lastFrameChange++;
			}
			else{
				displayedImage = playerImgW1;

			}
			break;


		}
		g2d.drawImage(displayedImage, x, y, null);
		g2d.drawString("Ammo: " + weapon.getCurrentAmmo(), 5, 15);
	}

	public void update()
	{
		handleMovement();
		weapon.update();
	}

	public void passEventHandler(EventHandler eventHandler) {
		eventHandler_ = eventHandler;
	}


	private boolean isWalkable(double x, double y) {
		int x_ = (int) x;
		int y_ = (int) y;
		currentTiles_ = this.getCurrentTiles(x_,y_);
		if (currentTiles_.areWalkableTiles())
			return true;
		return false;
	}

	public boolean isSwitchingLevel (int x, int y) {
		currentTiles_ = this.getCurrentTiles(x,y);
		if (currentTiles_.areSwitchLevelTiles()) {
			lastActionHelper = currentTiles_.getActionHelper();
			return true;
		}
		return false;
	}

	public Event getEvent() {
		return event_;
	}

	public boolean isOnEventTile(double x, double y) {
		int x_ = (int) x;
		int y_ = (int) y;
		currentTiles_ = this.getCurrentTiles(x_,y_);
		if (currentTiles_.areEventTiles()) {
			event_ = currentTiles_.getEvent();
			return true;
		}
		return false;
	}

	public int getLastActionHelper() {
		return lastActionHelper;
	}

	public static void move() {
		if (y < 2 * 64) {
			y = 10 * 64;
		}
		else if (y > 10 * 64) {
			y = 64;
		}
		else if (x < 3 * 64) {
			x = 14*64;
		}
		else {
			x = 64;
		}
	}

	public CurrentTiles getCurrentTiles(int x, int y) {

		double xH = x, yH = y;
		int xH2, yH2;		


		if (xH % 64 == 0 && yH % 64 == 0) {
			xH2 = (int) xH / 64;
			yH2 = (int) yH / 64;

			return new CurrentTiles(World.background[xH2][yH2]);
		}

		else if (xH % 64 == 0) {

			while (yH % 64 != 0) {
				yH--;
			}

			xH2 = (int) xH / 64;
			yH2 = (int) yH / 64;

			return new CurrentTiles(World.background[xH2][yH2],World.background[xH2][yH2 + 1]);				

		}

		else if (yH % 64 == 0) {

			while (xH % 64 != 0) {
				xH--;
			}

			xH2 = (int) xH / 64;
			yH2 = (int) yH / 64;

			return new CurrentTiles(World.background[xH2][yH2],World.background[xH2 + 1][yH2]);						

		}

		else {
			while (xH % 64 != 0) {
				xH--;
			}		
			while (yH % 64 != 0) {
				yH--;
			}

			xH2 = (int) xH / 64;
			yH2 = (int) yH / 64;

			return new CurrentTiles(World.background[xH2][yH2],World.background[xH2 + 1][yH2],
					World.background[xH2][yH2 + 1],World.background[xH2 + 1][yH2 + 1]);

		}
	}







	private void handleMovement() {
		if(Canvas.keyboardKeyState(KeyEvent.VK_W) && Canvas.keyboardKeyState(KeyEvent.VK_CONTROL)) {
			face = Face.NORTH;
		}
		else if(Canvas.keyboardKeyState(KeyEvent.VK_S) && Canvas.keyboardKeyState(KeyEvent.VK_CONTROL)) {
			face = Face.SOUTH;
		}
		else if(Canvas.keyboardKeyState(KeyEvent.VK_A) && Canvas.keyboardKeyState(KeyEvent.VK_CONTROL)) {
			face = Face.WEST;
		}
		else if(Canvas.keyboardKeyState(KeyEvent.VK_D) && Canvas.keyboardKeyState(KeyEvent.VK_CONTROL)) {
			face = Face.EAST;
		}
		else {{ 


			if(Canvas.keyboardKeyState(KeyEvent.VK_W)) {
				face = Face.NORTH;
				for(int i = 0; i < playerSpeed; i++)
					if (isWalkable(x, y - 1))
						y -= 1;
				isMoving = true;
			}

			if(Canvas.keyboardKeyState(KeyEvent.VK_S)) {
				face = Face.SOUTH;
				for(int i = 0; i < playerSpeed; i++)
					if (isWalkable(x, y + 1))
						y += 1;
				isMoving = true;
			}
			
			if(Canvas.keyboardKeyState(KeyEvent.VK_D)) {
				face = Face.EAST;
				for(int i = 0; i < playerSpeed; i++)
					if (isWalkable(x + 1, y))
						x += 1;
				isMoving = true;
			}

			if(Canvas.keyboardKeyState(KeyEvent.VK_A)) {
				face = Face.WEST;
				for(int i = 0; i < playerSpeed; i++)
					if (isWalkable(x - 1, y))
						x -= 1;
				isMoving = true;
			}}
		if(!Canvas.keyboardKeyState(KeyEvent.VK_A) && !Canvas.keyboardKeyState(KeyEvent.VK_S) && !Canvas.keyboardKeyState(KeyEvent.VK_D) && !Canvas.keyboardKeyState(KeyEvent.VK_W)){
			isMoving = false;
				faceChange = true;
			lastFace = face;
		}

		}

		if (Canvas.keyboardKeyState(KeyEvent.VK_R)) {
			weapon.reload();
		}

		if (Canvas.keyboardKeyState(KeyEvent.VK_ESCAPE)) {
			Framework.createMainMenu();
		}



	}

}
