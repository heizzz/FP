package com.example;

public class EnemyDark extends Enemy {
//	Elements elemen;
	public EnemyDark(int id, int price, double speed, double health, int damage, int maxHealth) {
		super(id,price,speed,health,damage,maxHealth);
		this.element = new ElementDark("dark");
	}

}
