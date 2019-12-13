package com.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.text.html.parser.Element;


public class Screen extends JPanel implements Runnable {

	Thread thread = new Thread (this);
	
	private int fps = 0;
	Frame frame;
	User user;
	Level level;
	LevelFile levelFile;
	Wave wave;
	EnemyAI enemyAI;
	
//	Elements element;
	
//	DoubleProperty lol;
	
	private static int xGrid = 18;
	private static int yGrid = 10;
	
	static boolean win = false;
	static boolean paused = false;
	static boolean alive = false;
	public static boolean running = false;
	public int [][] map = new int [this.xGrid] [this.yGrid];
	public Tower[][] towerMap = new Tower[18][10];
	public int [][]towerMap1 = new int[18][10];
	public boolean[][] towerMap2 = new boolean [18][10];
	public Image[] terrain = new Image[100];
	public static int scene = 0;
	public static int towerSide = 1;
	public static int hand = 0;
	
	public int getHand() {
		return hand;
	}

	public void setHand(int hand) {
		this.hand = hand;
	}


	public int handXPos = 0;
	public int handYPos = 0;
	
	private String packagename = "com/example";
	public EnemyMove[] enemyMap = new EnemyMove[200];
	private int enemies = 0;
//	Tower tower = new Tower();
	Image BGMainMenu = new ImageIcon("res/Main-menu.png").getImage();
	Image Map = new ImageIcon("res/Map1.png").getImage();
	Image Elemen = new ImageIcon ("res/gacha.png").getImage();
	Image bg = new ImageIcon ("res/Bg2.png").getImage();
	Image gameover = new ImageIcon ("res/GameOver-Panel.png").getImage();
	Image pauseI = new ImageIcon ("res/pause-icon.png").getImage();
	Image pauseS = new ImageIcon("res/Pause.png").getImage();
	Image winS = new ImageIcon("res/win.png").getImage();
	Image storyS = new ImageIcon("res/Smode.png").getImage();
	Image check = new ImageIcon("res/check.png").getImage();
	Image energy = new ImageIcon("res/energy.png").getImage();
	
	
	public Screen (Frame frame) {
		this.frame = frame;
		thread.start();
	}
	
