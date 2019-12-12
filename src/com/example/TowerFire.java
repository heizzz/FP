package com.example;

public class TowerFire extends Tower{
	
	public TowerFire(int id, int range, int damage, int maxAttackTime, int maxAttackDelay, int attackStrategy) {
		super(id, range, damage, maxAttackTime, maxAttackDelay,attackStrategy);
		this.element = new ElementFire("fire");
	}
	
}
