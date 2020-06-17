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
	
	Rectangle restartRect;
	Rectangle homeRect;
	
	Font buttonFont = new Font("Arial",Font.BOLD,30);
	
	public GamePanel() {
		restartRect = new Rectangle(550,25,50,50);
		homeRect = new Rectangle(625,25,50,50);
		
		player = new Player(400,300,this);
		
		reset();		
		
		gameTimer = new Timer();
		gameTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				if(walls.get(walls.size()-1).x < 800) {
					offset+=700;
					makeWalls(offset);
				}
				
				player.set();
				for(Wall wall: walls) wall.set(cameraX);
				
				for(int i=0;i<walls.size();i++) { //supprimer murs derrieres le joueur
					if(walls.get(i).x < -800) walls.remove(i);
				}
				repaint();
				
			}
			
		},0,12); //temps entre chaque frame
	}
	
	public void reset(){ //Fonction appelee quand le joueur meurt
		player.x = 200;
		player.y = 150;
		cameraX = 150;
		player.xspeed = 0;
		player.yspeed = 0;
		walls.clear();
		
		offset=-150; //generer le niveau au debut de la fenêtre
		makeWalls(offset);
	}
	
	public void makeWalls(int offset) { //Fonction de creation d'obstacles
		int s = 50; //taille des murs
		Random rand = new Random();
		int index = rand.nextInt(5); //retourne un int entre 0 et param-1
		
		if (index == 0) {
			for (int i = 0; i<14; i++) walls.add(new Wall(offset + i*50,600,s,s));
		}
		else if (index == 1) {
			for(int i=0;i<5;i++) walls.add(new Wall(offset + i*50,600,s,s));
			walls.add(new Wall(offset + 500,600,s,s));
			walls.add(new Wall(offset + 550,600,s,s));
			walls.add(new Wall(offset + 600,600,s,s));
			walls.add(new Wall(offset + 650,600,s,s));
			walls.add(new Wall(offset + 700,600,s,s));
			walls.add(new Wall(offset + 750,600,s,s));
		}
		else if (index == 2) {
			for(int i=0;i<14;i++) walls.add(new Wall(offset+i*50,600,s,s));
			for(int i=0;i<12;i++) walls.add(new Wall(offset+i*50+50,550,s,s));
			for(int i=0;i<10;i++) walls.add(new Wall(offset+i*50+100,500,s,s));
			for(int i=0;i<8;i++) walls.add(new Wall(offset+i*50+150,450,s,s));
			for(int i=0;i<6;i++) walls.add(new Wall(offset+i*50+200,400,s,s));			
		}
		else if (index == 3) {
			for(int i=0;i<14;i++) walls.add(new Wall(offset+i*50,600,s,s));
			for(int i=0;i<14;i++) walls.add(new Wall(offset+i*50+450,600,s,s));
			walls.add(new Wall(offset+150,550,s,s));
			walls.add(new Wall(offset+200,550,s,s));
			walls.add(new Wall(offset+200,500,s,s));
			walls.add(new Wall(offset+200,450,s,s));
			walls.add(new Wall(offset+500,550,s,s));
			walls.add(new Wall(offset+450,500,s,s));
			walls.add(new Wall(offset+450,550,s,s));
			walls.add(new Wall(offset+450,450,s,s));
		}
		else if (index == 4) {
			for(int i=0;i<5;i++) walls.add(new Wall(offset+i*50,600,s,s));
			for(int i=0;i<5;i++) walls.add(new Wall(offset+i*50+50,550,s,s));
			for(int i=0;i<5;i++) walls.add(new Wall(offset+i*50+100,500,s,s));
			for(int i=0;i<5;i++) walls.add(new Wall(offset+i*50+150,450,s,s));
			for(int i=0;i<5;i++) walls.add(new Wall(offset+i*50+500,600,s,s));
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D gtd = (Graphics2D) g; //cast de Graphics g dans Graphics2D gtd
		player.draw(gtd);
		for (Wall wall:walls) wall.draw(gtd);
		
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
	public void actionPerformed(ActionEvent arg0) {
		
	}

	
	
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
