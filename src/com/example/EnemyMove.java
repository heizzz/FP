package com.example;

public class EnemyMove {

	Enemy enemy;
	SpawnPoint spawnPoint;
	
	double xPos;
	double yPos;
	boolean attack;
	
	int routePosX;
	int routePosY;
	
	double health;
	int maxHealth;
	Elements element;
	
	public EnemyMove ( Enemy enemy, SpawnPoint spawnPoint, SpawnPoint endPoint) {
		this.enemy = enemy;
//		System.out.println(spawnPoint.getX());
//		System.out.println(spawnPoint.getY());
		
		this.routePosX = spawnPoint.getX();
		this.routePosY = spawnPoint.getY();
		
		this.xPos = 200+spawnPoint.getX() * Screen.towerSide;
		this.yPos = 50+spawnPoint.getY() * Screen.towerSide;
		this.health = enemy.health;
		this.maxHealth = enemy.maxHealth;
		this.element = enemy.element;
	}
	
	public EnemyMove update(User user, int[][] map) {
		EnemyMove currentEnemy = this;
		
		if (currentEnemy.health <= 0) {
			user.player.money += currentEnemy.enemy.price;
			return null;
		}
		
		if (map[this.routePosX][this.routePosY] == 3) {
			user.player.health -= currentEnemy.enemy.damage;
			if (user.player.health <= 0) {
				Screen.alive  = false;
			}
			return null;
		}
			
		return currentEnemy;
	}
}
