package com.example;

import javax.swing.ImageIcon;

//import com.example.Elements;

public class ElementDark extends Elements {
	public ElementDark(String s) {
		super(s);
		this.level = number[0];
		this.setStrong(("ground"));
		this.setWeak(("light"));
		this.setTexture(new ImageIcon("res/element/dark.png").getImage());
	}
}
