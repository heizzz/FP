package com.example;

public class User {
	

	private Screen screen;
	Player player;
	int startingMoney = 300;
	int startingHealth = 100;

	public User(Screen screen) {
		// TODO Auto-generated constructor stub
		this.screen = screen;
		this.screen.scene = 0;
	}
	
	public void createPlayer() {
		this.player = new Player(this);
	}

}
