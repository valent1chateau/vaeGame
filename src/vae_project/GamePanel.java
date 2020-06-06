package vae_project;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends javax.swing.JPanel implements ActionListener{

	Player player;
	ArrayList<Wall> walls = new ArrayList<>();
	Timer gameTimer;
	
	public GamePanel() {
		
		player = new Player(400,300,this);
		
		makeWalls();
		
		gameTimer = new Timer();
		gameTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				player.set();
				repaint();
				
			}
			
		},0,17);
	}
	
	public void makeWalls() {
		for(int i = 50; i<650; i+=50){
			walls.add(new Wall(i, 600, 50, 20));
		}
		walls.add(new Wall(100, 550, 50, 20));
		walls.add(new Wall(100, 500, 50, 20));
		walls.add(new Wall(100, 450, 50, 20));
		walls.add(new Wall(100, 400, 50, 20));
		walls.add(new Wall(100, 350, 50, 20));
		walls.add(new Wall(100, 300, 50, 20));
		walls.add(new Wall(100, 250, 50, 20));
		walls.add(new Wall(100, 200, 50, 20));
		walls.add(new Wall(150, 350, 50, 20));
		walls.add(new Wall(150, 300, 50, 20));
		walls.add(new Wall(150, 250, 500, 200));
		walls.add(new Wall(150, 200, 50, 20));
		walls.add(new Wall(200, 350, 50, 20));
		walls.add(new Wall(200, 300, 50, 20));
		walls.add(new Wall(200, 250, 50, 20));
		walls.add(new Wall(200, 200, 50, 20));
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D gtd = (Graphics2D) g;
		player.draw(gtd);
		for (Wall wall:walls) wall.draw(gtd);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyChar() == 'q') player.keyLeft = true;
		if(e.getKeyChar() == 'd') player.keyRight = true;
		if(e.getKeyChar() == 'z') player.keyUp = true;
		if(e.getKeyChar() == 's') player.keyDown = true;
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyChar() == 'q') player.keyLeft = false;
		if(e.getKeyChar() == 'd') player.keyRight = false;
		if(e.getKeyChar() == 'z') player.keyUp = false;
		if(e.getKeyChar() == 's') player.keyDown = false;
	}
	
	
}
