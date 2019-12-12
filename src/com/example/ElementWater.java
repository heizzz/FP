package com.example;

import javax.swing.ImageIcon;

//import com.example.Elements;

public class ElementWater extends Elements {

	public ElementWater(String s) {
		super(s);
		this.setStrong(("fire"));
		this.setWeak(("lightning"));
		this.level = number[4];
		this.setTexture(new ImageIcon("res/element/water.png").getImage());
	}
}
