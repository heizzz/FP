package com.example;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Enemy {
	public static final Enemy[] enemyList = new Enemy[200];
	public static final Enemy slime = new EnemySlime(0,5,9,100,30).getTextureFIle("EnemySlime");
	public static final Enemy enemy2 = new EnemySlime(1,5,12,30,20).getTextureFIle("Enemy2");
//	public static final Enemy slime = new EnemySlime(0,5,3,10);
	
	public String textureFile =  "";
	public Image texture = null;
	public int id;
	public int price;
	public double speed;
	public int health;
	public int damage;
	
	public Enemy (int id, int price, double speed, int health, int damage) {
		if (enemyList[id]!= null) {
			System.out.println("two enemies with same id");
		} else {
			enemyList[id] = this;
			this.id = id;
			this.price = price;
			this.health = health;
			this.speed = speed;	
			this.damage = damage;
//			this.texture = new ImageIcon("res/enemy/" + "Untitled"+ ".png").getImage();
		}
	}
	
	public Enemy getTextureFIle(String str) {
		this.textureFile = str;
		this.texture = new ImageIcon("res/enemy/" + this.textureFile+ ".png").getImage();
		return this;
	}
	public static void startup() {
		
	}

	public Image getTexture() {
		return texture;
	}

	public void setTexture(Image texture) {
		this.texture = texture;
	}
	
	
}
