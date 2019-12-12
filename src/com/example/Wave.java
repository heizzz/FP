package com.example;

public class Wave {
	
	Screen screen;
	static int waveNumber = 0;
	int enemiesThisRound = 0;
	int enemiesPerRound = 2;
	static int waveThisLevel;
	
	boolean waveSpawning; 
	
	public Wave(Screen screen,int waveThisLevel) {
		this.screen = screen;
		this.waveThisLevel = waveThisLevel;
	}
	
	public void nextWave() {
		this.waveNumber++;
		this.enemiesThisRound = 0;
		this.waveSpawning = true;
		
		System.out.println("[Wave] Wave "+this.waveNumber+" Incoming");
		
		
//		reset enemy per wave
//		for (int i=0;i < this.screen.enemyMap.length;i++) {
//			this.screen.enemyMap[i] = null;
//		}
	}
	
	private int currentDelay = 0;
	private int spawnRate = 500;
	
	public void spawnEnemies() {
//		if (this.enemiesThisRound < this.waveNumber*this.enemiesPerRound) {
		if (this.enemiesThisRound < this.enemiesPerRound) {
			if (currentDelay < spawnRate) {
				currentDelay++;
			} else {
				currentDelay = 0;
				System.out.println("[Wave] Enemy Spawned");
				
				this.enemiesThisRound++;
				this.screen.spawnEnemy((waveNumber-1)%6);
			}
		} else {
			if (waveThisLevel == this.waveNumber) {
				User.complete[(waveThisLevel/10)-1] = 1;
				waveNumber = 0;
				Screen.win = true;
			}
			this.enemiesPerRound+=enemiesPerRound;
			if (waveNumber % 6 == 0 && waveNumber != 0) Enemy.level++;
			this.waveSpawning = false;
		}
	}
}
