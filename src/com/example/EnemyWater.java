package com.example;

public class EnemyWater extends Enemy {
//	Elements elemen;
	public EnemyWater(int id, int price, double speed, double health, int damage, int maxHealth) {
		super(id,price,speed,health,damage,maxHealth);
		this.element = new ElementWater("water");
	}

}