	public void paintComponent (Graphics g) {
		
		g.clearRect(0, 0, this.frame.getWidth(), this.frame.getHeight());
		
		double square = (this.frame.getWidth() - (5 * (this.frame.getWidth() / 20))) / 20;
		towerSide =(int) square;
		if (scene == 0) {
			g.drawImage(BGMainMenu,0,0,null);
		}
		//Bg
		else if (scene == 3) {
			g.drawImage(storyS,0,0,null);
			if (User.complete[0] == 1) g.drawImage(check,425,480,135,120,null);
			if (User.complete[1] == 1) g.drawImage(check,1165,200,135,135,null);
			if (User.complete[2] == 1) g.drawImage(check,1425,735,130,130,null);
		}
		else if (scene==1) {
			g.setColor(Color.GREEN);
			g.fillRect(0,0,this.frame.getWidth(), this.frame.getHeight());
			
			g.drawImage(Map, 200, 50, null);
			g.drawImage(bg, 0,0,null);
			g.drawImage(Elemen, 550,890,null);
			g.drawImage(energy,300,885,75,75,null);
			
		
			//Health Money
			g.drawRect(50,950,400,50);
			g.setColor(Color.WHITE);
			g.fillRect(50,950,400,50);
			g.setColor(Color.GREEN);
			g.fillRect(50, 950, user.player.health*400/100, 50);
			g.setColor(Color.WHITE);
			g.setFont(new Font ("default", Font.BOLD,24));
			g.drawString("Wave "+ Wave.waveNumber,50,930);
			g.drawString(""+ user.player.money , 375,930);
			
			//pause
			g.setColor(Color.WHITE);
			g.drawRect(1825, 25, 75, 75);
			g.fillRect(1825, 25, 75, 75);
			g.drawImage(pauseI, 1825,25,75,75,null);
			
			//Element List
			
			for (int x= 0;x<=5;x++) {
				if (Elements.elementList[x]!=null) {
					g.drawImage(Elements.elementList[x].getTexture(),730 + x*180, 890, 160, 160,null);
					if (Elements.number[x]!=0)g.drawString(""+50*Elements.number[x], 730+x*180+ 65, 1060);
					g.drawString(""+ Elements.number[x], 730 + x*180+ 75, 900);
				}
				g.setColor(Color.WHITE);
				g.setFont(new Font ("default", Font.BOLD,24));
			}
			
			//towers on grid
			for (int x=0;x<this.xGrid;x++) {
				for (int y=0;y<this.yGrid; y++) {
					if (towerMap1[x][y]!=0 && towerMap[x][y]!=null) {
						if (towerMap2[x][y]) {
							g.setColor(Color.GRAY);
							g.drawRect(200+x*towerSide-towerMap[x][y].range * 2 * towerSide + towerSide+108, 108+50+y*towerSide-towerMap[x][y].range * 2 * towerSide + towerSide, towerMap[x][y].range * 2 * towerSide, towerMap[x][y].range * 2 * towerSide );
							g.setColor(new Color (64,64,64,64));
							g.fillRect(200+x*towerSide-towerMap[x][y].range * 2 * towerSide + towerSide+108, 108+50+y*towerSide-towerMap[x][y].range * 2 * towerSide + towerSide, towerMap[x][y].range * 2 * towerSide, towerMap[x][y].range * 2 * towerSide );
						}
						g.drawImage(Tower.towerList[towerMap1[x][y]].getTexture(level.map[x][y]), 200 + towerSide*x, 50+towerSide*y, towerSide, towerSide, null);

					}
				}
			}
			
			if (hand!=0 && Elements.elementList[hand-1] != null) {
				g.drawImage(Elements.elementList[hand-1].getTexture(), (int) this.handXPos - (int) square/2, this.handYPos - (int) square/2, (int) square,(int) square ,null);
			}
			
			//enemies
			
			for (int i=0; i<enemyMap.length;i++) {
				if (enemyMap[i]!=null) {
					g.drawImage(enemyMap[i].enemy.getTexture(level.map[enemyMap[i].routePosX][enemyMap[i].routePosY]),(int) enemyMap[i].xPos, (int)enemyMap[i].yPos, null);
					g.setColor(Color.BLACK);
					g.drawRect((int) enemyMap[i].xPos, (int)enemyMap[i].yPos-15, 72, 10);
					g.setColor(Color.RED);
					g.fillRect((int) enemyMap[i].xPos, (int)enemyMap[i].yPos-15, ((int) enemyMap[i].health * 72 / enemyMap[i].maxHealth), 10);
				}
			}
			
			if (paused) {
				g.setColor(new Color(128,128,128,128));
				g.fillRect(0, 0, 1920, 1080);
				g.drawImage(pauseS,0,0,null);
			}
			if (alive == false) {
				g.setColor(new Color(128,128,128,128));
				g.fillRect(0, 0, 1920, 1080);
				g.drawImage(gameover,0,0,1920,1080,null);
				g.setFont(new Font ("default", Font.BOLD,30));
				g.drawString("You Lost on wave-"+wave.waveNumber, 900, 540);
			}
			if (win) {
				g.setColor(new Color(128,128,128,128));
				g.fillRect(0, 0, 1920, 1080);
				g.drawImage(winS,0,0,null);
				
			}
		}
		
		g.drawString(fps + "", 10, 10);
	}
	
	//first
	public void loadGame() {
		
		int x=0;
		for (int i=0;i<=11;i+=2) {
			Elements.loc[i] = 730+x*180;
			Elements.loc[i+1] = Elements.loc[i]+160;
			x++;
		}
		user = new User(this);
		levelFile = new LevelFile();

		this.frame.addKeyListener(new KeyHandler(this));
		this.frame.addMouseListener(new MouseHandler(this,user));
		this.frame.addMouseMotionListener(new MouseHandler (this,user));
		running = true;
		
	}
	
