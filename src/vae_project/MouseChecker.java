package vae_project;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import vae_project.GamePanel.STATE;

public class MouseChecker implements MouseListener{

	GamePanel panel;
	
	public MouseChecker(GamePanel panel) {
		this.panel = panel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) { // Clique normal
	}
	

	@Override
	public void mouseEntered(MouseEvent arg0) { // Quand on place le curseur de la souris sur l'objet
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) { // Quand on enleve le curseur de la souris de l'objet
		
	}

	public void mousePressed(MouseEvent e) { // Quand on appuye sur le bouton de notre souris sur le bouton
		int mx = e.getX();
		int my = e.getY();
		
		if(mx>=300 && mx<= 400 && this.panel.State == STATE.MENU) {
			if(my>=200 && my<= 250) {
				System.out.println("test mouse pressed");
				this.panel.State = STATE.GAME;
				System.out.println(this.panel.State);
				this.panel.first_reset();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) { // Quand on relache sur le bouton de notre souris sur le bouton
		
	}
	

}
