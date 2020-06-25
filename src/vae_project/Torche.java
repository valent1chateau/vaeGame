package vae_project;

import java.awt.Color;
import java.awt.Graphics2D;

public class Torche extends Wall {
	
	public Torche(int x,int y, int width, int height) {
		super(x,y,width,height);
	}
	
	public void draw(Graphics2D gtd) {
		gtd.setColor(Color.black);
		gtd.drawRect(this.x, this.y, this.width, this.height);
		gtd.setColor(Color.red);
		gtd.fillRect(this.x+1, this.y+1, this.width-2, this.height-2);
		//gtd.drawImage(image, this.x, this.y, null);
	}
}