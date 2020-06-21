package vae_project;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseChecker implements MouseListener{

	GamePanel panel;
	
	public MouseChecker(GamePanel panel) {
		this.panel = panel;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) { // Clique normal
		panel.mouseClicked(arg0);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) { // Quand on place le curseur de la souris sur l'objet
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) { // Quand on enleve le curseur de la souris de l'objet
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) { // Quand on appuye sur le bouton de notre souris sur le bouton
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) { // Quand on relache sur le bouton de notre souris sur le bouton
		
	}
	

}
