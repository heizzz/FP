package com.example;

import javax.swing.ImageIcon;

public class ElementGround extends Elements {
	public ElementGround(String s) {
		super(s);
		this.setStrong(("lightning"));
		this.setWeak(("dark"));
		this.level = number[3];
		this.setTexture(new ImageIcon("res/element/tanah.png").getImage());
	}
}
