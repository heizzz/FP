package com.example;

public class Player{
	
	int health;
	int money;

	public Player(User user) {
		// TODO Auto-generated constructor stub
		this.money = user.startingMoney;
		this.health = user.startingHealth;
	}

	

}
