package vae_project;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player {
	
	GamePanel panel;
	int x;
	int y;
	int width;
	int height;
	
	double xspeed;
	double yspeed;
	
	Rectangle hitBox;
	
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	
	
	
	public Player(int x,int y,GamePanel panel) {
		this.panel = panel;
		this.x = x;
		this.y = y;
		
		width = 50;
		height = 100;
		hitBox = new Rectangle (x,y, width, height);
	}
	
	public void set() {
		if (keyLeft && keyRight || !keyLeft && !keyRight) xspeed *= 0.8;
		else if(keyLeft && !keyRight) xspeed --;
		else if(keyRight && !keyLeft) xspeed ++;
		
		//vitesse max
		if (xspeed>7) xspeed = 7;
		if (xspeed<-7) xspeed = -7;
		
		// anti bug low moove
		if (xspeed > 0 && xspeed < 0.75) xspeed = 0;
		if (xspeed < 0 && xspeed > -0.75) xspeed = 0;
		
		//saut 
		if (keyUp) {
			hitBox.y ++;//vérifier à quoi ça sert
			for (Wall wall: panel.walls) {
				if (wall.hitBox.intersects(hitBox)) yspeed = -6;
			}
			hitBox.y --;
		}

		yspeed += 0.3;
		
		//horizontal collision
		hitBox.x += xspeed;
		for(Wall wall:panel.walls) {
			if(hitBox.intersects(wall.hitBox)) {
				hitBox.x -= xspeed;
				while(!wall.hitBox.intersects(hitBox)) hitBox.x += Math.signum(xspeed);
				hitBox.x -= Math.signum(xspeed);
				xspeed = 0;
				x = hitBox.x;
			}
		}
		
		//vertical collision
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
		
		x += xspeed;
		y += yspeed;
		
		hitBox.x = x;
		hitBox.y = y;
	}
	
	public void draw(Graphics2D gtd) {
		gtd.setColor(Color.black);
		gtd.fillRect(x, y, width, height);
	}
}
