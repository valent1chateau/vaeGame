package vae_project;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class VaeGame {

	public static void main(String[] args) {
		MainFrame frame = new MainFrame ();
		
		frame.setSize(700,700); // Taille de la fenetre
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		frame.setLocation((int)(screenSize.getWidth()/2 - frame.getSize().getWidth()/2),
						  (int)(screenSize.getHeight()/2 - frame.getSize().getHeight()/2)); // Centrage de la fenetre
	
		
		// Affichage de la fenetre
		frame.setResizable(false);
		frame.setTitle("Vae Game");
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Arrete le programme quand on ferme la fenetre
	}

}
