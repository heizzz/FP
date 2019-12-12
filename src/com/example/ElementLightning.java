package com.example;

import javax.swing.ImageIcon;

//import com.example.Elements;

public class ElementLightning extends Elements {
	
	public ElementLightning(String s) {
		super(s);
		this.setStrong(("water"));
		this.setWeak(("ground"));
		this.level = number[2];
		this.setTexture(new ImageIcon("res/element/lightning.png").getImage());
	}

}
