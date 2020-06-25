package vae_project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends javax.swing.JPanel implements ActionListener{

	Player player;
	ArrayList<Wall> walls = new ArrayList<>();
	Timer gameTimer;
	
	int cameraX; //variable camera
	int offset;
	
	private MainMenu menu;
	
	public static enum STATE {
		MENU,
		GAME
	};
	
	public STATE State = STATE.MENU;
	
	int sw = 100;
	int sh = 50;
	
	public GamePanel() {
		
			menu = new MainMenu();
			for (int i = 0; i<14; i++) walls.add(new Wall(offset + i*sw,600,sw,sh)); //Creation du sol
			player = new Player(50,500,this);
			player.set();
			repaint();
	}
	public void first_reset() {
		gameTimer = new Timer();
		gameTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				if(walls.get(walls.size()-1).x < 1100 /* ATTENTION, VALEUR A CHANGE QUAND ON MODIFIE SW !!!!!!!!!!!! */) {
					offset+=(1000); // ATTENTION, VALEUR A CHANGE QUAND ON MODIFIE SW !!!!!!!!!!!!
					makeWalls(offset);
				}
				
				player.set(); // Update de la position du joueur
				for(Wall wall: walls) wall.set(cameraX);
				
				for(int i=0;i<walls.size();i++) { // Supprimer murs à gauche du joueur
					if(walls.get(i).x < -800) walls.remove(i);
				}
				repaint();
				
			}
			
		},0,17); //temps entre chaque frame
	}
	
	public void reset(){ //Fonction appelee quand le joueur meurt
			player.x = 200;
			player.y = 150;
			cameraX = 150;
			player.xspeed = 0;
			player.yspeed = 0;
			walls.clear();
			
			offset = -150; //generer le niveau au debut de la fenêtre -- Ne pas spawn au début du patern (24min)
			makeWalls(offset);
	}
	
	public void makeWalls(int offset) { //Fonction de creation d'obstacles
		//int s = 50; //taille des murs

		Random rand = new Random();
		int index = rand.nextInt(3); //retourne un int entre 0 et param-1
		
		if (index == 0) {
			for (int i = 0; i<14; i++) walls.add(new Wall(offset + i*sw,600,sw,sh)); //Creation du sol
		}
		else if (index == 1) {
			for(int i=0;i<14;i++) walls.add(new Wall(offset + i*sw,600,sw,sh));
			
			//Creation des obstacles (pyramide)
			for(int i=5;i<14;i++) walls.add(new Wall(offset + i*sw,600-sh,sw,sh));
			for(int i=8;i<11;i++) walls.add(new Wall(offset + i*sw,600-2*sh,sw,sh));
		}
		else if (index == 2) {
			//2blocs en hauteurs + trou
			for(int i=0;i<6;i++) walls.add(new Wall(offset+i*sw,600,sw,sh));
			for(int i=9;i<14;i++) walls.add(new Wall(offset+i*sw,600,sw,sh));
			walls.add(new Wall(offset + 4*sw,600-sh,sw,sh));
			walls.add(new Wall(offset + 5*sw,600-sh,sw,sh));
		}
		/*else if (index == 3) {
			for(int i=0;i<14;i++) walls.add(new Wall(offset+i*50,600,sw,sh));
			for(int i=0;i<14;i++) walls.add(new Wall(offset+i*50+450,600,sw,sh));
			walls.add(new Wall(offset+150,550,sw,sh));
			walls.add(new Wall(offset+200,550,sw,sh));
			walls.add(new Wall(offset+200,500,sw,sh));
			walls.add(new Wall(offset+200,450,sw,sh));
			walls.add(new Wall(offset+500,550,sw,sh));
			walls.add(new Wall(offset+450,500,sw,sh));
			walls.add(new Wall(offset+450,550,sw,sh));
			walls.add(new Wall(offset+450,450,sw,sh));
		}
		else if (index == 4) {
			for(int i=0;i<5;i++) walls.add(new Wall(offset+i*50,600,sw,sh));
			for(int i=0;i<5;i++) walls.add(new Wall(offset+i*50+50,550,sw,sh));
			for(int i=0;i<5;i++) walls.add(new Wall(offset+i*50+100,500,sw,sh));
			for(int i=0;i<5;i++) walls.add(new Wall(offset+i*50+150,450,sw,sh));
			for(int i=0;i<5;i++) walls.add(new Wall(offset+i*50+500,600,sw,sh));
		}*/
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D gtd = (Graphics2D) g; //cast de Graphics g dans Graphics2D gtd
		player.draw(gtd);
		for (Wall wall:walls) wall.draw(gtd);
		//for (Pic wall:walls) wall.draw_pic(gtd);
		if(State == STATE.MENU) {
			menu.render(gtd);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

	
	// Bind des différents touches
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == 'q') player.keyLeft = true;
		if(e.getKeyChar() == 'd') player.keyRight = true;
		if(e.getKeyChar() == 'z') player.keyUp = true;
		if(e.getKeyChar() == 's') player.keyDown = true;
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyChar() == 'q') player.keyLeft = false;
		if(e.getKeyChar() == 'd') player.keyRight = false;
		if(e.getKeyChar() == 'z') player.keyUp = false;
		if(e.getKeyChar() == 's') player.keyDown = false;
	}

	public void mouseClicked(MouseEvent arg0) {
		
	}
	
	
}
