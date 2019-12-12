package com.example;

import javax.swing.ImageIcon;

//import com.example.Elements;

public class ElementFire extends Elements {
	
	public ElementFire(String s) {
		super(s);
		this.level = number[1];
		this.setStrong(("light"));
		this.setWeak(("water"));
		this.setTexture(new ImageIcon("res/element/fire.png").getImage());
	}

}
