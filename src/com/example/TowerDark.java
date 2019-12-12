package com.example;

public class TowerDark extends Tower{
	
	public TowerDark(int id, int range, int damage, int maxAttackTime, int maxAttackDelay, int attackStrategy) {
		super(id, range, damage, maxAttackTime, maxAttackDelay,attackStrategy);
		this.element = new ElementDark("dark");
	}
	
}
