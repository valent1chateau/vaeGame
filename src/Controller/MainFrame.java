package Controller;

import java.awt.Color;

import View.GamePanel;

public class MainFrame extends javax.swing.JFrame {
	
	public MainFrame() {
	
	GamePanel panel = new GamePanel();
	panel.setLocation(0,0);
	panel.setSize(this.getSize());
	panel.setBackground(Color.LIGHT_GRAY);
	panel.setVisible(true);
	this.add(panel);
	
	addKeyListener(new KeyChecker(panel));
	addMouseListener(new MouseChecker(panel));
	}
}
