package com.example;

public class EnemyLight extends Enemy {
//	Elements elemen;
	public EnemyLight(int id, int price, double speed, double health, int damage, int maxHealth) {
		super(id,price,speed,health,damage,maxHealth);
		this.element = new ElementLight("light");
	}

}
