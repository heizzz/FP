package com.example;

public class EnemyLightning extends Enemy {
//	Elements elemen;
	public EnemyLightning(int id, int price, double speed, double health, int damage, int maxHealth) {
		super(id,price,speed,health,damage,maxHealth);
		this.element = new ElementLightning("lightning");
	}

}
