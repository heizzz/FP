package com.example;

public class EnemyGround extends Enemy {
//	Elements elemen;
	public EnemyGround(int id, int price, double speed, double health, int damage, int maxHealth) {
		super(id,price,speed,health,damage,maxHealth);
		this.element = new ElementGround("ground");
	}

}
