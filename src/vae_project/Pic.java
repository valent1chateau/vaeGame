package vae_project;

import java.awt.Color;
import java.awt.Graphics2D;


public class Pic extends Wall {
	int x;
	int y;
	int width;
	int height;
	int startX;
	
	public Pic(int x,int y, int width, int height) {
		super(x,y,width,height);
	}
	
	public void draw_pic(Graphics2D gtd) {
		gtd.setColor(Color.black);
		gtd.drawRect(x, y, width, height);
		gtd.setColor(Color.red);
		gtd.fillRect(x+1, y+1, width-2, height-2);
	}
}