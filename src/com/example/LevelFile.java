package com.example;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class LevelFile {
	
	FileInputStream file;
	InputStreamReader reader;
	
	Scanner scanner;
	
	Level level = new Level();
	
	public Level getLevel(String fileName) {
		try {
			file= new FileInputStream ("level/"+fileName+".level");
			reader = new InputStreamReader(file);
			scanner = new Scanner (reader);
			
			level.map = new int [18][10];
			
			int x=0,y=0;
			while (scanner.hasNext()) {
				level.map[x][y] = scanner.nextInt();
				
				if (x<18-1) x++;
				else {
					y++;
					x=0;
				}
			}
			return level;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
