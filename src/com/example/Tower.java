package com.example;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Tower {
	
	private String textureFile = "";
	protected Image[] texture = new Image[4];
	public static final Tower[] towerList = new Tower[200];
	
	public static final Tower darkT = new TowerDark(1,2,1,5,50,1).getTextureFile("dark");
	public static final Tower fireT = new TowerFire(4,2,1,5,3,2).getTextureFile("fire");
	public static final Tower lightningT = new TowerLightning(9,2,1,5,3,2).getTextureFile("lightning");
	public static final Tower groundT = new TowerGround(16,2,1,5,3,1).getTextureFile("ground");
	public static final Tower waterT = new TowerWater(25,2,1,5,3,2).getTextureFile("water");
	public static final Tower lightT = new TowerLight(36,2,1,5,3,1).getTextureFile("light");
	
	public static final Tower DF = new TowerDark(5,2,1,5,3,2).getTextureFile("darkfire");
	public static final Tower DT = new TowerLightning(10,2,1,5,3,2).getTextureFile("darklightning");
	public static final Tower DG = new TowerDark(17,2,1,5,3,2).getTextureFile("darkground");
	public static final Tower DW = new TowerWater(26,2,1,5,3,2).getTextureFile("darkwater");
	public static final Tower DL = new TowerDark(37,2,1,5,3,2).getTextureFile("darklight");
	
	public static final Tower FT = new TowerFire(13,2,1,5,3,2).getTextureFile("firelightning");
	public static final Tower FG = new TowerGround(20,2,1,5,3,2).getTextureFile("fireground");
	public static final Tower FW = new TowerFire(29,2,1,5,3,2).getTextureFile("firewater");
	public static final Tower FL = new TowerLight(40,2,1,5,3,2).getTextureFile("firelight");
	
	public static final Tower TG = new TowerLightning(24,2,1,5,3,2).getTextureFile("lightningground");
	public static final Tower TW = new TowerWater(34,2,1,5,3,2).getTextureFile("lightningwater");
	public static final Tower TL = new TowerLightning(45,2,1,5,3,2).getTextureFile("lightninglight");
	
	public static final Tower GW = new TowerGround(41,2,1,5,3,2).getTextureFile("groundwater");
	public static final Tower GL = new TowerLight(52,2,1,5,3,2).getTextureFile("groundlight");
	
	public static final Tower WL = new TowerWater(61,2,1,5,3,2).getTextureFile("waterlight");
	
	public int id;
	public int range;
	public int damage;
	public EnemyMove target;
	public int attackTime = 0; 
	public int attackDelay = 0;
	public int maxAttackTime;
	public int maxAttackDelay;
	public int arr;
	protected Elements element;
	
	public int FIRST = 1; //attack enemy nearest base
	public int RANDOM = 2; //attack random enemy
	public int attackStrategy;
	
//	public static int[] number = new int[] {0,0,0,0,0,0};
	
	public Tower (int id, int range, int damage, int maxAttackTime, int maxAttackDelay, int attackStrategy) {
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
			this.attackStrategy = attackStrategy;
		}
	}
	
	public Tower () {
		
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
				
//				int dx = enemyX - towerX;
//				int dy = enemyY - towerY;
//				int dradius = towerRadius + enemyRadius;
//				
//				if ((dx*dx) + (dy*dy)< (dradius*dradius)) {
//					enemiesInRange[i] = enemies[i];
//				}
				if (Math.abs(enemyX-towerX) < towerRadius && Math.abs(enemyY-towerY) < towerRadius) {
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
		else if (this.attackStrategy == FIRST) {
			int totalEnemies = 0;
			for (int i=0;i<enemiesInRange.length;i++) {
				if (enemiesInRange[i] != null) {
					return enemiesInRange[i];
				}	
			}
		}
		
		return null;
	}
	
	public Tower getTextureFile (String str) {
		this.textureFile = str;
		this.texture[0] = new ImageIcon("res/tower/" + this.textureFile+ "0.png").getImage();
		this.texture[1] = new ImageIcon("res/tower/" + this.textureFile+ "1.png").getImage();
		this.texture[2] = new ImageIcon("res/tower/" + this.textureFile+ "2.png").getImage();
		this.texture[3] = new ImageIcon("res/tower/" + this.textureFile+ "3.png").getImage();
		return null;	
	}
	
	//setter
	public Image getTexture(int i) {
		int x = 0;
		if (i == 7) x=0;
		if (i == 8) x=1;
		if (i == 9) x=2;
		if (i == 0) x=3;
		return texture[x];
	}
//	public void setTexture(Image texture) {
//		this.texture = texture;
//	}
	public String getTextureFile() {
		return textureFile;
	}
	public void setTextureFile(String textureFile) {
		this.textureFile = textureFile;
	}
}
