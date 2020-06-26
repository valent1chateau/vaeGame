package Model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Wall {
	private int x;
	protected int y;
	protected int width;
	protected int height;
	protected int startX; //explanation 12min approx
	
	Rectangle hitBox;
	
	static BufferedImage image = new Resource().getResourceImage("../picture/wall.png");
	
	public Wall(int x,int y, int width, int height) {
		this.setX(x);
		startX = x;
		this.y = y;
		this.width = width;
		this.height = height;
		hitBox = new Rectangle (x,y,width, height);
	}
	
	public void draw(Graphics2D gtd) {
		//gtd.setColor(Color.black);
		//gtd.drawRect(x, y, width, height);
		//gtd.setColor(Color.white);
		//gtd.fillRect(x+1, y+1, width-2, height-2);
		gtd.drawImage(image, this.getX(), this.y, null);
	}
	
	public int set(int cameraX){
		setX(startX + cameraX);
		hitBox.x = getX();
		return getX();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
}