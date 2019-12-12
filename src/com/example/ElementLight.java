package com.example;

import java.awt.Image;

import javax.swing.ImageIcon;

//import com.example.Elements;
public class ElementLight extends Elements {
	
	Image texture;
	
	public ElementLight (String s) {
		super(s);
		this.level = number[5];
		this.setStrong("dark");
		this.setWeak("light");
//		this.setTextureFile("light");?
		this.setTexture(new ImageIcon("res/element/light.png").getImage());
//		if (this.texture!=null) System.out.println("kekw");
	}
	
//	public Image getTexture() {
//		return texture;
//	}
//	public void setTexture(Image texture) {
//		this.texture = texture;
//	}
//	public ElementLight(int i) {
//		super(i);
//	}
	
}
