package com.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	private Screen screen;
	private Screen.KeyTyped keyTyped;
	
	public KeyHandler (Screen screen) {
		this.screen = screen;
		this.keyTyped = this.screen.new KeyTyped();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
//		System.out.println(e.getKeyCode()); 
		int keyCode = e.getKeyCode();
		System.out.println(keyCode);
		if (keyCode == 27) {
			this.keyTyped.keyESC();
		}
		if (keyCode == 32) {
			this.keyTyped.keySPACE();
		}
		if (keyCode == 10) {
			this.keyTyped.keyENTER();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

}
