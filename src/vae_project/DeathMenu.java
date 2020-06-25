package vae_project;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.BorderFactory;

public class DeathMenu {
	
	public Rectangle retryButton = new Rectangle(300,200,100,50);
	public Rectangle quitButton = new Rectangle(300,350,100,50);

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);
		
		Font fnt1 = new Font("monospaced", Font.BOLD,30);
		g.setFont(fnt1);
		g.drawString("Retry", this.retryButton.x + 6, this.retryButton.y + 35);
		g.drawString("Quit", this.quitButton.x + 12, this.quitButton.y + 35);
		
		g2d.draw(retryButton);
		g2d.draw(quitButton);
	}
}