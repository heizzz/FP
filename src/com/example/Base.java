package com.example;

public class Base {
	
	int yPos, xPos;
	int baseGround;
	int RIGHT = 1, DOWN = 2, LEFT = 3, UP = 4;
	
	public Base(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
}
