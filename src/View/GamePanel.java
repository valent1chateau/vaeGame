package View;

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

import Model.*;

public class GamePanel extends javax.swing.JPanel implements ActionListener{

	Player player;
	private ArrayList<Wall> walls = new ArrayList<>();
	private ArrayList<Pic> pics = new ArrayList<>();
	ArrayList<Torche> torches = new ArrayList<>();
	Timer gameTimer;
	
	private int cameraX; //variable camera
	int offset;
	
	private MainMenu menu;
	private DeathMenu dmenu;
	
	public static enum STATE {
		MENU,
		GAME
	};
	
	public STATE State = STATE.MENU;
	
	// Taille des blocs 2D
	int sw = 90; // Longueur
	int sh = 27; // Hauteur
	
	int swpic = 90; // Longueur
	int shpic = 27; // Hauteur
	
	int swtor = 90;
	int shtor = 27;
	
	public GamePanel() {
		
		menu = new MainMenu();
		dmenu = new DeathMenu();
		for (int i = 0; i<14; i++) getWalls().add(new Wall(offset + i*sw,600,sw,sh)); //Creation du sol
		player = new Player(50,500,this);
		player.set();
		repaint();
	}
	public void first_reset() {
		gameTimer = new Timer();
		gameTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				if(getWalls().get(getWalls().size()-1).getX() < 700) {
					offset+=(21*sw);
					makeWalls(offset);
				}
				
				player.set(); // Update de la position du joueur
				for(Wall wall: getWalls()) wall.set(getCameraX());
				for(Pic pic: getPics()) pic.set(getCameraX());
				for(Torche torche: torches) torche.set(getCameraX());
				
				for(int i=0;i<getWalls().size();i++) { // Supprimer murs à gauche du joueur
					if(getWalls().get(i).getX() < -800) getWalls().remove(i);
				}
				for(int i=0;i<getPics().size();i++) { // Supprimer murs à gauche du joueur
					if(getPics().get(i).getX() < -800) getPics().remove(i);
				}
				for(int i=0;i<torches.size();i++) { // Supprimer murs à gauche du joueur
					if(torches.get(i).getX() < -800) torches.remove(i);
				}
				repaint();
				
			}
			
		},0,17); //temps entre chaque frame
	}
	
	public void reset(){ //Fonction appelee quand le joueur meurt
		
		player.setX(200);
		player.setY(150);
		setCameraX(150);
		player.setXspeed(0);
		player.setYspeed(0);
		getWalls().clear();
		getPics().clear();
		torches.clear();
		
		offset = -150; //generer le niveau au debut de la fenêtre -- Ne pas spawn au début du patern (24min)
		makeWalls(offset);
	}
	
	public void makeWalls(int offset) { //Fonction de creation d'obstacles | Level design

		Random rand = new Random();
		int index = rand.nextInt(3); //retourne un int entre 0 et param-1
		
		if (index == 0) { // Pas d'obstacle
			for (int i = 0; i<21; i++) getWalls().add(new Wall(offset + i*sw,600,sw,sh));
		}
		
		else if (index == 1) { // Pyramide
			for(int i=0;i<21;i++) getWalls().add(new Wall(offset + i*sw,600,sw,sh));
			
			torches.add(new Torche(offset+6*sw+(sw-swtor),400, swtor,shtor)); // Torche légerement décalée vers la droite pour le centrage avec la pyramide
			torches.add(new Torche(offset+12*sw, 400, swtor,shtor));
			
			for(int i=5;i<14;i++) getWalls().add(new Wall(offset + i*sw,600-sh,sw,sh));
			
			getWalls().add(new Wall(offset + 8*sw,600-2*sh,sw,sh));
			getWalls().add(new Wall(offset + 10*sw,600-2*sh,sw,sh));
			
			getPics().add(new Pic(offset+9*sw,600-2*sh,sw,sh));
		}
		
		else if (index == 2) { // Hauteur et trou puis pics au sol
			for(int i=0;i<6;i++) getWalls().add(new Wall(offset+i*sw,600,sw,sh));
			for(int i=9;i<21;i++) getWalls().add(new Wall(offset+i*sw,600,sw,sh));
			
			getWalls().add(new Wall(offset + 5*sw,600-sh,sw,sh));	
			
			torches.add(new Torche(offset+5*sw+(sw-swtor), 400, swtor,shtor));
			torches.add(new Torche(offset+9*sw, 400, swtor,shtor));
			
			getPics().add(new Pic(offset+4*sw,600-sh,swpic,shpic));
			
			getPics().add(new Pic(offset+15*sw,600-sh,sw,sh));
			getPics().add(new Pic(offset+18*sw,600-sh,sw,sh));
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

		for (Wall wall:getWalls()) wall.draw(gtd); // Dessin des murs
		for (Pic pic:getPics()) pic.draw(gtd); // Dessin des pics
		for (Torche torche:torches) torche.draw(gtd); // Dessin des torches
		
		player.draw(gtd); // Dessin du joueur
		
		if(State == STATE.MENU) {
			menu.render(gtd); // Dessin du menu principal
		}
		else if((player.getXspeed() == 0 || player.getY() > 800) && State == STATE.GAME) {
			dmenu.render(gtd); // Dessin du menu de mort
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {}

	
	// Bind des différents touches
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == 'q') player.setKeyLeft(true);
		if(e.getKeyChar() == 'd') player.setKeyRight(true);
		if(e.getKeyChar() == 'z' && player.getXspeed()>0) player.setKeyUp(true);
		if(e.getKeyChar() == 's') player.setKeyDown(true);
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyChar() == 'q') player.setKeyLeft(false);
		if(e.getKeyChar() == 'd') player.setKeyRight(false);
		if(e.getKeyChar() == 'z') player.setKeyUp(false);
		if(e.getKeyChar() == 's') player.setKeyDown(false);
	}

	public void mouseClicked(MouseEvent arg0) {
		
	}
	public ArrayList<Wall> getWalls() {
		return walls;
	}
	public void setWalls(ArrayList<Wall> walls) {
		this.walls = walls;
	}
	public int getCameraX() {
		return cameraX;
	}
	public void setCameraX(int cameraX) {
		this.cameraX = cameraX;
	}
	public ArrayList<Pic> getPics() {
		return pics;
	}
	public void setPics(ArrayList<Pic> pics) {
		this.pics = pics;
	}
	
	
}
