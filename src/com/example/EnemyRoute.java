package com.example;

public class EnemyRoute {
	
	Level level;
	int[][] route = new int[18][10];
	int baseBlock = 3;
	int lastPos = -1;
	int xPos, yPos;
	int RIGHT = 1, DOWN = 2, LEFT = 3, UP = 4;
	Base base;
	int count=0;
	
	public EnemyRoute (Level level) {
		this.level = level;
		this.xPos = this.level.spawnPoint.getX();
		this.yPos = this.level.spawnPoint.getY();
//		System.out.println(this.level.spawnPoint.getX());
//		System.out.println(this.level.spawnPoint.getY());
		calculateRoute();
//		System.out.println(base.getxPos()+" "+base.getyPos());
	}
	
	private void calculateRoute() {
		while (base==null) {
			calculateNextPos();
//			if (count == 6 ) {
//				for (int x=0;x<18;x++) {
//					for (int y=0;y<10;y++) {
//						System.out.print(route[x][y]);
//					}
//					System.out.println();
//				}
////				break;
//			}
		}
	}

	private void calculateNextPos() {
		for (int i=1;i<5;i++) {
//			System.out.println(lastPos);
			if (i!=lastPos) {
				if(yPos>0 && i == UP) {
					if (level.map[xPos][yPos-1] == 1) {
						lastPos = DOWN;
						route[xPos][yPos] = UP;
						yPos--;
						break;
					}
					else if (level.map[xPos][yPos-1] == baseBlock) {
						base = new Base(xPos, yPos-1);
						lastPos = DOWN;
						route[xPos][yPos] = UP;
						yPos--;
						break;
					}
				}
				
				if(xPos<17 && i == RIGHT) {
					if (level.map[xPos+1][yPos] == 1) {
						lastPos = LEFT;
						route[xPos][yPos] = RIGHT;
						xPos++;
						break;
					}
					else if (level.map[xPos+1][yPos] == baseBlock) {
						base = new Base(xPos+1, yPos);
						lastPos = LEFT;
						route[xPos][yPos] = RIGHT;
						xPos++;
						break;
					}
				}
				if(yPos<9 && i == DOWN) {
					if (level.map[xPos][yPos+1] == 1) {
						lastPos = UP;
						route[xPos][yPos] = DOWN;
						yPos++;
						break;
					}
					else if (level.map[xPos][yPos+1] == baseBlock) {
						base = new Base(xPos, yPos+1);
						lastPos = UP;
						route[xPos][yPos] = DOWN;
						yPos++;
						break;
					}
				}
				if(xPos>0 && i == LEFT) {
					if (level.map[xPos-1][yPos] == 1) {
						lastPos = RIGHT;
						route[xPos][yPos] = LEFT;
						xPos--;
						break;
					}
					else if (level.map[xPos-1][yPos] == baseBlock) {
						base = new Base(xPos-1, yPos);
						lastPos = RIGHT;
						route[xPos][yPos] = LEFT;
						xPos--;
						break;
					}
				}
//				else System.out.println("omegalul");
//				count++;		
			}
//			else System.out.println("rofl");
		}
		
	}
	
}
