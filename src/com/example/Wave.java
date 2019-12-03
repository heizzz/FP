package com.example;

public class Wave {
	
	Screen screen;
	static int waveNumber= 0;
	int enemiesThisRound=0;
	int enemiesPerRound = 10;
	
	boolean waveSpawning; 
	
	public Wave(Screen screen) {
		this.screen = screen;
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
		if (this.enemiesThisRound < this.waveNumber*this.enemiesPerRound) {
			if (currentDelay < spawnRate) {
				currentDelay++;
			} else {
				currentDelay = 0;
				System.out.println("[Wave] Enemy Spawned");
				
				this.enemiesThisRound++;
				this.screen.spawnEnemy(waveNumber-1);
			}
		} else {
			this.waveSpawning = false;
		}
	}
}
