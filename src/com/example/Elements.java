package com.example;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Elements {
	protected String elementName;
	protected String strong;
	protected String weak;
	protected int level;
//	protected String textureFile = "";
	protected Image texture;
	public static int[] number = new int[] {0,0,0,0,0,0};
	public static final Elements elementList[] = new Elements[6];
	public static int[] loc = new int[12];
//	protected int price =100;
	
	public static final Elements light = new ElementLight("light");
	public static final Elements fire = new ElementFire("fire");
	public static final Elements lightning = new ElementLightning("lightning");
	public static final Elements tanah = new ElementGround("ground");
	public static final Elements dark = new ElementDark("dark");
	public static final Elements water = new ElementWater("water");
	public static final Elements dark1 = new ElementDark("dark");
//	public static final Elements watdefak = new ElementGround();

	
//	public Elements(int id) {
//		elementList[id] = this;
//	}
	public Elements(String elementName) {
		elementList[0] = dark;
		elementList[1] = fire;
		elementList[2] = lightning;
		elementList[3] = tanah;
		elementList[4] = water;
		elementList[5] = light;
		this.elementName = elementName;
	}
	
	
	
//	public Elements() {
//		for (int i=0;i<=5;i++) {
//			System.out.println(elementList[i]);
//		}
//	}
//	public String getTextureFile() {
//		return textureFile;
//	}
//	public void setTextureFile(String textureFile) {
//		this.textureFile = textureFile;
//	}
	
	public Image getTexture() {
		return texture;
	}
	public Elements setTexture(Image texture) {
		this.texture = texture;
		return this;
	}



	public String getStrong() {
		return strong;
	}



	public void setStrong(String strong) {
		this.strong = strong;
	}



	public String getWeak() {
		return weak;
	}



	public void setWeak(String weak) {
		this.weak = weak;
	}
	

	
//	public Elements(String elementName, Elements strong, Elements weak, int level) {
//		this.elementName = elementName;
//		this.strong = strong;
//		this.weak = weak;
//		this.level = level;
//	}
	
	
}
