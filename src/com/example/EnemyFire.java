package com.example;

public class EnemyFire extends Enemy {
//	Elements elemen;
	public EnemyFire(int id, int price, double speed, double health, int damage, int maxHealth) {
		super(id,price,speed,health,damage,maxHealth);
		this.element = new ElementFire("fire");
	}

}
