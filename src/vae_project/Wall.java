package vae_project;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Wall {
	int x;
	int y;
	int width;
	int height;
	
	int startX; //explanation 12min approx
	
	Rectangle hitBox;
	
	public Wall(int x,int y, int width, int height) {
		this.x = x;
		startX = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		hitBox = new Rectangle (x,y,width, height);
	}
	public void draw(Graphics2D gtd) {
		gtd.setColor(Color.black);
		gtd.drawRect(x, y, width, height);
		gtd.setColor(Color.white);
		gtd.fillRect(x+1, y+1, width-2, height-2);
		
	}
	public int set(int cameraX){
		x = startX + cameraX;
		hitBox.x = x;
		
		return x;
	}
}