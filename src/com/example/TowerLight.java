package com.example;

public class TowerLight extends Tower{
	
	public TowerLight(int id, int range, int damage, int maxAttackTime, int maxAttackDelay, int attackStrategy) {
		super(id, range, damage, maxAttackTime, maxAttackDelay,attackStrategy);
		this.element = new ElementLight("light");
	}
	
}
