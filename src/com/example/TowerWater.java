package com.example;

public class TowerWater extends Tower{
	
	public TowerWater(int id, int range, int damage, int maxAttackTime, int maxAttackDelay, int attackStrategy) {
		super(id, range, damage, maxAttackTime, maxAttackDelay,attackStrategy);
		this.element = new ElementWater("Water");
	}
	
}
