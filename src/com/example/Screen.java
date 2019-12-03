package com.example;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable {

	Thread thread = new Thread (this);
	
	private int fps = 0;
	Frame frame;
	User user;
	Level level;
	LevelFile levelFile;
	Wave wave;
	EnemyAI enemyAI;
	
	private static int xGrid = 18;
	private static int yGrid = 10;
	
	public static boolean running = false;
	public int [][] map = new int [this.xGrid] [this.yGrid];
	public Tower[][] towerMap = new Tower[this.xGrid][this.yGrid];
	public Image[] terrain = new Image[100];
	public int scene = 0;
	public static int towerSide = 1;
	public int hand = 0;
	public int handXPos = 0;
	public int handYPos = 0;
	
	private String packagename = "com/example";
	public EnemyMove[] enemyMap = new EnemyMove[200];
	private int enemies = 0;
	Image BGMainMenu = new ImageIcon("res/Main-menu.png").getImage();
	Image Map = new ImageIcon("res/Map.png").getImage();
//	public renderObject; 
	
	
	public Screen (Frame frame) {
		this.frame = frame;
		this.frame.addKeyListener(new KeyHandler(this));
		this.frame.addMouseListener(new MouseHandler(this));
		this.frame.addMouseMotionListener(new MouseHandler (this));
		thread.start();
	}
	
	public void paintComponent (Graphics g) {
		
		g.clearRect(0, 0, this.frame.getWidth(), this.frame.getHeight());
		
		double square = (this.frame.getWidth() - (5 * (this.frame.getWidth() / 20))) / 20;
		towerSide =(int) square;
//		System.out.println(square);
		if (scene == 0) {
//			g.setColor(Color.RED);
//			g.fillRect(0 ,0 ,this.frame.getWidth(), this.frame.getHeight());
			g.drawImage(BGMainMenu,0,0,null);
		}
		//Bg
		else if (scene==1) {
//			System.out.println("lol");
			g.setColor(Color.GREEN);
			g.fillRect(0,0,this.frame.getWidth(), this.frame.getHeight());
		//Grid	
			g.setColor(Color.GRAY);
			for (int x=0;x<this.xGrid;x++) {
				for (int y=0;y<this.yGrid;y++) {
					g.drawImage(terrain[map[x][y]], 200 + x* towerSide,50 + y * towerSide,null);  
					g.drawRect(200 + (x * (int)square), 50 + (y * (int)square), (int) square, (int) square);
				}
			}
			g.drawImage(Map, 200, 50, null);
//			g.drawRect(200, 200, 72, 72);
//			g.drawImage(Enemy.enemyList[0].getTexture(), 200,200, null);
		
			//Health Money
//			g.drawRect(200, 700, 200, 100);
			g.drawRect(200, 800, 200, 100);
			g.drawString("Health: "+user.player.health, 220,850);
			g.drawRect(200, 900, 200, 100);
			g.drawString("Money: "+ user.player.money , 220,950);
			
			//Element List
			for (int x=0;x<6;x++) {
				g.drawRect(500 + (x * (int)square), 850, (int) square, (int) square);
				if (Tower.towerList[x]!=null) g.drawImage(Tower.towerList[x].getTexture(),500 + (x * (int)square), 850,(int)square, (int)square, null);
			}
			
			//towers on grid
			for (int x=0;x<this.xGrid;x++) {
				for (int y=0;y<this.yGrid; y++) {
					if (towerMap[x][y]!= null) {
//						System.out.println("gambar tower");
						g.setColor(Color.GRAY);
						g.drawOval(200+x*towerSide-towerMap[x][y].range * 2 * towerSide + towerSide+108, 108+50+y*towerSide-towerMap[x][y].range * 2 * towerSide + towerSide, towerMap[x][y].range * 2 * towerSide, towerMap[x][y].range * 2 * towerSide );
						g.setColor(new Color (64,64,64,64));
						g.fillOval(200+x*towerSide-towerMap[x][y].range * 2 * towerSide + towerSide+108, 108+50+y*towerSide-towerMap[x][y].range * 2 * towerSide + towerSide, towerMap[x][y].range * 2 * towerSide, towerMap[x][y].range * 2 * towerSide );
						g.drawImage(Tower.towerList[towerMap[x][y].id].getTexture(), 200+towerSide*x, 50+towerSide*y, towerSide, towerSide, null);
						
						if (towerMap[x][y].target!=null) {
							g.setColor(Color.RED);
							g.drawLine(200+x*towerSide+towerSide/2, 50+ y*towerSide+towerSide/2, (int)towerMap[x][y].target.xPos, (int)towerMap[x][y].target.yPos);
						}
					}
				}
			}
			//hand
			if (hand!=0 && Tower.towerList[hand-1] != null) {
				g.drawImage(Tower.towerList[hand-1].getTexture(), (int) this.handXPos - (int) square/2, this.handYPos - (int) square/2, (int) square,(int) square ,null);
			}
			
			//enemies
			
			for (int i=0; i<enemyMap.length;i++) {
				if (enemyMap[i]!=null) {
//					System.out.println(spawnPoint.getX);
//					System.out.println(enemyMap[i].xPos + " " + enemyMap[i].yPos);
					g.drawImage(enemyMap[i].enemy.texture,(int) enemyMap[i].xPos, (int)enemyMap[i].yPos, null);
				}
			}
//			if (Enemy.enemyList[0]!=null) {
//				g.drawImage(Enemy.enemyList[0].getTexture(), 200,200, null);
////				System.out.println("woy");
//			}
		}
		
			
//		else {
//			g.setColor(Color.WHITE);
//			g.fillRect(0,0,this.frame.getWidth(), this.frame.getHeight());
//		}
		
		g.drawString(fps + "", 10, 10);
	}
	
	//first
	public void loadGame() {
		user = new User(this);
		levelFile = new LevelFile();
		wave = new Wave (this);
		
		ClassLoader cl = this.getClass().getClassLoader();
		for (int y=0;y<10;y++) {
			for (int x=0;x<10;x++) {
				terrain[x + y*10] = new ImageIcon(cl.getResource(packagename + "/terrain.png")).getImage();
				terrain[x + y*10] = createImage (new FilteredImageSource (terrain[x+y*10].getSource(), new CropImageFilter(x*72, y*72, 72,72)));
			}
		}
		
		running = true;
	}
	//tiap start level baru
	public void startGame(User user, String levelName) {
//		System.out.println("tes");
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
//		System.out.println("tes");
		long lastFrame = System.currentTimeMillis();
		int frames = 0;
//		System.out.println("tes");
		while (running) {
//			System.out.println("tes");
			repaint();
			frames++;
			
			if(System.currentTimeMillis()-1000 >= lastFrame) {
				fps = frames;
				frames = 0;
				lastFrame = System.currentTimeMillis();
			}
			
			update();
//			
//			System.out.println("tes");
			
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
		if (this.towerMap[x][y].target == null) {
			//cari target
			if (this.towerMap[x][y].attackDelay > this.towerMap[x][y].maxAttackDelay) {
				EnemyMove currentEnemy = this.towerMap[x][y].calculateEnemy(enemyMap, x, y);
				
				if(currentEnemy != null) {
					currentEnemy.health -= this.towerMap[x][y].damage;
					this.towerMap[x][y].target = currentEnemy;
					this.towerMap[x][y].attackTime = 0;
					this.towerMap[x][y].attackDelay = 0;
					
					System.out.println("[Tower] Enemy Attacked! health : " + currentEnemy.health);
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
//				enemyMap[i] = new EnemyMove(Enemy.enemyList[0], level.spawnPoint);
				enemyMap[i] = new EnemyMove(Enemy.enemyList[enemyID], level.spawnPoint, level.endPoint);
				break;
				
			}
		}
	}
	public void placeTower(int x, int y) {
		int xPos = (x - 200 )/towerSide;
		int yPos = (y - 50)/towerSide;
		
		System.out.println("gridpos = "+xPos + " " + yPos);
//		System.out.println("musepos = "+x + " " + y );
//		System.out.println(yPos);
		if (xPos>=this.xGrid || xPos < 0 || yPos < 0|| yPos >= this.yGrid) {
			
		} else if (map[xPos][yPos]==0) {
//			System.out.println("masuk woy");
			towerMap[xPos][yPos] = Tower.towerList[hand-1];
			if (towerMap[xPos][yPos]!=null) System.out.println("berhasil");  
		}
	
	}
	
	public class KeyTyped{
		public void keyESC() {
			running = false;
		}

		public void keySPACE() {
			startGame(user, "Level1");
		}
		public void keyENTER() {
			wave.nextWave();
		}
	}
	
	public class MouseHeld {
		boolean mouseDown = false;
		
		
		public void mouseMoved(MouseEvent e) {
			handXPos = e.getXOnScreen();
			handYPos = e.getYOnScreen();
			
		}
		
		public void updateMouse(MouseEvent e) {
			if (scene == 0) {
				if (mouseDown) {
//					System.out.println("x = "+e.getXOnScreen());
//					System.out.println("y = "+e.getYOnScreen());
					if (e.getXOnScreen() >= 772 && e.getXOnScreen() <= 1212) {
						if (e.getYOnScreen() >= 711 && e.getYOnScreen() <= 773) {
							startGame(user, "Level1");
						}
					}
				}
			}
			if (scene == 1) {
				if (mouseDown && hand == 0) {
					if (e.getXOnScreen() >= 500 && e.getXOnScreen() <= 500+6*towerSide ) {
						if (e.getYOnScreen() >= 850 && e.getYOnScreen() <= 850+towerSide) {
							int code = (e.getXOnScreen()-500)/72+1;
							if (Tower.number[code] > 0) {
								System.out.println("Berhasil beli tower "+code);
//								Tower.number[code]--;
								hand=code;
//								System.out.println(hand);
							}
						}
					}
				}
			}
		}

		public void mouseDown(MouseEvent e) {
			mouseDown = true;
			if (hand!=0) {
				//place tower
//				g.drawRect(200 + (x * (int)square), 50 + (y * (int)square), (int) square, (int) square);
				if (e.getXOnScreen() >= 200 && e.getXOnScreen() <= Screen.getxGrid()*towerSide+200 && e.getYOnScreen() >= 50 && e.getYOnScreen() <= Screen.getyGrid()*towerSide + 50) {
					placeTower(e.getXOnScreen(), e.getYOnScreen());
					hand = 0;
				}				
			}
			updateMouse(e);
			
		}
		
	}
	public static int getxGrid() {
		return xGrid;
	}


	public static int getyGrid() {
		return yGrid;
	}
}
