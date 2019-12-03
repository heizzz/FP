package com.example;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Tower {
	
	private String textureFile = "";
	private Image texture;
	public static final Tower[] towerList = new Tower[200];
	public static final Tower lightningTower = new TowerLightning(0,2,1,5,3).getTextureFile("Morphling");
	public static final Tower FireTower = new TowerLightning(1,2,1,5,3).getTextureFile("Phoenix");
	
	public int id;
	public int range;
	public int damage;
	public EnemyMove target;
	public int attackTime = 0; 
	public int attackDelay = 0;
	public int maxAttackTime;
	public int maxAttackDelay;
	
	public int FIRST = 1; //attack enemy nearest base
	public int RANDOM = 2; //attack random enemy
	public int attackStrategy = RANDOM;
	
	public static int[] number = new int[] {1,1,1,1,1,1};
	
	public Tower (int id, int range, int damage, int maxAttackTime, int maxAttackDelay) {
		this.range = range;
		if (towerList[id]!=null) {
			System.out.println("[TowerInitializattion] Two towers with same id = "+id);
		}
		else {
			towerList[id] = this;
			this.id = id;
			this.damage = damage;
			this.maxAttackDelay = maxAttackDelay;
			this.maxAttackTime = maxAttackTime;
		}
	}
	
	public EnemyMove calculateEnemy(EnemyMove[] enemies, int x, int y) {
		//cek musuh mana aja yang ada di range
		EnemyMove[] enemiesInRange = new EnemyMove [enemies.length];
		int towerX = x, towerY = y;
		int enemyX, enemyY;
		int towerRadius = this.range;
		int enemyRadius = 1;
		
		for (int i=0;i<enemies.length;i++) {
			if (enemies[i] !=  null) {
				enemyX = (int) ((enemies[i].xPos-200)/Screen.towerSide);
				enemyY = (int) ((enemies[i].yPos-50)/Screen.towerSide);
				
				int dx = enemyX - towerX;
				int dy = enemyY - towerY;
				int dradius = towerRadius + enemyRadius;
				
				if ((dx*dx) + (dy*dy)< (dradius*dradius)) {
					enemiesInRange[i] = enemies[i];
				}
			}
		}
		
		if(this.attackStrategy == RANDOM) {
			int totalEnemies = 0;
			for (int i=0;i<enemiesInRange.length;i++) {
				
				if (enemiesInRange[i] != null) {
					totalEnemies++;
				}
			}
			
			if (totalEnemies > 0) {
				int enemy = new Random().nextInt(totalEnemies);
				int enemiesTaken = 0;
				int i = 0;
				
				while (true) {
					if (enemiesTaken == enemy && enemiesInRange[i] != null) {
						return enemiesInRange[i];
					}
					if (enemiesInRange[i] != null) enemiesTaken++;
					
					i++;
				}
			}
			
		}
		
		return null;
	}
	
	public Tower getTextureFile (String str) {
		this.textureFile = str;
		this.texture = new ImageIcon("res/tower/" + this.textureFile+ ".png").getImage();
		return null;	
	}
	
	//setter
	public Image getTexture() {
		return texture;
	}
	public void setTexture(Image texture) {
		this.texture = texture;
	}
	public String getTextureFile() {
		return textureFile;
	}
	public void setTextureFile(String textureFile) {
		this.textureFile = textureFile;
	}
}
