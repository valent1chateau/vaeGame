package vae_project;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyChecker extends KeyAdapter {
	GamePanel panel;
	
	public KeyChecker(GamePanel panel) {
		this.panel = panel;
	}
	
	public void keyPressed(KeyEvent e) { // Permet de creer une actiion quand une touche est appuyée
		panel.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) { //Permet de creer une action quand une touche est relachée
		panel.keyReleased(e);
	}
}
