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
	ArrayList<Pic> pics = new ArrayList<>();
	ArrayList<Torche> torches = new ArrayList<>();
	Timer gameTimer;
	
	int cameraX; //variable camera
	int offset;
	
	Rectangle restartRect;
	Rectangle homeRect;
	
	Font buttonFont = new Font("Arial",Font.BOLD,30);
	
	// Taille des blocs 2D
	int sw = 90; // Longueur
	int sh = 27; // Hauteur
	
	int swpic = 90; // Longueur
	int shpic = 27; // Hauteur
	
	int swtor = 50;
	int shtor = 50;
	
	public GamePanel() {
		restartRect = new Rectangle(550,25,50,50);
		homeRect = new Rectangle(625,25,50,50);
		
		player = new Player(400,300,this);
		
		reset();
		
		gameTimer = new Timer();
		gameTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				if(walls.get(walls.size()-1).x < 700) {
					offset+=(21*sw);
					makeWalls(offset);
				}
				
				player.set(); // Update de la position du joueur
				for(Wall wall: walls) wall.set(cameraX);
				for(Pic pic: pics) pic.set(cameraX);
				for(Torche torche: torches) torche.set(cameraX);
				
				for(int i=0;i<walls.size();i++) { // Supprimer murs à gauche du joueur
					if(walls.get(i).x < -800) walls.remove(i);
				}
				for(int i=0;i<pics.size();i++) { // Supprimer murs à gauche du joueur
					if(pics.get(i).x < -800) pics.remove(i);
				}
				for(int i=0;i<torches.size();i++) { // Supprimer murs à gauche du joueur
					if(torches.get(i).x < -800) torches.remove(i);
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
		pics.clear();
		torches.clear();
		
		offset = -150; //generer le niveau au debut de la fenêtre -- Ne pas spawn au début du patern (24min)
		makeWalls(offset);
	}
	
	public void makeWalls(int offset) { //Fonction de creation d'obstacles | Level design

		Random rand = new Random();
		int index = rand.nextInt(3); //retourne un int entre 0 et param-1
		
		if (index == 0) { // Pas d'obstacle
			for (int i = 0; i<21; i++) walls.add(new Wall(offset + i*sw,600,sw,sh));
		}
		
		else if (index == 1) { // Pyramide
			for(int i=0;i<21;i++) walls.add(new Wall(offset + i*sw,600,sw,sh));
			
			torches.add(new Torche(offset+6*sw+(sw-swtor),400, swtor,shtor)); // Torche légerement décalée vers la droite pour le centrage avec la pyramide
			torches.add(new Torche(offset+12*sw, 400, swtor,shtor));
			
			for(int i=5;i<14;i++) walls.add(new Wall(offset + i*sw,600-sh,sw,sh));
			
			walls.add(new Wall(offset + 8*sw,600-2*sh,sw,sh));
			walls.add(new Wall(offset + 10*sw,600-2*sh,sw,sh));
			
			pics.add(new Pic(offset+9*sw,600-2*sh,sw,sh));
		}
		
		else if (index == 2) { // Hauteur et trou puis pics au sol
			for(int i=0;i<6;i++) walls.add(new Wall(offset+i*sw,600,sw,sh));
			for(int i=9;i<21;i++) walls.add(new Wall(offset+i*sw,600,sw,sh));
			
			walls.add(new Wall(offset + 5*sw,600-sh,sw,sh));	
			
			torches.add(new Torche(offset+5*sw+(sw-swtor), 400, swtor,shtor));
			torches.add(new Torche(offset+9*sw, 400, swtor,shtor));
			
			pics.add(new Pic(offset+4*sw,600-sh,swpic,shpic));
			
			pics.add(new Pic(offset+16*sw,600,sw,sh));
			pics.add(new Pic(offset+18*sw,600,sw,sh));
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
		for (Wall wall:walls) wall.draw(gtd); // Dessin des murs
		for (Pic pic:pics) pic.draw(gtd); // Dessin des pics
		for (Torche torche:torches) torche.draw(gtd);

		
		// Dessin des boutons R et H
		gtd.setColor(Color.BLACK);
		gtd.drawRect(550, 25, 50, 50);
		gtd.drawRect(625, 25, 50, 50);
		gtd.setColor(Color.WHITE);
		gtd.fillRect(551, 26, 48, 48);
		gtd.fillRect(626, 26, 48, 48);
		gtd.setColor(Color.BLACK);
		gtd.setFont(buttonFont);
		gtd.drawString("R", 564, 60);
		gtd.drawString("H", 639, 60);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {}

	
	// Bind des différents touches
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == 'q') player.keyLeft = true;
		if(e.getKeyChar() == 'd') player.keyRight = true;
		if(e.getKeyChar() == 'z') player.keyUp = true;
		if(e.getKeyChar() == 's') player.keyDown = true;
		if(e.getKeyChar() == 'r') reset();
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyChar() == 'q') player.keyLeft = false;
		if(e.getKeyChar() == 'd') player.keyRight = false;
		if(e.getKeyChar() == 'z') player.keyUp = false;
		if(e.getKeyChar() == 's') player.keyDown = false;
	}

	public void mouseClicked(MouseEvent arg0) {
		if(restartRect.contains(new Point(arg0.getPoint().x, arg0.getPoint().y - 27))) reset();
	}
	
	
}
