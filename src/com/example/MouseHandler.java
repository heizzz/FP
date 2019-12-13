package com.example;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {

	private Screen screen;
	boolean mouseDown = false;
	private User user;
//	private Screen.MouseHeld mouseHeld;
	
	
	public MouseHandler (Screen screen, User user) {
		this.screen = screen;
		this.user = user;
//		this.mouseHeld = this.screen.new MouseHeld();
	}
	
	public void updateMouse(MouseEvent e) {
		if (Screen.scene == 0) {
			if (mouseDown) {
				System.out.println("x = "+e.getXOnScreen() + " y = "+ e.getYOnScreen());
				if (e.getXOnScreen() >= 772 && e.getXOnScreen() <= 1212) {
					if (e.getYOnScreen() >= 800 && e.getYOnScreen() <= 862) {
						screen.startGame(user,"level1",100);
					}
				}
				if (e.getXOnScreen() >= 772 && e.getXOnScreen() <= 1212) {
					if (e.getYOnScreen() >= 711 && e.getYOnScreen() <= 773) {
						Screen.scene = 3;
					}
				}
			}
		}
		if (Screen.scene == 3) {
			System.out.println("x = "+e.getXOnScreen() + " y = "+ e.getYOnScreen());
			if (e.getXOnScreen() >= 425 && e.getXOnScreen() <= 560 && e.getYOnScreen() >= 480 && e.getYOnScreen() <= 600) {
				screen.startGame(user, "level1", 10);
			}
			else if (e.getXOnScreen() >= 1165 && e.getXOnScreen() <= 1300 && e.getYOnScreen() >= 200 && e.getYOnScreen() <= 335) {
				screen.startGame(user, "level1", 20);
			}
			else if (e.getXOnScreen() >= 1425 && e.getXOnScreen() <= 1555 && e.getYOnScreen() >= 735 && e.getYOnScreen() <= 850) {
				screen.startGame(user, "level1", 30);
			}
		}
		if (Screen.scene == 1) {
			System.out.println("x = "+e.getXOnScreen() + " y = "+ e.getYOnScreen());
			if (mouseDown && screen.getHand() == 0) {
				int x=0;
				for (int i=0;i<=11;i+=2) {
					if (e.getXOnScreen() >= Elements.loc[i] && e.getXOnScreen() <= Elements.loc[i+1] ) {
						if (e.getYOnScreen() >= 890 && e.getYOnScreen() <= 1050) {
							if (Elements.number[x] > 0 && user.player.money>= Elements.number[x]*50) {
							System.out.println("Berhasil beli tower "+x);
							user.player.money-=(Elements.number[x]*50);
							screen.setHand(x+1);
							}
						}
					}
					x++;
				}
			}
			
			if (e.getYOnScreen() >= 25 && e.getYOnScreen() <= 100 && e.getXOnScreen() >= 1825 && e.getXOnScreen() <= 1900 && screen.paused == false) {
				screen.paused = true;
				System.out.println("paused");
			}
			else if (e.getYOnScreen() >= 750 && e.getYOnScreen() <= 840 && e.getXOnScreen() >= 925 && e.getXOnScreen() <= 1035 && screen.paused == true) {
				screen.paused = false;
				System.out.println("unpaused");
				try {
					screen.thread.sleep(100);
				} catch (InterruptedException i){
					i.printStackTrace();
				}
			}
			if (e.getYOnScreen() >= 890 && e.getYOnScreen() <= 1050) {
				if (e.getXOnScreen() >= 550 && e.getXOnScreen() <= 710) {
					boolean check = true;
					int count=0;
					for (int i=0;i<=5;i++) if (Elements.number[i] == 3) count++;
					if (count==6) check = false;
					if (user.player.money >= 250 && check) {
						user.player.money-=250;
						boolean test = true;
						int num = (int)(Math.random()*6);
						while (test) {
							if (Elements.number[num] < 3) test = false;
							else num = (int)(Math.random()*6);
						}
						Elements.number[num]++;
						
					}
				}
			}
			if (screen.alive == false) {
				if (e.getXOnScreen() >= 850 && e.getXOnScreen() <= 940 && e.getYOnScreen() >= 760 && e.getYOnScreen() <= 850) {
					System.out.println("home");
					screen.loadGame();
					Screen.scene = 0;
				}
					
				else if (e.getXOnScreen() >= 985 && e.getXOnScreen() <= 1065 && e.getYOnScreen() >= 760 && e.getYOnScreen() <= 850) {
					screen.startGame(user, "level1",10);
					System.out.println("ngulang");
				}		
			}
			if (screen.win && e.getYOnScreen() >= 750 && e.getYOnScreen() <= 840 && e.getXOnScreen() >= 925 && e.getXOnScreen() <= 1035) {
				Screen.scene = 0;
			}
		}
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
		

	}

	
	public void mouseExited(MouseEvent e) {
		

	}

	public void mousePressed(MouseEvent e) {
		mouseDown(e);
	}

	
	public void mouseReleased(MouseEvent e) {
		
	}

	
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
		
		
	}

	public void mouseDown(MouseEvent e) {
		mouseDown = true;
		if (screen.getHand()!=0) {
			//place tower
			if (e.getXOnScreen() >= 200 && e.getXOnScreen() <= Screen.getxGrid()*72+200 
					&& e.getYOnScreen() >= 50 && e.getYOnScreen() <= Screen.getyGrid()*72 + 50) {
				screen.placeTower(e.getXOnScreen(), e.getYOnScreen());
				screen.setHand(0);
			}				
		}
		
		
		
		updateMouse(e);
		
	}
	
	public void mouseMoved(MouseEvent e) {
		screen.handXPos = e.getXOnScreen();
		screen.handYPos = e.getYOnScreen();
		screen.towerMap2 = new boolean [18][10];
		if (Screen.scene == 1 && screen.getHand()==0) {
			if (e.getXOnScreen() >= 200 && e.getXOnScreen() <= Screen.getxGrid()*72+200 
					&& e.getYOnScreen() >= 50 && e.getYOnScreen() <= Screen.getyGrid()*72 + 50) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen(); 
				int xPos = (x - 200 )/72;
				int yPos = (y - 50)/72;
				if (screen.towerMap1[xPos][yPos]!=0) screen.towerMap2[xPos][yPos]=true;
			}
		}
//		this.mouseHeld.mouseMoved(e);
		
	}

}