	//tiap start level baru
	public void startGame(User user, String levelName, int waveThisLevel) {
		win=false;
//		System.out.println("tes");
		wave = new Wave (this, waveThisLevel);
		wave.waveSpawning = false;
		towerMap = new Tower[xGrid][yGrid];
		towerMap1 = new int[xGrid][yGrid];
		enemyMap = new EnemyMove[100];
		for (int i=0;i<6;i++) Elements.number[i] = 0;
		alive = true;
		user.createPlayer();
		this.level = levelFile.getLevel(levelName);
		this.level.findSpawnPoint();
		this.map = this.level.map;
		this.wave.waveNumber=0;
		this.scene = 1; //lvl 1
		this.enemyAI = new EnemyAI(this.level);
	}
	
	@Override
	public void run() {
		loadGame();
		long lastFrame = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			repaint();
			frames++;
			
			if(System.currentTimeMillis()-1000 >= lastFrame) {
				fps = frames;
				frames = 0;
				lastFrame = System.currentTimeMillis();
			}
			if (paused == false) {
				
				if (alive)
					update();
				else {
					try {
						thread.sleep(100);
					} catch (InterruptedException e){
						e.printStackTrace();
					}
				}
				
			} else {
				try {
					thread.sleep(100);
				} catch (InterruptedException e){
					e.printStackTrace();
				}
			}

			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.exit(0);
	}
	
	public void enemyUpdate() {
		for (int i=0;i<enemyMap.length;i++) {
			if (enemyMap[i] != null) {
				if (!enemyMap[i].attack) {
					EnemyAI.moveAI.move(enemyMap[i]);
				}
				
				enemyMap[i] = enemyMap[i].update(user, this.map); //cek masi idup ato nda
			}
		}
	}
	
	public void towerUpdate() {
		for (int x=0 ; x <this.xGrid;x++) {
			for (int y=0;y<this.yGrid;y++) {
				if (towerMap[x][y]!=null) {
					towerAttack(x,y);
				}
			}
		}
	}
	
