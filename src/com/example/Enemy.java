package com.example;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Enemy {
	public static final Enemy[] enemyList = new Enemy[200];
	public static final Enemy dark = new EnemyDark(0,5,9,100,5,100).getTextureFIle("dark");
	public static final Enemy fire = new EnemyFire(1,5,9,100,5,100).getTextureFIle("fire");
	public static final Enemy lightning = new EnemyLightning(2,5,9,100,5,100).getTextureFIle("lightning");
	public static final Enemy ground = new EnemyGround(3,5,9,100,5,100).getTextureFIle("ground");
	public static final Enemy water = new EnemyWater(4,5,9,100,5,100).getTextureFIle("water");
	public static final Enemy light = new EnemyLight(5,5,9,100,5,100).getTextureFIle("light");

	static int level = 0;
	public String textureFile =  "";
	protected Image[] texture = new Image[4];
	public int id;
	public int price;
	public double speed;
	public double health;
	public int damage;
	public int maxHealth;
	protected Elements element;
	
	public Enemy (int id, int price, double speed, double health, int damage, int maxHealth) {
		if (enemyList[id]!= null) {
			System.out.println("two enemies with same id");
		} else {
			enemyList[id] = this;
			this.id = id;
			this.price = price;
			this.health = health;
			this.speed = speed;	
			this.damage = damage;
			this.maxHealth = (int) health;
//			this.texture = new ImageIcon("res/enemy/" + "Untitled"+ ".png").getImage();
		}
	}
	
	public Enemy getTextureFIle(String str) {
		this.textureFile = str;
		
		this.texture[0] = new ImageIcon("res/enemy/" + this.textureFile+ "0.png").getImage();
		this.texture[1] = new ImageIcon("res/enemy/" + this.textureFile+ "1.png").getImage();
		this.texture[2] = new ImageIcon("res/enemy/" + this.textureFile+ "2.png").getImage();
		this.texture[3] = new ImageIcon("res/enemy/" + this.textureFile+ "3.png").getImage();
		
		return this;
	}
	public static void startup() {
		
	}

	public Image getTexture(int i) {
		int x = 0;
		if (i == 4) x=0;
		if (i == 5) x=1;
		if (i == 1) x=2;
		if (i == 6) x=3;
		return texture[x];
	}

//	public void setTexture(Image texture) {
//		
//		this.texture[x] = texture;
//	}
	
	
}
