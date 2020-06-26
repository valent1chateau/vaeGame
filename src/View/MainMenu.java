package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.BorderFactory;

public class MainMenu {
	
	public Rectangle playButton = new Rectangle(300,200,100,50);
	public Rectangle quitButton = new Rectangle(300,350,100,50);


	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Font fnt0 = new Font("monospaced",Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.black);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 

		g.drawString("VAE GAME", 225,100);
		
		Font fnt1 = new Font("monospaced", Font.BOLD,30);
		g.setFont(fnt1);
		g.drawString("Play", this.playButton.x + 12, this.playButton.y + 35);
		g.drawString("Quit", this.quitButton.x + 12, this.quitButton.y + 35);
		
		g2d.draw(playButton);
		g2d.draw(quitButton);
	}
}