	public void towerAttack (int x, int y) {
		for (int i=0;i<=5;i++) {
			if (i==0 && this.towerMap[x][y] instanceof TowerDark) this.towerMap[x][y].damage += (5*Elements.number[i]);
			if (i==1 && this.towerMap[x][y] instanceof TowerFire) this.towerMap[x][y].damage += (5*Elements.number[i]);
			if (i==2 && this.towerMap[x][y] instanceof TowerLightning) this.towerMap[x][y].damage += (5*Elements.number[i]);
			if (i==3 && this.towerMap[x][y] instanceof TowerGround) this.towerMap[x][y].damage += (5*Elements.number[i]);
			if (i==4 && this.towerMap[x][y] instanceof TowerWater) this.towerMap[x][y].damage += (5*Elements.number[i]);
			if (i==5 && this.towerMap[x][y] instanceof TowerLight) this.towerMap[x][y].damage += (5*Elements.number[i]);
		}
		
		if (this.towerMap[x][y].target == null) {
			//cari target
			if (this.towerMap[x][y].attackDelay > this.towerMap[x][y].maxAttackDelay) {
				EnemyMove currentEnemy = this.towerMap[x][y].calculateEnemy(enemyMap, x, y);
				
				if(currentEnemy != null) {
					if (currentEnemy.element.getWeak() == this.towerMap[x][y].element.elementName) 
						currentEnemy.health = currentEnemy.health - (1.5 * this.towerMap[x][y].damage); 
					else if (currentEnemy.element.getStrong() == this.towerMap[x][y].element.elementName)
						currentEnemy.health = currentEnemy.health - (0.5 * this.towerMap[x][y].damage);
					else currentEnemy.health -= this.towerMap[x][y].damage;
					
					this.towerMap[x][y].target = currentEnemy;
					this.towerMap[x][y].attackTime = 0;
					this.towerMap[x][y].attackDelay = 0;
					
//					System.out.println("[Tower] Enemy Attacked! health : " + currentEnemy.health);
				}
			}else {
				this.towerMap[x][y].attackDelay+=1;
			}
		} else {
			if (this.towerMap[x][y].attackTime < this.towerMap[x][y].maxAttackTime) {
				this.towerMap[x][y].attackTime+=1;
			}else {
				this.towerMap[x][y].target = null;
			}
		}
		for (int i=0;i<=5;i++) {
			if (i==0 && this.towerMap[x][y] instanceof TowerDark) this.towerMap[x][y].damage -= (5*Elements.number[i]);
			if (i==1 && this.towerMap[x][y] instanceof TowerFire) this.towerMap[x][y].damage -= (5*Elements.number[i]);
			if (i==2 && this.towerMap[x][y] instanceof TowerLightning) this.towerMap[x][y].damage -= (5*Elements.number[i]);
			if (i==3 && this.towerMap[x][y] instanceof TowerGround) this.towerMap[x][y].damage -= (5*Elements.number[i]);
			if (i==4 && this.towerMap[x][y] instanceof TowerWater) this.towerMap[x][y].damage -= (5*Elements.number[i]);
			if (i==5 && this.towerMap[x][y] instanceof TowerLight) this.towerMap[x][y].damage -= (5*Elements.number[i]);
		}
	}
	
	
	public void update() {
		enemyUpdate();
		towerUpdate();
		if (wave.waveSpawning) {
			wave.spawnEnemies();
		}
	}
	public void spawnEnemy(int enemyID) {
		for (int i=0;i<enemyMap.length; i++) {
			if (enemyMap[i] == null) {
				enemyMap[i] = new EnemyMove(Enemy.enemyList[enemyID], level.spawnPoint, level.endPoint);
				enemyMap[i].health*= (1+Enemy.level);
				break;
				
			}
		}
	}
	public void placeTower(int x, int y) {
		int xPos = (x - 200 )/towerSide;
		int yPos = (y - 50)/towerSide;
		
		System.out.println("gridpos = "+xPos + " " + yPos);
		if (xPos>=this.xGrid || xPos < 0 || yPos < 0|| yPos >= this.yGrid) {
			
		} 
		else if ((map[xPos][yPos] == 0 || map[xPos][yPos] == 7 || map[xPos][yPos] == 8|| map[xPos][yPos] == 9) 
				&& (towerMap1[xPos][yPos] == 0)) {
			towerMap1[xPos][yPos] += ((hand)*(hand));
			towerMap[xPos][yPos] = Tower.towerList[towerMap1[xPos][yPos]];
		}
		else if ((towerMap1[xPos][yPos] == 1 || towerMap1[xPos][yPos] == 4 || towerMap1[xPos][yPos] == 9 || towerMap1[xPos][yPos] == 16 || 
				towerMap1[xPos][yPos] == 25 || towerMap1[xPos][yPos] == 36 ) && (towerMap1[xPos][yPos] != ((hand)*(hand)))) {
			
			if ((towerMap1[xPos][yPos] == 16 && hand == 3) || (towerMap1[xPos][yPos] == 9 && hand == 4)) towerMap1[xPos][yPos] = 24;
			else towerMap1[xPos][yPos] += (hand*hand);
			towerMap[xPos][yPos] = Tower.towerList[towerMap1[xPos][yPos]];
		}
	
	}
	
	public class KeyTyped{
		public void keyESC() {
			running = false;
		}
		public void keyENTER() {
			if (wave.waveSpawning==false && Screen.scene == 1) {
				wave.nextWave();
			}
			
		}
	}
	
	public static int getxGrid() {
		return xGrid;
	}


	public static int getyGrid() {
		return yGrid;
	}
}
