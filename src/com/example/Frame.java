package com.example;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Frame extends JFrame {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){

			public void run() {
				new Frame();			
			}
			
		});
			
	}
	
	public Frame() {
		 new JFrame();
		 
//		 this.setSize(1920,1080);
		 this.setTitle("Feraldy Jelek");
		 this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		 this.setExtendedState(MAXIMIZED_BOTH);
		 this.setUndecorated(true);
		 this.setResizable(false);
		 this.setVisible(true);
//		 this.setLocationRelativeTo(null);
		
		 //add screen
		 Screen screen = new Screen(this);
		 this.add(screen);
	}
	
//	public Frame(int i)

}
