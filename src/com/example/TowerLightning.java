package com.example;


public class TowerLightning extends Tower implements Renderable{;
	
	public TowerLightning(int id, int range, int damage, int maxAttackTime, int maxAttackDelay, int attackStrategy) {
		super(id,range, damage, maxAttackTime, maxAttackDelay, attackStrategy);
		// TODO Auto-generated constructor stub
		this.element = new ElementLightning("lightning");
	}

	@Override
	public void render() {
		
		
	}

	


}
