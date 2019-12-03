package com.example;

public class EnemyAI {
	
	public static EnemyRoute route;
	public static EnemyAIMove moveAI;
	public static int basePosX, basePosY;
	public int id;
	
	
	public EnemyAI (Level level) {
		route = new EnemyRoute(level);
		
		basePosX = route.base.xPos;
		basePosY = route.base.yPos;
		
		moveAI = new EnemyAIMove(0);
	}
	
	public EnemyAI (int id) {
		this.id = id;
	}
	
	public EnemyRoute getRoute() {
		return route;
	}
}
