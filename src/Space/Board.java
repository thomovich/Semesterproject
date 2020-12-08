package Space;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import Model.MathModel;
import Model.ModelManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Board extends JPanel {

	private static final long serialVersionUID = 1L;
	private Dimension d;
	private List<Alien> aliens;
	private Player player;
	private Shot shot;
	private MathModel model;
	
	private String problemQuestion;
	private int randomNumberAnswer;
	private int randomNumberAnswer1;
	private int randomNumberAnswer2;
	private int randomNumberAnswer3;
	private int randomNumberAnswer4;
	private int randomNumberAnswer5;
	private boolean drawRect1=false;
	private boolean drawRect2=false;
	private boolean drawRect3=false;
	private boolean wrongNumber=false;
	
	private int randomEntry;
	private boolean ready=false;
	private int currentNumber;
	private String currentAnswer;
	private String currentQuestion;

	private int jValue = 0;

	int count =0;
	private int direction;
	private int speed;
	private int deaths = 0;

	private boolean inGame = true;
	private String explImg = "../Images/explosion.jpg";
	private String message = "Game Over";
	private ArrayList<String> messages = new ArrayList<String>();
	private ArrayList<String> answers = new ArrayList<String>();
	private ArrayList<String> answersTaken = new ArrayList<String>();
	//random positioning
	private ArrayList<Integer> firstRowPositions = new ArrayList<Integer>();
	private ArrayList<Integer> secondRowPositions = new ArrayList<Integer>();
	private ArrayList<Integer> thirdRowPositions = new ArrayList<Integer>();
	private ArrayList<Integer> fourthRowPositions = new ArrayList<Integer>();
	
	private ArrayList<String> firstRowAnswers = new ArrayList<String>();
	private ArrayList<String> secondRowAnswers = new ArrayList<String>();
	private ArrayList<String> thirdRowAnswers = new ArrayList<String>();
	private ArrayList<String> fourthRowAnswers = new ArrayList<String>();
	
	private ArrayList<Integer> numbers = new ArrayList<Integer>();
	private ArrayList<Integer> numberAnswers = new ArrayList<Integer>();
	
	private int randomNumber;
	private int randomNumber2;
	private int randomNumber3;
	
	private int secondRandomNumber;
	private int secondRandomNumber2;
	private int secondRandomNumber3;
	
	private int thirdRandomNumber;
	private int thirdRandomNumber2;
	private int thirdRandomNumber3;
	
	private int fourthRandomNumber;
	private int fourthRandomNumber2;
	private int fourthRandomNumber3;
	

	private Timer timer;

	public Board(String speed,MathModel model) {
		
		this.model= model;
		switch(speed) {
		case"high":
			direction=-3;
			this.speed=3;
			break;
		case"medium":
			direction=-2;
			this.speed=2;
			break;
		case"low":
			direction=-1;
			this.speed=1;
			break;
		}
		answers.add("36");//0
		answers.add("108");//1
		answers.add("70");//2
		answers.add("130");//3
		answers.add("72");//4
		answers.add("135");//5
		answers.add("35");//6
		answers.add("63");//7
		answers.add("64");//8
		answers.add("126");//9
		answers.add("88");//10
		answers.add("120");//11
		answers.add("21");//12
		answers.add("48");//13
		answers.add("300");//14
		answers.add("55");//15
		answers.add("144");//16
		answers.add("56");//17
		answers.add("60");//18
		answers.add("20");//19
		answers.add("45");//20
		answers.add("19");//21
		answers.add("77");//22
		
		initBoard();
		gameInit();
		
	}

	private int randomPositionInRow() {
		return (int) (Math.floor(Math.random() * 5));
	}
	
	private void initBoard() {
		
		randomNumber=(int) Math.floor(Math.random()*4);
		randomNumber2=(int) Math.floor(Math.random()*3);
		randomNumber3=(int) Math.floor(Math.random()*2);
		
		secondRandomNumber=(int) Math.floor(Math.random()*4);
		secondRandomNumber2=(int) Math.floor(Math.random()*3);
		secondRandomNumber3=(int) Math.floor(Math.random()*2);
		
		thirdRandomNumber=(int) Math.floor(Math.random()*4);
		thirdRandomNumber2=(int) Math.floor(Math.random()*3);
		thirdRandomNumber3=(int) Math.floor(Math.random()*2);
		
		fourthRandomNumber=(int) Math.floor(Math.random()*4);
		fourthRandomNumber2=(int) Math.floor(Math.random()*3);
		fourthRandomNumber3=(int) Math.floor(Math.random()*2);
		
		
		for(int i=0;i<5;i++) {
			int number1 = randomPositionInRow();
			while(firstRowPositions.contains(number1)) {
				number1=randomPositionInRow();
			}
			firstRowPositions.add(number1);
			
			int number2 = randomPositionInRow();
			while(secondRowPositions.contains(number2)) {
				number2=randomPositionInRow();
			}
			secondRowPositions.add(number2);
			
			int number3 = randomPositionInRow();
			while(thirdRowPositions.contains(number3)) {
				number3=randomPositionInRow();
			}
			thirdRowPositions.add(number3);
			
			int number4 = randomPositionInRow();
			while(fourthRowPositions.contains(number4)) {
				number4=randomPositionInRow();
				}
			fourthRowPositions.add(number4);
			
		}
		
		addKeyListener(new TAdapter());
		setFocusable(true);
		d = new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
		setBackground(Color.black);

		timer = new Timer(Commons.DELAY, new GameCycle());
		timer.start();
		
	}
	
	

	private void gameInit() {

		aliens = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				Alien alien = new Alien(Commons.ALIEN_INIT_X + 25 * j, Commons.ALIEN_INIT_Y + 35 * i);
				alien.setRowPosition(i+1);//goes from 1-4
				if((j==3 && i==1)||(j==2 && i==0)||(j==4 && i==2)||(j==0 && i==3)) {
					alien.setColumnPosition(100);//goes from 1-6	
				}
				
				aliens.add(alien);
			}
		}

		player = new Player();
		shot = new Shot();
	}

	private void drawAliens(Graphics g) {

		for (int j = 0; j < aliens.size(); j++) {//0-23 aliens
			
			if(aliens.get(j).getRowPosition()==1) {//0-5
				if(aliens.get(j).getColumnPosition()==100) {//checks for the column positions in the firstRow to find the 1 in 6 position that it does not have and paint an alien
					
					if (aliens.get(j).isVisible()) {
					g.drawImage(aliens.get(j).getImage(), aliens.get(j).getX(), aliens.get(j).getY(), this);//
					}
					if (aliens.get(j).isDying()) {

						aliens.get(j).die();
					}
				}
				else {
					if(aliens.get(j).isHit()) {
						g.setColor(Color.black);
						g.drawString("",0,300);
					}
					else {
						g.setColor(Color.green);
						if(firstRowAnswers.size()<4) {
							
							firstRowAnswers.add(aliens.get(j).setNumber(answers.get(firstRowPositions.get(j))));//the answers
							
							aliens.get(j).unSetBomb();
						}
						g.drawString(answers.get((firstRowPositions.get(j))),aliens.get(j).getX() + 7, aliens.get(j).getY() + 13);
					}
					
					
			}
			}
				
			
			if(aliens.get(j).getRowPosition()==2) {//6-13
				if(aliens.get(j).getColumnPosition()==100) {//checks for the column positions in the firstRow to find the 1 in 6 position that it does not have and paint an alien
					if (aliens.get(j).isVisible()) {
					g.drawImage(aliens.get(j).getImage(), aliens.get(j).getX(), aliens.get(j).getY(), this);//
					}
					if (aliens.get(j).isDying()) {

						aliens.get(j).die();
					}
				}
				else {
					if(aliens.get(j).isHit()) {
						g.setColor(Color.black);
						g.drawString("",0,300);
					}
					else {
						g.setColor(Color.green);
						if(secondRowAnswers.size()<4) {
							
							secondRowAnswers.add(aliens.get(j).setNumber(answers.get(secondRowPositions.get(j-5)+6)));//the answers
							
							aliens.get(j).unSetBomb();
						}
						g.drawString(answers.get((secondRowPositions.get(j-5))+6),aliens.get(j).getX() + 7, aliens.get(j).getY() + 13);
					}
					
					
				}
				
					
				}
			
            
			if(aliens.get(j).getRowPosition()==3) {//if the alien is in the first row we take from the firstRow arraylist 
				if(aliens.get(j).getColumnPosition()==100) {//checks for the column positions in the firstRow to find the 1 in 6 position that it does not have and paint an alien
					if (aliens.get(j).isVisible()) {
					g.drawImage(aliens.get(j).getImage(), aliens.get(j).getX(), aliens.get(j).getY(), this);//
					}
					if (aliens.get(j).isDying()) {

						aliens.get(j).die();
					}
				}
				else {
					if(aliens.get(j).isHit()) {
						g.setColor(Color.black);
						g.drawString("",0,300);
					}
					else {
						g.setColor(Color.green);
						if(thirdRowAnswers.size()<4) {
							
							thirdRowAnswers.add(aliens.get(j).setNumber(answers.get(thirdRowPositions.get(j-10)+12)));//the answers
							
							aliens.get(j).unSetBomb();
						}
						g.drawString(answers.get((thirdRowPositions.get(j-10))+12),aliens.get(j).getX() + 7, aliens.get(j).getY() + 13);
					}
					
					
					
					
				}
			}
            
			if(aliens.get(j).getRowPosition()==4) {//if the alien is in the first row we take from the firstRow arraylist 
				if(aliens.get(j).getColumnPosition()==100) {//checks for the column positions in the firstRow to find the 1 in 6 position that it does not have and paint an alien
					if (aliens.get(j).isVisible()) {
					g.drawImage(aliens.get(j).getImage(), aliens.get(j).getX(), aliens.get(j).getY(), this);//
					}
					if (aliens.get(j).isDying()) {

						aliens.get(j).die();
					}
				}
				else {
					if(aliens.get(j).isHit()) {
						g.setColor(Color.black);
						g.drawString("",0,300);
						
					
					}
					else {
						g.setColor(Color.green);
						if(fourthRowAnswers.size()<4) {
							
							fourthRowAnswers.add(aliens.get(j).setNumber(answers.get(fourthRowPositions.get(j-15)+18)));//the answers
							
							aliens.get(j).unSetBomb();
						}
						g.drawString(answers.get((fourthRowPositions.get(j-15))+18),aliens.get(j).getX() + 7, aliens.get(j).getY() + 13);
					}
					
					
					
				}
			}
			
			
			
		}
	}

	private void drawPlayer(Graphics g) {

		if (player.isVisible()) {

			g.drawImage(player.getImage(), player.getX(), player.getY(), this);
		}

		if (player.isDying()) {

			player.die();
			inGame = false;
		}
	}

	private void drawShot(Graphics g) {

		if (shot.isVisible()) {

			g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
		}
	}

	private void drawBombing(Graphics g) {

		for (Alien a : aliens) {
			if (a.getBomb() != null) {
				Bomb b = a.getBomb();

				if (!b.isDestroyed()) {

					g.drawImage(b.getImage(), b.getX(), b.getY(), this);
				}
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		doDrawing(g);

	}

	private void doDrawing(Graphics g) {

		
		if (inGame) {

			
			g.drawLine(0, Commons.GROUND, Commons.BOARD_WIDTH, Commons.GROUND);

			drawAliens(g);
			drawPlayer(g);
			drawShot(g);
			drawBombing(g);
		    drawQuestion(g);
			
		} else {
			model.setScore(count,currentQuestion,"chapter5");
			gameOver(g);
			
			//questionProblem=currentQuestion;
			if (timer.isRunning()) {
				timer.stop();
			}

			
		}
		
		Toolkit.getDefaultToolkit().sync();
	}

	private void gameOver(Graphics g) {

		g.setColor(Color.black);
		g.fillRect(0, 0, Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

		g.setColor(new Color(0, 32, 48));
		g.fillRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);
		g.setColor(Color.white);
		g.drawRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);

		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics fontMetrics = this.getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(message, (Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2, Commons.BOARD_HEIGHT / 2);
	}

	private void drawQuestion(Graphics g) {
		g.setColor(Color.green);
		g.fillOval(Commons.BOARD_HEIGHT/2-75, Commons.BOARD_WIDTH / 2+100, 150, 50);

		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics fontMetrics = this.getFontMetrics(small);

		g.setColor(Color.WHITE);
		g.setFont(small);
		System.out.println(fourthRowAnswers.size());
		if(fourthRowAnswers.size()==4) {
			
			g.drawString(fourthRowAnswers.get(fourthRandomNumber),(Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2, Commons.BOARD_WIDTH/2+130);
			currentQuestion=fourthRowAnswers.get(fourthRandomNumber);
		}
		if(fourthRowAnswers.size()==3) {
			
			g.drawString(fourthRowAnswers.get(fourthRandomNumber2),(Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2, Commons.BOARD_WIDTH/2+130);
			currentQuestion=fourthRowAnswers.get(fourthRandomNumber2);
		}
		if(fourthRowAnswers.size()==2) {
			
			g.drawString(fourthRowAnswers.get(fourthRandomNumber3),(Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2, Commons.BOARD_WIDTH/2+130);
			currentQuestion=fourthRowAnswers.get(fourthRandomNumber3);
		}
        if(fourthRowAnswers.size()==1) {
			
			g.drawString(fourthRowAnswers.get(0),(Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2, Commons.BOARD_WIDTH/2+130);
			currentQuestion=fourthRowAnswers.get(0);
		}
        
        if(fourthRowAnswers.size()==0) {
        //third row
        if(thirdRowAnswers.size()==4) {
			
			g.drawString(thirdRowAnswers.get(thirdRandomNumber),(Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2, Commons.BOARD_WIDTH/2+130);
			currentQuestion=thirdRowAnswers.get(thirdRandomNumber);
		}
		if(thirdRowAnswers.size()==3) {
			
			g.drawString(thirdRowAnswers.get(thirdRandomNumber2),(Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2, Commons.BOARD_WIDTH/2+130);
			currentQuestion=thirdRowAnswers.get(thirdRandomNumber2);
		}
		if(thirdRowAnswers.size()==2) {
			
			g.drawString(thirdRowAnswers.get(thirdRandomNumber3),(Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2, Commons.BOARD_WIDTH/2+130);
			currentQuestion=thirdRowAnswers.get(thirdRandomNumber3);
		}
        if(thirdRowAnswers.size()==1) {
			
			g.drawString(thirdRowAnswers.get(0),(Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2, Commons.BOARD_WIDTH/2+130);
			currentQuestion=thirdRowAnswers.get(0);
		}
        }
        if(thirdRowAnswers.size()==0) {
            //second row
            if(secondRowAnswers.size()==4) {
    			System.out.println(randomNumber);
    			g.drawString(secondRowAnswers.get(secondRandomNumber),(Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2, Commons.BOARD_WIDTH/2+130);
    			currentQuestion=secondRowAnswers.get(secondRandomNumber);
    		}
    		if(secondRowAnswers.size()==3) {
    			
    			g.drawString(secondRowAnswers.get(secondRandomNumber2),(Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2, Commons.BOARD_WIDTH/2+130);
    			currentQuestion=secondRowAnswers.get(secondRandomNumber2);
    		}
    		if(secondRowAnswers.size()==2) {
    			
    			g.drawString(secondRowAnswers.get(secondRandomNumber3),(Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2, Commons.BOARD_WIDTH/2+130);
    			currentQuestion=secondRowAnswers.get(secondRandomNumber3);
    		}
            if(secondRowAnswers.size()==1) {
    			
    			g.drawString(secondRowAnswers.get(0),(Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2, Commons.BOARD_WIDTH/2+130);
    			currentQuestion=secondRowAnswers.get(0);
    		}
            }
        if(secondRowAnswers.size()==0) {
            //second row
            if(firstRowAnswers.size()==4) {
    			System.out.println(randomNumber);
    			g.drawString(firstRowAnswers.get(randomNumber),(Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2, Commons.BOARD_WIDTH/2+130);
    			currentQuestion=firstRowAnswers.get(randomNumber);
    		}
    		if(firstRowAnswers.size()==3) {
    			
    			g.drawString(firstRowAnswers.get(randomNumber2),(Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2, Commons.BOARD_WIDTH/2+130);
    			currentQuestion=firstRowAnswers.get(randomNumber2);
    		}
    		if(firstRowAnswers.size()==2) {
    			
    			g.drawString(firstRowAnswers.get(randomNumber3),(Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2, Commons.BOARD_WIDTH/2+130);
    			currentQuestion=firstRowAnswers.get(randomNumber3);
    		}
            if(firstRowAnswers.size()==1) {
    			
    			g.drawString(firstRowAnswers.get(0),(Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2, Commons.BOARD_WIDTH/2+130);
    			currentQuestion=firstRowAnswers.get(0);
    		}
            }

		

	}
	private void update() {
		if(count>16) {
			inGame = false;
			timer.stop();
			message = "Game won!";
		}

		// player
		player.act();

		// shot
		if (shot.isVisible()) {

			int shotX = shot.getX();
			int shotY = shot.getY();

			for (Alien alien : aliens) {

				int alienX = alien.getX();
				int alienY = alien.getY();

				if (alien.isVisible() && shot.isVisible()) {
					if (shotX >= (alienX) && shotX <= (alienX + Commons.ALIEN_WIDTH) && shotY >= (alienY)
							&& shotY <= (alienY + Commons.ALIEN_HEIGHT)) {
						
						
						if (alien.getBomb() != null) {
							String alienImg = "../Images/explosion.jpg";
							ImageIcon ii = loadImage(alienImg);
							shot.setImage(ii.getImage());
							System.out.println("shot");
							alien.setDying(true);
							deaths++;
							shot.die();
						} else {//it is a number
							System.out.println(currentQuestion);
							if(alien.getQuestion().equals(currentQuestion)) {//is the aliens question the same as the current one
								count++;
								if(fourthRowAnswers.size()!=0) {
								if(fourthRowAnswers.size()==4) {
									System.out.println("success");
									alien.setDying(true);
									alien.setHit(true);
									alien.die();
									fourthRowAnswers.remove(fourthRandomNumber);
									shot.die();
								}
								if(fourthRowAnswers.size()==3) {
									System.out.println("success");
									alien.setDying(true);
									alien.setHit(true);
									alien.die();
									fourthRowAnswers.remove(fourthRandomNumber2);
									shot.die();
								}
								if(fourthRowAnswers.size()==2) {
									System.out.println("success");
									alien.setDying(true);
									alien.setHit(true);
									alien.die();
									fourthRowAnswers.remove(fourthRandomNumber3);
									shot.die();
								}
								
								if(fourthRowAnswers.size()==1) {
									System.out.println("success");
									alien.setDying(true);
									alien.setHit(true);
									alien.die();
									fourthRowAnswers.remove(0);
									shot.die();
								}
								}
								if(thirdRowAnswers.size()!=0) {
									if(thirdRowAnswers.size()==4) {
										System.out.println("success");
										alien.setDying(true);
										alien.setHit(true);
										alien.die();
										thirdRowAnswers.remove(thirdRandomNumber);
										shot.die();
									}
									if(thirdRowAnswers.size()==3) {
										System.out.println("success");
										alien.setDying(true);
										alien.setHit(true);
										alien.die();
										thirdRowAnswers.remove(thirdRandomNumber2);
										shot.die();
									}
									if(thirdRowAnswers.size()==2) {
										System.out.println("success");
										alien.setDying(true);
										alien.setHit(true);
										alien.die();
										thirdRowAnswers.remove(thirdRandomNumber3);
										shot.die();
									}
									
									if(thirdRowAnswers.size()==1) {
										System.out.println("success");
										alien.setDying(true);
										alien.setHit(true);
										alien.die();
										thirdRowAnswers.remove(0);
										shot.die();
									}
									}
								if(secondRowAnswers.size()!=0) {
									if(secondRowAnswers.size()==4) {
										System.out.println("success");
										alien.setDying(true);
										alien.setHit(true);
										alien.die();
										secondRowAnswers.remove(secondRandomNumber);
										shot.die();
									}
									if(secondRowAnswers.size()==3) {
										System.out.println("success");
										alien.setDying(true);
										alien.setHit(true);
										alien.die();
										secondRowAnswers.remove(secondRandomNumber2);
										shot.die();
									}
									if(secondRowAnswers.size()==2) {
										System.out.println("success");
										alien.setDying(true);
										alien.setHit(true);
										alien.die();
										secondRowAnswers.remove(secondRandomNumber3);
										shot.die();
									}
									
									if(secondRowAnswers.size()==1) {
										System.out.println("success");
										alien.setDying(true);
										alien.setHit(true);
										alien.die();
										secondRowAnswers.remove(0);
										shot.die();
									}
									}
								if(firstRowAnswers.size()!=0) {
									if(firstRowAnswers.size()==4) {
										System.out.println("success");
										alien.setDying(true);
										alien.setHit(true);
										alien.die();
										firstRowAnswers.remove(randomNumber);
										shot.die();
									}
									if(firstRowAnswers.size()==3) {
										System.out.println("success");
										alien.setDying(true);
										alien.setHit(true);
										alien.die();
										firstRowAnswers.remove(randomNumber2);
										shot.die();
									}
									if(firstRowAnswers.size()==2) {
										System.out.println("success");
										alien.setDying(true);
										alien.setHit(true);
										alien.die();
										firstRowAnswers.remove(randomNumber3);
										shot.die();
									}
									
									if(firstRowAnswers.size()==1) {
										System.out.println("success");
										alien.setDying(true);
										alien.setHit(true);
										alien.die();
										firstRowAnswers.remove(0);
										shot.die();
									}
									}
								
							}
							
							else {
								System.out.println("not succesfull");
							inGame=false;
							timer.stop();
							message="Game over";
							}
							
							
							
							
						}
						
					}
				}
			}

			int y = shot.getY();
			y -= 4;

			if (y < 0) {
				shot.die();
			} else {
				shot.setY(y);
			}
		}

		// aliens

		for (Alien alien : aliens) {

			int x = alien.getX();

			if (x >= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT && direction != -speed) {// går over treshold til højre på
																						// skærmen

				direction = -speed;

				Iterator<Alien> i1 = aliens.iterator();

				while (i1.hasNext()) {// så længe der er en alien mere tilbage i arraylisten

					Alien a2 = i1.next();
					a2.setY(a2.getY() + Commons.GO_DOWN);// får man også den til at flytte sig på y aksen
				}
			}

			if (x <= Commons.BORDER_LEFT && direction != speed) {

				direction = speed;

				Iterator<Alien> i2 = aliens.iterator();

				while (i2.hasNext()) {

					Alien a = i2.next();
					a.setY(a.getY() + Commons.GO_DOWN);
				}
			}
		}

		Iterator<Alien> it = aliens.iterator();

		while (it.hasNext()) {

			Alien alien = it.next();

			if (alien.isVisible()) {

				int y = alien.getY();

				if (y > Commons.GROUND - Commons.ALIEN_HEIGHT) {
					inGame = false;
					message = "Invasion!";
				}

				alien.act(direction);
			}
		}

		// bombs
		Random generator = new Random();

		for (Alien alien : aliens) {
			if (alien.getBomb() != null) {
				int shot = generator.nextInt(15);

				Bomb bomb = alien.getBomb();

				if (shot == Commons.CHANCE && alien.isVisible() && bomb.isDestroyed()) {

					bomb.setDestroyed(false);
					bomb.setX(alien.getX());
					bomb.setY(alien.getY());
				}

				int bombX = bomb.getX();
				int bombY = bomb.getY();
				int playerX = player.getX();
				int playerY = player.getY();

				if (player.isVisible() && !bomb.isDestroyed()) {

					if (bombX >= (playerX) && bombX <= (playerX + Commons.PLAYER_WIDTH) && bombY >= (playerY)
							&& bombY <= (playerY + Commons.PLAYER_HEIGHT)) {
						ImageIcon ii = new ImageIcon(explImg);
						player.setImage(ii.getImage());
						player.setDying(true);
						bomb.setDestroyed(true);
					}
				}

				if (!bomb.isDestroyed()) {

					bomb.setY(bomb.getY() + 1);

					if (bomb.getY() >= Commons.GROUND - Commons.BOMB_HEIGHT) {

						bomb.setDestroyed(true);
					}
				}
			}
		}
	}

	private void doGameCycle() {
		update();
		repaint();
	}

	private class GameCycle implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			doGameCycle();
		}
	}

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {

			player.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {

			player.keyPressed(e);

			int x = player.getX();
			int y = player.getY();

			int key = e.getKeyCode();

			if (key == KeyEvent.VK_SPACE) {

				if (inGame) {

					if (!shot.isVisible()) {

						shot = new Shot(x, y);
					}
				}
			}
		}
	}

	protected ImageIcon loadImage(String path) {

		ImageIcon ii = createImageIcon(path);

		return ii;

	}

	private ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}