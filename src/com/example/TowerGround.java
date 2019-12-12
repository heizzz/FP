package com.example;

public class TowerGround extends Tower{
	
	public TowerGround(int id, int range, int damage, int maxAttackTime, int maxAttackDelay, int attackStrategy) {
		super(id, range, damage, maxAttackTime, maxAttackDelay,attackStrategy);
		this.element = new ElementGround("ground");
	}
	
}
