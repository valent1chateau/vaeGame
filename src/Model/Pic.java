package Model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Pic extends Wall {
	static BufferedImage image = new Resource().getResourceImage("../picture/pic.png");
	public Pic(int x,int y, int width, int height) {
		super(x,y,width,height);
	}
	
	public void draw(Graphics2D gtd) {
		//gtd.setColor(Color.black);
		//gtd.drawRect(this.x, this.y, this.width, this.height);
		//gtd.setColor(Color.green);
		//gtd.fillRect(this.x+1, this.y+1, this.width-2, this.height-2);
		gtd.drawImage(image, this.getX(), this.y, null);
	}
}