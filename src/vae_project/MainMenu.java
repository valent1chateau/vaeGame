package vae_project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class MainMenu {
	
	public Rectangle playButton = new Rectangle(300,200,100,50);
	public Rectangle quitButton = new Rectangle(300,350,100,50);


	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Font fnt0 = new Font("arial",Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.black);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 

		g.drawString("VAE GAME", (int)screenSize.getWidth()/6,100);
		
		Font fnt1 = new Font("arial", Font.BOLD,30);
		g.setFont(fnt1);
		g.drawString("Play", playButton.x + 20, playButton.y + 33);
		g.drawString("Quit", quitButton.x + 20, quitButton.y + 33);
		
		g2d.draw(playButton);
		g2d.draw(quitButton);
	}
}
