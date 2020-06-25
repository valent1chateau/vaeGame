package vae_project;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Player {
	
	GamePanel panel;
	int x;
	int y;
	// Volume du joueur
	int width;
	int height;
	
	// Velocite du joueur
	double xspeed;
	double yspeed;
	
	Rectangle hitBox;
	
	
	// Variable sur les touches directionnelles
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	
	ImageIcon iconPlayer;
	Image imagePlayer;

	
	public Player(int x,int y,GamePanel panel) {
		this.panel = panel;
		this.x = x; // Coordonnée x au spawn
		this.y = y; // Coordonnée y au spawn
		
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
		
		xspeed = 7;
		
		// Vitesse maximale
		if (xspeed>7) xspeed = 7;
		if (xspeed<-7) xspeed = -7;
		

		// anti bug low move
		if (xspeed > 0 && xspeed < 0.75) xspeed = 0;
		if (xspeed < 0 && xspeed > -0.75) xspeed = 0;
		
		// Saut 
		if (keyUp && xspeed > 0) {
			hitBox.y ++; // La hitbox suit le personnage durant son saut
			for (Wall wall: panel.walls) {
				if (wall.hitBox.intersects(hitBox)) yspeed = -11;
			}		
			hitBox.y --;
		}

		yspeed += 0.5; // Gravité
		
		// Collisions horizontales
		hitBox.x += xspeed;
		for(Wall wall:panel.walls) {
			if(hitBox.intersects(wall.hitBox)) {
				hitBox.x -= xspeed;
				while(!wall.hitBox.intersects(hitBox)) hitBox.x += Math.signum(xspeed);
				hitBox.x -= Math.signum(xspeed);
				panel.cameraX += x - hitBox.x; //fix collision camera bug
				xspeed = 0;
				hitBox.x = x;
			}
		}
		
		// Collisions verticales
		hitBox.y += yspeed;
		for(Wall wall:panel.walls) {
			if(hitBox.intersects(wall.hitBox)) {
				hitBox.y -= yspeed;
				while(!wall.hitBox.intersects(hitBox)) hitBox.y += Math.signum(yspeed);
				hitBox.y -= Math.signum(yspeed);
				yspeed = 0;
				y = hitBox.y;
			}
		}
		
		// Collisions pics
		hitBox.y += yspeed;
		for(Pic pic:panel.pics) {
			if(hitBox.intersects(pic.hitBox)) {
				hitBox.y -= yspeed;
				while(!pic.hitBox.intersects(hitBox)) hitBox.y += Math.signum(yspeed);
				hitBox.y -= Math.signum(yspeed);
				xspeed = 0;
				y = hitBox.y;
			}
		}
		
		// Quand le joueur bouge, sa hitbox bouge avec lui
		panel.cameraX -= xspeed;
		y += yspeed;
		
		hitBox.x = x;
		hitBox.y = y;
		
		//respawn condition
		if(y > 800) panel.repaint(); // Le joueur tombe
		if(xspeed==0) panel.repaint();// Le joueur heurte un mur ou un pic
		
		
		
	}
	
	public void draw(Graphics2D gtd) {
		gtd.setColor(Color.blue);
		//gtd.fillRect(x, y, width, height);
		gtd.drawImage(imagePlayer, this.x, this.y, null);
	}
}