package com.example;

public class EnemyAIMove extends EnemyAI {

	public EnemyAIMove(int id) {
		super(id);
	}
	
	public void move(EnemyMove enemy) {
		
		if((enemy.xPos-200) % Screen.towerSide == 0 && (enemy.yPos-50) % Screen.towerSide == 0 
				&& enemy.routePosX == (enemy.xPos-200)/Screen.towerSide && enemy.routePosY == (enemy.yPos-50)/Screen.towerSide ) {
			
			//kalo sdua sampe base ngedamage
			if (enemy.routePosX == basePosX && enemy.routePosY == basePosY) {
//				System.out.println("wtf");
				enemy.attack = true;
			}
			else {
				if (route.route[enemy.routePosX][enemy.routePosY] == route.UP) {
					enemy.routePosY--;
				}
				else if (route.route[enemy.routePosX][enemy.routePosY] == route.DOWN) {
					enemy.routePosY++;
				}
				else if (route.route[enemy.routePosX][enemy.routePosY] == route.RIGHT) {
					enemy.routePosX++;
				}
				else if (route.route[enemy.routePosX][enemy.routePosY] == route.LEFT) {
					enemy.routePosX--;
				}
			}
		}
		else {
			double xPos = (enemy.xPos-200)/Screen.towerSide;
			double yPos = (enemy.yPos-50)/Screen.towerSide;
			
			int lol=0;
			
			if (xPos > enemy.routePosX) {
				enemy.xPos -= enemy.enemy.speed/24;
				lol++;
			}
			if (xPos < enemy.routePosX) {
				enemy.xPos += enemy.enemy.speed/24;
				lol++;
			}
			if (yPos > enemy.routePosY) {
				enemy.yPos -= enemy.enemy.speed/24;
				lol++;
			}
			if (yPos < enemy.routePosY) {
				enemy.yPos += enemy.enemy.speed/24;
				lol++;
			}
//			if (lol==2) {
//				System.out.println(xPos+" " + yPos);
//				System.out.println(enemy.routePosX+" " + enemy.routePosY);
//				System.exit(0);
//			}
//			System.out.println(xPos+" " + yPos);
//			System.out.println(enemy.routePosX+" " + enemy.routePosY);
		}
			
	}
	
	public void cantFindRoute(){
		System.out.println("[EnemyAIMove] cant find route");
	}
	
}
