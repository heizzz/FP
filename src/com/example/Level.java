package com.example;

public class Level {
	int [][] map;
	SpawnPoint spawnPoint;
	SpawnPoint endPoint;
	
	public void findSpawnPoint() {
		for (int x=0;x< map.length;x++) {
			for (int y=0;y<map[0].length;y++) {
//				System.out.print(map[x][y]);
				if (map[x][y] == 2) {
					spawnPoint = new SpawnPoint (x,y);
				}
				if (map[x][y] == 3) {
					endPoint = new SpawnPoint (x,y);
				}
			}
//			System.out.println();
		}
	}
}
