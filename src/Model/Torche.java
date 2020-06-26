package Model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Torche extends Wall {
	ImageIcon iconTorche;
	Image imageTorche;
	public Torche(int x,int y, int width, int height) {
		super(x,y,width,height);
		this.iconTorche= new ImageIcon(getClass().getResource("../picture/torche.gif"));
		this.imageTorche = this.iconTorche.getImage();
	}
	
	public void draw(Graphics2D gtd) {
		//gtd.setColor(Color.black);
		//gtd.drawRect(this.x, this.y, this.width, this.height);
		//gtd.setColor(Color.red);
		//gtd.fillRect(this.x+1, this.y+1, this.width-2, this.height-2);
		gtd.drawImage(imageTorche, this.getX(), this.y, null);
	}
}