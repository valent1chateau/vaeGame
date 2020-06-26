package Model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import View.GamePanel;


public class Player {
	
	GamePanel panel;
	private int x;
	private int y;
	// Volume du joueur
	int width;
	int height;
	
	// Velocite du joueur
	private double xspeed;
	private double yspeed;
	
	Rectangle hitBox;
	
	
	// Variable sur les touches directionnelles
	private boolean keyLeft;
	private boolean keyRight;
	private boolean keyUp;
	private boolean keyDown;
	
	ImageIcon iconPlayer;
	Image imagePlayer;

	
	public Player(int x,int y,GamePanel panel) {
		this.panel = panel;
		this.setX(x); // Coordonnée x au spawn
		this.setY(y); // Coordonnée y au spawn
		
		width = 50;
		height = 100;
		hitBox = new Rectangle (x,y, width, height);
		
		this.iconPlayer= new ImageIcon(getClass().getResource("../picture/run.gif"));
		this.imagePlayer = this.iconPlayer.getImage();
	}
	
	public void set() { // Fonction appelee a chaque frame du jeu
		//if (keyLeft && keyRight || !keyLeft && !keyRight) xspeed *= 0.8; //Si on appuye sur aucune touche ou les deux touches droite et gauche en meme tmeps,
																		// le personnage ne bouge toujous pas sinon sa vitesse est réduite entre chaque frame
		//else if(keyLeft && !keyRight) xspeed --; // Mouvement du personnage 
		//else if(keyRight && !keyLeft) xspeed ++;
		
		setXspeed(7);
		
		// Vitesse maximale
		if (getXspeed()>7) setXspeed(7);
		if (getXspeed()<-7) setXspeed(-7);
		

		// anti bug low move
		if (getXspeed() > 0 && getXspeed() < 0.75) setXspeed(0);
		if (getXspeed() < 0 && getXspeed() > -0.75) setXspeed(0);
		
		// Saut 
		if (isKeyUp() && getXspeed() > 0) {
			hitBox.y ++; // La hitbox suit le personnage durant son saut
			for (Wall wall: panel.getWalls()) {
				if (wall.hitBox.intersects(hitBox)) setYspeed(-11);
			}		
			hitBox.y --;
		}

		setYspeed(getYspeed() + 0.5); // Gravité
		
		// Collisions horizontales
		hitBox.x += getXspeed();
		for(Wall wall:panel.getWalls()) {
			if(hitBox.intersects(wall.hitBox)) {
				hitBox.x -= getXspeed();
				while(!wall.hitBox.intersects(hitBox)) hitBox.x += Math.signum(getXspeed());
				hitBox.x -= Math.signum(getXspeed());
				panel.setCameraX(panel.getCameraX() + (getX() - hitBox.x)); //fix collision camera bug
				setXspeed(0);
				hitBox.x = getX();
			}
		}
		
		// Collisions verticales
		hitBox.y += getYspeed();
		for(Wall wall:panel.getWalls()) {
			if(hitBox.intersects(wall.hitBox)) {
				hitBox.y -= getYspeed();
				while(!wall.hitBox.intersects(hitBox)) hitBox.y += Math.signum(getYspeed());
				hitBox.y -= Math.signum(getYspeed());
				setYspeed(0);
				setY(hitBox.y);
			}
		}
		
		// Collisions pics
		hitBox.y += getYspeed();
		for(Pic pic:panel.getPics()) {
			if(hitBox.intersects(pic.hitBox)) {
				hitBox.y -= getYspeed();
				while(!pic.hitBox.intersects(hitBox)) hitBox.y += Math.signum(getYspeed());
				hitBox.y -= Math.signum(getYspeed());
				setXspeed(0);
				setY(hitBox.y);
			}
		}
		
		// Quand le joueur bouge, sa hitbox bouge avec lui
		panel.setCameraX((int) (panel.getCameraX() - getXspeed()));
		setY((int) (getY() + getYspeed()));
		
		hitBox.x = getX();
		hitBox.y = getY();
		
		//respawn condition
		if(getY() > 800) panel.repaint(); // Le joueur tombe
		if(getXspeed()==0) panel.repaint();// Le joueur heurte un mur ou un pic
		
		
		
	}
	
	public void draw(Graphics2D gtd) {
		gtd.setColor(Color.blue);
		//gtd.fillRect(x, y, width, height);
		gtd.drawImage(imagePlayer, this.getX(), this.getY(), null);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getXspeed() {
		return xspeed;
	}

	public void setXspeed(double xspeed) {
		this.xspeed = xspeed;
	}

	public double getYspeed() {
		return yspeed;
	}

	public void setYspeed(double yspeed) {
		this.yspeed = yspeed;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isKeyLeft() {
		return keyLeft;
	}

	public void setKeyLeft(boolean keyLeft) {
		this.keyLeft = keyLeft;
	}

	public boolean isKeyRight() {
		return keyRight;
	}

	public void setKeyRight(boolean keyRight) {
		this.keyRight = keyRight;
	}

	public boolean isKeyUp() {
		return keyUp;
	}

	public void setKeyUp(boolean keyUp) {
		this.keyUp = keyUp;
	}

	public boolean isKeyDown() {
		return keyDown;
	}

	public void setKeyDown(boolean keyDown) {
		this.keyDown = keyDown;
	}
}